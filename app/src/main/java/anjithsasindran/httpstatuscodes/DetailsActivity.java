package anjithsasindran.httpstatuscodes;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
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

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(cursor.getString(1));
        actionBar.setSubtitle(cursor.getString(2));

        CardView wikiCardView = (CardView) findViewById(R.id.wiki_card_view);
        TextView wikiCardBody = (TextView) wikiCardView.findViewById(R.id.wikicard_body);
        wikiCardBody.setText(cursor.getString(4));

        CardView ietfCardView = (CardView) findViewById(R.id.ietf_card_view);
        TextView ietfCardBody = (TextView) ietfCardView.findViewById(R.id.ietfcard_body);

        if (cursor.getString(6) == null) {
            ietfCardView.setVisibility(CardView.GONE);
        } else {
            ietfCardBody.setText(cursor.getString(6));
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
}
