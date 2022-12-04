package com.nbmlon.a2022mobileprogrammingteamproject.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.nbmlon.a2022mobileprogrammingteamproject.utils.FirebaseName;

import java.util.Collections;
import java.util.List;

public class PlaceRepository {
    private PlaceRepository() {}
    private static FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private static PlaceRepository INSTANCE = null;

    public static void initialize() {
        INSTANCE = new PlaceRepository();
    }

    public static PlaceRepository getINSTANCE() {
        if (INSTANCE == null) {
            initialize();
        }
        return INSTANCE;
    }

    public void searchCondition(
            String area,

            String parking,
            String storage,
            String infant,
            String wheel,
            String pointRoad,

            CompleteQueryCallback completeQueryCallback
    ) {
        CollectionReference collection = firestore.collection(FirebaseName.Collection.name);
        Query query = collection;

        if(!area.isEmpty())
            query = query.whereEqualTo(FirebaseName.Field.area, area);
        if(parking != null)
            query = query.whereEqualTo(FirebaseName.Field.parking, parking);
        if(storage != null)
            query = query.whereEqualTo(FirebaseName.Field.storage, storage);
        if(infant != null)
            query = query.whereEqualTo(FirebaseName.Field.infant, infant);
        if(wheel != null)
            query = query.whereEqualTo(FirebaseName.Field.wheel, wheel);
        if(pointRoad != null)
            query = query.whereEqualTo(FirebaseName.Field.pointRoad, pointRoad);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    completeQueryCallback.QueryComplete(task.getResult().getDocuments());
                }
            }
        });
    }

    public interface CompleteQueryCallback {
        public void QueryComplete(List<DocumentSnapshot> documentSnapshots);
    }

}
