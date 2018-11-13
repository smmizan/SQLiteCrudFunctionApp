package com.smmizan.sqlitecrudapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyDatabaseHelper myDatabaseHelper;


    EditText eID,eName,eEmail,eAddress,ePhone;
    Button bSubmit,bRead,bEdit,bDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        eID = (EditText) findViewById(R.id.etID);
        eName = (EditText) findViewById(R.id.etName);
        eEmail = (EditText) findViewById(R.id.etEmail);
        eAddress = (EditText) findViewById(R.id.etAddress);
        ePhone = (EditText) findViewById(R.id.etPhone);

        bSubmit = (Button) findViewById(R.id.bSubmitt);
        bRead = (Button) findViewById(R.id.bRead);
        bEdit = (Button) findViewById(R.id.bEdit);
        bDelete = (Button) findViewById(R.id.bDelete);




        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();




        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String Name = eName.getText().toString();
                 String Email = eEmail.getText().toString();
                 String Address = eAddress.getText().toString();
                 String Phone = ePhone.getText().toString();

                long rowID = myDatabaseHelper.dataInsert(Name,Email,Address,Phone);
                if(rowID == -1)
                {
                    Toast.makeText(MainActivity.this, "Not Insert Data", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(MainActivity.this, "row "+rowID+" Successfully Insert", Toast.LENGTH_SHORT).show();
                }
            }
        });


        bRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = myDatabaseHelper.showAllData();

                if (cursor.getCount() == 0)
                {
                    showData("Error","No Data Found");
                    return;
                }

                StringBuffer stringBuffer = new StringBuffer();
                while (cursor.moveToNext())
                {
                    stringBuffer.append("ID : "+cursor.getString(0)+"\n");
                    stringBuffer.append("Name : "+cursor.getString(1)+"\n");
                    stringBuffer.append("Email : "+cursor.getString(2)+"\n");
                    stringBuffer.append("Address : "+cursor.getString(3)+"\n");
                    stringBuffer.append("Phone : "+cursor.getString(4)+"\n \n \n \n");
                }

                showData("Data Store",stringBuffer.toString());
            }
        });


        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID = eID.getText().toString();
                String Name = eName.getText().toString();
                String Email = eEmail.getText().toString();
                String Address = eAddress.getText().toString();
                String Phone = ePhone.getText().toString();



                Boolean updated = myDatabaseHelper.updateData(ID,Name,Email,Address,Phone);
                if(updated == true)
                {
                    Toast.makeText(MainActivity.this, "Successfully Update Data", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "Not Update ! Error", Toast.LENGTH_SHORT).show();
                }


            }
        });


        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = eID.getText().toString();
                int delete = myDatabaseHelper.deleteData(id);
                if(delete > 0 )
                {
                    Toast.makeText(MainActivity.this, "Successfully Delete row", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Not Deleted a row", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }

    public void showData(String title,String data)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(data);
        builder.setCancelable(true);
        builder.show();

    }
}
