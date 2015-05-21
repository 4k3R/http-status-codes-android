package anjithsasindran.httpstatuscodes;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

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


    /*
     * Methods for opening project in webpage, my personal github page, website and instagram
     */
    public void openProjectGithub(View view) {

        startActivity(new Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse("https://github.com/4k3R/httpstatuscodes-android")));
    }

    public void openAnjithGithub(View view) {

        startActivity(new Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse("https://github.com/4k3R")));
    }

    public void openAnjithWebsite(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse("http://anjithsasindran.in")));
    }

    public void openAnjithInstagram(View view) {

        Uri uri = Uri.parse("http://instagram.com/_u/anjithsasindran");
        Intent insta = new Intent(Intent.ACTION_VIEW, uri);
        insta.setPackage("com.instagram.android");

        PackageManager packageManager = getBaseContext().getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(insta, PackageManager.MATCH_DEFAULT_ONLY);

        if (list.size()>0) {
            startActivity(insta);
        }
        else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/anjithsasindran")));
        }
    }
}
