package pfcaygill.ripen;

import org.joda.time.DateTime;

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

    //public static ArrayList<DataClass> createFruitList()
}
