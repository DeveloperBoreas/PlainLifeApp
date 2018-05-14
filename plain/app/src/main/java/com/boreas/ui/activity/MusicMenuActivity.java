package com.boreas.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.boreas.Constants;
import com.boreas.R;
import com.boreas.base.BaseActivity;
import com.boreas.databinding.ActivityMusicMenuBinding;

public class MusicMenuActivity extends BaseActivity {

    private ActivityMusicMenuBinding binding;
    private String[] names = {"新歌", "热歌", "摇滚", "爵士", "流行", "欧美金曲", "经典老歌", "情歌对唱", "影视金曲", "网络歌曲"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_menu);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_music_menu);
        initView();
    }

    private void initView() {
        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item,android.R.id.text1,names);
        this.binding.musicMenuList.setAdapter(simpleAdapter);
        this.binding.musicMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent.getAdapter();
                handlerOnTouch(adapter.getItem(position));
            }
        });
    }

    private void handlerOnTouch(String item) {
        int type;
        switch (item) {
            case "新歌":
                type = Constants.MusicType.NEW_MUSIC_LIST;
                break;
            case "热歌":
                type = Constants.MusicType.HOT_MUSIC_LIST;
                break;
            case "摇滚":
                type = Constants.MusicType.YAOGUN_MUSIC_LIST;
                break;
            case "爵士":
                type = Constants.MusicType.JUESHI_MUSIC_LIST;
                break;
            case "流行":
                type = Constants.MusicType.LIUXING_MUSIC_LIST;
                break;
            case "欧美金曲":
                type = Constants.MusicType.OUMEI_MUSIC_LIST;
                break;
            case "经典老歌":
                type = Constants.MusicType.JINGDIAN_MUSIC_LIST;
                break;
            case "情歌对唱":
                type = Constants.MusicType.QINGGE_MUSIC_LIST;
                break;
            case "影视金曲":
                type = Constants.MusicType.YINGSHI_MUSIC_LIST;
                break;
            case "网络歌曲":
                type = Constants.MusicType.NET_MUSIC_LIST;
                break;
            default:
                type = Constants.MusicType.HOT_MUSIC_LIST;
                break;
        }
        Intent intent = new Intent();
        intent.putExtra("type", type);
        setResult(RESULT_OK, intent);
        finish();
    }
}
