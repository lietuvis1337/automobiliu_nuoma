package eunuoma.freecluster.rol.nuoma;

import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class LoginActivity extends AppCompatActivity {

    private Button login_button, register_button;
    private EditText login_text, password_text;

    private CheckBox rememberMeCheckBox;

    private static final String REGISTER_URL = "http://rol.freecluster.eu/mobile/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //kreipimasis i paveldimos klases metoda
        setContentView(R.layout.activity_login); //susiejame xml su kodu

        RegisterButton();
        Authentication();
    }


    public void RegisterButton() {
        register_button = (Button) findViewById(R.id.login_register);
        register_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                LoginActivity.this.startActivity(myIntent);

            }

        });
    }

    public void Authentication() {

        login_button = (Button)findViewById(R.id.login_submit);
        login_text = (EditText)findViewById(R.id.login_username);
        password_text = (EditText)findViewById(R.id.login_password);
        rememberMeCheckBox = (CheckBox) findViewById(R.id.remember_me);

        final User dude = new User(getApplicationContext());

        rememberMeCheckBox.setChecked(dude.isRemembered());

        if (dude.isRemembered()) {
            login_text.setText(dude.getUsernameForLogin(), TextView.BufferType.EDITABLE);
            password_text.setText(dude.getPasswordForLogin(), TextView.BufferType.EDITABLE);
        } else {
            login_text.setText("", TextView.BufferType.EDITABLE);
            password_text.setText("", TextView.BufferType.EDITABLE);
        }

        login_text.setError(null);
        password_text.setError(null);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // vykdomas kodas kai paspaudziamas mygtukas
                if (Validation.isValid(login_text.getText().toString()) && Validation.isValid(password_text.getText().toString())) {
                    dude.setUsernameForLogin(login_text.getText().toString());
                    dude.setPasswordForLogin(password_text.getText().toString());
                    if (rememberMeCheckBox.isChecked()){
                        dude.setRemembered(true);
                    } else {
                        dude.setRemembered(false);
                    }
                    postToDatabase(dude);

                } else {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean IsValid(String credentials) {
        final String CREDENTIALS_PATTERN = "^([0-9a-zA-Z]{3,15})+$";
        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher (credentials);
        return matcher.matches();
    }

    private void postToDatabase (final User dude){
        class LoginToDB extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            DB database = new DB();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginActivity.this, "Prašome palaukti",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                if (s.equals("202")){
                    Intent pereitiKitur = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(pereitiKitur);

                } else{
                    Toast.makeText(LoginActivity.this,"Neteisingas vartotojo vardas arba slaptažodis",Toast.LENGTH_LONG).show();
                }
                super.onPostExecute(s);
                loading.dismiss();
            }

            @Override
            protected String doInBackground(String... params) {
                // Pirmas string raktas, antras string reiksme
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("username",params[0]);
                data.put("password",params[1]);

                String result = database.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        LoginToDB loginToDB = new LoginToDB();
        /*loginToDB.execute(type,String.valueOf(order),gender,String.valueOf(price),color,String.valueOf(length),
                String.valueOf(width),delivery);*/
        loginToDB.execute(dude.getUsernameForLogin(), dude.getPasswordForLogin());

    }
}