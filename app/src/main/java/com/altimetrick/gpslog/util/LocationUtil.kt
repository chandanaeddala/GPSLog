package com.altimetrick.gpslog.util

import android.content.Context
import android.location.Location
import com.altimetrick.gpslog.R


class LocationUtil {

    companion object {

        // Debugging tag for the application
        val APPTAG = "LocationSample"

        // Name of shared preferences repository that stores persistent state
        val SHARED_PREFERENCES = "com.altimetrick.gpslog.location.SHARED_PREFERENCES"

        // Key for storing the "updates requested" flag in shared preferences
        val KEY_UPDATES_REQUESTED = "com.altimetrick.gpslog.location.KEY_UPDATES_REQUESTED"

        /*
         * Define a request code to send to Google Play services
         * This code is returned in Activity.onActivityResult
         */
        val CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000

        /*
         * Constants for location update parameters
         */
        // Milliseconds per second
        val MILLISECONDS_PER_SECOND = 1000

        // The update interval
        val UPDATE_INTERVAL_IN_SECONDS = 5

        // A fast interval ceiling
        val FAST_CEILING_IN_SECONDS = 1

        // Update interval in milliseconds
        val UPDATE_INTERVAL_IN_MILLISECONDS =
            (MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS).toLong()

        // A fast ceiling of update intervals, used when the app is visible
        val FAST_INTERVAL_CEILING_IN_MILLISECONDS =
            (MILLISECONDS_PER_SECOND * FAST_CEILING_IN_SECONDS).toLong()

        // Create an empty string for initializing strings
        val EMPTY_STRING = String()

        /**
         * Get the latitude and longitude from the Location object returned by
         * Location Services.
         *
         * @param currentLocation A Location object containing the current location
         * @return The latitude and longitude of the current location, or null if no
         * location is available.
         */
        fun getLatLng(context: Context, currentLocation: Location?): String {
            // If the location is valid
            return if (currentLocation != null) {

                // Return the latitude and longitude as strings
                context.getString(
                    R.string.latitude_longitude,
                    currentLocation.getLatitude(),
                    currentLocation.getLongitude()
                )
            } else {

                // Otherwise, return the empty string
                EMPTY_STRING
            }
        }
    }

}