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

    public static ArrayList<FruitClass> createFruitList(Context context){

        ArrayList<FruitClass> fruitList = new ArrayList<FruitClass>();//initialise fruitlist
        try{
            String fruitFile = readFruitIn(context);
            JSONArray fileFruitList= new JSONArray(fruitFile);
            for(int i = 0;i<fileFruitList.length();i++){
                JSONObject fruit = fileFruitList.getJSONObject(i);
                String Title = fruit.getString("title");
                DateTime picked = DateTime.parse(fruit.getString("picked"));
                Long interval = fruit.getLong("interval");
                fruitList.add(new FruitClass(Title,picked,interval));
            }
        }catch(JSONException e){
            Toast.makeText(context,
                    "Couldn't load Fruit, a JSON parseing error has occured",
                    Toast.LENGTH_LONG);
        }catch(IOException e){
            Toast.makeText(context,
                    "Couldn't load Fruit, there was an error reading the FruitFile",
                    Toast.LENGTH_LONG);
        }
        return fruitList;
    }
    private static String readFruitIn(Context context) throws IOException, JSONException{
        File file = new File(context.getFilesDir(), FILE_FRUIT);//open file
        BufferedReader input = new BufferedReader(new FileReader(file));
        //JSONObject jsonFromFile ;
        String content = "";
        String currentLine = input.readLine();
        while(currentLine!=null){
            content+=currentLine;
            currentLine = input.readLine();
        }
        return content;
    }
}
