package com.thousand.petdog.fragement;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thousand.petdog.R;


public class MyFragment extends Fragment {

    private String content;

    public MyFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_my,container,false);

        return view;
    }
}
