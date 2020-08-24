package com.boreas.plainlife.mvp.views.fragments;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.boreas.plainlife.R;
import com.boreas.plainlife.base.BaseFragment;
import com.boreas.plainlife.databinding.FragmentNoteParentlayoutBinding;
import com.boreas.plainlife.mvp.views.fragments.picnote.NoteListAdapter;

import java.util.ArrayList;

public class PicNoteFragment extends BaseFragment<FragmentNoteParentlayoutBinding> {

    private ArrayList<String> data = new ArrayList<String>(){{
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
        add("1");
    }};
    @Override
    public void lazyFetchData() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_note_parentlayout;
    }

    @Override
    public void initView() {
        this.binding.noteList.setHasFixedSize(false);
        this.binding.noteList.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.noteList.setAdapter(new NoteListAdapter(getContext(),data,R.layout.test_item));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initComponent() {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
