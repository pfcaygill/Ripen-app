package pfcaygill.ripen;

import android.content.Context;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by PFCaygill on 12/09/2017.
 * Data class stores the information for the individual components in the main recycler view
 */

public class FruitClass {

    private String Title;
    private DateTime LastPicked;
    private Long Interval;

    public FruitClass(String DataTitle,DateTime DataLast,Long DataInterval){
        Title=DataTitle;
        LastPicked=DataLast;
        Interval= DataInterval;
    }

    public Long getInterval() {
        return Interval;
    }

    public String getTitle() {
        return Title;
    }

    public DateTime getLastPicked() {
        return LastPicked;
    }

}
