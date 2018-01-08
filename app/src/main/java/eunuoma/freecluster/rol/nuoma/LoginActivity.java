package eunuoma.freecluster.rol.nuoma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button next_kintamasis = (Button) findViewById(R.id.login_submit);

        final EditText loginUsername = (EditText) findViewById(R.id.login_username);
        final EditText loginPassword = (EditText) findViewById(R.id.login_password);

        next_kintamasis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // vykdomas kodas kai paspaudziamas mygtukas

                Toast.makeText(LoginActivity.this,"Prisijungimo vardas: " + loginUsername.getText().toString() + "\n Slaptažodis: " + loginPassword.getText().toString(),Toast.LENGTH_LONG).show();

                Intent pereitiKitur = new Intent(LoginActivity.this, SearchActivity.class);
                startActivity(pereitiKitur);
            }

        });
    }
}
