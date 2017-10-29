package pfcaygill.ripen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

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

        fruitList = (ArrayList<FruitClass>) FruitClass.loadFruitElements();

        //build the adapter
        FruitAdapter adapter = new FruitAdapter(this, fruitList);
        //attach the adapter to the recyclerview to populate the fruit
        rvFruit.setAdapter(adapter);
        // Set layout manager to position the items
        rvFruit.setLayoutManager(new LinearLayoutManager(this));
        //add dividers to the fruit
        rvFruit.addItemDecoration(new DividerItemDecoration(rvFruit.getContext(),
                DividerItemDecoration.HORIZONTAL));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu,menu);
        return true;
    }
}
