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

    public List<List<Integer>> checkNum(Integer[] nums, Integer value, Integer unCheckIndex) {
        if (nums == null) {
            return resultList;
        }
        int length = nums.length;
        if (length == 0) {
            return resultList;
        }
        for (int i = 0; i < nums.length; i++) {
            if (unCheckIndex == i) {
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

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * 你可以按任意顺序返回答案。
     *
     * @param nums
     * @param target
     * @return
     */

    public int[] twoSum(int[] nums, int target) {
        //key是值，value是下标
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //若存在组合，结对存入散列表
            if (result.containsKey(target - nums[i])) {
                int[] answer = new int[]{result.get(target - nums[i]), i};
                return answer;
            }
            if (!result.containsKey(nums[i])) {
                result.put(nums[i], i);
            }
        }
        return null;
    }


}
