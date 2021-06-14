package com.hamada.sinwar.myproject2021.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hamada.sinwar.myproject2021.R;
import com.hamada.sinwar.myproject2021.models.MyMarker;

public class Utils {

    public static void setupItem(final View view, final MyMarker obj) {
        final TextView txt = view.findViewById(R.id.txt_item);
        txt.setText(obj.getTitle());

        final TextView txt2 = view.findViewById(R.id.txt);
        txt2.setText(obj.getSnippet());

        final ImageView img = view.findViewById(R.id.img_item);
        Glide.with(view).load(obj.getImage()).into(img);
    }
}
