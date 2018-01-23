package eunuoma.freecluster.rol.nuoma;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddEntryActivity extends AppCompatActivity {

    // kelias byethost iki register.php failiuko
    private static final String ADD_ENTRY_URL = "http://rol.freecluster.eu/mobile/addentry.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        Button submit_kintamasis = (Button) findViewById(R.id.entry_submit);

        final EditText entry_Vardas = (EditText) findViewById(R.id.entry_vardas);
        final EditText entry_Pavarde = (EditText) findViewById(R.id.entry_pavarde);
        final EditText entry_AsmensKodas = (EditText) findViewById(R.id.entry_asmenskodas);

        final CheckBox paslauga1 = (CheckBox) findViewById(R.id.paslauga1);
        final CheckBox paslauga2 = (CheckBox) findViewById(R.id.paslauga2);
        final CheckBox paslauga3 = (CheckBox) findViewById(R.id.paslauga3);

        final RadioGroup automobiliai = (RadioGroup) findViewById(R.id.automobiliai);
        final RadioButton[] automobilis = new RadioButton[1];

        final Spinner tipas = (Spinner) findViewById(R.id.tipas);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddEntryActivity.this,
                R.array.tipas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tipas.setAdapter(adapter);

        submit_kintamasis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // vykdomas kodas kai paspaudziamas mygtukas
                if (Validation.isValid(entry_Vardas.getText().toString())) {
                    if (Validation.isValid(entry_Pavarde.getText().toString())) {
                        if (Validation.isValid(entry_AsmensKodas.getText().toString())) {

                            int selectedId = automobiliai.getCheckedRadioButtonId();
                            automobilis[0] = (RadioButton) findViewById(selectedId);

                            StringBuffer paslaugos = new StringBuffer();
                            if (paslauga1.isChecked()) {
                                paslaugos.append("pilnas bakas kuro").append(", ");
                            }
                            if (paslauga2.isChecked()) {
                                paslaugos.append("švarus automobilis").append(", ");
                            }
                            if (paslauga3.isChecked()) {
                                paslaugos.append("automobilinis šaldytuvas").append(", ");
                            }
                            User user = new User(AddEntryActivity.this);
                            addEntry(entry_Vardas.getText().toString(), entry_Pavarde.getText().toString(), entry_AsmensKodas.getText().toString(), automobilis[0].getText().toString(), tipas.getSelectedItem().toString(), paslaugos.toString(), user.getUsernameForLogin());
                            Intent pereitiKitur = new Intent(AddEntryActivity.this, SearchActivity.class);
                            startActivity(pereitiKitur);

                        } else {
                            Toast.makeText(AddEntryActivity.this, getResources().getString(R.string.add_entry_error), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddEntryActivity.this, getResources().getString(R.string.add_entry_error), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddEntryActivity.this, getResources().getString(R.string.add_entry_error), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addEntry(String vardas, String pavarde, String asmenskodas, String automobilis, String tipas, String paslaugos, String vartotojas) {
        String urlSuffix = "?vardas="+vardas+"&pavarde="+pavarde+"&asmenskodas="+asmenskodas+"&automobilis="+automobilis+"&tipas="+tipas+"&paslaugos="+paslaugos+"&vartotojas="+vartotojas;
        class addEntry extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddEntryActivity.this, "Prašome palaukti",null, true, true);
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
                    URL url = new URL(ADD_ENTRY_URL+s);
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

        addEntry ae = new addEntry();
        ae.execute(urlSuffix);
    }
}