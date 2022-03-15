package com.bdrecruit.springbootmsbdrc.controller;

import com.bdrecruit.springbootmsbdrc.model.User;
import com.bdrecruit.springbootmsbdrc.util.Constants;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author salomao.junior 2022-03-15 20:31
 */
@Service
public class UserService {

    public String createUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection(Constants.COLLECTION_USER).document(user.getName()).set(user);

        return  collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String updateUser(User user) {
        return "";
    }

    public User getUser(String documentId) throws ExecutionException, InterruptedException {

        Firestore dbFireStore = FirestoreClient.getFirestore();

        DocumentReference documentReference = dbFireStore.collection(Constants.COLLECTION_USER).document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        User user;
        if(document.exists()){
            user = document.toObject(User.class);
            return user;
        }

        return null;
    }

    public List<User> getAllUsers() {

        return null;
    }
}
