package com.example.novedeepassignment2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.wear.widget.WearableRecyclerView;
import androidx.wear.widget.WearableLinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListAppointmentsActivity extends Activity {

    private WearableRecyclerView recyclerView;
    private AppointmentAdapter appointmentAdapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_appointments);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setEdgeItemsCenteringEnabled(true);
        recyclerView.setLayoutManager(new WearableLinearLayoutManager(this));

        sharedPreferences = getSharedPreferences("appointments", MODE_PRIVATE);
        Set<String> appointmentsSet = sharedPreferences.getStringSet("appointments", null);

        List<String> appointmentsList = new ArrayList<>();
        if (appointmentsSet != null) {
            appointmentsList.addAll(appointmentsSet);
        }

        appointmentAdapter = new AppointmentAdapter(appointmentsList);
        recyclerView.setAdapter(appointmentAdapter);
    }
}
