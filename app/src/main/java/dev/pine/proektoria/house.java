package dev.pine.proektoria;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class house extends android.app.Fragment{
        /* USED FONTS */
        Typeface SF_regular;
        Typeface SF_light;

        TextView danger;
        TextView tips;
        TextView tip;
        TextView power;

        Button tipBtn;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.house, null);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            /* SET FONTS */
            SF_regular = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/SFUIText-Regular.ttf");
            SF_light = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SFUIText-Light.ttf");
            danger = getView().findViewById(R.id.danger);
            tips = getView().findViewById(R.id.tips);
            tip = getView().findViewById(R.id.tip);
            tipBtn = getView().findViewById(R.id.tipBtn);
            power = getView().findViewById(R.id.power);
            tips.setTypeface(SF_regular);
            danger.setTypeface(SF_regular);
            tip.setTypeface(SF_regular);
            power.setTypeface(SF_regular);
            tipBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), TipActivity.class);
                    startActivity(intent);
                }
            });
        }
}

