package com.reclamala.todolist.controller;

import com.reclamala.todolist.models.Task;
import com.reclamala.todolist.service.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private FirestoreService firestoreService;

    @PostMapping
    public String createTask(@RequestBody Task task) {
        try {
            return firestoreService.saveTask(task);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating task: " + e.getMessage();
        }
    }
}
