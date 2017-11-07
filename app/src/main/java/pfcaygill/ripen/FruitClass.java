package pfcaygill.ripen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.ArraySet;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by PFCaygill on 12/09/2017.
 * Data class stores the information for the individual components in the main recycler view
 */

public class FruitClass {

    private String Title;
    private DateTime LastPicked;
    private Duration Interval;
    private int Progress;

    public FruitClass(String DataTitle,DateTime DataLast,Duration DataInterval){
        Title=DataTitle;
        LastPicked=DataLast;
        Interval= DataInterval;
        updateProgress();
    }

    public Duration getInterval() {
        return Interval;
    }

    public String getTitle() {
        return Title;
    }

    public DateTime getLastPicked() {
        return LastPicked;
    }

    public int getProgress(){return Progress;}
    //Sets the current progress, also used for initialising
    public void updateProgress(){
        this.Progress = calculateProgress(this.LastPicked,this.Interval);
    }
    //static method to calculate the progress of ripening
    public static int calculateProgress(DateTime lastPicked,Duration interval){
        DateTime current = DateTime.now();// needed for manual calculation
        Duration timeRipening = new Duration(lastPicked,current);
        if( timeRipening.isLongerThan(interval)){
            return 100;
        }else{
            Double ratio =//using double fixes the forced use of longs
                    (10.0*timeRipening.getStandardHours())/
                            (10.0*interval.getStandardHours());
            return (int)(100*ratio);
        }
    }
    /**
     * Methods for parseing to and from string.
     * */
    public FruitClass(String fromString){
        String[] data =  fromString.split(";");
        Title = data[0];
        LastPicked= DateTime.parse(data[1]);
        Interval= Duration.parse(data[2]);
        updateProgress();
    }
    public String toString(){
        return Title +";"+
        LastPicked.toString() +";"+
        Interval.toString();
    }

    /**
     * Old method used to provide test data to the recycler view.
     * */
    @Deprecated
    public static List<FruitClass> loadFruitElements(){
        ArrayList<FruitClass> fruitFromMemory = new ArrayList<FruitClass>();
        //Duration should be one standard day
        Duration testDuration = Duration.standardDays(1);

        fruitFromMemory.add(new FruitClass(
                "Test_Fruit",
                DateTime.now().minusHours(3),
                testDuration));
        fruitFromMemory.add(new FruitClass(
                "Test_Fruit_2_Electric_Bugaloo",
                DateTime.now().minusHours(12),
                testDuration));
        //TODO: load non test content/ better test content

        return fruitFromMemory;
    }
}
