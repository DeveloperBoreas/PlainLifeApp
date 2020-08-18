package com.lcw.library.imagepicker.loader;


import android.content.Context;
import android.os.Environment;

import com.lcw.library.imagepicker.data.MediaFile;
import java.io.File;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import static com.oden.syd_camera.camera.CameraInterface.*;
import static com.oden.syd_camera.camera.CameraParaUtil.*;

/**
 * private文件夹扫描类(图片/视频(视频暂无))
 * Create by: chenWei.li
 * Date: 2018/8/21
 * Time: 上午1:01
 * Email: lichenwei.me@foxmail.com
 */
public class PrivateScanner {
    private Context context;

    public PrivateScanner(Context context) {
        this.context = context;
    }


    protected File getScanFile() {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.separator + PRIVATE_FILE_PATH;
        return new File(path);
    }


    public Observable<ArrayList<MediaFile>> queryMedia() {
        return Observable.create(new ObservableOnSubscribe<ArrayList<MediaFile>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<MediaFile>> emitter) throws Exception {
                File rootFile = getScanFile();
                if (rootFile.exists()) {
                    if (rootFile.isDirectory()) {
                        ArrayList<MediaFile> privateFiles = new ArrayList<>();
                        File[] files = rootFile.listFiles();
                        MediaFile mediaFile = null;
                        for (File file : files) {
                            if (file.getAbsolutePath().endsWith(MEDIA_TYPE_PRIVATE_IMAGE_SUFFIX)) {
                                mediaFile = new MediaFile();
                                mediaFile.setPath(file.getAbsolutePath());
                                mediaFile.setMime(String.valueOf(MEDIA_TYPE_PRIVATE_IMAGE));
                                mediaFile.setFolderId(context.getResources().getIdentifier(rootFile.getAbsolutePath(), null, null));
                                mediaFile.setDuration(0);
                                mediaFile.setFolderName(PRIVATE_FILE_PATH);
                                mediaFile.setDateToken(System.currentTimeMillis());
                            } else if (file.getAbsolutePath().endsWith(MEDIA_TYPE_PRIVATE_VIDEO_SUFFIX)) {
                                mediaFile = new MediaFile();
                                mediaFile.setPath(file.getAbsolutePath());
                                mediaFile.setMime(String.valueOf(MEDIA_TYPE_PRIVATE_VIDEO));
                                mediaFile.setFolderId(context.getResources().getIdentifier(rootFile.getAbsolutePath(), null, null));
                                mediaFile.setDateToken(System.currentTimeMillis());
                                mediaFile.setFolderName(PRIVATE_FILE_PATH);
                                mediaFile.setDuration(100);
                            }
                            privateFiles.add(mediaFile);
                        }
                        if (privateFiles.isEmpty()) {
                            emitter.onError(new NullPointerException("没有查询到文件。"));
                            return;
                        }
                        emitter.onNext(privateFiles);
                        emitter.onComplete();
                    }
                } else {
                    emitter.onError(new NullPointerException(PRIVATE_FILE_PATH + " 文件不存在。"));
                }
            }
        });
    }
}
