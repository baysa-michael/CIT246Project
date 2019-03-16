package cs246.businesscalendar.view_presenter.landing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

import cs246.businesscalendar.R;
import cs246.businesscalendar.model.ModelAppointment;

public class LandingRecyclerViewAdapter extends RecyclerView.Adapter<LandingRecyclerViewAdapter.MyViewHolder> {
    private List<ModelAppointment> appointments;
    private Context context;


    // Provide a reference to the views for each data item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView myDate;
        private TextView myTime;
        private TextView myTitle;

        private MyViewHolder(View view) {
            super(view);
            myDate = view.findViewById(R.id.landingrecyclerowDate);
            myTime = view.findViewById(R.id.landingrecyclerowTime);
            myTitle = view.findViewById(R.id.landingrecyclerowTitle);
        }
    }

    public LandingRecyclerViewAdapter(Context newContext, List<ModelAppointment> newAppointments) {
        this.appointments = newAppointments;
        this.context = newContext;
    }

    @Override
    @NonNull
    public LandingRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                      int viewType) {
        // Create a New View from the row.xml layout file
        LayoutInflater myInflater = LayoutInflater.from(context);
        View newView = myInflater.inflate(R.layout.landing_recycle_row, parent, false);

        return new MyViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Formatting Strings
        DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd, EEEE");
        DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");

        // Set Time String
        String appointmentTime = appointments.get(position).getAppointmentStart().
                toString(formatTime) + " - " +
                appointments.get(position).getAppointmentEnd().toString(formatTime);

        // Retrieve information at a position in the dataset and replace
        // the contents of this view with that data
        holder.myDate.setText(appointments.get(position).getAppointmentDate().toString(formatDate));
        holder.myTime.setText(appointmentTime);
        holder.myTitle.setText(appointments.get(position).getAppointmentTitle());
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public void clearData() {
        appointments.clear();
    }
}
