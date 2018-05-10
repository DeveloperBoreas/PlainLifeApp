package com.boreas.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.boreas.R;
import com.boreas.adapter.PicAdapter;
import com.boreas.base.BaseActivity;
import com.boreas.databinding.ActivityChoosePicsBinding;
import com.boreas.di.componects.DaggerChooseImgsComponent;
import com.boreas.di.modules.ChooseImgsModule;
import com.boreas.listener.ClickListener;
import com.boreas.model.entity.ImageFolderEntity;
import com.boreas.model.entity.PicEntity;
import com.boreas.presenter.ChooseImgsPresenter;
import com.boreas.presenter.PicPresenter;
import com.boreas.presenter.PresenterContract;
import com.boreas.ui.recycle.OffsetDecoration;
import com.boreas.ui.widget.ImagesFolderPopupWindow;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author boreas
 */
public class ChoosePicsActivity extends BaseActivity implements
        PresenterContract.ChooseImgsView<HashMap<String, ArrayList<PicEntity.Pic>>>,
        ClickListener<PicEntity.Pic>,
        ImagesFolderPopupWindow.FinishOnItemClickListener{

    private ActivityChoosePicsBinding binding = null;
    protected OffsetDecoration decoration = new OffsetDecoration();
    private ArrayList<ImageFolderEntity> arrayList = new ArrayList<>();
    @Inject
    ChooseImgsPresenter presenter;
    private PicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_pics);
        initComponent();
        initPresenter();
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getLocalImages();
    }

    private void initPresenter() {
    }

    private void initComponent() {
        DaggerChooseImgsComponent.builder().chooseImgsModule(new ChooseImgsModule(this)).build().inject(ChoosePicsActivity.this);
    }

    private void initView() {
        ImagesFolderPopupWindow pop = new ImagesFolderPopupWindow(this);
        pop.setFinishOnItemClickListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        binding.choosePicRecycle.setLayoutManager(layoutManager);
        binding.choosePicRecycle.setHasFixedSize(false);
        binding.choosePicRecycle.removeItemDecoration(decoration);
        binding.choosePicRecycle.addItemDecoration(decoration);
        binding.selectImagesFromFolder.setOnClickListener((view)->{
            pop.showPopupWindow(binding.selectImagesFromFolder);
        });
    }

    private void initData() {

    }


    @Override
    public void onItemClick(View itemView, int position, PicEntity.Pic pic) {
        Intent intentToClip = new Intent();
        intentToClip.putExtra("path",pic.picParentPath);
        this.setResult(RESULT_OK,intentToClip);
        this.finish();
    }

    @Override
    public void onItemLongClick(View itemView, int position, PicEntity.Pic pic) {

    }

    @Override
    public void getLocalImgs(HashMap<String, ArrayList<PicEntity.Pic>> map) {
        if (map == null && map.size() == 0) {
            return;
        }
        Iterator<Map.Entry<String, ArrayList<PicEntity.Pic>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,ArrayList<PicEntity.Pic>> entity = iterator.next();
            ImageFolderEntity imageFolderEntity = new ImageFolderEntity();
            imageFolderEntity.firstImage = entity.getValue().get(0).picParentPath;
            imageFolderEntity.folerName = entity.getKey();
            imageFolderEntity.imgs = entity.getValue().size();
            if(!entity.getKey().equals(getResources().getString(R.string.all_image))){
                imageFolderEntity.isSelected = false;
                arrayList.add(imageFolderEntity);
            }else{
                imageFolderEntity.isSelected = true;
                arrayList.add(0,imageFolderEntity);
            }
        }
        adapter = new PicAdapter(this,null);
        adapter.setOnClickListener(this);
        this.binding.choosePicRecycle.setAdapter(adapter);
    }

    @Override
    public void OnFinishedClick(String name) {

    }
}
