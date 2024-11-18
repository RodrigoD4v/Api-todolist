package com.reclamala.todolist.controller;

import com.reclamala.todolist.models.Task;
import com.reclamala.todolist.service.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private FirestoreService firestoreService;

    @PostMapping("/{userUid}")
    public ResponseEntity<String> createTask(@PathVariable String userUid, @RequestBody Task task) {
        try {
            // Printando o conteúdo da tarefa recebida para visualizar no terminal
            System.out.println("Dados recebidos para a tarefa: " + task);
            System.out.println("ID do usuário: " + userUid);

            // Salvar tarefa no Firestore para o usuário específico
            String result = firestoreService.saveTask(userUid, task);
            return ResponseEntity.ok("Tarefa criada com sucesso: " + result);
        } catch (Exception e) {
            System.out.println("Erro na criação da tarefa: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao criar tarefa");
        }
    }
    
    @GetMapping("/{userUid}")
    public ResponseEntity<List<Task>> getTasks(@PathVariable String userUid) {
        try {
            // Buscar tarefas do usuário no Firestore
            List<Task> tasks = firestoreService.getTasks(userUid);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            System.out.println("Erro ao buscar tarefas: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{userUid}/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable String userUid, @PathVariable String taskId) {
        try {
            // Excluir a tarefa do Firestore
            String result = firestoreService.deleteTask(userUid, taskId);
            return ResponseEntity.ok("Tarefa excluída com sucesso: " + result);
        } catch (Exception e) {
            System.out.println("Erro ao excluir a tarefa: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir tarefa");
        }
    }
}

