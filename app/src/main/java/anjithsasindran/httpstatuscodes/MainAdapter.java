package anjithsasindran.httpstatuscodes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anjith Sasindran on 16-May-15.
 * Custom adapter for inflating ListView of MainActivity and SearchView filtering
 */
public class MainAdapter extends ArrayAdapter<HttpStatusCodes> {

    HttpStatusCodes httpStatusCodes[];
    HttpStatusCodes statusCodesForAdapter[];

    public MainAdapter(Context context, HttpStatusCodes httpStatusCodes[]) {
        super(context, 0, httpStatusCodes);
        this.httpStatusCodes = httpStatusCodes;
        this.statusCodesForAdapter = httpStatusCodes;
    }

    @Override
    public int getCount() {
        return statusCodesForAdapter.length;
    }

    @Override
    public HttpStatusCodes getItem(int position) {

        return statusCodesForAdapter[position];
    }

    @Override
    public long getItemId(int position) {
        return statusCodesForAdapter[position].hashCode();
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

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                int length = httpStatusCodes.length;

                FilterResults result = new FilterResults();
                List<HttpStatusCodes> statusCodesList = new ArrayList<>();

                if (constraint == null) {
                    result.values = httpStatusCodes;
                    result.count = length;
                } else {
                    for (int i = 0 ; i < length ; i ++) {
                        if (httpStatusCodes[i].getCode().startsWith(constraint.toString())) {
                            statusCodesList.add(httpStatusCodes[i]);
                        }
                    }

                    length = statusCodesList.size();
                    HttpStatusCodes resultantStatusCodes[] = new HttpStatusCodes[length];

                    for (int i = 0 ; i < length; i ++) {
                        resultantStatusCodes[i] = statusCodesList.get(i);
                    }

                    result.values = resultantStatusCodes;
                    result.count = length;
                }

                return result;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                statusCodesForAdapter = (HttpStatusCodes[]) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
