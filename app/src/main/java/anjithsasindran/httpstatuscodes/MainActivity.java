package anjithsasindran.httpstatuscodes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {

    MainAdapter mainAdapter;

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

        mainAdapter = new MainAdapter(this, obj.httpStatusCodes);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(mainAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView codeView = (TextView) view.findViewById(R.id.listviewcode);
                Intent detailsIntent = new Intent(getBaseContext(), DetailsActivity.class);
                detailsIntent.putExtra("code", codeView.getText());
                startActivity(detailsIntent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint(getResources().getString(R.string.searchview_hint));
        searchView.setInputType(InputType.TYPE_CLASS_NUMBER);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                mainAdapter.getFilter().filter(s);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {

            Intent settingsIntent = new Intent(this, AboutActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}