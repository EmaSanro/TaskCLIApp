import controller.TaskController;
import model.Status;

public class TaskCLI {
    public static void main(String[] args) throws Exception {
        TaskController task = new TaskController();

        if(args.length < 1) {
            System.out.println("Please Use: TaskCLI <Command> [arguments]");
            return;
        }

        String command = args[0];

        switch (command) {
            case "add": {
                if(args.length < 2) {
                    System.out.println("Please provide a description of task");
                    return;
                }
                task.addTask(args[1]);
            }break;
            case "update": {
                if(args.length < 3) {
                    System.out.println("Please provide id task and new description for it");
                    return;
                }
                task.updateTask(args[2], args[1]);
            }break;
            case "delete": {
                if(args.length < 2) {
                    System.out.println("Please provide an id task");
                    return;
                }
                task.removeTask(args[1]);
            }break;
            case "mark-done": {
                if(args.length < 2) {
                    System.out.println("Please provide an id task");
                    return;
                }
                task.markAs(args[1], "done");
            }break;
            case "mark-in-progress": {
                if(args.length < 2) {
                    System.out.println("Please provide an id task");
                    return;
                }
                task.markAs(args[1], "in_progress");
            }break;
            case "list" : {
                if(args.length < 2) {
                    task.listTasks("All");
                } else {
                    Status stat;
                    try {
                        stat = Status.valueOf(args[1].toUpperCase().strip().replace("-", "_"));
                    } catch (Exception e) {
                        throw new IllegalArgumentException("ERROR: Status invalid");
                    }
                    task.listTasks(stat.toString());
                }
            }break;
            default : {
                throw new IllegalArgumentException("Invalid Command!");
            }
        }
        task.saveTasks();
    }
}
