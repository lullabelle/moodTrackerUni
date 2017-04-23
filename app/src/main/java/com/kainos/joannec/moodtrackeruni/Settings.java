package com.kainos.joannec.moodtrackeruni;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends Activity implements View.OnClickListener {
//Variables
private TextView mTextMessage;
    // adds navigation location text to top of each screen
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_word_clouds);
                    return true;
            }
            return false;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button b1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        new App().setContext(this);
        b1 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      addNotification();
                                  }
        });

        //bottom navigation bar
        mTextMessage = (TextView) findViewById(R.id.cloud_message);
        BottomNavigationView navigationWord = (BottomNavigationView) findViewById(R.id.navigationWord);
        navigationWord.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        findViewById(R.id.navigation_home).setOnClickListener(this);
        findViewById(R.id.navigation_dashboard).setOnClickListener(this);
        findViewById(R.id.navigation_notifications).setOnClickListener(this);
    }
//METHODS
// bottom navigation bar
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
    @Override
    public void onClick(View v) {
        Class view_class = null;

        switch(v.getId()){
            case R.id.navigation_home:
                view_class = MainActivity.class;
                break;
            case R.id.navigation_dashboard:
                view_class = Pie2Activity.class;
                break;
            case R.id.navigation_notifications:
                view_class = MainActivity.class;
                break;
        }
        startActivity(new Intent(this, view_class));
    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.bell)
                        .setContentTitle("Notification")
                        .setContentText("you have a mood logging notification");

        Intent notificationIntent = new Intent(this, NotificationView.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

}
