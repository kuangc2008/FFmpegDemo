package com.example.myapplication;

import org.junit.Test;

import java.util.Arrays;

public class A {






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
