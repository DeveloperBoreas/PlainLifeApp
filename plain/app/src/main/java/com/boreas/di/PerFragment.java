package com.boreas.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by admin on 2018/4/13.
 */

@Scope
@Retention(RUNTIME)
public @interface PerFragment {
}
