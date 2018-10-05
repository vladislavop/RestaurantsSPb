package com.nixbyte.project.modules.accountsmenu.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nixbyte.project.R;

public class AccountItem extends RecyclerView.ViewHolder {


    public TextView userLogin;
    public ImageView network_logo;
    public Button deleteButton;

    public AccountItem(View itemView) {
        super(itemView);
        network_logo = itemView.findViewById(R.id.network_logo);
        userLogin = itemView.findViewById(R.id.network_login);
        deleteButton = itemView.findViewById(R.id.deletbutton);
    }
}
