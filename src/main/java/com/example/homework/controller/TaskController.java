package com.example.homework.controller;

import com.example.homework.model.Task;
import com.example.homework.repository.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/dodaj")
    public String addTask(){
        return "add";
    }

    @PostMapping("/dodaj")
    public String addTask(Task task){
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/lista")
    public String list(Model model, @RequestParam boolean done) {
        List<Task> tasks = taskRepository.findByDoneEquals(done);
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", new Task());
        model.addAttribute("taskDone", done);
        return "list";
    }

    @GetMapping("/wykonane")
    public String markAsDone(@RequestParam Long id){
        Task task = taskRepository.findById(id).orElse(null);
        task.setDone(true);
        taskRepository.save(task);
        return "redirect:/lista?done=true";
    }
}
