package anjithsasindran.httpstatuscodes;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardView;

/**
 * Created by Anjith Sasindran on 17-May-15.
 * Activity which opens when ListView is clicked, show the details
 */
public class DetailsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        Cursor cursor = dbHelper.getAllHttpCode(getIntent().getStringExtra("code"));

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        cursor.moveToFirst();
        actionBar.setTitle(cursor.getString(1));
        actionBar.setSubtitle(cursor.getString(2));

        /*
         * Uses cardslib (https://github.com/gabrielemariotti/cardslib) library by Gabrielle Mariotti
         * Card layout for Wikipedia description and link
         */
        Card wikiCard = new Card(this);
        CardHeader wikiCardHeader = new CardHeader(this);
        wikiCard.addCardHeader(wikiCardHeader);

        CardView wikiCardView = (CardView) findViewById(R.id.wiki_card);
        wikiCardView.setCard(wikiCard);

        //Card Layout for IETF description
        Card ietfCard = new Card(this);
        CardHeader ietfCardHeader = new CardHeader(this);
        ietfCard.addCardHeader(ietfCardHeader);

        CardView ietfCardView = (CardView) findViewById(R.id.ietf_card);
        ietfCardView.setCard(ietfCard);
    }
}
