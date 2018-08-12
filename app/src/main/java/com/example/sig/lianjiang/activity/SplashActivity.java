package com.example.sig.lianjiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.example.sig.lianjiang.StarryHelper;
import com.example.sig.lianjiang.R;
//import com.example.sig.lianjiang.conference.ConferenceActivity;
import com.hyphenate.util.EasyUtils;


/**
 * 开屏页
 *
 */
public class SplashActivity extends BaseActivity {

    private static final int sleepTime = 2000;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.activity_splash);
        super.onCreate(arg0);


        StarryHelper.getInstance().initHandler(this.getMainLooper());

        RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.splash_root);
        TextView versionText = (TextView) findViewById(R.id.tv_version);

        versionText.setText(getVersion());
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        rootLayout.startAnimation(animation);
//        StarryHelper.getInstance().isLoggedIn();
//        getApplicationContext();
//        StarryHelper.getInstance().init(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            public void run() {
                if (StarryHelper.getInstance().isLoggedIn()) {
                    // auto login mode, make sure all group and conversation is loaed before enter the main screen
                    long start = System.currentTimeMillis();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    EMClient.getInstance().groupManager().loadAllGroups();
                    long costTime = System.currentTimeMillis() - start;
                    //wait
                    if (sleepTime - costTime > 0) {
                        try {
                            Thread.sleep(sleepTime - costTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String topActivityName = EasyUtils.getTopActivityName(EMClient.getInstance().getContext());
                    if (topActivityName != null && (topActivityName.equals(VideoCallActivity.class.getName()) || topActivityName.equals(VoiceCallActivity.class.getName())
//                            || topActivityName.equals(ConferenceActivity.class.getName())
                    )) {
                        // nop
                        // avoid main screen overlap Calling Activity
                    } else {
                        //enter main screen
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                    finish();
                }else {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                    }
                    startActivity(new Intent(SplashActivity.this, LoginActivitysteup1.class));
                    finish();
                }
            }
        }).start();

    }

    /**
     * get sdk version
     */
    private String getVersion() {
        return EMClient.getInstance().VERSION;
    }
}
