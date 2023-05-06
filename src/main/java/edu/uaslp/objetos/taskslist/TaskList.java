package edu.uaslp.objetos.taskslist;

import java.util.*;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int currentIndex=0;

    public int getSize() {
        return taskList.size();
    }
    public void add(Task task){
        taskList.add(task);
    }
    public void remove(Task task){
        taskList.remove(task);
    }
    public Task find(String title){
        Iterator<Task> iterator = taskList.iterator();
        Task currentTask;
        do{
            currentTask = iterator.next();
            if(currentTask.getTitle().equals(title))
                return currentTask;
        }while (iterator.hasNext());
        throw new TaskNotFoundException(title);
    }
    public void markAsDone(String title){
        Task task;
        int index;
        task = find(title);
        index = taskList.indexOf(task);
        task.setDone(true);
        taskList.set(index,task);
    }
    public void markAsNotDone(String title){
        Task task;
        int index;
        task = find(title);
        index = taskList.indexOf(task);
        task.setDone(false);
        taskList.set(index,task);
    }
    public Task getNextTask(){
        currentIndex++;
        return taskList.get(currentIndex);
    }
    public List<Task> getNextTasks(){
        List<Task> list = new LinkedList<>();
        for(Task task : taskList){
            if(!(task.isDone()))
                list.add(task);
        }
        list.sort(Comparator.comparing(Task::getDueDate));
        return list;

    }
}
