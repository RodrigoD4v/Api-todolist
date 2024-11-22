package com.reclamala.todolist.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.reclamala.todolist.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

    @Autowired
    private Firestore firestore;

    public String saveTask(String userUid, Task task) throws Exception {
        CollectionReference userTasks = firestore.collection("users")
                                                  .document(userUid)
                                                  .collection("user_tasks");
        
        ApiFuture<WriteResult> writeResult = userTasks.document(task.getId()).set(task);
        
        return writeResult.get().getUpdateTime().toString();
    }

    public List<Task> getTasks(String userUid) throws ExecutionException, InterruptedException {
        CollectionReference userTasks = firestore.collection("users")
                                                  .document(userUid)
                                                  .collection("user_tasks");
        
        ApiFuture<QuerySnapshot> querySnapshot = userTasks.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        
        List<Task> tasks = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            tasks.add(document.toObject(Task.class));
        }
        
        return tasks;
    }
    
    public String updateTask(String userUid, String taskId, Task updatedTask) throws Exception {
        CollectionReference userTasks = firestore.collection("users")
                                                  .document(userUid)
                                                  .collection("user_tasks");
    
        // Verificar se a tarefa existe
        DocumentReference taskRef = userTasks.document(taskId);
        ApiFuture<DocumentSnapshot> taskSnapshot = taskRef.get();
        if (!taskSnapshot.get().exists()) {
            throw new Exception("Tarefa não encontrada para o ID: " + taskId);
        }
    
        // Atualizar tarefa
        ApiFuture<WriteResult> writeResult = taskRef.set(updatedTask);
        return writeResult.get().getUpdateTime().toString();
    }

    public String deleteTask(String userUid, String taskId) throws Exception {
        CollectionReference userTasks = firestore.collection("users")
                                                  .document(userUid)
                                                  .collection("user_tasks");
        
        ApiFuture<WriteResult> writeResult = userTasks.document(taskId).delete();
        return writeResult.get().getUpdateTime().toString();
    }
}
