package com.reclamala.todolist.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String idToken = payload.get("idToken");
        System.out.println("ID Token recebido: " + idToken);

        if (idToken == null || idToken.isEmpty()) {
            System.out.println("ID Token é nulo ou vazio"); 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID Token não pode ser nulo ou vazio");
        }
        
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            System.out.println("Usuário autenticado com sucesso: " + uid);
            return ResponseEntity.ok("Usuário autenticado com sucesso: " + uid);
        } catch (FirebaseAuthException e) {
            System.out.println("Erro na verificação do token: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido: " + e.getMessage());
        }
    }
}
