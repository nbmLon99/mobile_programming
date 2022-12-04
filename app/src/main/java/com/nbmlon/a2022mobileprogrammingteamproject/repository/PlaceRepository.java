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

        if(area != null)
            query = query.whereIn(FirebaseName.Field.area, Collections.singletonList(area));
        if(parking != null)
            query = query.whereIn(FirebaseName.Field.parking, Collections.singletonList(parking));
        if(storage != null)
            query = query.whereIn(FirebaseName.Field.storage, Collections.singletonList(storage));
        if(infant != null)
            query = query.whereIn(FirebaseName.Field.infant, Collections.singletonList(infant));
        if(wheel != null)
            query = query.whereIn(FirebaseName.Field.wheel, Collections.singletonList(wheel));
        if(pointRoad != null)
            query = query.whereIn(FirebaseName.Field.pointRoad, Collections.singletonList(pointRoad));

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
