package cs246.businesscalendar.view_presenter.daily_calendar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Map;

import cs246.businesscalendar.R;
import cs246.businesscalendar.model.Appointment;

public class DailyCalendarRecyclerViewAdapter extends RecyclerView.Adapter<DailyCalendarRecyclerViewAdapter.MyViewHolder> {
    private static final String TAG = "DailyCalendarRVAdapter";
    private List<LocalTime> timeSlice;
    private List<Appointment> appointments;
    private Context context;

    // Provide a reference to the views for each data item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView timeBlock;
        private ConstraintLayout appointmentContainer;
        private TextView contentBlock;

        private MyViewHolder(View view) {
            super(view);
            timeBlock = view.findViewById(R.id.dailycalendarrecyclerowQuarter);
            appointmentContainer = view.findViewById(R.id.dailycalendarrecyclerowAppointmentBlock);
            contentBlock = view.findViewById(R.id.dailycalendarrecyclerowContent);
        }
    }

    public DailyCalendarRecyclerViewAdapter(Context newContext, List<LocalTime> timeSlice,
                                            List<Appointment> appointments) {
        this.timeSlice = timeSlice;
        this.appointments = appointments;
        this.context = newContext;
    }

    @Override
    @NonNull
    public DailyCalendarRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                      int viewType) {
        // Create a New View from the row.xml layout file
        LayoutInflater myInflater = LayoutInflater.from(context);
        View newView = myInflater.inflate(R.layout.daily_calendar_recycle_row, parent,
                false);

        return new MyViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Formatting Strings
        DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");

        // Retrieve information at a position in the dataset and replace
        // the contents of this view with that data
        holder.timeBlock.setText(timeSlice.get(position).toString(formatTime));

        // Add appointment information as appropriate
        Map<String, Integer> timeSegment = determineTimeSegment(position);
        switch (timeSegment.get("type")) {
            case 0:
                // Start of Appointment
                holder.appointmentContainer.setBackgroundResource(R.drawable.start_segment);
                holder.contentBlock.setText(appointments.get(timeSegment.get("position"))
                        .getAppointmentTitle());
                break;
            case 1:
                // End of Appointment
                holder.appointmentContainer.setBackgroundResource(R.drawable.end_segment);
                break;
            case 2:
                // Middle of Appointment
                holder.appointmentContainer.setBackgroundResource(R.drawable.middle_segment);
                break;
            default:
                // No additional actions
        }
    }

    @Override
    public int getItemCount() {
        return timeSlice.size();
    }

    public void clearData() {
        timeSlice.clear();
    }

    public Map<String, Integer> determineTimeSegment(int position) {
        // Create Map to Store Data
        Map<String, Integer> matchingAppointment = new ArrayMap<>();
        // Default Value if no appointments containing this time are found, return a -1
        matchingAppointment.put("position", -1);
        matchingAppointment.put("type", -1);

        // Convert Position to Time Block
        LocalTime positionTime = new LocalTime(position / 4, position % 4 * 15);

        // Check each appointment in day to see if the position time is within an appointment
        for (int i = 0; i < appointments.size(); i++) {
            LocalTime testStart = appointments.get(i).getAppointmentStart();
            LocalTime testEnd = appointments.get(i).getAppointmentEnd();

            // TO REMOVE**************
            Log.i(TAG, "Start Time:  " + testStart.toString() + " - "
            + "End Time:  " + testEnd.toString());

            // If position time is within an appointment, return a value
            if(positionTime.equals(testStart)) {
                // Starting Time
                matchingAppointment.put("position", i);
                matchingAppointment.put("type", 0);
                break;
            } else if (positionTime.equals(testEnd)) {
                // Ending Time
                matchingAppointment.put("position", i);
                matchingAppointment.put("type", 1);
                break;
            } else if (positionTime.isAfter(testStart) && positionTime.isBefore(testEnd)) {
                // Within Appointment
                matchingAppointment.put("position", i);
                matchingAppointment.put("type", 2);
                break;
            }

            // *** NEED TO ADD LOGIC FOR NOT ADDING AN EXTRA SEGMENT
        }


        // TO REMOVE**************
        String logString = "Input Position:  " + position
                + " - Position Time:  " + positionTime.toString()
                + " - Output Position:  " + ((Integer) matchingAppointment.get("position")).toString()
                + " - Type:  " + ((Integer) matchingAppointment.get("type")).toString();
        Log.i(TAG, logString);

        return matchingAppointment;
    }


}
