package com.boreas.plainlife.mvp.views.viewinterfaces;

import com.boreas.plainlife.mvp.ViewInterface;
import com.boreas.plainlife.mvp.models.location.LocationUserListModel;

import java.util.ArrayList;

public interface LocationLoveFragmentInterface extends ViewInterface {
    /**
     * 查询已绑定用户成功
     */
    void onQueryBindUserSuccess(ArrayList<LocationUserListModel.Data> userList);

    /**
     * 数据请求失败回调
     */
    void onFailed(String message);
}
