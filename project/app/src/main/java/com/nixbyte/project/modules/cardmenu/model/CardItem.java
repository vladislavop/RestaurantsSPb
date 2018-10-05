package com.nixbyte.project.modules.cardmenu.model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.nixbyte.project.R;

public class CardItem extends RecyclerView.ViewHolder {

    public TextView title, snippet;
    public Button go;
    public ImageView image;

    View.OnClickListener listener;

    public CardItem(View itemView){
        super(itemView);
        title = itemView.findViewById(R.id.name);
        snippet = itemView.findViewById(R.id.snippet);
        go = itemView.findViewById(R.id.button);
        image = itemView.findViewById(R.id.cardImage);
    }

}
