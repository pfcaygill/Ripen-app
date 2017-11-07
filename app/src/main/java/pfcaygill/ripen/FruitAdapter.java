package pfcaygill.ripen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul-sicle on 12/09/2017.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<FruitClass> listFruits;
    private Context wrappingContext;
    private ContentBroker broker;
    /**
     * Constructors for different loading methods
     * */
    public FruitAdapter(Context context, ContentBroker instanceBroker){
        wrappingContext = context;
        this.broker = instanceBroker;
        listFruits = (ArrayList<FruitClass>)broker.loadFruitKeysFromMemory();
    }

    private Context getContext() {
        return wrappingContext;
    }

    /**
     * Modify Data Set Commands
     * Adds contents of list to the list, optionally clearing
     * */
    public void addAll(List<FruitClass> list) {
        listFruits.addAll(list);
        notifyDataSetChanged();
    }
    public void addAll(List<FruitClass> list, boolean isReplacementList){
        if(isReplacementList)
            listFruits.clear();
        listFruits.addAll(list);
        notifyDataSetChanged();
    }
    /**
     * Reset each of the fruit durations
     * */
    public void refresh(){
        for (int i=0;i<listFruits.size();i++){
            listFruits.get(i).updateProgress();
        }
        notifyDataSetChanged();
    }
    /**
     * Methods overriden from default behaviour
     * */
    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_fruit, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FruitAdapter.ViewHolder viewHolder, int position) {
        FruitClass fruit = listFruits.get(position);
        // Get item views
        TextView textView = viewHolder.nameTextView;
        ProgressBar progressBar=viewHolder.fruitProgress;
        TextView information = viewHolder.infoTextView;
        // Set item views based on your views and data model
        textView.setText(fruit.getTitle());
        // the progress has been initialised, and can be updated
        int ripeness = fruit.getProgress();
        progressBar.setProgress(ripeness);
        String progressText = "Ripeness: " + ripeness + "%";
        information.setText(progressText);
    }

    @Override
    public int getItemCount() {
        return listFruits.size();
    }
    /**
     * Assisting class for the viewholder, for each individual Fruit
     * */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public ProgressBar fruitProgress;
        public TextView infoTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.Fruit_title);
            fruitProgress = (ProgressBar) itemView.findViewById(R.id.Fruit_progress);
            infoTextView = (TextView) itemView.findViewById(R.id.Fruit_progText);
        }
    }
}
