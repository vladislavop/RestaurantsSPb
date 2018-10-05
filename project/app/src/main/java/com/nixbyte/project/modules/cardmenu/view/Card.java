package com.nixbyte.project.modules.cardmenu.view;

import android.view.View;
import android.view.ViewGroup;

public class Card {
    private String name;
    private String snippet;
    private int pictype;

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    private View.OnClickListener listener;

    public int getPictype() {
        return pictype;
    }

    public void setPictype(int pictype) {
        this.pictype = pictype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Card(String name, String snippet, int pictype, View.OnClickListener listener) {
        this.name = name;
        this.snippet = snippet;
        this.pictype = pictype;
        this.listener = listener;
    }
}
