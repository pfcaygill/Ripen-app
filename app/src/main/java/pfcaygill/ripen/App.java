package pfcaygill.ripen;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by PFCaygill on 12/09/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}
