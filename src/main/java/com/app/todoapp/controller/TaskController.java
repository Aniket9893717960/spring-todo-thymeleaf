package com.app.todoapp.controller;

import com.app.todoapp.models.Task;
import com.app.todoapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        System.out.println("Number of tasks fetched: " + tasks.size()); // Debug line
        tasks.forEach(task -> System.out.println(task.getTitle())); // Debug line
        model.addAttribute("tasks", tasks);
        return "tasks";//Refers to src/main/resources/templates/tesks.html
    }

//    @GetMapping("/debug")
//    @ResponseBody // This will return raw JSON
//    public List<Task> debugTasks() {
//        List<Task> tasks = taskService.getAllTasks();
//        System.out.println("DEBUG - Tasks found: " + tasks.size());
//        tasks.forEach(task -> System.out.println("Task: " + task.getTitle() + " (ID: " + task.getId() + ")"));
//        return tasks;
//    }

    @PostMapping
    public String createTask(@RequestParam("title") String title){ // Explicit @RequestParam
          taskService.createTask(title);
          return "redirect:/tasks"; //Redirect back to the tasks page
    }

    @GetMapping("/{id}/delete")
    public String deleteTasks(@PathVariable Long id) {
        taskService.deleteTasks(id);
        return "redirect:/tasks";//Refers to src/main/resources/templates/tesks.html
    }

    @GetMapping("/{id}/toggle")
    public String toggleTasks(@PathVariable Long id) {
        try {
            taskService.toggleTasks(id);
            return "redirect:/tasks";
        } catch (IllegalArgumentException e) {
            // Log the error and redirect anyway
            System.err.println("Error toggling task: " + e.getMessage());
            return "redirect:/tasks";
        }
    }
}
