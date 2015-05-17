package anjithsasindran.httpstatuscodes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Anjith Sasindran on 16-May-15.
 * Custom adapter for inflating ListView of MainActivity
 */
public class MainAdapter extends ArrayAdapter<HttpStatusCodes> {

    public MainAdapter(Context context, HttpStatusCodes httpStatusCodes[]) {
        super(context, 0, httpStatusCodes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HttpStatusCodes httpStatusCode = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }

        TextView codeText = (TextView) convertView.findViewById(R.id.listviewcode);
        TextView summaryText = (TextView) convertView.findViewById(R.id.listviewsummary);

        codeText.setText(httpStatusCode.getCode());
        summaryText.setText(httpStatusCode.getSummary());

        return convertView;
    }
}
