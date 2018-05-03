package com.boreas.interactor;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.boreas.base.UseCase;
import com.boreas.repository.PlainDataRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 *
 * @author admin
 * @date 2018/2/23
 */

public class MusicInfo extends UseCase {

    private PlainDataRepository repository = null;
    private int type;
    private Activity activity;
    @Inject
    public MusicInfo(PlainDataRepository repository){
        this.repository = repository;
    }

    public void setType (int type){
        this.type =type;
    }
    public void setActivity(Fragment fragment){
        activity = fragment.getActivity();
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.getMusicInfo(activity,type);
    }
}
