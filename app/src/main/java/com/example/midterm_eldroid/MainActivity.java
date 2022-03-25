package com.example.midterm_eldroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText idNumber, firstName, lastName, contact, dateBirth;
    Button create, retrieve, retrieveId,update, delete, clear;
    DatabaseConnector DBconnector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*EditTexts*/
        idNumber = findViewById(R.id.et_id_number);
        firstName = findViewById(R.id.et_first_name);
        lastName = findViewById(R.id.et_last_name);
        contact = findViewById(R.id.et_contact);
        dateBirth = findViewById(R.id.et_date_birth);
        /*Buttons*/
        create = findViewById(R.id.btn_create);
        retrieve = findViewById(R.id.btn_retrieve);
        retrieveId = findViewById(R.id.btn_retrieve_id);
        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);
        clear = findViewById(R.id.btn_clear_all);
        /*OnClickListeners*/
        DBconnector = new DatabaseConnector(this);
        /*Create*/
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idNumber.getText().toString();
                String fname = firstName.getText().toString();
                String lname = lastName.getText().toString();
                String contactNo = contact.getText().toString();
                String bDate = dateBirth.getText().toString();
                Boolean createdata = DBconnector.create(id, fname, lname, contactNo, bDate);
                if(createdata==true){
                    Toast.makeText(MainActivity.this,"Entry Successfully Submitted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Entry Not Submitted", Toast.LENGTH_SHORT).show(); } }});
        /*RetrieveAll*/
        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                Cursor result = DBconnector.getdata();
                if (result.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return; }
                while (result.moveToNext()) {
                    buffer.append("Id Number: " + result.getString(0) + "\n");
                    buffer.append("First Name: " + result.getString(1) + "\n");
                    buffer.append("Last Name: " + result.getString(2) + "\n");
                    buffer.append("Contact: " + result.getString(3) + "\n");
                    buffer.append("Date of Birth: " + result.getString(4) + "\n\n"); }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Information Records");
                builder.setMessage(buffer.toString());
                builder.show();}});
        /*Update*/
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idNumber.getText().toString();
                String fname = firstName.getText().toString();
                String lname = lastName.getText().toString();
                String contactNo = contact.getText().toString();
                String bDate = dateBirth.getText().toString();
                Boolean updatedata = DBconnector.update(id, fname, lname, contactNo, bDate);
                if(updatedata == true){
                    Toast.makeText(MainActivity.this,"Entry Successfully Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Entry Not Updated", Toast.LENGTH_SHORT).show(); } }});
        /*Delete*/
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idNumber.getText().toString();
                Boolean deletedata = DBconnector.delete(id);
                if(deletedata == true){
                    Toast.makeText(MainActivity.this,"Entry Successfully Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Entry Not Deleted", Toast.LENGTH_SHORT).show(); } }});
        /*Retrieve*/
        retrieveId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                Cursor result = DBconnector.retrievedata(idNumber);
                if (result.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return; }
                while (result.moveToNext()) {
                    buffer.append("Id Number: " + result.getString(0) + "\n");
                    buffer.append("First Name: " + result.getString(1) + "\n");
                    buffer.append("Last Name: " + result.getString(2) + "\n");
                    buffer.append("Contact: " + result.getString(3) + "\n");
                    buffer.append("Date of Birth: " + result.getString(4) + "\n\n"); }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Information Records");
                builder.setMessage(buffer.toString());
                builder.show();}});
        /*Clear*/
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idNumber.getText().toString();
                String fname = firstName.getText().toString();
                String lname = lastName.getText().toString();
                String contactNo = contact.getText().toString();
                String bDate = dateBirth.getText().toString();
                if(id.isEmpty() && fname.isEmpty() && lname.isEmpty() && contactNo.isEmpty() && bDate.isEmpty() ){
                    Toast.makeText(getApplicationContext(),"Already Empty!",Toast.LENGTH_SHORT).show();
                }else{
                    idNumber.setText("");
                    firstName.setText("");
                    lastName.setText("");
                    contact.setText("");
                    dateBirth.setText("");
                } }});
    }
}