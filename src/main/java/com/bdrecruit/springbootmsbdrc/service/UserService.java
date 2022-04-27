package com.bdrecruit.springbootmsbdrc.service;

import com.bdrecruit.springbootmsbdrc.model.User;
import com.bdrecruit.springbootmsbdrc.util.Constants;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author salomao.junior 2022-03-15 20:31
 */
@Service
public class UserService {

    Firestore dbFireStore = FirestoreClient.getFirestore();

    public String createUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection(Constants.COLLECTION_USER).document(user.getId()).set(user);

        return  collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String updateUser(User user) {
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection(Constants.COLLECTION_USER).document(user.getId()).set(user);
        return user.getId();
    }

    public User getUser(String Id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = dbFireStore.collection(Constants.COLLECTION_USER).document(Id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        User user;
        if(document.exists()){
            user = document.toObject(User.class);
            user.setId(Id);

            return user;
        }

        return null;
    }

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        List<User> users = new ArrayList<>();
        Iterable<DocumentReference> documentsReference = dbFireStore.collection(Constants.COLLECTION_USER).listDocuments();

        for(DocumentReference documentReference: documentsReference){
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            User user;
            if(document.exists()){
                user = document.toObject(User.class);
                users.add(user);
            }
        }

        return users;
    }
}
