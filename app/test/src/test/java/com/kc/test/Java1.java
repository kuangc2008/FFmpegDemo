package com.kc.test;

import org.junit.Test;

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
}


