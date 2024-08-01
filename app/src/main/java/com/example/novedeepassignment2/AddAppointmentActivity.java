package com.example.novedeepassignment2;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AddAppointmentActivity extends Activity {

    private EditText etAppointmentName, etAppointmentDate, etAppointmentTime;
    private Button btnAddAppointment;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        etAppointmentName = findViewById(R.id.etAppointmentName);
        etAppointmentDate = findViewById(R.id.etAppointmentDate);
        etAppointmentTime = findViewById(R.id.etAppointmentTime);
        btnAddAppointment = findViewById(R.id.btnAddAppointment);

        sharedPreferences = getSharedPreferences("appointments", MODE_PRIVATE);

        btnAddAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAppointment();
            }
        });
    }

    private void addAppointment() {
        String appointmentName = etAppointmentName.getText().toString();
        String appointmentDate = etAppointmentDate.getText().toString();
        String appointmentTime = etAppointmentTime.getText().toString();

        if (appointmentName.isEmpty() || appointmentDate.isEmpty() || appointmentTime.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String appointmentId = UUID.randomUUID().toString();
        String appointmentDetails = appointmentId + "," + appointmentName + "," + appointmentDate + " " + appointmentTime;

        Set<String> appointments = sharedPreferences.getStringSet("appointments", new HashSet<String>());
        appointments.add(appointmentDetails);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("appointments", appointments);
        editor.apply();

        Toast.makeText(this, "Appointment added", Toast.LENGTH_SHORT).show();

        // Schedule the alarm
        scheduleAlarm();

        finish();
    }

    private void scheduleAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long triggerAtMillis = System.currentTimeMillis() + (60 * 1000); // Set the alarm to trigger in 1 minute
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
    }
}
