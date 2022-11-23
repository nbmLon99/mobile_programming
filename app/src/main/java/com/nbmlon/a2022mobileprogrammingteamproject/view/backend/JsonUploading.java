package com.nbmlon.a2022mobileprogrammingteamproject.view.backend;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nbmlon.a2022mobileprogrammingteamproject.model.PlaceDTO;

import java.lang.reflect.Type;
import java.util.List;

public class JsonUploading {
    public static List<PlaceDTO> getJSONitems(String jsonFileString){
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<PlaceDTO>>() { }.getType();

        List<PlaceDTO> places = gson.fromJson(jsonFileString, listUserType);
        return places;
    }

    public static void upload(List<PlaceDTO> places){
        CollectionReference locationDB =  FirebaseFirestore.getInstance().collection("Location");
        for (PlaceDTO place : places){
            locationDB.add(place).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("Uploading Failed",place.name);
                }
            });
        }

    }
}

