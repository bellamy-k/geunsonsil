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
        WALK
    }

    private View rootView;
    private TextView holder_atk, holder_def, holder_hp, holder_mp, holder_walk;

    private volatile int walk_counter = 0;

    private BroadcastReceiver myRecv = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("Walking Detected", intent.toString());
            walk_counter++;
            setValueOf(Integer.toString(walk_counter), TEXT_TYPE.WALK);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initView();

        setValueOf("sddfsdf", TEXT_TYPE.ATK);

        return rootView;
    }

    private void initView(){
        this.holder_atk = rootView.findViewById(R.id.holder_atk);
        this.holder_def = rootView.findViewById(R.id.holder_def);
        this.holder_hp = rootView.findViewById(R.id.holder_hp);
        this.holder_mp = rootView.findViewById(R.id.holder_mp);
        this.holder_walk = rootView.findViewById(R.id.holder_walk);

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
        }
    }

}
