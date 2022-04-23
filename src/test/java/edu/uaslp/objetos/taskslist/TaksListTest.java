package edu.uaslp.objetos.taskslist;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TaksListTest {

    @Test
    public void givenANewTasksList_whenCreated_thenListIsEmpty() {
        // Given: (Inicialización)
        TaskList taskList = new TaskList();

        // When: (Ejecución)
        int itemsCount = taskList.getSize();

        // Then: (Validación)
        assertThat(itemsCount).isZero();
    }

    @Test
    public void givenANewTasksList_whenAdd_thenListContainsATask() {
        // Given: (Inicialización)
        TaskList taskList = new TaskList();
        LocalDateTime dateInFuture = LocalDateTime.now().plusWeeks(20);
        Task task = new Task("Lavar los trastes", "Lavar los trastes sin tirar mucha agua", dateInFuture, false);

        // When: (Ejecución)
        taskList.add(task);

        // Then: (Validación)
        int itemsCount = taskList.getSize();
        assertThat(itemsCount).isEqualTo(1);
    }

    @Test
    public void givenANewTasksListWithTasks_whenRemove_thenElementIsRemoved() {
        // Given: (Inicialización)
        TaskList taskList = new TaskList();

        Task task1 = new Task("Lavar los trastes", "Lavar los trastes sin tirar mucha agua", LocalDateTime.now().plusWeeks(20), false);
        Task task2 = new Task("Hacer la tarea", "Hacer la tarea de POO", LocalDateTime.now().plusDays(1), false);
        Task task3 = new Task("Recoger documentos", "", LocalDateTime.now().plusMonths(3), false);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        // When: (Ejecución)
        taskList.remove(task2);

        // Then: (Validación)
        int itemsCount = taskList.getSize();
        assertThat(itemsCount).isEqualTo(2);
    }

    @Test
    public void findTaskTest() {
        // Given: (Inicialización)
        TaskList taskList = new TaskList();

        Task task1 = new Task("Lavar los trastes", "Lavar los trastes sin tirar mucha agua", LocalDateTime.now().plusWeeks(20), false);
        LocalDateTime deadLineForTask = LocalDateTime.now().plusDays(1);
        Task task2 = new Task("Hacer la tarea", "Hacer la tarea de POO", deadLineForTask, false);
        Task task3 = new Task("Recoger documentos", "", LocalDateTime.now().plusMonths(3), false);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        // When: (Ejecución)
        Task taskFound = taskList.find("Hacer la tarea");

        // Then: (Validación)
        int itemsCount = taskList.getSize();
        assertThat(itemsCount).isEqualTo(3);
        assertThat(taskFound.getTitle()).isEqualTo("Hacer la tarea");
        assertThat(taskFound.getDescription()).isEqualTo("Hacer la tarea de POO");
        assertThat(taskFound.getDueDate()).isEqualTo(deadLineForTask);
        assertThat(taskFound.isDone()).isFalse();
    }

    @Test
    public void findTaskTestForNotExistent() {
        // Given: (Inicialización)
        TaskList taskList = new TaskList();

        Task task1 = new Task("Lavar los trastes", "Lavar los trastes sin tirar mucha agua", LocalDateTime.now().plusWeeks(20), false);
        LocalDateTime deadLineForTask = LocalDateTime.now().plusDays(1);
        Task task2 = new Task("Hacer la tarea", "Hacer la tarea de POO", deadLineForTask, false);
        Task task3 = new Task("Recoger documentos", "", LocalDateTime.now().plusMonths(3), false);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        // When: (Ejecución)
        // Then: (Validación)
        assertThatThrownBy(() ->taskList.find("Hacer ejercicio"))
                .isInstanceOf(RuntimeException.class)
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessage("Task with title 'Hacer ejercicio' not found");

    }

    @Test
    public void markTaskAsDone() {
        // Given: (Inicialización)
        TaskList taskList = new TaskList();

        Task task1 = new Task("Lavar los trastes", "Lavar los trastes sin tirar mucha agua", LocalDateTime.now().plusWeeks(20), false);
        LocalDateTime deadLineForTask = LocalDateTime.now().plusDays(1);
        Task task2 = new Task("Hacer la tarea", "Hacer la tarea de POO", deadLineForTask, false);
        Task task3 = new Task("Recoger documentos", "", LocalDateTime.now().plusMonths(3), false);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        // When: (Ejecución)
        taskList.markAsDone("Hacer la tarea");

        // Then: (Validación)
        Task taskFound = taskList.find("Hacer la tarea");
        assertThat(taskFound.isDone()).isTrue();
    }

    @Test
    public void markTaskAsUndone() {
        // Given: (Inicialización)
        TaskList taskList = new TaskList();

        Task task1 = new Task("Lavar los trastes", "Lavar los trastes sin tirar mucha agua", LocalDateTime.now().plusWeeks(20), false);
        LocalDateTime deadLineForTask = LocalDateTime.now().plusDays(1);
        Task task2 = new Task("Hacer la tarea", "Hacer la tarea de POO", deadLineForTask, true);
        Task task3 = new Task("Recoger documentos", "", LocalDateTime.now().plusMonths(3), false);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        // When: (Ejecución)
        taskList.markAsNotDone("Hacer la tarea");

        // Then: (Validación)
        Task taskFound = taskList.find("Hacer la tarea");
        assertThat(taskFound.isDone()).isFalse();
    }

    @Test
    public void testGetNextTask() {
        // Given: (Inicialización)
        TaskList taskList = new TaskList();

        Task task1 = new Task("Lavar los trastes", "Lavar los trastes sin tirar mucha agua", LocalDateTime.now().plusWeeks(20), false);
        LocalDateTime deadLineForTask = LocalDateTime.now().plusDays(1);
        Task task2 = new Task("Hacer la tarea", "Hacer la tarea de POO", deadLineForTask, false);
        Task task3 = new Task("Recoger documentos", "", LocalDateTime.now().plusMonths(3), false);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        // When: (Ejecución)
        Task taskFound = taskList.getNextTask();

        // Then: (Validación)
        assertThat(taskFound.getTitle()).isEqualTo("Hacer la tarea");
        assertThat(taskFound.getDescription()).isEqualTo("Hacer la tarea de POO");
        assertThat(taskFound.getDueDate()).isEqualTo(deadLineForTask);
        assertThat(taskFound.isDone()).isFalse();
    }

    @Test
    public void testGetListOfUndoneTasks() {
        // Given: (Inicialización)
        TaskList taskList = new TaskList();

        Task task1 = new Task("Lavar los trastes", "Lavar los trastes sin tirar mucha agua", LocalDateTime.now().plusWeeks(20), false);
        LocalDateTime deadLineForTask = LocalDateTime.now().plusDays(1);
        Task task2 = new Task("Hacer la tarea", "Hacer la tarea de POO", deadLineForTask, false);
        Task task3 = new Task("Recoger documentos", "", LocalDateTime.now().plusMonths(3), false);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        taskList.markAsDone("Recoger documentos");

        // When: (Ejecución)
        List<Task> tasksFound = taskList.getNextTasks();

        // Then: (Validación)
        assertThat(tasksFound).hasSize(2);
        assertThat(tasksFound.get(0).getTitle()).isEqualTo("Hacer la tarea");
        assertThat(tasksFound.get(1).getTitle()).isEqualTo("Lavar los trastes");
    }

}
