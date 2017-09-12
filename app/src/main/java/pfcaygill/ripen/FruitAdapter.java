package pfcaygill.ripen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by Paul-sicle on 12/09/2017.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public ProgressBar fruitProgress;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.Fruit_title);
            fruitProgress = (ProgressBar) itemView.findViewById(R.id.Fruit_progress);
        }
    }

    private List<FruitClass> listFruits;

    private Context wrappingContext;

    public FruitAdapter(Context context, List<FruitClass> fruit) {
        wrappingContext = context;
        listFruits = fruit;
    }

    private Context getContext() {
        return wrappingContext;
    }

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
        // Set item views based on your views and data model
        textView.setText(fruit.getTitle());
        // Calculate
        //For boolean use:
        //IsRipe = fruit.getLastPicked().plus(fruit.getInterval()).isBeforeNow();
        DateTime current = DateTime.now();// needed for manual calculation
        DateTime lastPicked = fruit.getLastPicked();
        Long timeRipening = current.getMillis()-lastPicked.getMillis();
        int ripeness =(int) (timeRipening/fruit.getInterval());
        progressBar.setProgress(ripeness);
    }

    @Override
    public int getItemCount() {
        return listFruits.size();
    }
}
