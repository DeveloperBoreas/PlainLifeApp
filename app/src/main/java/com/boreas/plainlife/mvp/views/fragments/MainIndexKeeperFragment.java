package com.boreas.plainlife.mvp.views.fragments;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.boreas.plainlife.App;
import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.VideoListAdapter;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentIndexKeeperLayoutBinding;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.framwork.KeeperChartDataset;
import com.boreas.plainlife.internal.components.DaggerMainKeeperFragmentComponect;
import com.boreas.plainlife.internal.modules.MainKeeperFragmentModule;
import com.boreas.plainlife.mvp.views.activitys.KeeperMyOperationActivity;
import com.boreas.plainlife.mvp.views.activitys.KeeperUpComingActivity;
import com.boreas.plainlife.mvp.views.activitys.MessageActivity;
import com.boreas.plainlife.mvp.views.viewinterfaces.KeeperFragmentInterface;

import java.util.ArrayList;

import cn.jzvd.Jzvd;

public class MainIndexKeeperFragment extends BaseFragment<FragmentIndexKeeperLayoutBinding> implements KeeperFragmentInterface {
    private ArrayList<String> videoList;
    private KeeperChartDataset keeperChartDataset;
    private static final int T = 8768768; //温度
    private static final int H = 8768769; //湿度

    /**
     * 延迟获取数据
     */
    @Override
    public void lazyFetchData() {


    }

    @Override
    public int setContent() {
        return R.layout.fragment_index_keeper_layout;
    }

    @Override
    public void initView() {
        this.initChartLine();
        this.initVideoListData();
        this.initVideoList();
        this.handlerShowTOrShowHToView(this.T);
    }

    private void initVideoListData() {
        videoList = new ArrayList();
        videoList.add("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4");
        videoList.add("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        videoList.add("http://vjs.zencdn.net/v/oceans.mp4");
        videoList.add("https://media.w3.org/2010/05/sintel/trailer.mp4");
        videoList.add("http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4");
    }

    private void initChartLine() {
        keeperChartDataset = new KeeperChartDataset(this.binding.lineChart);
        keeperChartDataset.showT();
    }

    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    private void initVideoList() {
        VideoListAdapter videoListAdapter = new VideoListAdapter(getActivity(), videoList, R.layout.videolist_item_layout);
        this.binding.keeperVideolist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        this.binding.keeperVideolist.setHasFixedSize(true);
        this.binding.keeperVideolist.setAdapter(videoListAdapter);
    }

    @Override
    public void initListener() {
        this.binding.T.setOnClickListener(new ClickProxy(v -> {
            this.keeperChartDataset.showT();
            this.handlerShowTOrShowHToView(this.T);
        }));
        this.binding.H.setOnClickListener(new ClickProxy(v -> {
            this.keeperChartDataset.showH();
            this.handlerShowTOrShowHToView(this.H);
        }));
        this.binding.llTodo.setOnClickListener(new ClickProxy(v -> {
            Intent intent = new Intent(getActivity(), KeeperUpComingActivity.class);
            startActivity(intent);
        }));
        this.binding.llDeal.setOnClickListener(new ClickProxy(v -> {
            startActivity(new Intent(getActivity(), KeeperMyOperationActivity.class));
        }));
        this.binding.llMessage.setOnClickListener(new ClickProxy(v -> startActivity(new Intent(getActivity(), MessageActivity.class))));
    }

    /**
     * 处理显示温度还是湿度
     *
     * @param type T温度  H湿度
     */
    private void handlerShowTOrShowHToView(int type) {
        if (type == T) {
            this.binding.H.setTextColor(getResources().getColor(R.color.white));
            this.binding.HLine.setVisibility(View.INVISIBLE);
            this.binding.T.setTextColor(getResources().getColor(R.color.light3_blue));
            this.binding.TLine.setVisibility(View.VISIBLE);
        } else {
            this.binding.T.setTextColor(getResources().getColor(R.color.white));
            this.binding.TLine.setVisibility(View.INVISIBLE);
            this.binding.H.setTextColor(getResources().getColor(R.color.light3_blue));
            this.binding.HLine.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initComponent() {
        DaggerMainKeeperFragmentComponect.builder()
                .netComponent(App.getInstance().getmNetComponent())
                .mainKeeperFragmentModule(new MainKeeperFragmentModule(this))
                .build().inject(this);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed() {

    }

    @Override
    public void noNetWork() {

    }

    @Override
    public void noData() {

    }

    @Override
    public void onDisLoadingDialog() {

    }

    /**
     * 显示LoadingDialog
     */
    @Override
    public void onShowLoadingDialog() {

    }
}
