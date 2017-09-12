package pfcaygill.ripen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<FruitClass> fruitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvFruit = (RecyclerView) findViewById(R.id.rvFruit);

        fruitList = new ArrayList<FruitClass>();
        //TODO: load non test content/ better test content
        Duration testDuration = new Duration(
                new DateTime(2004,12,25,0,0,0,0),
                new DateTime(2004,12,26,0,0,0,0)
        );//this should be one day, is there a better way to do this?
        fruitList.add(new FruitClass("Test_Fruit", DateTime.now(),testDuration.getStandardHours()));
        //build the adapter
        FruitAdapter adapter = new FruitAdapter(this, fruitList);
        //attach the adapter to the recyclerview to populate the fruit
        rvFruit.setAdapter(adapter);
        // Set layout manager to position the items
        rvFruit.setLayoutManager(new LinearLayoutManager(this));
    }
}
