package com.nixbyte.project.modules.slidingmenu.model;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nixbyte on 30.01.15.
 */
public abstract class MenuItem implements View.OnClickListener {
    public enum TYPE {
        DEFAULT_ITEM(0),
        EMPTY_ITEM(1),
        STROKE_ITEM(2);

        final int value;
        TYPE(int value) {
            this.value = value;
        }

        public static TYPE getType(int value) {
            return TYPE.values()[value];
        }

        public int getValue() {
            return value;
        }
    }

    public abstract int getType();

    public abstract int getIcon();

    public abstract String getTitle();

    public abstract ViewGroup.LayoutParams getLayoutParams();
}
