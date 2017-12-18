package pfcaygill.ripen;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PFCaygill on 12/09/2017.
 * Data class stores the information for the individual components in the main recycler view
 */

public class FruitClass {

    private String Title;
    private DateTime LastPicked;
    private Period Interval;
    private int Progress;

    public FruitClass(String DataTitle, DateTime DataLast, Period DataInterval){
        Title=DataTitle;
        LastPicked=DataLast;
        Interval= DataInterval;
        updateProgress();
    }

    public Period getInterval() {
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
    public static int calculateProgress(DateTime lastPicked, Period interval){
        DateTime current = DateTime.now();// needed for manual calculation
        Duration timeRipening = new Duration(lastPicked,current);
        if( timeRipening.isLongerThan(interval.toStandardDuration())){
            return 100;
        }else{
            Double ratio =//using double fixes the forced use of longs
                    (10.0*timeRipening.getStandardHours())/
                            (10.0*interval.getHours());
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
        Interval= Period.parse(data[2]);
        updateProgress();
    }
    @Override
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
        Period testDuration = new Period(0,0,0,1,0,0,0,0);

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
