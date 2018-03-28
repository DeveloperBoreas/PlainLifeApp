package com.boreas.base;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * @author boreas
 * @date 2018/2/23
 */

public abstract class UseCase {
    /**
     * 创建   Observable
     * @return
     */
    protected abstract Observable buildUseCaseObservable();

    public void execute (Subscriber subscriber){
        this.buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
