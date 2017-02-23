package com.cookie.ipcdome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cookie.ipcdome.service.LocalService;
import com.cookie.ipcdome.service.RemoteService;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.startService(new Intent(this, LocalService.class));
        this.startService(new Intent(this, RemoteService.class));
    }
}
