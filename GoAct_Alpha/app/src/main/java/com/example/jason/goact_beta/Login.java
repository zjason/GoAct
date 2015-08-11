package com.example.jason.goact_beta;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;


public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActionBar().hide();
        final ImageButton register = (ImageButton) findViewById(R.id.register);
        final ImageButton login = (ImageButton) findViewById(R.id.login);

        final EditText emaildisplay = (EditText) findViewById(R.id.loginemail);
        final EditText passworddisplay = (EditText) findViewById(R.id.loginpassword);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign = new Intent(Login.this, SignUp.class);
                startActivity(sign);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new loginUploader().execute(new Userlogin(emaildisplay.getText().toString(),passworddisplay.getText().toString()));
            }
        });
    }

    private class loginUploader extends AsyncTask<Userlogin, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Userlogin... params) {
            try {
                return checklogin(params[0]);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            if (result) {
                Toast.makeText(getApplicationContext(),"Login successful", Toast.LENGTH_SHORT).show();
                Intent App = new Intent(Login.this, MainActivity.class);
                startActivity(App);
                finish();
                Log.d("vt", "posted!");

            } else {
                Toast.makeText(getApplicationContext(),"Email or password is wrong!",Toast.LENGTH_SHORT).show();
                Log.d("vt", "not posted!");
            }
        }

    }

    public boolean checklogin(Userlogin input) throws IOException, JSONException {

        InputStream is = null;
        HttpURLConnection conn = (HttpURLConnection) ((new URL(MainActivity.myurl + "/login").openConnection()));
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestMethod("POST");
        conn.connect();

        JSONObject entry = new JSONObject();
        entry.put("email", input.email);
        entry.put("password", input.password);

        Log.d("vt", "this is user information" + entry.toString());
        Writer wr = new OutputStreamWriter(conn.getOutputStream());

        wr.write(entry.toString());
        wr.flush();
        wr.close();
        Log.d("vt", String.valueOf(conn.getResponseCode()));
        is = conn.getResponseCode() >= 400 ? conn.getErrorStream() : conn.getInputStream();
        String resp = readIt(is,2);
        Log.d("vt","http response: " + resp);
        if (conn.getResponseCode() >= 400 || resp.equals("nu")) {
            return false;

        } else {
            return true;

        }

    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    private class Userlogin{
        public String email,password;

        private Userlogin(String email, String password){
            this.email = email;
            this.password = password;
        }
    }


}
