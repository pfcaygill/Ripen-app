package pfcaygill.ripen;

import android.content.Context;
import android.content.SharedPreferences;
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

/**
 * Created by PFCaygill on 12/09/2017.
 * Data class stores the information for the individual components in the main recycler view
 */

public class FruitClass {

    private String Title;
    private DateTime LastPicked;
    private Duration Interval;

    public FruitClass(String DataTitle,DateTime DataLast,Duration DataInterval){
        Title=DataTitle;
        LastPicked=DataLast;
        Interval= DataInterval;
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

    public static List<FruitClass> loadFruitElements(){
        ArrayList<FruitClass> fruitFromMemory = new ArrayList<FruitClass>();

        Duration testDuration = new Duration(
                new DateTime(2004,12,25,0,0,0,0),
                new DateTime(2004,12,26,0,0,0,0)
        );//this should be one day, is there a better way to do this?
        fruitFromMemory.add(new FruitClass(
                "Test_Fruit",
                new DateTime(2017,9,29,12,0,0,0),
                testDuration));
        //TODO: load non test content/ better test content

        return fruitFromMemory;
    }

}
