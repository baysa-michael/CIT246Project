package cs246.businesscalendar.view_presenter.landing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cs246.businesscalendar.R;
import cs246.businesscalendar.model.ModelAppointment;

public class LandingRecyclerViewAdapter extends RecyclerView.Adapter<LandingRecyclerViewAdapter.MyViewHolder> {
    private List<ModelAppointment> appointments;
    private Context context;


    // Provide a reference to the views for each data item
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
        }
    }

    public LandingRecyclerViewAdapter(Context newContext, List<ModelAppointment> newAppointments) {
        this.appointments = newAppointments;
        this.context = newContext;
    }

    // Create New Views (invoked by the Layout Manager)
    @Override
    public LandingRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                      int viewType) {
        // Create a New View from the row.xml layout file
        LayoutInflater myInflater = LayoutInflater.from(context);
        View newView = myInflater.inflate(R.layout.landing_recycle_row, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(newView);

        return viewHolder;
    }

    // Replace contents of a view (invoked by the Layout Manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // Retrieve information at a position in the dataset and replace
        // the contents of this view with that data
        //holder.myView.setText(myDataset.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public void clearData() {
        appointments.clear();
    }
}
