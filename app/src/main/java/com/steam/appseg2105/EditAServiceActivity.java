package com.steam.appseg2105;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditAServiceActivity extends AppCompatActivity {
    private Button editServiceBut;
    private EditText editServiceEdit;
    private EditText editServiceTitle;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_aservice);
        editServiceBut = findViewById(R.id.editButton);
        editServiceEdit = findViewById(R.id.editServiceHourly);
        editServiceTitle = findViewById(R.id.serviceTitleEdit);
        editServiceBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance().getReference("services");
                database.child(editServiceTitle.getText().toString().trim()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            Toast.makeText(EditAServiceActivity.this, "Error",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            database.child(editServiceTitle.getText().toString().trim()).child("hourlyRate").setValue(editServiceEdit.getText().toString().trim());
                            Toast.makeText(EditAServiceActivity.this, "Successful Change",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditAServiceActivity.this,Admin.class));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
