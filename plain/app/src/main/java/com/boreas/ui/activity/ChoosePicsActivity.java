package com.boreas.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.boreas.R;
import com.boreas.adapter.PicAdapter;
import com.boreas.base.BaseActivity;
import com.boreas.databinding.ActivityChoosePicsBinding;
import com.boreas.databinding.ChooseHeadImgBinding;
import com.boreas.listener.ClickListener;
import com.boreas.model.entity.PicEntity;
import com.boreas.presenter.PicPresenter;
import com.boreas.presenter.PresenterContract;
import com.boreas.ui.recycle.OffsetDecoration;

import java.util.List;

import javax.inject.Inject;

/**
 * @author boreas
 */
public class ChoosePicsActivity extends BaseActivity implements ClickListener<PicEntity.Pic> {

    private ActivityChoosePicsBinding binding = null;
    protected OffsetDecoration decoration = new OffsetDecoration();

    @Inject

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this,R.layout.activity_choose_pics);
        initView();
        initData();
    }
    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        binding.choosePicRecycle.setLayoutManager(layoutManager);
        binding.choosePicRecycle.setHasFixedSize(false);
        binding.choosePicRecycle.removeItemDecoration(decoration);
        binding.choosePicRecycle.addItemDecoration(decoration);
        this.initData();
    }
    private void initData(){
        PicAdapter adapter = new PicAdapter(this,null);
        adapter.setOnClickListener(this);
        this.binding.choosePicRecycle.setAdapter(adapter);
    }




    @Override
    public void onItemClick(View itemView, int position, PicEntity.Pic pic) {

    }

    @Override
    public void onItemLongClick(View itemView, int position, PicEntity.Pic pic) {

    }
}
