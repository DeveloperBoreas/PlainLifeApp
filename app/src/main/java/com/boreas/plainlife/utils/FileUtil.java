package com.boreas.plainlife.utils;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FileUtil {

    public static void copyFilesFromAssetsToData(Context context, String fromName) {
        Observable.create(emitter -> {
            try {
                String fileName = context.getFilesDir().getAbsolutePath() + File.separator + fromName;
                InputStream is = context.getAssets().open(fromName);
                OutputStream os = new FileOutputStream(fileName);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
                emitter.onNext(true);
                emitter.onComplete();
            } catch (IOException e) {
                e.printStackTrace();
                emitter.onNext(false);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    if ((boolean) aBoolean) {
                        Logger.e("复制成功");
                    }else{
                        Logger.e("复制失败");
                    }
                });
    }
}
