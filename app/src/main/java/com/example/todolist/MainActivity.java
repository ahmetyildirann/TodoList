package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView titlePage,subtitlePage,endPage;
    Button buttondelete;



    DatabaseReference databaseReference;
    TaskAdapter taskAdapter;
    RecyclerView recyclerView;
    ArrayList<TaskModel> taskModels = new ArrayList<>();
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    //gerekli data calısmaları

        button=findViewById(R.id.btnAddNew);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,NewTaskActivity.class);
                intent.putExtra("action","new");
                startActivity(intent);
            }
        });

        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskModels = new ArrayList<TaskModel>();

        taskAdapter =new  TaskAdapter(MainActivity.this,taskModels);
        recyclerView.setAdapter(taskAdapter);

        //firebseden data cekme

        databaseReference = FirebaseDatabase.getInstance().getReference().child("TaskApp");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //database değiştirmek için buraya yazmalısın
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    TaskModel model=dataSnapshot1.getValue(TaskModel.class);
//                    Log.i("LOG", "onDataChange: " + model.titletask);
                    taskModels.add(model);

                }
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                      // database i iptal için buraya yaz
                Toast.makeText(getApplicationContext(),"No Data ",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
