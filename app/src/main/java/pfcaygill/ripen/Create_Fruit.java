package pfcaygill.ripen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.Set;

public class Create_Fruit extends AppCompatActivity {
    private ContentBroker broker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__fruit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.create_fruit);

        broker = new ContentBroker(this);

        Spinner durationSpinner = (Spinner) findViewById(R.id.durationSpinner);
        //Create an adapter for the spinner
        ArrayAdapter<CharSequence> durationAdapter = ArrayAdapter
                .createFromResource(
                  this, R.array.dropdown_duration_selector,
                        android.R.layout.simple_spinner_item
                );
        durationSpinner.setAdapter(durationAdapter);

        Button submitButton = (Button) findViewById(R.id.create_fruit_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fruitName = (EditText)findViewById(R.id.nameTextField);
                EditText durationUnits = (EditText)findViewById(R.id.durationUnits);
                Spinner durationSpinner = (Spinner) findViewById(R.id.durationSpinner);

                String durationAsString = durationUnits.getText().toString().replaceAll("[\\D]","");
                String nameAsString = fruitName.getText().toString();

                Period jodaValue=calculateJodaValue(
                        durationSpinner.getSelectedItem().toString(),
                        Integer.parseInt(durationAsString)
                );
                saveFruitUsingBroker(nameAsString,jodaValue);
            }
        });
    }
    protected void saveFruitUsingBroker(String fruitName, Period jodaValue){
        Set<String> keys = broker.saveNewFruit(
                new FruitClass(
                        fruitName,
                        DateTime.now(),
                        jodaValue
                )
        );
    }

    /**
     * Static method used for testing and external access
     * */

    static protected Period calculateJodaValue(
            String durationSpinner ,
            int durationUnits){

        Period result;

        switch (durationSpinner){
            case "Days":
                result = Period.days(durationUnits);break;
            case "Weeks":
                result = Period.weeks(durationUnits);break;
            case "Months":
                result = Period.months(durationUnits);break;
            case "Years":
                result = Period.years(durationUnits);break;
            default://defaults to hours as a unit
                result = Period.hours(durationUnits);break;
        }

        return result;
    }

}
