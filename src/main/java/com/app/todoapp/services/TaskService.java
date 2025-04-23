package com.app.todoapp.services;
import com.app.todoapp.models.Task;
import com.app.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository tr;

    public TaskService(TaskRepository tr) {
        this.tr = tr;
    }

    public List<Task> getAllTasks() {
        return tr.findAll();
    }

    public void createTask(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty!");
        }
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        tr.save(task);   // This could fail if DB isn't set up
    }

    public void deleteTasks(Long id) {
        tr.deleteById(id);
    }

    public void toggleTasks(Long id) {
        System.out.println("Attempting to toggle task with ID: " + id); // Debug
        Task task = tr.findById(id)
                .orElseThrow(() -> {
                    System.err.println("Task not found with ID: " + id); // Debug
                    return new IllegalArgumentException("Invalid task id: " + id);
                });
        boolean newStatus = !task.isCompleted();
        System.out.println("Toggling task '" + task.getTitle() + "' from " +
                task.isCompleted() + " to " + newStatus); // Debug
        task.setCompleted(newStatus);
        tr.save(task);
    }
}
