package com.example.application.settingsandpreferencesexample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SettingsActivity extends Activity {
    // This SettingsActivity is launched by MainActivity when the user clicks the SETTINGS button.
    //  It then displays the SettingsFragment customized with the preferences.xml file we created

    public static final String KEY_PREF_BOLD = "bold_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
