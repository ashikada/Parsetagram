package me.ashikada.parsetagram;

import android.app.Application;
import com.parse.Parse;

public class ParseApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        final Parse.Configuration configuration= new Parse.Configuration.Builder(this)
                .applicationId("parsetagram")
                .clientKey("Illini20")
                .server("http://ashikada-parsetagram.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }
}
