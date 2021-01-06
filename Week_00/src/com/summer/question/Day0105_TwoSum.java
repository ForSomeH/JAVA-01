package com.summer.question;

import java.util.*;

/**
 * 两数求和，在数组中，存在任意两个数有a+b=c的情况，请找出所有的值，并且不能重复
 * 解题思路，无序数组可以利用散列表，来精准配对和输出
 * 有序数组可以使用头尾指针法
 * 时间复杂度 O(n)
 * 空间复杂度 O(n)
 */
public class Day0105_TwoSum {
    //结果集散列表
    HashMap<Integer, Integer> result = new HashMap<>();
    List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> checkNum(Integer[] nums, Integer value,Integer unCheckIndex) {
        if (nums == null) {
            return resultList;
        }
        int length = nums.length;
        if (length == 0) {
            return resultList;
        }
        for (int i = 0; i < nums.length; i++) {
            if (unCheckIndex==i){
                continue;
            }
            //若存在组合，结对存入散列表
            if (result.containsKey(value - nums[i])) {
                result.put(value - nums[i], nums[i]);
                continue;
            }
            if (!result.containsKey(nums[i])) {
                result.put(nums[i], null);
            }
        }

        Iterator numsMap = result.entrySet().iterator();
        while (numsMap.hasNext()) {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) numsMap.next();
            if (entry.getValue() != null) {
                System.out.println("[" + entry.getKey() + "," + entry.getValue() + "]");
                List<Integer> result = new ArrayList();
                result.add(entry.getKey());
                result.add(entry.getValue());
                resultList.add(result);
            }
        }
        return resultList;


    }


}
