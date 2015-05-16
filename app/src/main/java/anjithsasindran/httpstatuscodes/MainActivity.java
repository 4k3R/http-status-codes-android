package anjithsasindran.httpstatuscodes;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.sql.SQLException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHelper dbHelper = new DataBaseHelper(this);

        try {
            dbHelper.createDatabase();
        } catch (Exception e) {
            throw new Error("Unable to create Database");
        }
        try {
            dbHelper.openDatabase();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        dbHelper.close();

        /*
         * CodesAndSummary class retrieves codes and summary from database and uses the setters of
         * HttpStatusCodes to set the values.
         */
        CodesAndSummary obj = new CodesAndSummary(this);
        obj.setValues();

        MainAdapter mainAdapter = new MainAdapter(this, obj.httpStatusCodes);

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(mainAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}