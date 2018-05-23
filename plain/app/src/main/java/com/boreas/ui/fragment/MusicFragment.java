package com.boreas.ui.fragment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import com.boreas.Constants;
import com.boreas.IMusicPlayer;
import com.boreas.IMusicPlayerListener;
import com.boreas.R;
import com.boreas.adapter.MusicAdapter;
import com.boreas.base.BaseActivity;
import com.boreas.base.BaseFragment;
import com.boreas.databinding.FragmentMusicBinding;
import com.boreas.di.componects.DaggerMusicFragmentComponent;
import com.boreas.di.modules.MusicFragmentModule;
import com.boreas.listener.ClickListener;
import com.boreas.listener.SeekBarChangeListener;
import com.boreas.model.entity.MusicEntity;
import com.boreas.model.entity.MusicEntityList;
import com.boreas.presenter.MusicPresenter;
import com.boreas.presenter.PresenterContract;
import com.boreas.service.MusicService;
import com.boreas.ui.activity.MainActivity;
import com.boreas.ui.activity.MusicMenuActivity;
import com.boreas.ui.recycle.OffsetDecoration;
import com.boreas.ui.widget.PullToRefreshView;
import com.boreas.utils.GsonHelper;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.boreas.service.MusicService.MUSIC_ACTION_INIT;
import static com.boreas.service.MusicService.MUSIC_ACTION_NEXT;
import static com.boreas.service.MusicService.MUSIC_ACTION_PLAY;
import static com.boreas.service.MusicService.MUSIC_ACTION_PREVIOUS;

