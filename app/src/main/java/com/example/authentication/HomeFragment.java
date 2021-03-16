package com.example.authentication;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    ImageView imageView;
    private Button btn_save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        btn_save=view.findViewById(R.id.btn_save);
        imageView=view.findViewById(R.id.imageview);
        AnimationDrawable animationDrawable =(AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return  view;


  }

}
