package anjithsasindran.httpstatuscodes;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Anjith Sasindran on 16-May-15.
 * CodesAndSummary class retrieves codes and summary from database and uses the setters of
 * HttpStatusCodes to set the values.
 */

public class CodesAndSummary {

    private int count;
    private Context context;
    protected HttpStatusCodes httpStatusCodes[];

    public CodesAndSummary(Context context) {
        this.context = context;
    }

    public void setValues() {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        Cursor cursor = dbHelper.getCodesAndSummary();
        cursor.moveToFirst();
        count = cursor.getCount();
        httpStatusCodes = new HttpStatusCodes[count];
        /*
         * Object of HttpStatusCodes to hold details of codes & summary
         * i is used for iterating and incrementing object counter
        */
        int i = 0;
        while(!cursor.isAfterLast()) {
            httpStatusCodes[i] = new HttpStatusCodes();
            httpStatusCodes[i].setCode(cursor.getString(0));
            httpStatusCodes[i].setSummary(cursor.getString(1));
            cursor.moveToNext();
            i++;
        }
        dbHelper.close();
    }

    public int getCount() {
        return count;
    }
}
