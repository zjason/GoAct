package com.example.jason.goact_beta;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.jason.goact_beta.Fragments.AddEvent;
import com.example.jason.goact_beta.Fragments.Home;
import com.example.jason.goact_beta.Fragments.Mapfrag;
import com.example.jason.goact_beta.Fragments.Setting;
import com.example.jason.goact_beta.Fragments.User;


public class MainActivity extends Activity implements Home.OnFragmentInteractionListener, Mapfrag.OnFragmentInteractionListener,AddEvent.OnFragmentInteractionListener,
       User.OnFragmentInteractionListener, Setting.OnFragmentInteractionListener{

    private static final String TAG_HOME = "Home";
    private static final String TAG_MAP = "Mapfrag";
    private static final String TAG_ADDEVENT = "AddEvent";
    private static final String TAG_USER= "User";
    private static final String TAG_SETTING = "Setting";

    private int itemId = 0;
    private Fragment home,map,addevent,user,setting;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = this.getFragmentManager();



        //initHomeFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Intent Login = new Intent(MainActivity.this, Login.class);
        //startActivity(Login);
        //finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions, menu);

        this.getActionBar().setDisplayShowHomeEnabled(false);
        this.getActionBar().setDisplayShowTitleEnabled(false);

        return super.onCreateOptionsMenu(menu);
    }

    //helper method that removes the current fragment
    //if you don't remove current fragment and add a new one
    // the fragments will overlap
    private void removeFragments() {
        if (home != null) {
            fm.beginTransaction().remove(home).commit();
            Log.d("test", "home is null");
        }
        if (map != null){
            fm.beginTransaction().remove(map).commit();
            Log.d("test", "map is null");
        }
        if (addevent != null) {
            fm.beginTransaction().remove(addevent).commit();
            Log.d("test", "post is null");
        }
        if (user != null) {
            fm.beginTransaction().remove(user).commit();
            Log.d("test", "user is null");
        }
        if (setting != null) {
            fm.beginTransaction().remove(setting).commit();
            Log.d("test", "setting is null");
        }
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item){
        if(itemId != item.getItemId()){
            removeFragments();
            itemId = item.getItemId();
        }
        else{
            return  false;
        }

        Log.d("test", "1");
        switch (itemId){
            case R.id.action_home:

                home = fm.findFragmentByTag(TAG_HOME);
                if(home == null){
                    home = Home.newInstance("a","n");
                    fm.beginTransaction().add(R.id.frag, home, TAG_HOME).commit();
                    Log.d("test", "home pressed");
                }
                break;
            case R.id.action_map:

                map = fm.findFragmentByTag(TAG_MAP);
                if(map == null){
                    map = Mapfrag.newInstance("a", "n");
                    fm.beginTransaction().add(R.id.frag, map, TAG_MAP).commit();
                }
                break;
            case R.id.action_post:

                addevent = fm.findFragmentByTag(TAG_ADDEVENT);
                if(addevent == null){
                    addevent = AddEvent.newInstance("a", "n");
                    fm.beginTransaction().add(R.id.frag, addevent, TAG_ADDEVENT).commit();
                }
                break;
            case R.id.action_user:

                user = fm.findFragmentByTag(TAG_USER);
                if(user == null){
                    user = User.newInstance("a", "n");
                    fm.beginTransaction().add(R.id.frag, user, TAG_USER).commit();
                }
                break;
            case R.id.action_settings:

                setting = fm.findFragmentByTag(TAG_SETTING);
                if(setting == null){
                    setting = Setting.newInstance("a", "n");
                    fm.beginTransaction().add(R.id.frag, setting, TAG_SETTING).commit();
                }
                break;
        }

        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
