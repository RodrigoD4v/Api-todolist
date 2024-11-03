package com.reclamala.todolist.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.reclamala.todolist.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirestoreService {

    @Autowired
    private Firestore firestore;

    public String saveTask(Task task) throws Exception {
        CollectionReference tasks = firestore.collection("tasks");
        ApiFuture<WriteResult> writeResult = tasks.document(task.getId()).set(task);
        return writeResult.get().getUpdateTime().toString();
    }
}
