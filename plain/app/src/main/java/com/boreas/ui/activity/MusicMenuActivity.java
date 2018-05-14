package com.boreas.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import com.boreas.R;
import com.boreas.base.BaseActivity;
import com.boreas.databinding.ActivityMusicMenuBinding;

public class MusicMenuActivity extends BaseActivity {

    private ActivityMusicMenuBinding binding;
    private String[] names = {"新歌","热歌","摇滚","爵士","流行","欧美金曲","经典老歌","情歌对唱","影视金曲","网络歌曲"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_menu);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_music_menu);
        initView();
    }

    private void initView() {
        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(this,android.R.layout.activity_list_item);
        this.binding.musicMenuList.setAdapter(simpleAdapter);
        this.binding.musicMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("type",1);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
