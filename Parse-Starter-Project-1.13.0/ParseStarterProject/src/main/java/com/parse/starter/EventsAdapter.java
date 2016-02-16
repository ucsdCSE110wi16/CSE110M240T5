package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by jennywong on 2/10/16.
 */
// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class EventsAdapter extends
        RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    public static int uniqueRows; // number of unique rows

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView dateTextView;
        public Button messageButton;
        public Context context;
        public List<Event> eventsList;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(Context context, View itemView, List<Event> eventsList) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            this.nameTextView = (TextView) itemView.findViewById(R.id.event_title);
            this.dateTextView = (TextView) itemView.findViewById(R.id.event_date);
            this.messageButton = (Button) itemView.findViewById(R.id.replyButton);
            this.context = context;
            this.eventsList = eventsList;

            // Attach a click listener to button
            messageButton.setOnClickListener(this);
        }

        // Handles row being clicked
        @Override
        public void onClick(View v) {
            int position = getLayoutPosition(); // gets item position
            String id = eventsList.get(position).getID();
            Toast.makeText(context, "pos: "+ position, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, ExpandDetailsActivity.class);
            intent.putExtra("id", id);

            context.startActivity(intent);
        }
    }

    // Store a member variable for the events
    private List<Event> eventsList;

    // Pass in the event array into the constructor
    public EventsAdapter(List<Event> events) {
            this.eventsList = events;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View eventView = inflater.inflate(R.layout.event_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(context, eventView, eventsList);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(EventsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Event ev = this.eventsList.get(position);

        // Set item views based on the data model
        TextView nameTV = viewHolder.nameTextView;
        nameTV.setText(ev.getTitle());

        TextView dateTV = viewHolder.dateTextView;
        dateTV.setText(ev.getDate().toString()); // TODO make date format prettier

        Button button = viewHolder.messageButton;

    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    // Return an item in the list
    public Event getItem(int index) {
        return eventsList.get(index);
    }
}