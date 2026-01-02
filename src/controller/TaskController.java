package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import model.Status;
import model.Task;

public class TaskController {
    private final Path PATH = Path.of("tasks.json"); 
    private List<Task> tasks;

    public TaskController() {
        this.tasks = loadTasks();
    }

    public List<Task> loadTasks() {
        List<Task> storedTask = new ArrayList<>();
        
        if(!Files.exists(PATH)) {
            return new ArrayList<>();
        }
        try {
            String tasks = Files.readString(PATH);
            String[] tasksString = tasks.replace("[", "")
                                        .replace("]", "")
                                        .split("},");
            for(String task : tasksString) {
                if(!task.endsWith("}")) {
                    task = task + "}";
                    storedTask.add(Task.toTask(task));
                } else {
                    storedTask.add(Task.toTask(task));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storedTask;
    }

    public void saveTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < tasks.size(); i++){
            sb.append(tasks.get(i).toJson());
            if (i < tasks.size() - 1){
                sb.append(",\n");
            }
        }
        sb.append("\n]");

        String jsonContent = sb.toString();
        try {
            Files.writeString(PATH, jsonContent);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void listTasks(String status) {
        for(Task t : tasks) {
            String stat = t.getStatus().getValue().strip();
            if(status.equals("All") || status == stat) {
                System.out.println(t.toString());
            }
        }
    }

    public void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("Task added succesfully!");
    }

    public void removeTask(String id) {
        Task t = findTask(id);
        tasks.remove(t);
        System.out.println("Task removed succesfully!");
    }

    public void updateTask(String id, String newDescription) {
        Task t = findTask(id);
        t.setDescription(newDescription);
        System.out.println("Task: " + id + " updated succesfully!");
    }

    public void markAs(String id, String status) {
        Task t = findTask(id);
        if(status == Status.DONE.getValue()) {
            t.setStatus(Status.DONE);
            System.out.println("Status updated");
        } else if(status == Status.IN_PROGRESS.getValue()) {
            t.setStatus(Status.IN_PROGRESS);
            System.out.println("Status updated");
        }
        else {
            System.out.println("ERROR: Not valid status!");
        }
    }

    public Task findTask(String id) {
        return tasks.stream().filter((t) -> Integer.parseInt(id) == t.getId()).findFirst()
                                                            .orElseThrow(() -> new IllegalArgumentException("Task with id: " + id + " not found"));
    }
}
