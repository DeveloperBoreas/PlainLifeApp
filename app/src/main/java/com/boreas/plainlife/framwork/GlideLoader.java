package com.boreas.plainlife.framwork;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.lcw.library.imagepicker.utils.ImageLoader;
import com.boreas.plainlife.App;
import com.boreas.plainlife.R;

/**
 * 实现自定义图片加载
 * Create by: chenWei.li
 * Date: 2018/8/30
 * Time: 下午11:10
 * Email: lichenwei.me@foxmail.com
 */
public class GlideLoader implements ImageLoader {
    @Override
    public String loadImage(ImageView imageView, String imagePath) {
        return null;
    }

    @Override
    public void loadPreImage(ImageView imageView, String imagePath) {

    }

    @Override
    public void clearMemoryCache() {

    }

//    private RequestOptions mOptions = new RequestOptions()
//            .centerCrop()
//            .dontAnimate()
//            .format(DecodeFormat.PREFER_RGB_565)
//            .placeholder(R.mipmap.icon_image_default)
//            .error(R.mipmap.default_img_failed);
//
//    private RequestOptions mPreOptions = new RequestOptions()
//            .skipMemoryCache(true)
//            .error(R.mipmap.default_img_failed);
//
//    @Override
//    public String loadImage(ImageView imageView, String imagePath) {
//        Log.e(" loadImage 图片路径","imagePath :" + imagePath);
//        //小图加载
//        Glide.with(imageView.getContext()).load(imagePath).apply(mOptions).into(imageView);
//        return imagePath;
//    }
//
//    @Override
//    public void loadPreImage(ImageView imageView, String imagePath) {
//        //大图加载
//        Log.e("loadPreImage 图片路径","imagePath :" + imagePath);
//        Glide.with(imageView.getContext()).load(imagePath).apply(mPreOptions).into(imageView);
//
//    }
//
//    @Override
//    public void clearMemoryCache() {
//        //清理缓存
//        Glide.get(App.getInstance()).clearMemory();
//    }
}
