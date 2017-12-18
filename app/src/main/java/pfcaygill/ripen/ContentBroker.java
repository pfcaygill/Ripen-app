package pfcaygill.ripen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.util.ArraySet;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by welly on 7/11/2017.
 */

public class ContentBroker {

    private Context context;
    private SharedPreferences sharedPref;


    public ContentBroker(Context targetContext){
        this.context=targetContext;
        sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        );
    }
    /*
    Methods for reading and loading data to shared preferences
    */
    //Helper method keeps access asynchronous
    private Set<String> getFruitKeySet(){
        return sharedPref.getStringSet(
                context.getString(R.string.preference_fruit_list),
                new ArraySet<String>()
        );
    }
    //
    public void clearFruit(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }

    //Finds the Fruit content
    @Nullable
    public List<FruitClass> loadFruitKeysFromMemory(){
        Set<String> fruit_key_set = getFruitKeySet();
        if (!fruit_key_set.isEmpty()){
            String[] fruit_key_list = fruit_key_set.toArray(new String[0]);
            //process each fruit by accessing its key
            //the key gives us a set of strings to be decoded into a constructer
            ArrayList<FruitClass> fruit_from_shared_pref = new ArrayList<FruitClass>();
            for (String key:fruit_key_list) {
                fruit_from_shared_pref.add(
                        new FruitClass(sharedPref.getString(key,null))
                );
            }
            return fruit_from_shared_pref;
        }else{
            return new ArrayList<FruitClass>();
        }
    }
    public Set<String> saveNewFruit(FruitClass newFruit){
        //TODO: Safety check name collisions of the title field
        SharedPreferences.Editor editor = sharedPref.edit();
        Set<String> fruit_key_set = getFruitKeySet();
        if(fruit_key_set.contains(newFruit.getTitle())){
            //ERROR CASE "Collision"
        }else{
            fruit_key_set.add(newFruit.getTitle());
            editor.putStringSet(
                    context.getString(R.string.preference_fruit_list),
                    fruit_key_set
            );//Add the new fruit to the keyset
            editor.putString(
                    newFruit.getTitle(),
                    newFruit.toString()
            );//add the new fruit to the dictionary
            editor.commit();

        }return fruit_key_set;
    }
}
