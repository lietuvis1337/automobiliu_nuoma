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

        Button login_kintamasis = (Button) findViewById(R.id.login_submit);
        Button register_kintamasis = (Button) findViewById(R.id.login_register);

        final EditText loginUsername = (EditText) findViewById(R.id.login_username);
        final EditText loginPassword = (EditText) findViewById(R.id.login_password);

        login_kintamasis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            // vykdomas kodas kai paspaudziamas mygtukas
                if (Validation.isValid(loginUsername.getText().toString()) && Validation.isValid(loginPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Prisijungimo vardas: " + loginUsername.getText().toString() + "\n Slaptažodis: " + loginPassword.getText().toString(), Toast.LENGTH_LONG).show();

                    Intent pereitiKitur = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(pereitiKitur);
                } else {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                }
            }

        });
        register_kintamasis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent pereitiKitur = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(pereitiKitur);
            }
        });
    }
}
