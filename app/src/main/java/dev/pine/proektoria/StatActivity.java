package dev.pine.proektoria;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.Typeface;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatActivity extends AppCompatActivity {

    /* USED FONTS */
    Typeface SF_regular;
    Typeface SF_light;

    /* USED UI ELEMENTS */
    Button btnHouse;
    Button btnPosel;
    Fragment house;
    Fragment poselok;
    Fragment btn;
    FragmentTransaction fTrans;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        /* SET FONTS */
        SF_regular = Typeface.createFromAsset(getAssets(),  "fonts/SFUIText-Regular.ttf");
        SF_light = Typeface.createFromAsset(getAssets(), "fonts/SFUIText-Light.ttf");

        configUI();
        configListeners();
    }

    public void configUI() {
        btnHouse = findViewById(R.id.btnHouse);
        btnPosel = findViewById(R.id.btnPosel);
        btnHouse.setTypeface(SF_light);
        btnPosel.setTypeface(SF_light);
        house = new house();
        poselok = new poselok();
        btn = new btn();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.stat, house);
        fTrans.commit();
    }

    public void configListeners() {
        btnHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fTrans = getFragmentManager().beginTransaction();
                fTrans.replace(R.id.stat, house);
                fTrans.addToBackStack(null);
                fTrans.commit();
                btnHouse.setBackgroundColor(getResources().getColor(R.color.blueBtn));
                btnHouse.setTextColor(getResources().getColor(R.color.whiteBtn));
                btnPosel.setBackgroundColor(getResources().getColor(R.color.whiteText));
                btnPosel.setTextColor(getResources().getColor(R.color.blueText));
                btnHouse.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_house, 0, 0, 0);
                btnPosel.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_poselok, 0, 0, 0);
            }
        });
        btnPosel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fTrans = getFragmentManager().beginTransaction();
                fTrans.replace(R.id.stat, poselok);
                fTrans.addToBackStack(null);
                fTrans.commit();
                btnPosel.setBackgroundColor(getResources().getColor(R.color.blueBtn));
                btnHouse.setBackgroundColor(getResources().getColor(R.color.whiteBtn));
                btnPosel.setTextColor(getResources().getColor(R.color.whiteText));
                btnHouse.setTextColor(getResources().getColor(R.color.blueText));
                btnHouse.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_house_blue, 0, 0, 0);
                btnPosel.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_poselok_white, 0, 0, 0);
            }
        });
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }

    }
}
