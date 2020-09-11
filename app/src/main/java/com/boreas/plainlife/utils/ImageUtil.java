package com.boreas.plainlife.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.R;

public class ImageUtil {

    public static void loadImg(Context context, String url, ImageView imageView) {
        final RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.bg)// 正在加载中的图片
                .error(R.mipmap.bg); // 加载失败的图片
        Glide.with(context)
                .load(url) // 图片地址
                .apply(options)
                .into(imageView);
    }

//    private static String handlerUrl(String imgUrl) {
//        return Constant.BASE_ICON_URL + imgUrl;
//    }

}
