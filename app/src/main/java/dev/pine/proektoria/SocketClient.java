package dev.pine.proektoria;

import android.os.AsyncTask;
import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketExtension;
import com.neovisionaries.ws.client.WebSocketFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static dev.pine.proektoria.Config.ip;
import static dev.pine.proektoria.Config.timeout;
import static dev.pine.proektoria.Config.ip;
import static dev.pine.proektoria.Config.timeout;

public class SocketClient extends AsyncTask<Void, Void, Void> {
    private WebSocket webSocket;
    private String login = "cat";
    private String password = "meow";
    boolean run = true;

    private String message = "";
    private WebSocket disconnect;


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Log.d("Socket", "New websocket");
            webSocket = new WebSocketFactory()
                    .setConnectionTimeout(timeout)
                    .createSocket(ip)
                    .addListener(new WebSocketAdapter() {
                        public void onTextMessage(WebSocket websocket, String message) {
                            try {
                                Log.d("Received: ", message);
                                JSONObject j = new JSONObject(message);
                                String event = j.getString("event");
                                if(event.equals("response")) {
                                    JSONObject jO = j.getJSONObject("data");
                                    if(jO.getString("response").equals("ok")) {
                                        Log.d("status", "ok");
                                    }
                                } else if(event.equals("authNotify")) {
                                    JSONObject jsonObject = new JSONObject();
                                    JSONObject jsonData = new JSONObject();
                                    try{
                                        jsonObject.put("event", "authReq");
                                        jsonData.put("login", login);
                                        jsonData.put("pass", password);
                                        jsonObject.put("data", jsonData);
                                    } catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                    websocket.sendText(jsonObject.toString());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    })
                    .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
                    .connect();
            while (run) {
                if(!message.equals("")) {
                    webSocket.sendText(message);
                    message = "";
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WebSocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createReq(boolean status) {
        JSONObject j = new JSONObject(), jO = new JSONObject();
        try {
            j.put("event", "set");
            jO.put("login", login);
            jO.put("pass", password);
            jO.put("status", status);
            j.put("data", jO);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        message = j.toString();
    }

    public void close() {
        try {
            run = false;
            webSocket.disconnect();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
