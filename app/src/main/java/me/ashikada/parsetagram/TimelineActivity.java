package me.ashikada.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.ashikada.parsetagram.model.Post;

public class TimelineActivity extends AppCompatActivity {

    PostAdapter postAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //instantiate
        posts = new ArrayList<>();
        postAdapter = new PostAdapter(posts);
        rvPosts = (RecyclerView) findViewById(R.id.rvPosts);

        //RecyclerView setup (layout manager, use adapter)
        rvPosts.setLayoutManager(new LinearLayoutManager(this));

        //set the adapter
        rvPosts.setAdapter(postAdapter);


        loadTopPosts();
    }

    private void loadTopPosts(){
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getTop().withUser();


        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if(e == null){

                    posts.addAll(objects);
                    Collections.reverse(posts);
                    postAdapter.notifyDataSetChanged();

                }
                else {
                    e.printStackTrace();
                }
            }
        });
    }

    public void goToCapture(View view){
        Intent i = new Intent(TimelineActivity.this, PostActivity.class);
        startActivity(i);
    }


}
