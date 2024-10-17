package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TaskManager {

    private StringBuilder jsonContent;
    private List<Task> tasks;
    private String path = "src/resources/file.json";


    public TaskManager(){

        tasks = new ArrayList<>();

    }

    public void addTask(String desc)
    {
        Task task = new Task(desc);
        tasks.add(task);
        saveTask();
        System.out.printf("Task %s with id %s added successfully \n",task.getTaskId(),task.getTaskdesc());


    }

    public void saveTask()
    {
        //iterate and convert task list to string
        //append in a singleString
        // write in file
        StringBuilder sb = new StringBuilder("[");

        for (int i=0;i<tasks.size();i++)
        {
            sb.append(tasks.get(i).toString());
            if(i != tasks.size()-1)
            {
                sb.append(", \n");
            }
            else
            {
                sb.append("]");
            }
        }


        updateIntoFile(sb);


      //  System.out.println(sb);

    }

    public void updateIntoFile(StringBuilder sb)
    {

        try {
            File fileObj = new File(path);
            if (!fileObj.exists()) {
                fileObj.createNewFile();
            }
            FileWriter fileWriter =  new FileWriter(path);
            fileWriter.write(sb.toString());
            fileWriter.close();

        }

        catch (IOException e)
        {
            System.out.println("Error while writing on file \n");
        }


    }

    public void readFile()
    {
        try {
        File myObj = new File(path);
        Scanner sc = new Scanner(myObj);
        while (sc.hasNextLine())
        {
            Task task = Task.parseTask(sc.nextLine());
            tasks.add(task);
            Task.lastId = task.getTaskId()+1;
        }
        sc.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error while reading the file \n");
        }
    }

    public void updateById(String desc, int id)
    {
        Task task = getTaskById(id);
        if(task!=null)
        {
            task.updateDesc(desc);
            saveTask();
            System.out.printf("Task with id : %s successfully updated \n",id);
        }
        else {
            System.out.printf("Cannot find task with id : %s \n",id);
        }
    }

    public void markProgress(int id,Status status)
    {
        Task task = getTaskById(id);
        if(task!=null)
        {
            task.updateStatus(status);
            saveTask();
            System.out.printf("Task with id : %s marked %s \n",id,status);
        }
        else {
            System.out.printf("Cannot find task with id : %s \n",id);
        }
    }

    public String listTaskByStatus(Status status)
    {
        return printTasks(tasks.stream().filter(t -> t.getStatus() == status).collect(Collectors.toList()));
    }

    public String getAllTask()
    {
        return printTasks(tasks);
    }

    public String printTasks(List<Task> tasks)
    {
        StringBuilder sb = new StringBuilder("");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        for(Task t : tasks)
        {
            sb.append("[ id: ").append(t.getTaskId()).append(", desc: ").append(t.getTaskdesc()).append(", status: ").append(t.getStatus()).append(", created on: ").append(dateTimeFormatter.format(t.getCreatedAt())).append(", updated on: ").append(dateTimeFormatter.format(t.getUpdatedAt())).append(" ]\n");
        }

        return sb.toString();
    }

    public String listCommands()
    {
        StringBuilder sb = new StringBuilder("");
        sb.append("{ \n")
                .append("   ADD -> add <<Task Description>> \n")
                .append("   UPDATE -> update <<Task id>> <<Updated Description>> \n")
                .append("   DELETE -> delete <<Task id>> \n")
                .append("   MARKING INPROGRESS -> mark-in-progress <<Task id>> \n")
                .append("   MARKING DONE -> mark-done <<Task id>> \n")
                .append("   LIST TASK-> list \n")
                .append("} \n");
        return sb.toString();
    }

    public void deleteById(int id)
    {
        Task tasktoBeDeleted = null;
        for(Task t : tasks)
        {
            if(t.getTaskId() == id)
            {
                tasktoBeDeleted = t;

            }
        }

        if(tasktoBeDeleted!=null)
        {
            tasks.remove(tasktoBeDeleted);
            saveTask();
            System.out.printf("Task with id : %s succesfully deleted \n",id);
        }
       else {
            System.out.printf("Cannot find task with id : %s \n",id);
        }


    }

    public Task getTaskById(int id)
    {
        for(Task t : tasks)
        {
            if(t.getTaskId() == id)
            {
                return t;
            }
        }

        return null;
    }

}
