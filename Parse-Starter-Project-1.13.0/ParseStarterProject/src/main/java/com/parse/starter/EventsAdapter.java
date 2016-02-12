package com.parse.starter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jennywong on 2/10/16.
 */
// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class EventsAdapter extends
        RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView dateTextView;
        public Button messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.event_title);
            dateTextView = (TextView) itemView.findViewById(R.id.event_date);
            messageButton = (Button) itemView.findViewById(R.id.replyButton);
        }
    }

    // Store a member variable for the events
    private List<EventsForLoading> eventsList;

    // Pass in the event array into the constructor
    public EventsAdapter(List<EventsForLoading> events) {
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
        ViewHolder viewHolder = new ViewHolder(eventView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(EventsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        EventsForLoading contact = this.eventsList.get(position);

        // Set item views based on the data model
        TextView nameTV = viewHolder.nameTextView;
        nameTV.setText(contact.getEventName());

        TextView dateTV = viewHolder.dateTextView;
        dateTV.setText(contact.getDate());

        Button button = viewHolder.messageButton;

    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}