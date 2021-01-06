package com.summer.question;

import java.util.*;

/**
 * 三数求和，在数组中，存在任意三个数有a+b+c=d的情况，请找出所有的值，并且不能重复
 * 解题思路1：遍历数组，然后按照两数求和进行剩余的数，递归查找输出
 *  时间复杂度 O(n)
 *  空间复杂度
 */
public class Day0106_ThreeSum {
    public void checkNum(Integer[] nums, Integer value) {
        List<List<Integer>> twoList=new ArrayList<>();
         Map<String, String> result=new HashMap<>();
        for (int i = 0; i <nums.length; i++) {
            //求差
            Integer otherNum=value-nums[i];
            Day0105_TwoSum twoSum=new Day0105_TwoSum();
            twoList=twoSum.checkNum(nums,otherNum,i);
            int a=nums[i];
            //加入新数字，并且排序
            twoList.forEach(list->{
                list.add(a);
                list.sort(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1.compareTo(o2);
                    }
                });
                result.put(list.toString(),"["+list.get(0)+"]");
            });
        }

        Iterator numsMap = result.entrySet().iterator();
        while (numsMap.hasNext()) {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) numsMap.next();
            System.out.println(entry.getKey());
        }
    }
}
