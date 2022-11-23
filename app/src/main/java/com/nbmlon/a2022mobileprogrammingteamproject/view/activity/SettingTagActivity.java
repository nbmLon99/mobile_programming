package com.nbmlon.a2022mobileprogrammingteamproject.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nbmlon.a2022mobileprogrammingteamproject.R;

public class SettingTagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_setting);

        findViewById(R.id.btn_add_tag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(SettingTagActivity.this)
                        .setView(new EditText(SettingTagActivity.this))
                        .setCancelable(true)
                        .create();
            }
        });
    }
}