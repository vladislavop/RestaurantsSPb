package com.nixbyte.project.activities.restaurant.model;

import android.view.View;
import android.view.ViewGroup;

public abstract class RestaurantItem implements View.OnClickListener{

    public enum TYPE{
        RATE_ITEM(0),
        COMMENT_ITEM(1),
        SNIPPET_ITEM(2),
        COMMENT_INPUT_ITEM(3);

        final int value;
        TYPE(int value){
            this.value = value;
        }

        public static TYPE getType(int value) { return TYPE.values()[value]; }

        public int getValue() { return value; }

    }

    public abstract int getType();

    public abstract ViewGroup.LayoutParams getLayoutParams();

}
