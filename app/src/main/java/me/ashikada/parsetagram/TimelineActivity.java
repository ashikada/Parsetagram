package me.ashikada.parsetagram;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import me.ashikada.parsetagram.model.Post;

public class TimelineActivity extends AppCompatActivity {

    PostAdapter postAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPosts;
    private Button logOutBtn;
    private SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //instantiate
        posts = new ArrayList<>();
        postAdapter = new PostAdapter(posts);
        rvPosts = (RecyclerView) findViewById(R.id.rvPosts);
        logOutBtn = findViewById(R.id.logout_btn);

        //RecyclerView setup (layout manager, use adapter)
        rvPosts.setLayoutManager(new LinearLayoutManager(this));

        //set the adapter
        rvPosts.setAdapter(postAdapter);
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                loadTopPosts();
                rvPosts.scrollToPosition(0);
            }
        });

        logOutBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(TimelineActivity.this)
                        .setMessage("Do you want to log out?")
                        .setTitle("Log Out?")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ParseUser currentUser = ParseUser.getCurrentUser();
                                currentUser.logOut();
                                Intent intent = new Intent(TimelineActivity.this, MainActivity.class);
                                startActivity(intent);

                                //close the dialog
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        });


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
                    postAdapter.notifyDataSetChanged();

                    swipeContainer.setRefreshing(false);

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
