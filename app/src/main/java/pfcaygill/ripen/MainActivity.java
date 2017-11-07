package pfcaygill.ripen;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private SwipeRefreshLayout swipeContainer;
    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvFruit = (RecyclerView) findViewById(R.id.rvFruit);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        //using a shared preference broker load the content that is needed to load
        ContentBroker broker = new ContentBroker(this);//this is a context
         //build the adapter & attach the adapter to the recyclerview to populate the fruit
        adapter = new FruitAdapter(this, broker);
        rvFruit.setAdapter(adapter);

        // Set layout manager to position the items
        rvFruit.setLayoutManager(new LinearLayoutManager(this));
        //initialise dividers to the fruit
        DividerItemDecoration customDivider = new DividerItemDecoration(
                rvFruit.getContext(),DividerItemDecoration.VERTICAL);
        //attach the drawable to the divider
        rvFruit.addItemDecoration(customDivider);

        //create a custom swipe refresh listener
        swipeContainer.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener(){
                    @Override
                    public void onRefresh(){
                        //the refresh functionality links out of here
                        refreshItems();
                    }
                }
        );
    }
    @Override
    public void onResume(){
        super.onResume();
        //refresh the elements
        refreshItems();
    }

    void refreshItems(){
        adapter.refresh();
        swipeContainer.setRefreshing(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_fruit:
               /* Toast create_toast = Toast.makeText(
                        getApplicationContext(),//Context
                        getApplicationContext().getString(R.string.create_fruit_toast),//text to be shown
                        Toast.LENGTH_SHORT//duration
                );
                create_toast.show();*/
               
                return true;
            case R.id.delete_fruit:
                Toast delete_toast = Toast.makeText(
                        getApplicationContext(),//Context
                        getApplicationContext().getString(R.string.delete_fruit_toast),//text to be shown
                        Toast.LENGTH_SHORT//duration
                );
                delete_toast.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
