package com.reclamala.todolist.controller;

import com.reclamala.todolist.models.Task;
import com.reclamala.todolist.service.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
