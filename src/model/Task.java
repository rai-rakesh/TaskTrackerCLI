package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Task {

    public static int lastId = 0;
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:MM:SS");


    @Override
    public String toString() {
        return '{'+"\"id\":\"" + id +
                "\" , \"description\":\"" + description +
                "\", \"status\":\"" + status +
                "\", \"createdAt\":\"" + createdAt +
                "\", \"updatedAt\":\"" + updatedAt +
                "\"}";
    }

    Task(String desc){
        this.id = lastId;
        lastId++;
        this.description = desc;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    Task(int id,String description,Status status,LocalDateTime createdAt,LocalDateTime updatedAt)
    {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    public int getTaskId()
    {
        return this.id;
    }

    public Status getStatus()
    {
        return this.status;
    }

    public String getTaskdesc()
    {
        return this.description;
    }

    public  void updateDesc(String description)
    {
        this.description = description;
    }

    public void updateStatus(Status status)
    {
        this.status = status;
    }

    public LocalDateTime getCreatedAt()
    {
        return this.createdAt;
    }


    public LocalDateTime getUpdatedAt()
    {
        return this.updatedAt;
    }

    public static Task parseTask(String jsonString) {

        String[] parts = jsonString.replaceAll("[]{}\"]", "").split(", ");

        int id = 0;
        String description = "";
        Status status = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        // DateTime format used in the JSON string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnn");

        for (String part : parts) {
            String[] keyValue = part.split(":",2);
            String key = keyValue[0];
            String value = keyValue[1];

            switch (key) {
                case "id":
                    id = Integer.parseInt(value.trim());
                    break;
                case "description":
                    description = value.trim();
                    break;
                case "status":
                    status = Status.valueOf(value.trim());
                    break;
                case "createdAt":
                    createdAt = LocalDateTime.parse(value, formatter);
                    break;
                case "updatedAt":
                    updatedAt = LocalDateTime.parse(value, formatter);
                    break;
            }
        }

        return new Task(id, description, status, createdAt, updatedAt);
    }



}
