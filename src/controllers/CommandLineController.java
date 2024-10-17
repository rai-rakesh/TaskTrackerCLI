package controllers;

import model.Status;
import model.TaskManager;

import java.util.Scanner;

public class CommandLineController {

    public void init() {
        TaskManager taskManager = new TaskManager();
        taskManager.readFile();
        boolean flag = true;
        while (flag) {
            System.out.printf("Reading File Done.\n");
            System.out.printf("Please enter the commands \n");
            Scanner sc = new Scanner(System.in);
            String operation = sc.nextLine();
            String[] args = operation.split(" ");

            switch (args[0])
            {
                case "add":
                    if(args.length!=2)
                    {
                        System.out.printf("Invalid command");
                        System.out.print(taskManager.listCommands());
                    }
                    else taskManager.addTask(args[1]);
                    break;

                case "delete":
                    if(args.length!=2)
                    {
                        System.out.printf("Invalid command");
                        System.out.print(taskManager.listCommands());
                    }
                    else taskManager.deleteById(Integer.parseInt(args[1].trim()));
                    break;
                case "update":
                    if(args.length!=3)
                    {
                        System.out.printf("Invalid command");
                        System.out.print(taskManager.listCommands());
                    }
                    else taskManager.updateById(args[2],Integer.parseInt(args[1].trim()));
                    break;
                case "mark-in-progress":
                    if(args.length!=2)
                    {
                        System.out.printf("Invalid command");
                        System.out.print(taskManager.listCommands());
                    }
                    else taskManager.markProgress(Integer.parseInt(args[1].trim()),Status.IN_PROGRESS);
                    break;
                case "mark-done":
                    if(args.length!=2)
                    {
                        System.out.print("Invalid command");
                        System.out.print(taskManager.listCommands());
                    }
                    else taskManager.markProgress(Integer.parseInt(args[1].trim()),Status.DONE);
                    break;
                case "list":
                    if(args.length == 1)
                    {
                        System.out.printf(taskManager.getAllTask());
                    }
                    else if(args.length == 2)
                    {
                        Status commandStatus = Status.valueOf(args[1].toUpperCase().trim());
                        System.out.printf(taskManager.listTaskByStatus(commandStatus));
                    }
                    else
                    {
                        System.out.print("Invalid command");
                        System.out.print(taskManager.listCommands());
                    }
                default:
                    System.out.print(taskManager.listCommands());

            }
        }
    }
}
