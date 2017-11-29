package pfcaygill.ripen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Create_Fruit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__fruit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.create_fruit);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner durationSpinner = (Spinner) findViewById(R.id.durationSpinner);
        //Create an adapter for the spinner
        ArrayAdapter<CharSequence> durationAdapter = ArrayAdapter
                .createFromResource(
                  this, R.array.dropdown_duration_selector,
                        android.R.layout.simple_spinner_item
                );
        durationSpinner.setAdapter(durationAdapter);
    }

}
