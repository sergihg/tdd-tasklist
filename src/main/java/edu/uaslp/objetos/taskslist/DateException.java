package edu.uaslp.objetos.taskslist;

public class DateException extends TaskListException{
    @Override
    public String getMessage() {
        return "Due date is set in the past";
    }
}
