package com.boreas.plainlife.mvp.views.activitys;


import android.view.View;

import com.dyman.easyshow3d.ModelFactory;
import com.dyman.easyshow3d.bean.ModelObject;
import com.dyman.easyshow3d.imp.ModelLoaderListener;
import com.orhanobut.logger.Logger;
import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.databinding.Activity3dBinding;

import java.io.File;

public class Show3DActivity extends BaseActivity<Activity3dBinding> {


    @Override
    public int setContentView() {
        return R.layout.activity_3d;
    }

    @Override
    protected void initView() {
        showLoadingDialog(false);
        String filePath = getFilesDir() + File.separator + "3dmodel.obj";
        Logger.i("filePath： " + filePath);
        ModelFactory.decodeFile(this, filePath, new ModelLoaderListener() {
            @Override
            public void loadedUpdate(float progress) {
                Logger.i("模型解析进度： " + progress);
            }

            @Override
            public void loadedFinish(ModelObject modelObject) {
                if (modelObject != null) {
                    //  解析完成，显示模型
                    dismissLoadingDialog();
                    binding.progress.setVisibility(View.GONE);
                    binding.showModelView.setModelObject(modelObject);
                }
            }

            @Override
            public void loaderCancel() {
            }

            @Override
            public void loaderFailure() {
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initComponent() {

    }

    @Override
    protected void initData() {

    }
}
