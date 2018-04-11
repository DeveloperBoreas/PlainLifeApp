package com.boreas.ui.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boreas.IMusicPlayer;
import com.boreas.IMusicPlayerListener;
import com.boreas.R;
import com.boreas.adapter.MusicAdapter;
import com.boreas.base.BaseFragment;
import com.boreas.databinding.FragmentMusicBinding;
import com.boreas.di.componects.DaggerMusicFragmentComponent;
import com.boreas.di.modules.MusicFragmentModule;
import com.boreas.listener.ClickListener;
import com.boreas.listener.SeekBarChangeListener;
import com.boreas.model.entity.MusicEntity;
import com.boreas.presenter.MusicPresenter;
import com.boreas.presenter.PresenterContract;
import com.boreas.service.MusicService;
import com.boreas.ui.activity.MainActivity;
import com.boreas.ui.recycle.OffsetDecoration;
import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import static com.boreas.service.MusicService.MUSIC_ACTION_PLAY;

/**
 * 作者 boreas
 * 日期 18-3-14
 * 邮箱 13051089921@163.com
 *
 * @author boreas
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MusicFragment extends BaseFragment implements PresenterContract.MusicView,ClickListener<MusicEntity> {

    private FragmentMusicBinding binding = null;
    protected OffsetDecoration decoration = new OffsetDecoration();

    @Inject
    MusicPresenter presenter;

    private IMusicPlayer iMusicPlayerService;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_music, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        binding.fragmentMusicRecycle.setLayoutManager(linearLayoutManager);
        binding.fragmentMusicRecycle.setHasFixedSize(false);
        binding.fragmentMusicRecycle.removeItemDecoration(decoration);
        binding.fragmentMusicRecycle.addItemDecoration(decoration);

        try {
            iMusicPlayerService = MainActivity.context.getMusicPlayer();
            binding.musicmsgSeekbar.setOnSeekBarChangeListener(new SeekBarChangeListener(iMusicPlayerService));
            iMusicPlayerService.registerListener(musicPlayerListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        initDagger();
        initPresenter();
        return binding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            if (iMusicPlayerService != null) {
                iMusicPlayerService.unregisterListener(musicPlayerListener);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void initPresenter() {
    }

    private void initDagger() {
        DaggerMusicFragmentComponent.builder().musicFragmentModule(new MusicFragmentModule(this)).build().inject(this);
    }

    @Override
    public void lazyFetchData() {
        presenter.getMusicList();
    }

    @Override
    public void getData(List list) {
        MusicAdapter adapter = new MusicAdapter(list);
        adapter.setOnClickListener(this);
        binding.fragmentMusicRecycle.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View itemView, int position, MusicEntity musicEntity) {
        //将歌曲信息展示到bottom栏

        //并且1秒播放歌曲
        try {
            iMusicPlayerService.action(MUSIC_ACTION_PLAY,"");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //点击bottom栏  跳转到歌词界面
        binding.musicBottom.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(),null);
            startActivity(intent);
        });
    }

    @Override
    public void onItemLongClick(View itemView, int position, MusicEntity musicEntity) {

    }

    IMusicPlayerListener musicPlayerListener = new IMusicPlayerListener.Stub(){

        @Override
        public void action(int action, Message msg) throws RemoteException {
            mHandler.sendMessage(msg);
        }
    };
    private final int UPDATE_UI = 23;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MusicService.MUSIC_ACTION_SEEK_PLAY:
                    updateSeek(msg);
                    break;
                case MusicService.MUSIC_ACTION_PLAY:
                case UPDATE_UI:
                    onPlay();
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    };
    private void updateSeek(Message msg) {
        int currentPosition = msg.arg1;
        int totalDuration = msg.arg2;
        binding.musicmsgSeekbar.setMax(totalDuration);
        binding.musicmsgSeekbar.setProgress(currentPosition);
    }
    private void onPlay() {
        try {
            MusicEntity.MusicBean musicBean =
                    (MusicEntity.MusicBean) iMusicPlayerService.getCurrentSongInfo().obj;

            if (musicBean == null) {
                return;
            }
            binding.startandpause.setImageResource(R.mipmap.playbar_btn_pause);
            Glide.with(getContext()).load(musicBean.getAlbumpic_small())
                    .into(binding.musicmsgIcon);
            binding.musicmsgName.setText(musicBean.getSongname());
//            mPlayBarSinger.setText(musicBean.author);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
