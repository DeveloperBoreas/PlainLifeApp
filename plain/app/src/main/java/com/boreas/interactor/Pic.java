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

public class Pic extends UseCase {

    private PlainDataRepository repository = null;


    @Inject
    public Pic(PlainDataRepository repository){
        this.repository = repository;
    }


    @Override
    protected Observable buildUseCaseObservable() {
        return repository.getPicListInfo();
    }
}
