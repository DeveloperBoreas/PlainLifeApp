package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.boreas.plainlife.mvp.ViewInterface;
import com.boreas.plainlife.mvp.models.equipLib.EquipLibraryReceModel;

import java.util.ArrayList;
import java.util.Map;

public interface EquipFragmentInterface extends ViewInterface {
    /**
     * 请求数据成功回调
     */
    void onSuccess(Map<String, ArrayList<EquipLibraryReceModel.EquipDataListModel>> s);

    /**
     * 请求数据失败回调
     */
    void onFailed(String msg);
}
