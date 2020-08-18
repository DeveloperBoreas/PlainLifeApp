package com.lcw.library.imagepicker.task;

import android.content.Context;
import android.util.Log;

import com.lcw.library.imagepicker.data.MediaFile;
import com.lcw.library.imagepicker.listener.MediaLoadCallback;
import com.lcw.library.imagepicker.loader.ImageScanner;
import com.lcw.library.imagepicker.loader.MediaHandler;
import com.lcw.library.imagepicker.loader.PrivateScanner;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 媒体库扫描任务（图片）
 * Create by: chenWei.li
 * Date: 2018/8/25
 * Time: 下午12:31
 * Email: lichenwei.me@foxmail.com
 */
public class PrivateLoadTask implements Runnable {

    private Context mContext;
    private PrivateScanner mPrivateScanner;
    private MediaLoadCallback mMediaLoadCallback;

    public PrivateLoadTask(Context context, MediaLoadCallback mediaLoadCallback) {
        this.mContext = context;
        this.mMediaLoadCallback = mediaLoadCallback;
        mPrivateScanner = new PrivateScanner(context);
    }

    @Override
    public void run() {
        if (mPrivateScanner != null) {
            mPrivateScanner.queryMedia()
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Consumer<ArrayList<MediaFile>>() {
                        @Override
                        public void accept(ArrayList<MediaFile> imageFileList) throws Exception {
                            if (mMediaLoadCallback != null) {
                                mMediaLoadCallback.loadMediaSuccess(MediaHandler.getImageFolder(mContext, imageFileList));
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.e("获取PrivateFile Failed", throwable.getMessage());
                        }
                    });
        }


    }

}
