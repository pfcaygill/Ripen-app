package pfcaygill.ripen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul-sicle on 12/09/2017.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
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
        TextView information = viewHolder.infoTextView;
        // Set item views based on your views and data model
        textView.setText(fruit.getTitle());
        // Calculate
        //For boolean use:
        //IsRipe = fruit.getLastPicked().plus(fruit.getInterval()).isBeforeNow();
        int ripeness = calculateProgress(fruit.getLastPicked(),fruit.getInterval());
        progressBar.setProgress(ripeness);
        String progressText = "Ripeness: " + ripeness;
        information.setText(progressText);
    }

    @Override
    public int getItemCount() {
        return listFruits.size();
    }
    private int calculateProgress(DateTime lastPicked,Duration interval){
        DateTime current = DateTime.now();// needed for manual calculation
        Duration timeRipening = new Duration(lastPicked,current);
        if( timeRipening.isLongerThan(interval)){
            return 10;
        }else{
            Long ratio =
                    interval.getStandardMinutes()/timeRipening.getStandardMinutes();
            return (int)(100*ratio);
        }
    }
}
