package com.summer.test;

import com.summer.question.Day0104_Cal8Queen;
import com.summer.question.Day0105_TwoSum;
import com.summer.question.Day0106_ThreeSum;
import com.summer.question.Day0107_01Package;

/**
 * 每日一练
 */
public class KeepFarmAndCarryHard {

    public static void main(String[] args) {
        boolean today = true;
        boolean before = false;
        //八皇后问题
        if (before) {
            Day0104_Cal8Queen day20210104Cal8Queen = new Day0104_Cal8Queen();
            day20210104Cal8Queen.cal8Queen();
        }
        //两数求和的问题
        if (before) {
            Integer[] test = new Integer[]{-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,};
            Day0105_TwoSum day0105_twoSum = new Day0105_TwoSum();
            day0105_twoSum.checkNum(test, 4,-1);
        }
        //三数求和的问题
        if (before) {
            Integer[] test = new Integer[]{-1, -2,-3,0, 1, 2, 3, 4, 5, 6, 7, 8, 9,};
            Day0106_ThreeSum day0106_threeSum = new Day0106_ThreeSum();
            day0106_threeSum.checkNum(test, 4);
        }
        //01背包
        if (today) {

            Day0107_01Package aPackage = new Day0107_01Package();
            aPackage.pickStoneCache(0,0);
            System.out.println("最大的重量是："+aPackage.maxWeight);
        }

    }
}
