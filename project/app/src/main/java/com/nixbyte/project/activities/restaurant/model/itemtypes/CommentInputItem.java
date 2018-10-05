package com.nixbyte.project.activities.restaurant.model.itemtypes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nixbyte.project.R;

public class CommentInputItem extends RecyclerView.ViewHolder {

    public EditText comment_input;
    public Button send_button;

    public CommentInputItem(View itemView) {
        super(itemView);
        comment_input = itemView.findViewById(R.id.comment_input);
        send_button = itemView.findViewById(R.id.send);
        send_button.setOnClickListener(v->{

        });
    }

}
