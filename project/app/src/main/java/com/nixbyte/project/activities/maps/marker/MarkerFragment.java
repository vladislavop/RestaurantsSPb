package com.nixbyte.project.activities.maps.marker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nixbyte.project.R;
import com.nixbyte.project.activities.maps.MapsPresenter;
import com.nixbyte.project.activities.restaurant.RestaurantActivity;
import com.nixbyte.project.model.restaurant.ObjectRestaurant;
import com.nixbyte.project.model.userpojo.User;

import static android.content.Context.MODE_PRIVATE;

public class MarkerFragment extends Fragment {
    View view;
    LinearLayout markerView;
    TextView titleView;
    TextView address, site, number_phone, kitchen;
    String id;

    TextView like_counter_text, dislike_counter_text;

    public int like_count, dislike_count;

    ProgressDialog dialog;

    MarkerPresenter presenter;

    ImageButton like, dislike;
    Button more_info;

    String dolgota, shirota, address_str, site_str, number_phone_str, kitchen_str;

    String title, snippet;
    User userAuth;

    @Override
    public void onAttach(Context context){

        super.onAttach(context);

        dialog = new ProgressDialog(this.getActivity());
        presenter = new MarkerPresenter();
        presenter.onViewAttached(this);

        title = (String)this.getArguments().getSerializable("name");

        id = (String)this.getArguments().getSerializable("id");

        address_str = (String)this.getArguments().getSerializable("address");
        site_str = (String)this.getArguments().getSerializable("site");
        number_phone_str = (String)this.getArguments().getSerializable("phone");
        kitchen_str = (String)this.getArguments().getSerializable("kitchen");

        shirota = (String)this.getArguments().getSerializable("shirota");
        dolgota = (String)this.getArguments().getSerializable("dolgota");

        SharedPreferences preferences = getActivity().getSharedPreferences("authorization", MODE_PRIVATE);
        String userObject = preferences.getString("userdata", "");
        userAuth = new Gson().fromJson(userObject, User.class);
        presenter.getMyRestaurantData(Integer.valueOf(id), userAuth.id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.marker_info_window, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        titleView.setText(title);
        address.setText("Saint Petersburg, Nevsky prospect, 29");
        site.setText(site_str);
        number_phone.setText(number_phone_str);
        kitchen.setText(kitchen_str);
        like_counter_text.setText("(" + like_count + ")");
        dislike_counter_text.setText("(" + dislike_count + ")");
        like.setOnClickListener( v -> {
            dislike.setImageResource(R.drawable.thumb_down_non);
            dislike.setTag(R.drawable.thumb_down_non);
            like.setImageResource(R.drawable.thumb_up);
            like.setTag(R.drawable.thumb_up);
            like_count++;
            like_counter_text.setText("(" + like_count + ")");
            if(dislike_count!=0 || ((Integer)dislike.getTag()) == R.drawable.thumb_down){
                dislike_count--;
                dislike_counter_text.setText("(" + dislike_count + ")");
            }
            presenter.setRate(userAuth.id, Integer.valueOf(id),5);
        });
        dislike.setOnClickListener(v -> {
            dislike.setImageResource(R.drawable.thumb_down);
            dislike.setImageResource(R.drawable.thumb_down);
            like.setImageResource(R.drawable.thumb_up_non);
            like.setTag(R.drawable.thumb_up_non);
            if(like_count!=0 || ((Integer)like.getTag()) == R.drawable.thumb_up) {
                like_count--;
                like_counter_text.setText("(" + like_count + ")");
            }
            dislike_count++;
            dislike_counter_text.setText("(" + dislike_count + ")");
            presenter.setRate(userAuth.id, Integer.valueOf(id),2);
        });
        more_info.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), RestaurantActivity.class);
            intent.putExtra("restaurant_id", Integer.valueOf(id));
            intent.putExtra("dolgota", dolgota);
            intent.putExtra("shirota", shirota);
            intent.putExtra("title", title);
            intent.putExtra("site", site_str);
            intent.putExtra("kitchen", kitchen_str);
            intent.putExtra("address", "Saint Petersburg, Nevsky prospect, 29");
            intent.putExtra("number_phone", number_phone_str);
            startActivity(intent);
        });

    }

    private void initView(){
        markerView =  view.findViewById(R.id.marker_layout);
        titleView = view.findViewById(R.id.title);
        address = view.findViewById(R.id.address);
        site = view.findViewById(R.id.site);
        number_phone = view.findViewById(R.id.number);
        kitchen = view.findViewById(R.id.kitchen);
        like = view.findViewById(R.id.like);
        dislike = view.findViewById(R.id.dislike);
        dislike.setImageResource(R.drawable.thumb_down_non);
        like.setImageResource(R.drawable.thumb_up_non);
        like_counter_text = view.findViewById(R.id.like_count);
        dislike_counter_text = view.findViewById(R.id.dislike_count);
        more_info = view.findViewById(R.id.more_info);
    }

}
