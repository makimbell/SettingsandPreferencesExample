package com.example.application.settingsandpreferencesexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView lblHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Grab default values from preferences file
        //  Last parameter "false" means this will only run once so as not to override user prefs
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        // Call refresh settings method to refresh appearance based on chosen settings
        refreshSettings();
    }

    public void btnClick(View view) {
        // When the user clicks on the SETTINGS button, call the SettingsActivity using an intent
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // When coming back to this screen, always refresh the settings in case the user has made changes
        //  You can detect changes, but for this simple app, it's trivial to refresh each time
        //  (especially since the activity is being created again anyway)
        refreshSettings();
    }

    private void refreshSettings() {
        // Get default SharedPreferences file
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Read desired preferences from file and store them in local variables
        boolean isBold = sharedPreferences.getBoolean("bold_text", false);
        String username = sharedPreferences.getString("users_name", "Kevin");
        String fontSize = sharedPreferences.getString("font_size", "14");

        // Get reference to TextView that we will be modifying
        lblHello = (TextView) findViewById(R.id.lblHello);

        // Make TextView bold based on user setting
        if (isBold) {
            lblHello.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            lblHello.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }

        // Customize TextView message based on user setting
        lblHello.setText("Hello, " + username);

        // Set TextView text size based on user setting
        lblHello.setTextSize(Float.parseFloat(fontSize));

    }
}
