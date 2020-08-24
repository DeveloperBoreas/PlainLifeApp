package com.boreas.commonlib.xskinloader.skinDeployer;

import android.content.res.ColorStateList;
import android.view.View;
import android.widget.TextView;

import com.boreas.commonlib.xskinloader.entity.SkinAttr;
import com.boreas.commonlib.xskinloader.entity.SkinConfig;
import com.boreas.commonlib.xskinloader.skinInterface.ISkinResDeployer;
import com.boreas.commonlib.xskinloader.skinInterface.ISkinResourceManager;

/**
 * Created by Windy on 2018/1/11.
 * 文字提示颜色属性的换肤支持（android:textColorHint）
 */
public class TextColorHintResDeployer implements ISkinResDeployer {
    @Override
    public void deploy(View view, SkinAttr skinAttr, ISkinResourceManager resource) {
        if (!(view instanceof TextView)) {
            return;
        }

        TextView tv = (TextView) view;
        if (SkinConfig.RES_TYPE_NAME_COLOR.equals(skinAttr.attrValueTypeName)) {
            ColorStateList textHintColor = resource.getColorStateList(skinAttr.attrValueRefId);
            tv.setHintTextColor(textHintColor);
        }
    }
}
