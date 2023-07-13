package com.example.hafizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class StudentDetailsActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewAge;
    private TextView textViewClassName;
    private TextView textViewSabaq;
    private TextView textViewSabaqi;
    private TextView textViewManzil;
    private Button buttonUpdate;
    private com.example.hafizapp.DBHelper databaseHelper;
    private int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        textViewName = findViewById(R.id.textViewName);
        textViewAge = findViewById(R.id.textViewAge);
        textViewClassName = findViewById(R.id.textViewClassName);
        textViewSabaq = findViewById(R.id.textViewSabaq);
        textViewSabaqi = findViewById(R.id.textViewSabaqi);
        textViewManzil = findViewById(R.id.textViewManzil);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        databaseHelper = new com.example.hafizapp.DBHelper(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age", 0);
        String className = intent.getStringExtra("className");
        String sabaq = intent.getStringExtra("sabaq");
        String sabaqi = intent.getStringExtra("sabaqi");
        String manzil = intent.getStringExtra("manzil");
        studentId = intent.getIntExtra("id", 0);

        textViewName.setText(name);
        textViewAge.setText(String.valueOf(age));
        textViewClassName.setText(className);
        textViewSabaq.setText(sabaq);
        textViewSabaqi.setText(sabaqi);
        textViewManzil.setText(manzil);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentDetailsActivity.this);
                builder.setTitle("Update Sabaq and Manzil");
                builder.setMessage("Do you want to update the Sabaq,Sabaqi and Manzil values?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showUpdateDialog();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Sabaq,Sabaqi and Manzil ");

        // Set up the layout for the dialog
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(32, 16, 32, 16);

        // Create EditTexts for entering new Sabaq and Manzil values
        EditText editTextSabaq = new EditText(this);
        editTextSabaq.setHint("Enter new Sabaq");
        layout.addView(editTextSabaq);
        EditText editTextSabaqi = new EditText(this);
        editTextSabaqi.setHint("Enter new Sabaqi");
        layout.addView(editTextSabaqi);

        EditText editTextManzil = new EditText(this);
        editTextManzil.setHint("Enter new Manzil");
        layout.addView(editTextManzil);

        builder.setView(layout);

        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String updatedSabaq = editTextSabaq.getText().toString().trim();
                String updatedSabaqi = editTextSabaq.getText().toString().trim();
                String updatedManzil = editTextManzil.getText().toString().trim();


                if (!updatedSabaq.isEmpty() && !updatedManzil.isEmpty()) {
                    databaseHelper.updateStudentSabaqManzil(studentId, updatedSabaq,updatedSabaqi, updatedManzil);
                    Toast.makeText(StudentDetailsActivity.this, "Sabaq and Manzil updated", Toast.LENGTH_SHORT).show();
                    textViewSabaq.setText(updatedSabaq);
                    textViewManzil.setText(updatedManzil);
                } else {
                    Toast.makeText(StudentDetailsActivity.this, "Please enter values for Sabaq and Manzil", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}