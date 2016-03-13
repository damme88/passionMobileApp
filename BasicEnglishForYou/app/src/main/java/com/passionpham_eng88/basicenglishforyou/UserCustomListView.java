package com.passionpham_eng88.basicenglishforyou;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by passionpham on 2/12/2016.
 */
public class UserCustomListView extends ArrayAdapter<User>
{
    Context context;
    int layoutResourceId;
    String current_string = "";
    ArrayList<User> data = new ArrayList<User>();
    UserHolder holder = null;
    public UserCustomListView(Context context, int layoutResourceId,
                             ArrayList<User> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new UserHolder();
            holder.textSentence = (TextView) row.findViewById(R.id.txtView1);
            holder.btn_speak = (Button) row.findViewById(R.id.btnSpeak);

            if (context.getClass() == GrammarActivity.class)
            {
                holder.btn_speak.setVisibility(View.INVISIBLE);
            }
            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }

        User user = data.get(position);
        current_string = user.GetStence();
        holder.textSentence.setText(current_string);

        holder.btn_speak.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context.getClass() == IteamListActivity.class) {
                    IteamListActivity itemList = (IteamListActivity) context;
                    itemList.PlayAudio(position);
                }

                if (context.getClass() == WordGroupActivity.class)
                {
                    WordGroupActivity wordGroupScreen = (WordGroupActivity) context;
                    wordGroupScreen.PlayAudio(position);
                }
            }
        });
        return row;

    }
    static class UserHolder {
        TextView textSentence;
        Button btn_speak;
    }
}
