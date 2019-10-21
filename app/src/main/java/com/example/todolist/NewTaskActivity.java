package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTaskActivity extends AppCompatActivity {

    EditText titleTask;
    EditText descTask;
    EditText dateTask;
    Button btnSaveTask, btnCancelTask;
    DatabaseReference databaseReference;
    Integer taskNumber =new Random().nextInt();
    String keytask = Integer.toString(taskNumber);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);



        titleTask=findViewById(R.id.titletask);
        descTask =findViewById(R.id.desctask);
        dateTask=findViewById(R.id.datetask);

        btnSaveTask =findViewById(R.id.btnSaveTask);
        btnCancelTask =findViewById(R.id.btnCancel);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //burda databe e insert ediyoruz
                databaseReference= FirebaseDatabase.getInstance().getReference().child("TaskApp")
                        .child("Task"+taskNumber);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titletask").setValue(titleTask.getText().toString());
                        dataSnapshot.getRef().child("desctask").setValue(descTask.getText().toString());
                        dataSnapshot.getRef().child("datetask").setValue(dateTask.getText().toString());
                        dataSnapshot.getRef().child("keytask").setValue(keytask);


                        Intent intent=new Intent(NewTaskActivity.this,MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
