package br.com.stormtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.squareup.picasso.Picasso;

import br.com.stormtest.R;
import br.com.stormtest.cache.CacheManager;
import br.com.stormtest.models.Content;
import br.com.stormtest.models.Tag;

public class ContentDetail extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {

    private Content content;
    private ImageView shelfImage;
    private TextView contentTitle, contentDescription, tagHeader, tagHolder, relatedHeader;
    private ImageView shareContent;
    private LinearLayout horizontalScroll;

    private final String YOUTUBE_KEY = "AIzaSyBC0pZE8enJMRQd6_9msFvtNznhx5RwoI0";

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
        shareContent = (ImageView) findViewById(R.id.shareContent);

        YouTubePlayerSupportFragment fragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.videoHolder);

        if (TextUtils.equals(content.getType(), "Video")) {
            fragment.initialize(YOUTUBE_KEY, this);
        } else {
            fragment.getView().setVisibility(View.GONE);
        }


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

            for (Content related : content.getRelatedVideos()) {

                View card = LayoutInflater.from(this).inflate(R.layout.related_content, null);
                ImageView relatedImage = (ImageView) card.findViewById(R.id.relatedImage);

                Picasso.with(getApplicationContext())
                        .load(related.getShelfImage())
                        .fit()
                        .into(relatedImage);

                TextView relatedTitle = (TextView) card.findViewById(R.id.relatedTitle);
                relatedTitle.setText(related.getContentTitle());

                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(getApplicationContext(), ContentDetail.class);
                        i.putExtra("Content", related);
                        startActivity(i);
                    }
                });

                horizontalScroll.addView(card);
            }
        } else {
            relatedHeader.setVisibility(View.GONE);
            horizontalScroll.setVisibility(View.GONE);
        }

        shareContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, content.getContentTitle());
                String extraText = content.getContentURL();
                sendIntent.putExtra(Intent.EXTRA_TEXT, extraText);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getString(R.string.share_with)));
            }
        });


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

                    String msg;
                    if (isFavorite) {
                        fab.setImageResource(R.drawable.ic_favorite_white_24dp);
                        msg = content.getContentTitle() + " adicionado aos favoritos!";
                    } else {
                        fab.setImageResource(R.drawable.ic_favorite_border_white_24dp);
                        msg = content.getContentTitle() + " removido dos favoritos!";
                    }

                    Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
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

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restored) {
        if (!restored) {
            youTubePlayer.cueVideo(content.getContentURL());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getApplicationContext(), "Ops! Ocorreu um erro, tente novamente!", Toast.LENGTH_SHORT).show();
    }
}

