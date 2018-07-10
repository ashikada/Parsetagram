package me.ashikada.parsetagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import me.ashikada.parsetagram.model.Post;

public class ParseApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        final Parse.Configuration configuration= new Parse.Configuration.Builder(this)
                .applicationId("parsetagram")
                .clientKey("Illini20")
                .server("http://ashikada-parsetagram.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }
}
