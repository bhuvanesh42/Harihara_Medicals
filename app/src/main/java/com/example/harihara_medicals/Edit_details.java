package com.example.harihara_medicals;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Edit_details extends AppCompatActivity {
    FirebaseUser user;
    EditText number_bg,dob;
    final Calendar mycalender=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_details);
        number_bg=findViewById(R.id.regs_mob);
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            String num=user.getPhoneNumber();
            number_bg.setText(num);
        }
        final DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mycalender.set(Calendar.YEAR,year);
                mycalender.set(Calendar.MONTH,month);
                mycalender.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };
        dob=findViewById(R.id.regs_dob);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Edit_details.this,date,mycalender.get(Calendar.MONTH),
                        mycalender.get(Calendar.DAY_OF_MONTH),mycalender.get(Calendar.YEAR)).show();

            }
        });

    }
    public   void updateLabel(){
        String myfromat="MM/dd/yyyy";
        SimpleDateFormat sdf=new SimpleDateFormat(myfromat, Locale.UK);
        dob.setText(sdf.format(mycalender.getTime()));
    }
}
