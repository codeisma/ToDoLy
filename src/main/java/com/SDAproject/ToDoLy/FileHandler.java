package com.SDAproject.ToDoLy;

import java.io.*;


public class FileHandler
{
    public Tasker readAsData()
    {
        Tasker tasker;

        try
        {
            File file = new File("dataFile.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream reader = new ObjectInputStream(fileInputStream);

            tasker = (Tasker) reader.readObject( );
            reader.close();
            fileInputStream.close();

        }
        catch (EOFException e)
        {
            System.out.println("the file is empty");
            tasker = new Tasker();
        }
        catch (FileNotFoundException e)
        {
            tasker = new Tasker();
            writeAsData(tasker);
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            tasker = new Tasker();
        }

        return tasker;
    }



    public void writeAsData(Tasker tasker)
    {
        File file = new File("dataFile.txt");

        try
        {
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream writer = new ObjectOutputStream(fileStream);

            writer.writeObject(tasker);
            writer.close();
            fileStream.close();
        }
          catch (IOException e)

        {
            System.out.println(e.toString());
        }
    }
}
