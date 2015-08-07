package com.example.jason.goact_beta;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;


public class MainActivity extends Activity {

    private Fragment home,addevent,user,setting;
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton home = (ImageButton) findViewById()
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Intent Login = new Intent(MainActivity.this, Login.class);
        //startActivity(Login);
        //finish();
    }

    //helper method that removes the current fragment
    //if you don't remove current fragment and add a new one
    // the fragments will overlap
    private void removeFragments() {
        if (home != null) {
            fm.beginTransaction().remove(home).commit();
        }
        if (addevent != null) {
            fm.beginTransaction().remove(addevent).commit();
        }
        if (user != null) {
            fm.beginTransaction().remove(user).commit();
        }
        if (setting != null) {
            fm.beginTransaction().remove(setting).commit();
        }
    }
}
