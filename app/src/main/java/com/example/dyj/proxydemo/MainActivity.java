package com.example.dyj.proxydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity implements InvocationHandler {

    private AnimationInterface mAniamationInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAniamationInterface = (AnimationInterface) Proxy.newProxyInstance(AnimationInterface.class.getClassLoader(), new Class[]{AnimationInterface.class}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
        return m.invoke(this, args);
    }

    public void stop() {
        Log.d("dyj", "stop");
    }

    public void start() {
        Log.d("dyj", "start");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                mAniamationInterface.start();
                break;
            case R.id.stop:
                mAniamationInterface.stop();
                break;
            default:
                Log.d("dyj", "default");
        }
    }
}
