package cs246.businesscalendar.view_presenter.daily_calendar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

import cs246.businesscalendar.R;

public class DailyCalendarRecyclerViewAdapter extends RecyclerView.Adapter<DailyCalendarRecyclerViewAdapter.MyViewHolder> {
    private List<LocalTime> timeSlice;
    private Context context;

    // Provide a reference to the views for each data item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView timeBlock;
        private ConstraintLayout appointmentContainer;

        private MyViewHolder(View view) {
            super(view);
            timeBlock = view.findViewById(R.id.dailycalendarrecyclerowQuarter);
            appointmentContainer = view.findViewById(R.id.dailycalendarrecyclerowAppointmentBlock);
        }
    }

    public DailyCalendarRecyclerViewAdapter(Context newContext, List<LocalTime> timeSlice) {
        this.timeSlice = timeSlice;
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
        holder.appointmentContainer.setBackgroundResource(R.drawable.middle_segment);
    }

    @Override
    public int getItemCount() {
        return timeSlice.size();
    }

    public void clearData() {
        timeSlice.clear();
    }


}
