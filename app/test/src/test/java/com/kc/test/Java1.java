package com.kc.test;

import android.util.Log;

import org.junit.Test;

import kotlin.jvm.functions.Function2;

public class Java1 {


    public interface OnClickListener2 {
        public void onClick(int a);
    }

    public static class Button2 {
        public void setListener(OnClickListener2 listener) {
            listener.onClick(1);
        }
    }


    @Test
    public void test5() {
        Kotlin1.Companion.a();
        Kotlin1.Companion.a();

        Kotlin1Kt.b();
    }

    @Test
    public void test6() {
        Kotlin3 a = new Kotlin3();
        a.setAdd(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer invoke(Integer integer, Integer integer2) {
                return null;
            }
        });

        Kotlin6 b = new Kotlin6();
        Function2<Integer, Integer, Integer> sum = b.getSum();
        Integer invoke = sum.invoke(3, 4);
        Log.i("kcc", "invoke：" + invoke);
    }
}


