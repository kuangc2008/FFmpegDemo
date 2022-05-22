package com.example.myapplication;

import org.junit.Test;

import java.util.Arrays;

public class A {
    public static final Object lock = new Object();

    public int currPrintThread = 0;
    private int i = 0;

    @Test
    public void printAltermate() {
        int[] nums = new int[] {
                1, 2, 3, 4, 5, 6, 7, 8, 9 , 10
        };


        int[][] abc = new int[2][];

        abc[0] = new int[2];

        int[][]  bcd = new int[2][3];


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i != nums.length -1) {
                    synchronized (lock) {
                        while (currPrintThread == 2) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                            }
                        }
                    }

                    System.out.println( "thread 1: " + nums[i]);
                    i++;
                    synchronized (lock) {
                        currPrintThread = 2;
                        lock.notifyAll();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i != nums.length -1) {
                    synchronized (lock) {
                        while (currPrintThread == 0 || currPrintThread == 1) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                            }
                        }
                    }

                    System.out.println("thread 2: " + nums[i]);
                    i++;
                    synchronized (lock) {
                        currPrintThread = 1;
                        lock.notifyAll();
                    }
                }
            }
        }).start();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





    @Test
    public void a() {
//        int[] a = new int[] {
//                1, 2, 3, 4 ,5 ,6 , 7
//        };
//
//        rotate2(a, 3);
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//        }

        int j = 1 >>>2;


        int b = 2 | 1;

        int[] a = new int[] {
                0, 1, 0, 3, 12, 18, 0
        };



        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }


    public void moveZeroes(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                moveZeroEnd(nums, i);
            }
        }
    }

    public void moveZeroEnd(int[] nums, int zeroPos) {
//        int curr = nums[zeroPos];
        for (int i = zeroPos;  i <= nums.length - 2; i++) {
            nums[i] = nums[i + 1];
        }
//        nums[nums.length -1 ] = curr;
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
    }

    public void rotate2(int[] nums, int k) {
        reverse(nums, 0,  nums.length - 1);
        reverse(nums, 0, k -1);
        reverse(nums, k, nums.length -1 );
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;

            start++;
            end--;
        }
    }
}
