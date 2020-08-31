package com.boreas.plainlife.mvp.views.fragments.location;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentLocationLovepeopleBinding;
import com.boreas.plainlife.framwork.ClickProxy;

import java.util.ArrayList;
import java.util.List;

public class LocationLoveFragment extends BaseFragment<FragmentLocationLovepeopleBinding> {
    private List<String> stringList = new ArrayList<String>() {{
        add("1");
        add("1");
        add("1");
    }};

    @Override
    public void lazyFetchData() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_location_lovepeople;
    }

    @Override
    public void initView() {
        this.binding.headLayout.headTitle.setText(getString(R.string.ilove_people));

        this.binding.list.setHasFixedSize(false);
        this.binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.list.setAdapter(new LovePeopleAdapter(getContext(),stringList,R.layout.location_lovepeople_item).setIsShowFooter((int) getResources().getDimension(R.dimen.main_bottomNavigtion_height_50)));
    }

    @Override
    public void initListener() {
        this.binding.addLove.setOnClickListener(new ClickProxy(v -> {
            Intent intent = new Intent(getActivity(),null);
            startActivity(intent);
        }));

    }

    @Override
    public void initComponent() {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
