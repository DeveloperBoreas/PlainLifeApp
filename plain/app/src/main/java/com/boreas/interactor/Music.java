package com.boreas.interactor;

import com.boreas.base.UseCase;
import com.boreas.repository.PlainDataRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 *
 * @author admin
 * @date 2018/2/23
 */

public class Music extends UseCase {

    private PlainDataRepository repository = null;
    private int type;

    @Inject
    public Music(PlainDataRepository repository){
        this.repository = repository;
    }

    public void setType (int type){
        this.type =type;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.getMusicInfo(type);
    }
}
