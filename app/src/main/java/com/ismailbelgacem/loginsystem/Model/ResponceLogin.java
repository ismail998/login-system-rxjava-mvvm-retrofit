package com.ismailbelgacem.loginsystem.Model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponceLogin {
    @SerializedName("status")
    @Expose
    boolean status;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("token")
    @Expose
    String token;

    public ResponceLogin(boolean status, String message, String token) {
        this.status = status;
        this.message = message;
        this.token = token;
        Log.d("TAG", "ResponceLogin: "+this.toString());
    }

    @Override
    public String toString() {
        return "ResponceLogin{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
