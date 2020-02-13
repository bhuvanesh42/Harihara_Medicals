package com.example.harihara_medicals;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class regisration_page extends AppCompatActivity {
    EditText firstname,lastname,dob,email,address,drname;
    Button register;

    final Calendar mycalender=Calendar.getInstance();
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regisration_page);

        firstname=findViewById(R.id.regs_firstname);
        lastname=findViewById(R.id.regs_lastname);
        dob=findViewById(R.id.regs_dob);
        final DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mycalender.set(Calendar.YEAR,year);
                mycalender.set(Calendar.MONTH,month);
                mycalender.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(regisration_page.this,date,mycalender.get(Calendar.MONTH),
                        mycalender.get(Calendar.DAY_OF_MONTH),mycalender.get(Calendar.YEAR)).show();
            }
        });
        register = (Button) findViewById(R.id.regs_btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name=firstname.getText().toString().trim();
                String last_name=lastname.getText().toString().trim();
                String d_o_b=dob.getText().toString().trim();
                if (first_name.isEmpty()){
                    firstname.setError("enter your  first name");
                    firstname.requestFocus();
                }else if (last_name.isEmpty()){
                    lastname.setError("enter your last name");
                    lastname.requestFocus();
                }else if (d_o_b.isEmpty()){
                    dob.setError("enter your date of birth");
                    dob.requestFocus();
                }else {
                    startActivity(new Intent(regisration_page.this, HomePageActivity.class));
                }
            }
        });


    }
    public   void updateLabel(){
        String myfromat="MM/dd/yy";
        SimpleDateFormat sdf=new SimpleDateFormat(myfromat, Locale.UK);
        dob.setText(sdf.format(mycalender.getTime()));
    }


}
