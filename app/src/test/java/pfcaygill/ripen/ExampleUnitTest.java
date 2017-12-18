package pfcaygill.ripen;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    private final String[] units = new String[]{
            "Hours",
            "Days",
            "Weeks",
            "Months",
            "Years",
    };
    public void testDurationCalculation(
            String unit,
            int length,
            String[] slist,
            Duration dur
    ){
        assertEquals(
                dur.toString(),
                Create_Fruit.calculateJodaValue(
                        unit,length,slist
                ).toString()
        );
    }
    @Test
    public void jodaValueHour(){
        DateTime start = new DateTime(2000,12,25,11,0);
        DateTime end = new DateTime(2000,12,25,12,0);
        testDurationCalculation(
                "Hours",
                1,
                units,
                new Duration(start,end)
        );
    }

}