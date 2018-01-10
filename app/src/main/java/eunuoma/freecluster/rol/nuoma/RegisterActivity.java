package eunuoma.freecluster.rol.nuoma;

import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {

    // kelias byethost iki register.php failiuko
    private static final String REGISTER_URL = "http://rol.freecluster.eu/mobile/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button submit_kintamasis = (Button) findViewById(R.id.register_submit);

        final EditText registerUsername = (EditText) findViewById(R.id.register_username);
        final EditText registerPassword = (EditText) findViewById(R.id.register_password);
        final EditText registerEmail = (EditText) findViewById(R.id.register_email);

        submit_kintamasis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // vykdomas kodas kai paspaudziamas mygtukas
                if (Validation.isValid(registerUsername.getText().toString())) {
                    if (Validation.isValidEmail(registerEmail.getText().toString())) {
                        if (Validation.isValid(registerPassword.getText().toString())) {
                            registerUser(registerUsername.getText().toString(), registerPassword.getText().toString(), registerEmail.getText().toString());
                            Intent pereitiKitur = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(pereitiKitur);
                            // Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_successful), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_password_error), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_email_error), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_username_error), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerUser(String username, String password, String email) {
        String urlSuffix = "?username="+username+"&password="+password+"&email="+email;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterActivity.this, "Prašome palaukti",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    // byethost naudoja antibot sistema, todel reikia kiekvienam rankutėmis suvesti cookie turinį,
                    // kuris pas kiekviena bus skirtingas. kaip tai padaryti zemiau nuoroda
                    // http://stackoverflow.com/questions/31912000/byethost-server-passing-html-values-checking-your-browser-with-json-string
                    con.setRequestProperty("Cookie", "__test=7a4d917e220fbf9a55cab3046bd1a3b7; expires=2038 m. sausio 1 d., penktadienis 01:55:55; path=/");
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }
}
