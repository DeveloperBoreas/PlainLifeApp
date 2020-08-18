package com.lcw.library.imagepicker.task;

import android.content.Context;
import android.util.Log;

import com.lcw.library.imagepicker.data.MediaFile;
import com.lcw.library.imagepicker.data.MediaFolder;
import com.lcw.library.imagepicker.listener.MediaLoadCallback;
import com.lcw.library.imagepicker.loader.ImageScanner;
import com.lcw.library.imagepicker.loader.MediaHandler;
import com.lcw.library.imagepicker.loader.PrivateScanner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 媒体库扫描任务（图片）
 * Create by: chenWei.li
 * Date: 2018/8/25
 * Time: 下午12:31
 * Email: lichenwei.me@foxmail.com
 */
public class PrivateAndAllLoadTask implements Runnable {

    private Context mContext;
    private PrivateScanner mPrivateScanner;
    private ImageScanner imageScanner;
    private MediaLoadCallback mMediaLoadCallback;

    public PrivateAndAllLoadTask(Context context, MediaLoadCallback mediaLoadCallback) {
        this.mContext = context;
        this.mMediaLoadCallback = mediaLoadCallback;
        mPrivateScanner = new PrivateScanner(context);
        imageScanner = new ImageScanner(context);
    }

    @Override
    public void run() {
        final ArrayList<MediaFile> queryAllImg = new ArrayList<>();
        if (mPrivateScanner != null) {
            mPrivateScanner.queryMedia()
                    .flatMap(new Function<ArrayList<MediaFile>, ObservableSource<ArrayList<MediaFile>>>() {
                        @Override
                        public ObservableSource<ArrayList<MediaFile>> apply(ArrayList<MediaFile> mediaFiles) throws Exception {
                            queryAllImg.addAll(mediaFiles);
                            return Observable.create(new ObservableOnSubscribe<ArrayList<MediaFile>>() {
                                @Override
                                public void subscribe(ObservableEmitter<ArrayList<MediaFile>> emitter) throws Exception {
                                    emitter.onNext(queryAllImg);
                                    emitter.onComplete();
                                }
                            });
                        }
                    }).subscribeOn(Schedulers.newThread())
                    .subscribe(new Consumer<ArrayList<MediaFile>>() {
                        @Override
                        public void accept(ArrayList<MediaFile> imageFileList) throws Exception {
                            if (mMediaLoadCallback != null) {
                                queryAllImg.addAll(imageScanner.queryMedia());
                                mMediaLoadCallback.loadMediaSuccess(MediaHandler.getImageFolder(mContext, queryAllImg));
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.e("获取PrivateFile Failed", throwable.getMessage());
                            queryAllImg.addAll(imageScanner.queryMedia());
                            mMediaLoadCallback.loadMediaSuccess(MediaHandler.getImageFolder(mContext, queryAllImg));
                        }
                    });
        }


    }

}
