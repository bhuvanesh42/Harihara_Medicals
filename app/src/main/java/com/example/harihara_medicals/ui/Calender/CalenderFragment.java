package com.example.harihara_medicals.ui.Calender;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.harihara_medicals.ApiUtils;
import com.example.harihara_medicals.DbHander;
import com.example.harihara_medicals.Productapi;
import com.example.harihara_medicals.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalenderFragment extends Fragment {

    CalendarView calendarView;
    FloatingActionButton floatingActionButton;
    DbHander dbHander;
    EditText reminder_title,reminder_date,reminder_time,reminder_location,reminder_description;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calender,null);

        calendarView=root.findViewById(R.id.calender_calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String Date
                        = dayOfMonth + "-"
                        + (month + 1) + "-" + year;
            }
        });

        floatingActionButton=root.findViewById(R.id.calendar_remainder);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.calendar_remainder);
                reminder_title=dialog.findViewById(R.id.remainder_title);
                reminder_time=dialog.findViewById(R.id.remainder_time);
                reminder_date=dialog.findViewById(R.id.remainder_remainder);
                reminder_description=dialog.findViewById(R.id.remainder_description);
                reminder_location=dialog.findViewById(R.id.remainder_location);

                TextView done = dialog.findViewById(R.id.remainder_done);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            sendpost(reminder_title.getText().toString(),reminder_date.getText().toString(),reminder_description.getText().toString(),reminder_location.getText().toString(),reminder_time.getText().toString());
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        Toast.makeText(getContext(),"Remainder Done",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
        });



        return root;

    }

    private void sendpost(String title, String time, String date, String loc, String desc) throws  IOException {
        Call<Void> call= ApiUtils.getProductapi().getReminder(title, time, date, loc, desc);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("success","done");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("failure",t.getMessage());
            }
        });
    }

}