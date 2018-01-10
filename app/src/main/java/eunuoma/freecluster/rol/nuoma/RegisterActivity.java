package eunuoma.freecluster.rol.nuoma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button submit_kintamasis = (Button) findViewById(R.id.register_submit);

        final EditText registerUsername = (EditText) findViewById(R.id.register_username);
        final EditText registerEmail = (EditText) findViewById(R.id.register_email);
        final EditText registerPassword = (EditText) findViewById(R.id.register_password);

        submit_kintamasis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // vykdomas kodas kai paspaudziamas mygtukas
                if (Validation.isValid(registerUsername.getText().toString())) {
                    if (Validation.isValidEmail(registerEmail.getText().toString())) {
                        if (Validation.isValid(registerPassword.getText().toString())) {
                            Intent pereitiKitur = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(pereitiKitur);
                            Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_successful), Toast.LENGTH_LONG).show();
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
}
