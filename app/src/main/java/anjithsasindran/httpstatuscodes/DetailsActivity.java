package anjithsasindran.httpstatuscodes;

import android.animation.Animator;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

/**
 * Created by Anjith Sasindran on 17-May-15.
 * Activity which opens when ListView is clicked, show the details
 */
public class DetailsActivity extends AppCompatActivity {

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        cursor = dbHelper.getAllHttpCode(getIntent().getStringExtra("code"));
        cursor.moveToFirst();
        dbHelper.close();

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(cursor.getString(1));
        actionBar.setSubtitle(cursor.getString(2));

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setFillAfter(true);
        fadeIn.setDuration(1000);

        //Wikipedia CardView
        CardView wikiCardView = (CardView) findViewById(R.id.wiki_card_view);

        final TextView wikiCardHeader = ((TextView) wikiCardView.findViewById(R.id.wikicard_header));
        wikiCardHeader.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            wikiCardHeader.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    int cx = (left + right) / 2;
                    int cy = (top + bottom) / 2;
                    int finalRadius = Math.max(cx, cy);
                    Animator anim = ViewAnimationUtils.createCircularReveal(wikiCardHeader, cx, cy, 0, finalRadius);
                    wikiCardHeader.setVisibility(View.VISIBLE);
                    anim.start();
                }
            });
        } else {
            wikiCardHeader.setVisibility(View.VISIBLE);
        }
        TextView wikiCardBody = (TextView) wikiCardView.findViewById(R.id.wikicard_body);
        wikiCardBody.setText(cursor.getString(4));
        wikiCardBody.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
        wikiCardBody.setAnimation(fadeIn);

        //IETF CardView
        CardView ietfCardView = (CardView) findViewById(R.id.ietf_card_view);

        final TextView ietfCardHeader = ((TextView) ietfCardView.findViewById(R.id.ietfcard_header));
        ietfCardHeader.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));

        TextView ietfCardBody = (TextView) ietfCardView.findViewById(R.id.ietfcard_body);

        if (cursor.getString(6) == null) {
            ietfCardView.setVisibility(CardView.GONE);
        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                ietfCardHeader.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

                    @Override
                    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                        int cx = (left + right) / 2;
                        int cy = (top + bottom) / 2;
                        int finalRadius = Math.max(cx, cy);
                        Animator anim = ViewAnimationUtils.createCircularReveal(ietfCardHeader, cx, cy, 0, finalRadius);
                        ietfCardHeader.setVisibility(View.VISIBLE);
                        anim.start();
                    }
                });
            } else {
                ietfCardHeader.setVisibility(View.VISIBLE);
            }

            ietfCardBody.setText(cursor.getString(6));
            ietfCardBody.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
            ietfCardBody.setAnimation(fadeIn);
        }

    }

    public void shareWiki(View view) {

        Intent shareWikiIntent = new Intent(Intent.ACTION_SEND);
        shareWikiIntent.putExtra(Intent.EXTRA_TEXT, cursor.getString(4));
        shareWikiIntent.putExtra(Intent.EXTRA_SUBJECT, getResources()
                .getString(R.string.http_status_code) + " " + cursor.getString(1) +" details");
        shareWikiIntent.setType("text/plain");
        startActivity(shareWikiIntent);
    }

    public void shareIetf(View view) {

        Intent shareIetfIntent = new Intent(Intent.ACTION_SEND);
        shareIetfIntent.putExtra(Intent.EXTRA_TEXT, cursor.getString(6));
        shareIetfIntent.putExtra(Intent.EXTRA_SUBJECT, getResources()
        .getString(R.string.http_status_code) + " " + cursor.getString(1) + " details");
        shareIetfIntent.setType("text/plain");
        startActivity(shareIetfIntent);
    }

    public void linkWiki(View view) {

        String url = cursor.getString(5);
        Intent wikiLinkIntent = new Intent(Intent.ACTION_VIEW);
        wikiLinkIntent.setData(Uri.parse(url));
        startActivity(wikiLinkIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.home :
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
