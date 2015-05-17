package anjithsasindran.httpstatuscodes;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

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
    }
}
