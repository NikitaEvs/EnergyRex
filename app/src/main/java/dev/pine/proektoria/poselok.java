package dev.pine.proektoria;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class poselok extends android.app.Fragment{
    /* USED FONTS */
    Typeface SF_regular;
    Typeface SF_light;

    TextView availablePower;
    TextView availableCount;
    TextView emergency;

    Button emergencyBtn;

    FragmentTransaction fTrans;
    Fragment btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.poselok, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        /* SET FONTS */
        SF_regular = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/SFUIText-Regular.ttf");
        SF_light = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SFUIText-Light.ttf");
        availablePower = getView().findViewById(R.id.availablePower);
        availableCount = getView().findViewById(R.id.availableCount);
        emergency = getView().findViewById(R.id.emergency);
        emergencyBtn = getView().findViewById(R.id.emergencyBtn);

        availablePower.setTypeface(SF_regular);
        availableCount.setTypeface(SF_regular);
        emergency.setTypeface(SF_regular);

        if(!((Status) getActivity().getApplication()).isStatus()) {
            availableCount.setText("8200 Вт");
            availableCount.setTextColor(getResources().getColor(R.color.blueText));
        }

        btn = new btn();

        emergencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fTrans = getFragmentManager().beginTransaction();
                fTrans.replace(R.id.stat, btn);
                fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });
    }
}

