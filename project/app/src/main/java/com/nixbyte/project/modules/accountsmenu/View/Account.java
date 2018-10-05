package com.nixbyte.project.modules.accountsmenu.View;

import android.view.View;
import android.widget.ImageView;

public class Account {

    private String login;
    private int network_picture_logo;
    private View.OnClickListener listener;

    public int getNetwork_picture_logo() {
        return network_picture_logo;
    }

    public void setNetwork_picture_logo(int network_picture_logo) {
        this.network_picture_logo = network_picture_logo;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Account(String login, int network_picture_logo, View.OnClickListener listener){
        this.login = login;
        this.network_picture_logo = network_picture_logo;
        this.listener = listener;
    }

}
