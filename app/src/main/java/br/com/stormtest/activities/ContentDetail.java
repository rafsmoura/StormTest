package br.com.stormtest.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.stormtest.R;
import br.com.stormtest.cache.CacheManager;
import br.com.stormtest.models.Content;
import br.com.stormtest.models.Tag;

public class ContentDetail extends AppCompatActivity {

    private Content content;
    private ImageView shelfImage;
    private TextView contentTitle, contentDescription, tagHeader, tagHolder, relatedHeader;
    private LinearLayout horizontalScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (toolbar != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);


        }

        if (getIntent() != null) {

            content = getIntent().getParcelableExtra("Content");

        }

        setTitle("");

        shelfImage = (ImageView) findViewById(R.id.shelfImage);
        contentTitle = (TextView) findViewById(R.id.contentTitle);
        contentDescription = (TextView) findViewById(R.id.descriptionText);
        tagHeader = (TextView) findViewById(R.id.tagHeader);
        tagHolder = (TextView) findViewById(R.id.tagHolder);
        horizontalScroll = (LinearLayout) findViewById(R.id.relatedContentScroll);
        relatedHeader = (TextView) findViewById(R.id.relatedVideosHeader);


        contentTitle.setText(content.getContentTitle());
        contentDescription.setText(content.getDescription());

        StringBuffer buffer = new StringBuffer();
        for (Tag tag : content.getTags()) {

            buffer.append("#" + tag.getTagName() + " ");

        }

        if (buffer.length() > 0) {
            tagHolder.setText(buffer.toString());
        } else {
            tagHeader.setVisibility(View.GONE);
            tagHolder.setVisibility(View.GONE);
        }


        Picasso.with(this)
                .load(content.getShelfImage())
                .fit()
                .into(shelfImage);

        if (content.getRelatedVideos() != null && content.getRelatedVideos().size() > 0) {

            for (Object related : content.getRelatedVideos()) {

                View card = LayoutInflater.from(this).inflate(R.layout.related_content, null);

                horizontalScroll.addView(card);
            }
        } else {
            relatedHeader.setVisibility(View.GONE);
            horizontalScroll.setVisibility(View.GONE);
        }


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {

            if (CacheManager.getInstance().isFavorite(getApplicationContext(), content)) {
                fab.setImageResource(R.drawable.ic_favorite_white_24dp);
            } else {
                fab.setImageResource(R.drawable.ic_favorite_border_white_24dp);
            }

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isFavorite = CacheManager.getInstance().addToFavorites(getApplicationContext(), content);

                    if (isFavorite) {
                        fab.setImageResource(R.drawable.ic_favorite_white_24dp);
                    } else {
                        fab.setImageResource(R.drawable.ic_favorite_border_white_24dp);
                    }

//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

