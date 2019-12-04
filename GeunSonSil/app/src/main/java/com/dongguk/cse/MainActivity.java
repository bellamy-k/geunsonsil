package com.dongguk.cse;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.paitoanderson.stepcounter.data.Preferences;
import com.paitoanderson.stepcounter.services.StepCounter;

import bases.BaseActivity;

public class MainActivity extends BaseActivity {

    private static final int PAGE_COUNT = 2;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private TextView menu_home;
    private TextView menu_log;


    private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.paitoanderson.stepcounter.services.StepCounter".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.menu_home:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.menu_log:
                mViewPager.setCurrentItem(1);
                break;
        }
    }

    private void initView(){
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        menu_home = findViewById(R.id.menu_home);
        menu_log = findViewById(R.id.menu_log);

        setClick(menu_home, menu_log);

        if (!isServiceRunning()) {

            // Mark Service as Started
            Preferences.setServiceRun(this, false);

            // Start Step Counting service
            Intent serviceIntent = new Intent(this, StepCounter.class);
            startService(serviceIntent);
        }
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0: // Home
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new LogFragment();
                    break;
            }

            return fragment;
//            Fragment temp;
//            Bundle args = new Bundle();
//            args.putInt("key", position);
//            temp = new TutorialFragment();
//            temp.setArguments(args);
//            return temp;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";
            return title;
        }
    }

}
