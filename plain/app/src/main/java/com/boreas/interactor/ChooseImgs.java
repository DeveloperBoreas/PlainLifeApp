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

public class ChooseImgs extends UseCase {

    private PlainDataRepository repository = null;



    @Inject
    public ChooseImgs(PlainDataRepository repository){
        this.repository = repository;
    }


    @Override
    protected Observable buildUseCaseObservable() {
        return repository.getPicListInfo();
    }
}