/**
 * 作者 boreas
 * 日期 18-3-14
 * 邮箱 13051089921@163.com
 *
 * @author boreas
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MusicFragment extends BaseFragment implements PresenterContract.MusicView<MusicEntityList>, ClickListener<MusicEntityList.SongListBean>,
        View.OnClickListener{

    private FragmentMusicBinding binding = null;
    protected OffsetDecoration decoration = new OffsetDecoration();
    private boolean isFabOpen = false;
    private static final int MUSIC_MENU_REQUEST = 210;
    @Inject
    MusicPresenter presenter;
    private boolean isSearchMusic = true;
    private IMusicPlayer iMusicPlayerService;
    IMusicPlayerListener musicPlayerListener = new IMusicPlayerListener.Stub() {
        @Override
        public void action(int action, Message msg) throws RemoteException {
            mHandler.sendMessage(msg);
        }
    };
    private MusicAdapter adapter;
    private List<MusicEntityList.SongListBean> songListBeanList;
    private List<MusicEntityList.SongListBean> queryLocalList;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_music, container, false);
        binding.pullToRefresh.setOnRefreshListener(onRefreshListener);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        binding.fragmentMusicRecycle.setLayoutManager(linearLayoutManager);
        binding.fragmentMusicRecycle.setHasFixedSize(false);
        binding.fragmentMusicRecycle.removeItemDecoration(decoration);
        binding.fragmentMusicRecycle.addItemDecoration(decoration);
        queryLocalList = new ArrayList<>();
        binding.per.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.startandpause.setOnClickListener(this);
        binding.musicmsgSeekbar.setOnSeekBarChangeListener(new SeekBarChangeListener(iMusicPlayerService));
        binding.musicFab.attachToRecyclerView(binding.fragmentMusicRecycle);
        binding.musicFab.setOnClickListener(this);
        binding.musicSearchView.setSubmitButtonEnabled(false);
        binding.musicSearchView.setOnQueryTextListener(new OnQueryTextListener());
        binding.musicSearchView.setOnCloseListener(new OnCloseListener());
        binding.masking.setOnClickListener(this);
        binding.fabMenu.setOnClickListener(this);
        binding.fabSearch.setOnClickListener(this);
        binding.musicmsgBottom.setOnClickListener(this);
        adapter = new MusicAdapter<MusicEntityList.SongListBean>(getContext());
        adapter.setOnClickListener(this);
        binding.fragmentMusicRecycle.setAdapter(adapter);
        initDagger();
        initPresenter();
        return binding.getRoot();
    }

    private class OnCloseListener implements SearchView.OnCloseListener{

        @Override
        public boolean onClose() {
            Logger.d("onClose :");
            queryLocalList.clear();
            adapter.setData(songListBeanList);
            adapter.notifyDataSetChanged();
            return false;
        }
    }
    private class OnQueryTextListener implements SearchView.OnQueryTextListener{

        @Override
        public boolean onQueryTextSubmit(String query) {
            if(TextUtils.isEmpty(query)){
                return true;
            }
            queryLocalList.clear();
            if(isSearchMusic){
                for (int i = 0; i < songListBeanList.size(); i++) {
                    String title = songListBeanList.get(i).getTitle();
                    String author = songListBeanList.get(i).getAuthor();
                    if(title.equals(query) || author.equals(query)){
                        queryLocalList.add(songListBeanList.get(i));
                    }
                }
            }else{//搜索网络

            }

            adapter.setData(queryLocalList);
            adapter.notifyDataSetChanged();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String query) {

//            if(TextUtils.isEmpty(query)){
//                return true;
//            }
//            if(!isSearchMusic){//搜索本地
//                songListBeanList.clear();
//                for (int i = 0; i < songListBeanList.size(); i++) {
//                    String title = songListBeanList.get(i).getTitle();
//                    String author = songListBeanList.get(i).getAuthor();
//                    if(title.contains(query) || author.contains(query)){
//                        queryLocalList.add(songListBeanList.get(i));
//                    }
//                }
//            }
//            Logger.d("onQueryTextChange :" + queryLocalList.size());
//            adapter.setData(queryLocalList);
//            adapter.notifyDataSetChanged();
            return true;
        }
    }

    private PullToRefreshView.OnRefreshListener onRefreshListener = () -> {
        ((BaseActivity) getActivity()).showLoading();
        presenter.getMusicList(this, Constants.MusicType.HOT_MUSIC_LIST);
    };

    @Override
    public void onResume() {
        super.onResume();
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (!MainActivity.linkSuccess) {
                    SystemClock.sleep(300);
                }
                getActivity().runOnUiThread(() -> {
                    iMusicPlayerService = MainActivity.main.getMusicPlayerService();
                    if (iMusicPlayerService == null) {
                        Logger.d("iMusicPlayerService", "iMusicPlayerService 对象：" + iMusicPlayerService);
                    } else {
                        try {
                            iMusicPlayerService.registerListener(musicPlayerListener);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }.start();
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
        ((BaseActivity) getActivity()).showLoading();
        presenter.getMusicList(this, Constants.MusicType.HOT_MUSIC_LIST);
    }

    @Override
    public void getData(MusicEntityList musicEntityList) {
        ((BaseActivity) getActivity()).dismissLoading();
        binding.pullToRefresh.setRefreshing(false);
        if (musicEntityList == null) {
            return;
        }
        songListBeanList = musicEntityList.getSong_list();
        try {
            iMusicPlayerService.action(MUSIC_ACTION_INIT, GsonHelper.getGson().toJson(musicEntityList));
            adapter.setData(musicEntityList);
            adapter.notifyDataSetChanged();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void setViewData(MusicEntityList.SongListBean bean) {
        Glide.with(getActivity())
                .load(bean.getPic_small())
                .into(binding.musicmsgIcon);
        binding.musicmsgName.setText(bean.getTitle() + "");
        binding.musicmsgSingername.setText(bean.getAuthor() + "");
    }

    @Override
    public void onItemClick(View itemView, int position, MusicEntityList.SongListBean bean) {
        try {
            iMusicPlayerService.action(MUSIC_ACTION_PLAY, GsonHelper.getGson().toJson(bean));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemLongClick(View itemView, int position, MusicEntityList.SongListBean musicBean) {
    }


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
        try {
            MusicEntityList.SongListBean musicBean = (MusicEntityList.SongListBean) iMusicPlayerService.getCurrentSongInfo().obj;
            this.setViewData(musicBean);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void onPlay() {
        try {
            MusicEntityList.SongListBean musicBean = (MusicEntityList.SongListBean) iMusicPlayerService.getCurrentSongInfo().obj;
            if (musicBean == null) {
                return;
            }
            this.setViewData(musicBean);
            binding.startandpause.setImageResource(R.mipmap.playbar_btn_pause);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.per: //上一首
                    if (iMusicPlayerService != null) {
                        iMusicPlayerService.action(MUSIC_ACTION_PREVIOUS, "");
                    }
                    break;
                case R.id.next: //下一首
                    if (iMusicPlayerService != null) {
                        iMusicPlayerService.action(MUSIC_ACTION_NEXT, "");
                    }
                    break;
                case R.id.startandpause: //开始或者暂停
                    onPayBtnPress();
                    break;

                case R.id.music_fab:
                    if (!isFabOpen) {
                        openFabMenu(binding.musicFab);
                    } else {
                        closeFabMenu(binding.musicFab);
                    }
                    break;
                case R.id.masking:
                    closeFabMenu(binding.musicFab);
                    break;
                case R.id.fab_menu:
                    closeFabMenu(binding.musicFab);
                    Intent intent = new Intent(getActivity(), MusicMenuActivity.class);
                    startActivityForResult(intent, MUSIC_MENU_REQUEST);
                    break;
                case R.id.fab_search:
                    closeFabMenu(binding.musicFab);
                    break;
                case R.id.musicmsg_bottom:

                    break;
                default:
                    break;
            }
        } catch (RemoteException e) {

        }
    }

    private void onPayBtnPress() {
        try {
            Logger.d("MusicService.MUSIC_CURRENT_ACTION : " + MusicService.MUSIC_CURRENT_ACTION);
            switch (MusicService.MUSIC_CURRENT_ACTION) {
                case MusicService.MUSIC_ACTION_PLAY:
                    iMusicPlayerService.action(MusicService.MUSIC_ACTION_PAUSE, "");
                    binding.startandpause.setImageResource(R.mipmap.playbar_btn_pause);
                    break;
                case MusicService.MUSIC_ACTION_STOP:
                    binding.startandpause.setImageResource(R.mipmap.playbar_btn_play);
                    break;
                case MusicService.MUSIC_ACTION_PAUSE:
                    iMusicPlayerService.action(MusicService.MUSIC_ACTION_CONTINUE_PLAY, "");
                    binding.startandpause.setImageResource(R.mipmap.playbar_btn_play);
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private void openFabMenu(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0, -135, -125);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 0.7f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        binding.masking.startAnimation(alphaAnimation);
        binding.masking.setVisibility(View.VISIBLE);
        isFabOpen = true;
    }

    private void closeFabMenu(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", -135, 20, 0);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.7f, 0);
        alphaAnimation.setDuration(500);
        binding.masking.startAnimation(alphaAnimation);
        binding.masking.setVisibility(View.GONE);
        isFabOpen = false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MUSIC_MENU_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                int type = data.getIntExtra("type", 2);
                Logger.d("--------!!!!-------" + type);
                if (type > 0) {
                    ((BaseActivity) getActivity()).showLoading();
                    presenter.getMusicList(this, type);
                }

            }
        }
    }
}
