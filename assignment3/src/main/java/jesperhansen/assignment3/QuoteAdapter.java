package jesperhansen.assignment3;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * QuoteAdapter for bridging data with xml files
 */
public class QuoteAdapter extends BaseAdapter {
    private List<String> mQuoteList;
    private LayoutInflater mLayoutInflater;

    public QuoteAdapter(List<String> mQuoteList, LayoutInflater mLayoutInflater) {
        this.mQuoteList = mQuoteList;
        this.mLayoutInflater = mLayoutInflater;
    }
    @Override
    public int getCount() {
        return mQuoteList.size();
    }

    @Override
    public Object getItem(int position) {
        return mQuoteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.quote_item, parent, false);
        }

        TextView quoteTextView = (TextView) convertView.findViewById(R.id.quote_text_view);
        //quoteTextView.setText(getItem(position).toString());

        return convertView;
    }
}
