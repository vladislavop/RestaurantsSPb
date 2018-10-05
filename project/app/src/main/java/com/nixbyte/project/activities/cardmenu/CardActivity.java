package com.nixbyte.project.activities.cardmenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.main.MainActivity;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.modules.cardmenu.model.CardMenu;

public class CardActivity extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_card_menu, container, false);
        CardMenu.createMenu((MainActivity)getActivity(),view);
        return view;
    }

}
