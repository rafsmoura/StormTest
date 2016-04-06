package br.com.stormtest.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.stormtest.R;
import br.com.stormtest.activities.ContentDetail;
import br.com.stormtest.models.Content;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by root on 03/04/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private View view;

    private Context context;

    private List<Content> contents;

    public RecyclerViewAdapter(Context context, List<Content> contents) {
        this.context = context;
        this.contents = contents;
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_content, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Content content = contents.get(position);

        Picasso.with(context)
                .load(content.getUser().getUserProfilePhoto())
                .fit()
                .into(holder.userProfileImage);

        holder.userName.setText(content.getUser().getUserName());


        Picasso.with(context)
                .load(content.getShelfImage())
                .fit()
                .into(holder.contentImage);

        holder.contentTitle.setText(content.getContentTitle());

        holder.shortDescription.setText(content.getShortDescription());

        holder.viewCount.setText(Integer.toString(content.getViewCount()));

        holder.favoriteCount.setText(Integer.toString(content.getLikes()));

        holder.view.setOnClickListener((View v) -> {

                Intent i = new Intent(context, ContentDetail.class);
                i.putExtra("Content", content);
                context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView userProfileImage;
        private ImageView contentImage;
        private TextView userName, contentTitle, shortDescription, viewCount, favoriteCount;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            userProfileImage = (CircleImageView) itemView.findViewById(R.id.profileImage);
            contentImage = (ImageView) itemView.findViewById(R.id.contentImage);

            userName = (TextView) itemView.findViewById(R.id.userName);
            contentTitle = (TextView) itemView.findViewById(R.id.contentTitle);
            shortDescription = (TextView) itemView.findViewById(R.id.contentShotdescription);
            viewCount = (TextView) itemView.findViewById(R.id.contentViewCount);
            favoriteCount = (TextView) itemView.findViewById(R.id.contentFavoriteCount);
        }
    }

    @Override
    public long getItemId(int position) {
        return contents.get(position).hashCode();
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
