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

        int code = Integer.parseInt(httpStatusCode.getCode());
        if (code >= 100 && code < 200) {
            codeText.setTextColor(getContext().getResources().getColor(R.color.primary_dark_1xx));
        } else
        if (code >= 200 && code < 300) {
            codeText.setTextColor(getContext().getResources().getColor(R.color.primary_dark_2xx));
        } else
        if (code >= 300 && code < 400) {
            codeText.setTextColor(getContext().getResources().getColor(R.color.primary_dark_3xx));
        } else
        if (code >= 400 && code < 500) {
            codeText.setTextColor(getContext().getResources().getColor(R.color.primary_dark_4xx));
        } else
        if (code >= 500) {
            codeText.setTextColor(getContext().getResources().getColor(R.color.primary_dark_5xx));
        }

        return convertView;
    }
}
