package com.dongguk.cse;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private enum TEXT_TYPE{
        ATK,
        DEF,
        HP,
        MP,
        WALK,
        LEVEL
    }

    private View rootView;
    private TextView holder_atk, holder_def, holder_hp, holder_mp, holder_walk, holder_level;

    private volatile int walk_counter = 0;
    private volatile int level = 1;
    private BroadcastReceiver myRecv = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("Walking Detected", intent.toString());
            walk_counter++;
            setValueOf(Integer.toString(50-walk_counter), TEXT_TYPE.WALK);
            if(walk_counter==50)
            {
                walk_counter = 0;
                level  ++;
                setValueOf("Lv."+Integer.toString(level), TEXT_TYPE.LEVEL);

            }

            setValueOf(Integer.toString(level*60), TEXT_TYPE.ATK);
            setValueOf(Integer.toString(level*50), TEXT_TYPE.DEF);
            setValueOf(Integer.toString(level*200)+"/"+Integer.toString(level*200), TEXT_TYPE.MP);
            setValueOf(Integer.toString(level*300)+"/"+Integer.toString(level*300), TEXT_TYPE.HP);

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initView();
        int atk = 60;
        int def = 50;
        int hp = 300;
        int mp = 200;


        setValueOf(Integer.toString(atk), TEXT_TYPE.ATK);
        setValueOf(Integer.toString(def), TEXT_TYPE.DEF);
        setValueOf(Integer.toString(mp)+"/"+Integer.toString(mp), TEXT_TYPE.MP);
        setValueOf(Integer.toString(hp)+"/"+Integer.toString(hp), TEXT_TYPE.HP);

        return rootView;
    }

    private void initView(){
        this.holder_atk = rootView.findViewById(R.id.holder_atk);
        this.holder_def = rootView.findViewById(R.id.holder_def);
        this.holder_hp = rootView.findViewById(R.id.holder_hp);
        this.holder_mp = rootView.findViewById(R.id.holder_mp);
        this.holder_walk = rootView.findViewById(R.id.holder_walk);
        this.holder_level = rootView.findViewById(R.id.holder_level);

        getActivity().registerReceiver(myRecv, new IntentFilter("test_ggg_intent"));
    }

    /**
     * 요거쓰셈
     * Use this method to set value of text
     * @param value
     * @param text_type
     */
    private void setValueOf(String value, TEXT_TYPE text_type){
        switch (text_type){
            case ATK:
                this.holder_atk.setText(value);
                break;
            case DEF:
                this.holder_def.setText(value);
                break;
            case HP:
                this.holder_hp.setText(value);
                break;
            case MP:
                this.holder_mp.setText(value);
                break;
            case WALK:
                this.holder_walk.setText(value);
                break;
            case LEVEL:
                this.holder_level.setText(value);
                break;
        }
    }

}
