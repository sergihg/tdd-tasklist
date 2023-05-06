package edu.uaslp.objetos.taskslist;

public class TaskNotFoundException extends RuntimeException{
    private String message;
    public TaskNotFoundException(){
    }
    public TaskNotFoundException(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return "Task with title '"+ message + "' not found";
    }
}
