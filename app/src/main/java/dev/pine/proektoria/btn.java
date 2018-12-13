package dev.pine.proektoria;

import android.bluetooth.BluetoothAdapter;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class btn extends android.app.Fragment {
    /* USED FONTS */
    Typeface SF_regular;
    Typeface SF_light;

    TextView outdoorLED;
    TextView activePower;

    Button btn;

    SocketClient socketClient;
    /* Temporary */
    boolean status = true;
    private int d;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.btn, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        /* SET FONTS */
        SF_regular = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SFUIText-Regular.ttf");
        SF_light = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SFUIText-Light.ttf");
        outdoorLED = getView().findViewById(R.id.outdoorLED);
        activePower = getView().findViewById(R.id.activePower);
        btn = getView().findViewById(R.id.btnON);
        status = ((Status) getActivity().getApplication()).isStatus();
        Log.d("Status", status + "");
        updateBtn();
        outdoorLED.setTypeface(SF_regular);
        activePower.setTypeface(SF_regular);
        Log.d("Socket", "Create");
        socketClient = new SocketClient();
        socketClient.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = !status;
                ((Status) getActivity().getApplication()).setStatus(status);
                socketClient.createReq(status);
                updateBtn();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        socketClient.close();
        socketClient.cancel(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("btn", "onDestroy");
    }



    public void updateBtn() {
        if(status) {
            btn.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_button_x, 0, 0, 0);
        } else {
            btn.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_oval, 0, 0, 0);
        }
    }
}
