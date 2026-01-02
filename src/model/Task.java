package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static Integer lastId = 0;
    private Integer id;
    private String description;
    private Status status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Task(String description) {
        this.id = ++lastId;
        this.description = description;
        this.status = Status.TODO;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updated_at = LocalDateTime.now();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.updated_at = LocalDateTime.now();
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public static Task toTask(String json) {
        
        json = json.replace("{", "").replace("}", "").replace("\"", "");
        String[] newJson = json.split(",");

        String id = newJson[0].split(":")[1].strip();
        String description = newJson[1].split(":")[1].strip();
        String statusStr = newJson[2].split(":")[1].strip();
        String created_at = newJson[3].split("[a-z]:")[1].strip();
        String updated_at = newJson[4].split("[a-z]:")[1].strip();

        Status status = Status.valueOf(statusStr.toUpperCase().replace(" ", "_"));

        Task task = new Task(description);
        task.id = Integer.parseInt(id);
        task.status = status;
        task.created_at = LocalDateTime.parse(created_at, formatter);
        task.updated_at = LocalDateTime.parse(updated_at, formatter);

        if(Integer.parseInt(id)>lastId) {
            lastId = Integer.parseInt(id);
        }

        return task;
    }

    public String toJson() {
        return "{\"id\":\"" + id + "\", \"description\":\"" + description.strip() + "\", \"status\":\"" + status.toString() +
                "\", \"createdAt\":\"" + created_at.format(formatter) + "\", \"updatedAt\":\"" + updated_at.format(formatter) + "\"}";
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", description=" + description + ", status=" + status + ", created_at=" + created_at
                + ", updated_at=" + updated_at + "]";
    }

    
}
