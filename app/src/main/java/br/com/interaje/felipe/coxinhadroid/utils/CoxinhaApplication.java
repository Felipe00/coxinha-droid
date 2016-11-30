package br.com.interaje.felipe.coxinhadroid.utils;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by felipe on 30/11/16.
 */

public class CoxinhaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }
}
