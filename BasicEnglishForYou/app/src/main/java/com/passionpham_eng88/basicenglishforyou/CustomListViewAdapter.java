package com.passionpham_eng88.basicenglishforyou;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by passionpham on 1/24/2016.
 */
public class CustomListViewAdapter extends ArrayAdapter<String> {
    // Members
    private  final Activity context_;
    private  final String[] item_name_;
    private  final Integer[] img_id_;

    // Function
    public  CustomListViewAdapter(Activity context, String[] item_name, Integer[] img_id)
    {
        super(context, R.layout.mylist, item_name);
        this.context_ = context;
        this.item_name_ = item_name;
        this.img_id_ = img_id;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context_.getLayoutInflater();
        View row_view = inflater.inflate(R.layout.mylist, null, true);

        TextView txt_title = (TextView)row_view.findViewById(R.id.item);
        ImageView img_view = (ImageView)row_view.findViewById(R.id.icon);
        //TextView extra_text = (TextView)row_view.findViewById(R.id.textView1);

        if (position < item_name_.length)
        {
            txt_title.setText(item_name_[position]);
        }

        if (position < img_id_.length)
        {
            img_view.setImageResource(img_id_[position]);
        }
        //extra_text.setText("Description" + item_name_[position]);
        return row_view;
    }
}
