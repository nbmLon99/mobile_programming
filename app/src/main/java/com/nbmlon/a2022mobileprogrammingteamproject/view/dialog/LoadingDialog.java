package com.nbmlon.a2022mobileprogrammingteamproject.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.nbmlon.a2022mobileprogrammingteamproject.R;

public class LoadingDialog{
    AlertDialog dialogProgress;

    public LoadingDialog(Context context) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_loading, null);
        dialogProgress =  new MaterialAlertDialogBuilder(context).setCancelable(false).setView(dialogView).create();
        dialogProgress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void show(){ dialogProgress.show();}
    public void dismiss(){ dialogProgress.dismiss(); }
}
