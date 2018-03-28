package com.boreas.di.modules;

import com.boreas.base.UseCase;
import com.boreas.interactor.Login;
import com.boreas.repository.PlainDataRepository;

import dagger.Module;
import dagger.Provides;

/**
 * 作者 boreas
 * 日期 18-3-7
 * 邮箱 13051089921@163.com
 * @author boreas
 */
@Module
public class LoginModule {

    public LoginModule(){
    }

    @Provides
    public Login provideLoginUseCase(PlainDataRepository repository){
        return new Login(repository);
    }
}
