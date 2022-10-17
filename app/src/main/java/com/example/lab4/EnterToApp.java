package com.example.lab4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

public class EnterToApp extends Activity {

    String[] sendData = new String[]{"1", "2", "3", "4", "5"};
    EditText inName;
    EditText inPsw;
    SharedPreferences settingEnter;
    SharedPreferences.Editor editor;

    private static final String NAME = "name";
    private static final String PASSWORD = "password";

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ON START", "Start");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ON CREATE", "Create");

        setContentView(R.layout.enter_scene);

        settingEnter = this.getSharedPreferences("shr", Context.MODE_PRIVATE);

        Button enter = findViewById(R.id.enterBtn);
        inName = findViewById(R.id.inputName);
        inPsw = findViewById(R.id.inputPassword);


        Intent intent = new Intent(this, SecondActivity.class);
        Bundle data = new Bundle();
        data.putStringArray("key", sendData);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ON PAUSE", "Pause");
        editor = settingEnter.edit();
        editor.putString(NAME, inName.getText().toString());
        editor.apply();
        editor.putString(PASSWORD, inPsw.getText().toString());
        editor.apply();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ON RESUME", "Resume");
        inName.setText(settingEnter.getString(NAME, ""));
        inPsw.setText(settingEnter.getString(PASSWORD,""));

    }

    @Override
    protected void onDestroy() {
        settingEnter = this.getSharedPreferences("shr", Context.MODE_PRIVATE);
        boolean a = this.deleteSharedPreferences("shr");


        super.onDestroy();
        Log.i("ON DESTROY", "Destroy");
        //editor.remove(NAME);
        //editor.remove(PASSWORD);
        //inName.setText("");
        //inPsw.setText("");
       // this.getSharedPreferences("shr", Context.MODE_PRIVATE).edit().clear().apply();
       // editor = settingEnter.edit();
       // editor.clear().apply();
       // editor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ON StOP", "Stop");
        //editor.remove(NAME);
        //editor.remove(PASSWORD);
        //inName.setText("");
        //inPsw.setText("");
        // this.getSharedPreferences("shr", Context.MODE_PRIVATE).edit().clear().apply();
        // editor = settingEnter.edit();
        //editor.clear().apply();
        // editor.commit();
    }
}
