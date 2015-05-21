package anjithsasindran.httpstatuscodes;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.TextView;

/**
 * Created by Anjith Sasindran on 18-May-15.
 * Activity for settings
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);

        //Setting font since android:fontFamily in xml is not availabe in API 14
        CardView project_card = (CardView)findViewById(R.id.project_card);
        ((TextView)project_card.findViewById(R.id.project_name))
                .setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));

        ((TextView)project_card.findViewById(R.id.project_description))
                .setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));

        CardView anjith_card = (CardView)findViewById(R.id.anjith_card);
        ((TextView)anjith_card.findViewById(R.id.anjith_name))
                .setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));

        ((TextView)anjith_card.findViewById(R.id.anjith_description))
                .setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
    }
}
