package com.boreas.interactor;

import com.boreas.base.UseCase;
import com.boreas.repository.PlainDataRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * 作者 boreas
 * 日期 18-3-6
 * 邮箱 13051089921@163.com
 * @author boreas
 */

public class Login extends UseCase{

    private PlainDataRepository repository = null;
    private String name = null;
    private String password = null;

    @Inject
    public Login(PlainDataRepository repository){
        this.repository = repository;
    }

    public Login setData(String name,String password){
        this.name = name;
        this.password = password;
        return this;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.login(name,password);
    }
}
