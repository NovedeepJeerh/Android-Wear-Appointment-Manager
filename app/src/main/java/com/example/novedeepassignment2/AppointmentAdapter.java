package com.example.novedeepassignment2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private List<String> appointments;

    public AppointmentAdapter(List<String> appointments) {
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] details = appointments.get(position).split(",");
        holder.appointmentId.setText(details[0]);
        holder.appointmentName.setText(details[1]);
        holder.appointmentDateTime.setText(details[2]);
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView appointmentId, appointmentName, appointmentDateTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appointmentId = itemView.findViewById(R.id.appointmentId);
            appointmentName = itemView.findViewById(R.id.appointmentName);
            appointmentDateTime = itemView.findViewById(R.id.appointmentDateTime);
        }
    }
}
