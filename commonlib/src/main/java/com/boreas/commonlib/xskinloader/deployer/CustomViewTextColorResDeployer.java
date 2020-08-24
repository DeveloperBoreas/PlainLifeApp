package com.boreas.commonlib.xskinloader.deployer;

import android.view.View;

import com.boreas.commonlib.xskinloader.CustomTitleView;
import com.boreas.commonlib.xskinloader.entity.SkinAttr;
import com.boreas.commonlib.xskinloader.entity.SkinConfig;
import com.boreas.commonlib.xskinloader.skinInterface.ISkinResDeployer;
import com.boreas.commonlib.xskinloader.skinInterface.ISkinResourceManager;


/**
 * Created by Windy on 2018/1/10.
 */

public class CustomViewTextColorResDeployer implements ISkinResDeployer {

    @Override
    public void deploy(View view, SkinAttr skinAttr, ISkinResourceManager resource) {
        if (!(view instanceof CustomTitleView)) {
            return;
        }
        CustomTitleView titleView = (CustomTitleView) view;
        if (SkinConfig.RES_TYPE_NAME_COLOR.equals(skinAttr.attrValueTypeName)) {
            titleView.setTextColor(resource.getColor(skinAttr.attrValueRefId));
        }
    }
}
