package com.boreas.plainlife.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.base.adapter.BaseRecyclerHolder;
import com.boreas.plainlife.framwork.MyJzvdStd;
import com.boreas.plainlife.utils.ScreenUtil;

import java.io.FileDescriptor;
import java.util.List;


public class VideoListAdapter extends BaseRecyclerAdapter<String> {

    private Bitmap firstSuccen;

    public VideoListAdapter(Context context, List<String> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, String item, int position, boolean isScrolling) {
        MyJzvdStd video = holder.getView(R.id.video);
        video.setUp(item,"",MyJzvdStd.SCREEN_NORMAL);
        video.titleTextView.setTextSize(ScreenUtil.px2sp(getContext(),20));
        video.fullscreenButton.setVisibility(View.GONE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtil.px2dp(getContext(),355),ScreenUtil.px2dp(getContext(),355));
        video.startButton.setLayoutParams(params);
        video.bottomLinearLayout.setVisibility(View.GONE);
        Glide.with(getContext()).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(video.posterImageView);
    }

    private Bitmap getFirstSuccen(FileDescriptor assetFileDescriptor) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(assetFileDescriptor);
        Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime();
        return bitmap;
    }
}
