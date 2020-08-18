package com.xwzx.equipmanager.mvp.views.activitys;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.TabLayoutViewPagerAdapter;
import com.xwzx.equipmanager.base.BaseActivity;
import com.xwzx.equipmanager.base.BaseFragment;
import com.xwzx.equipmanager.databinding.ActivityKeeperUpComingBinding;
import com.xwzx.equipmanager.framwork.ClickProxy;
import com.xwzx.equipmanager.mvp.views.fragments.keeperDisposedFragment;
import com.xwzx.equipmanager.mvp.views.fragments.keeperUpComingFragment;

import java.util.ArrayList;

/**
 * 库管，我的待办
 */
public class KeeperUpComingActivity extends BaseActivity<ActivityKeeperUpComingBinding> {

    private ArrayList<BaseFragment> tabFragments;
    private String[] titles = new String[]{"待办 5", "已处理 5"};

    @Override
    public int setContentView() {
        return R.layout.activity_keeper_up_coming;
    }

    @Override
    protected void initView() {
        this.initTabFragments();
        this.binding.headLayout.headTitle.setText(getResources().getString(R.string.my_todo));
        this.binding.headLayout.rightTitle.setVisibility(View.GONE);
        this.binding.headLayout.rightBtn.setVisibility(View.GONE);
        TabLayoutViewPagerAdapter viewPagerAdapter = new TabLayoutViewPagerAdapter(getSupportFragmentManager(), this.tabFragments, titles);
        this.binding.viewPager.setOffscreenPageLimit(this.tabFragments.size());
        this.binding.viewPager.setAdapter(viewPagerAdapter);
        this.binding.tabLayout.setupWithViewPager(this.binding.viewPager);
        for (int i = 0; i < this.binding.tabLayout.getTabCount(); i++) {
            TabLayout.Tab tabAt = this.binding.tabLayout.getTabAt(i);
            View view = getLayoutInflater().inflate(R.layout.tab_item, null);
            TextView tab = view.findViewById(R.id.tab);
            tab.setText(titles[i]);
            tabAt.setCustomView(view);
        }
    }

    private void initTabFragments() {
        this.tabFragments = new ArrayList<>();
        this.tabFragments.add(new keeperUpComingFragment());
        this.tabFragments.add(new keeperDisposedFragment());
    }

    @Override
    protected void initListener() {
        this.binding.headLayout.back.setOnClickListener(new ClickProxy(v -> {
            finish();
        }));

    }

    @Override
    protected void initComponent() {

    }

    @Override
    protected void initData() {

    }
}
