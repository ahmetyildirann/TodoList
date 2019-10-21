package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTask extends AppCompatActivity {

    EditText titleTask;
    EditText descTask;
    EditText dateTask;

    Button updateButon,deleteButton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

       titleTask=findViewById(R.id.titletask);
       descTask =findViewById(R.id.desctask);
       dateTask =findViewById(R.id.datetask);

       updateButon=findViewById(R.id.btnUpadateTask);
       deleteButton=findViewById(R.id.btnDelete);

       //burda verileri alıyoruz ve editactivite de gosteriyoruz
       titleTask.setText(getIntent().getStringExtra("titletask"));
       descTask.setText(getIntent().getStringExtra("desctask"));
       dateTask.setText(getIntent().getStringExtra("datetask"));

       final String keykeytask= getIntent().getStringExtra("keytask");

        databaseReference= FirebaseDatabase.getInstance().getReference().child("TaskApp")
                .child("Task"+keykeytask);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                   if (task.isSuccessful()){
                       Intent intent = new Intent(EditTask.this,MainActivity.class);
                       startActivity(intent);
                   }else {
                       Toast.makeText(getApplicationContext(),"Unfortunately not delete",Toast.LENGTH_SHORT);
                   }

                    }
                });

            }

        });



       updateButon.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //burda databe e insert ediyoruz


               databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       dataSnapshot.getRef().child("titletask").setValue(titleTask.getText().toString());
                       dataSnapshot.getRef().child("desctask").setValue(descTask.getText().toString());
                       dataSnapshot.getRef().child("datetask").setValue(dateTask.getText().toString());
                       dataSnapshot.getRef().child("keytask").setValue(keykeytask);

                       Intent intent= new Intent(EditTask.this,MainActivity.class);
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
