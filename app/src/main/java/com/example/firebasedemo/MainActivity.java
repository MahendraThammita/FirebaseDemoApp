package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText txtID , txtName , txtAdd , txtConNo;
    Button btnSave , btnUpdate , btnDel , btnShow;
    DatabaseReference DBRef;
    Student std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID = findViewById(R.id.EdinputID);
        txtName = findViewById(R.id.EdinputName);
        txtAdd = findViewById(R.id.EdinputAddress);
        txtConNo = findViewById(R.id.EdinputConNo);

        btnSave = findViewById(R.id.BtnSave);
        btnShow = findViewById(R.id.BtnShow);
        btnDel = findViewById(R.id.BtnDel);
        btnUpdate = findViewById(R.id.BtnUpdate);

        std = new Student();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBRef = FirebaseDatabase.getInstance().getReference().child("Student");

                try {
                    if(TextUtils.isEmpty(txtID.getText().toString())){
                        Toast.makeText(getApplicationContext() , "Empty ID" , Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(txtName.getText().toString())){
                        Toast.makeText(getApplicationContext() , "Empty Nmae" , Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(txtAdd.getText().toString())){
                        Toast.makeText(getApplicationContext() , "Empty Address" , Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(txtConNo.getText().toString())){
                        Toast.makeText(getApplicationContext() , "Contact Number is Empty" , Toast.LENGTH_SHORT).show();
                    }
                    else {
                        std.setName(txtName.getText().toString().trim());
                        std.setID(txtID.getText().toString().trim());
                        std.setAdd(txtAdd.getText().toString().trim());
                        std.setConyNo(Integer.parseInt(txtConNo.getText().toString().trim()));
                        DBRef.child("std1").setValue(std);
                        Toast.makeText(getApplicationContext() , "Inserted Successfully" , Toast.LENGTH_SHORT).show();
                        ClearControls();
                    }
                }
                catch (NumberFormatException err){
                    Toast.makeText(getApplicationContext(), "Invalid Contact No" , Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBRef = FirebaseDatabase.getInstance().getReference().child("Student").child("std1");
                DBRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            txtID.setText(dataSnapshot.child("id").getValue().toString());
                            txtName.setText(dataSnapshot.child("name").getValue().toString());
                            txtAdd.setText(dataSnapshot.child("add").getValue().toString());
                            txtConNo.setText(dataSnapshot.child("conyNo").getValue().toString());
                        }
                        else
                            Toast.makeText(getApplicationContext() , "Cannot Find Std1" , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBRef = FirebaseDatabase.getInstance().getReference();
                DBRef.child("Student").child("std1").child("name").setValue(txtName.getText().toString().trim());
                DBRef.child("Student/std1/add").setValue(txtAdd.getText().toString().trim());
                Toast.makeText(getApplicationContext() , "Successfully Updated" , Toast.LENGTH_SHORT).show();
                ClearControls();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBRef = FirebaseDatabase.getInstance().getReference().child("Student").child("std1");
                DBRef.removeValue();
                Toast.makeText(getApplicationContext() , "Succesfully Deleated" , Toast.LENGTH_SHORT).show();
            }
        });





    }

    private void ClearControls(){
        txtID.setText("");
        txtName.setText("");
        txtAdd.setText("");
        txtConNo.setText("");


    }
}