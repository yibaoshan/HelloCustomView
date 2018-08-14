package com.xiaobao.hellocustomview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xiaobao.hellocustomview.view.HelloBezierView;
import com.xiaobao.hellocustomview.view.HelloPathView;

import java.lang.reflect.Constructor;


public class ShowViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getView();
        if (view != null) {
            setContentView(view);
        }
    }

    private View getView() {
        try {
            String className = "com.xiaobao.hellocustomview.view." + getIntent().getStringExtra(getIntent().getAction());
            Class aClass = Class.forName(className);//获取类
            Constructor constructor = aClass.getConstructor(Context.class);//获取构造器，并指定构造参数
            Object object = constructor.newInstance(this);
            if (object instanceof HelloPathView) {
                return new HelloPathView(this);
            }else if(object instanceof HelloBezierView){
                return new HelloBezierView(this);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
