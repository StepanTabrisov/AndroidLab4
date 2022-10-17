package com.example.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;

public class SecondActivity extends Activity {

    ArrayList<String> users = new ArrayList<String>();
    String user;
    int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        Button back = findViewById(R.id.backBtn);
        Button add = findViewById(R.id.addBtn);
        Button del = findViewById(R.id.deleteBtn);

        ListView userList = findViewById(R.id.listUser);

        EditText inputUserName = findViewById(R.id.ListViewName);

        ArrayAdapter<String> stringAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , users);
        userList.setAdapter(stringAdapter);

        Intent intent = new Intent(this, EnterToApp.class);
        Bundle recData = this.getIntent().getExtras();

        String[] inData = recData.getStringArray("key");
        Collections.addAll(users, inData);
        stringAdapter.notifyDataSetChanged();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = inputUserName.getText().toString();
                if(!user.isEmpty()) {
                    users.add(user);
                    inputUserName.setText("");
                    stringAdapter.notifyDataSetChanged();
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.remove(user);
                //userList.setSelected(false);
                userList.setSelector(R.color.white);
                stringAdapter.notifyDataSetChanged();
            }
        });

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                userList.setSelector(R.color.teal_200);
                user = stringAdapter.getItem(i);
                stringAdapter.notifyDataSetChanged();
            }
        });
    }

}
