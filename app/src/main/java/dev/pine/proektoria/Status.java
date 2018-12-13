package dev.pine.proektoria;

import android.app.Application;

public class Status extends Application {
    private boolean status = true;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
