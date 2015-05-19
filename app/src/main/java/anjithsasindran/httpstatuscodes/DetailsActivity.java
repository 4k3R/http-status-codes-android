package anjithsasindran.httpstatuscodes;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.TextView;

/**
 * Created by Anjith Sasindran on 17-May-15.
 * Activity which opens when ListView is clicked, show the details
 */
public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        Cursor cursor = dbHelper.getAllHttpCode(getIntent().getStringExtra("code"));
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
}
