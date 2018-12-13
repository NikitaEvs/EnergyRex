package dev.pine.proektoria;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TipActivity extends AppCompatActivity {

    TextView tip1;
    TextView tip2;
    TextView tip3;
    TextView tip4;
    TextView tip5;
    TextView tip6;

    Button back;

    /* USED FONTS */
    Typeface SF_regular;
    Typeface SF_light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        /* SET FONTS */
        SF_regular = Typeface.createFromAsset(getAssets(), "fonts/SFUIText-Regular.ttf");
        SF_light = Typeface.createFromAsset(getAssets(), "fonts/SFUIText-Light.ttf");

        configUI();
    }

    public void configUI() {
        tip1 = findViewById(R.id.tip1);
        tip2 = findViewById(R.id.tip2);
        tip3 = findViewById(R.id.tip3);
        tip4 = findViewById(R.id.tip4);
        tip5 = findViewById(R.id.tip5);
        tip6 = findViewById(R.id.tip6);
        tip1.setTypeface(SF_regular);
        tip2.setTypeface(SF_regular);
        tip3.setTypeface(SF_regular);
        tip4.setTypeface(SF_regular);
        tip5.setTypeface(SF_regular);
        tip6.setTypeface(SF_regular);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
