package com.project.android.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private ArrayList<Weather> weatherArrayList;

    public WeatherAdapter(ArrayList<Weather> weatherArrayList) {
        this.weatherArrayList = weatherArrayList;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted temperature string showing 1 decimal place (i.e. "3.2")
     * from a decimal temperature value.
     */
    private String formatTemperature(double magnitude) {
        DecimalFormat temperatureFormat = new DecimalFormat("0.0");
        return temperatureFormat.format(magnitude);
    }

    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.weather_list_item, parent, false);
        return new ViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder holder, int position) {
        // Find the TextView with view ID location
        TextView locationView = holder.itemView.findViewById(R.id.location);
        // Find the TextView with view ID date
        TextView dateView = holder.itemView.findViewById(R.id.date);
        // Find the TextView with view ID time
        TextView timeView = holder.itemView.findViewById(R.id.time);
        //Find the TextView with view ID temperature
        TextView temperatureView = holder.itemView.findViewById(R.id.temperature);

        // Get current viewholder position, aka. current weather
        Weather currentWeather = weatherArrayList.get(position);

        // Format the temperature to show 1 decimal place
        String formattedTemperature = formatTemperature(currentWeather.getTemperature());
        // Display the temperature of the current weather in that TextView
        temperatureView.setText(formattedTemperature);
        locationView.setText(currentWeather.getLocation());

        // Create a new Date object from the time in milliseconds of the weather
        Date dateObject = new Date(currentWeather.getTimeInMilliseconds()*1000);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current weather in that TextView
        dateView.setText(formattedDate);

        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current weather in that TextView
        timeView.setText(formattedTime);
    }

    @Override
    public int getItemCount() {
        return weatherArrayList.size();
    }

    public void setNewData(ArrayList<Weather> newWeatherArrayList) {
        this.weatherArrayList = newWeatherArrayList;

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
