package eunuoma.freecluster.rol.nuoma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button next_kintamasis = (Button) findViewById(R.id.pereiti);

        next_kintamasis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // vykdomas kodas kai paspaudziamas mygtukas

                Intent pereitiKitur = new Intent(SearchActivity.this, AddEntryActivity.class);
                startActivity(pereitiKitur);
            }

        });
    }
}