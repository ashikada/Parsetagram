package me.ashikada.parsetagram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import me.ashikada.parsetagram.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> mPosts;
    Context context;

    //pass in the Post array in the constructor
    public PostAdapter(List<Post> posts){
        mPosts = posts;
    }


    //for each row, inflate the layout and pass into viewHolder class
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    //bind the values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get the data according to this position
        Post post = mPosts.get(position);

        //populate the views according to this (user name and body, then image)
        holder.tvUsername.setText(post.getUser().getUsername());
        holder.tvDesc.setText(post.getDescription());
        holder.tvTimestamp.setText(post.getCreatedAt().toString());

        Glide.with(context).load(post.getImage().getUrl()).into(holder.ivPost);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    //create ViewHolder class

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivPost;
        public TextView tvUsername;
        public TextView tvDesc;
        public TextView tvTimestamp;

        public ViewHolder(View itemView){
            super(itemView);

            //perform findViewBtId lookups

            ivPost = (ImageView) itemView.findViewById(R.id.image_post_iv);
            tvUsername = (TextView) itemView.findViewById(R.id.username_tv);
            tvDesc = (TextView) itemView.findViewById(R.id.description_tv);
            tvTimestamp = (TextView) itemView.findViewById(R.id.timestamp_tv);


        }
    }

    // Clean all elements of the recycler
    public void clear() {
        mPosts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        mPosts.addAll(list);
        notifyDataSetChanged();
    }
}
