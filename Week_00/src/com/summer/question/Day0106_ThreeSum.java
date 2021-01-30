package com.summer.question;

import java.util.*;

/**
 * 三数求和，在数组中，存在任意三个数有a+b+c=d的情况，请找出所有的值，并且不能重复
 * 解题思路1：遍历数组，然后按照两数求和进行剩余的数，递归查找输出
 * 时间复杂度 O(n)
 * 空间复杂度
 */
public class Day0106_ThreeSum {
    public void checkNum(Integer[] nums, Integer value) {
        List<List<Integer>> twoList = new ArrayList<>();
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //求差
            Integer otherNum = value - nums[i];
            Day0105_TwoSum twoSum = new Day0105_TwoSum();
            twoList = twoSum.checkNum(nums, otherNum, i);
            int a = nums[i];
            //加入新数字，并且排序
            twoList.forEach(list -> {
                list.add(a);
                list.sort(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1.compareTo(o2);
                    }
                });
                result.put(list.toString(), "[" + list.get(0) + "]");
            });
        }

        Iterator numsMap = result.entrySet().iterator();
        while (numsMap.hasNext()) {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) numsMap.next();
            System.out.println(entry.getKey());
        }
    }


    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        HashMap<String, List<Integer>> dictAnswer = new HashMap<>();
        //答案数组
        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> lists = twoSum(nums, 0 - nums[i], i);
            if (lists != null && lists.size() != 0) {
                for (List<Integer> list : lists) {
                    list.add(nums[i]);
                    list.sort(new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o1.compareTo(o2);
                        }
                    });
                    dictAnswer.put(list.get(0) + "+" + list.get(1) + "+" + list.get(2), list);
                }
            }
        }
        List<List<Integer>> anList = new ArrayList<>();
        Iterator iterator = dictAnswer.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> entry = (Map.Entry<Integer, List<Integer>>) iterator.next();
            anList.add(entry.getValue());
        }
        return anList;
    }

    public static List<List<Integer>> twoSum(int[] nums, int target, int sourceIndex) {
        //答案数组
        List<List<Integer>> answer = new ArrayList<>();
        HashMap<Integer, Integer> dictAnswer = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //如果是当前的数的下标，跳过
            if (i == sourceIndex) {
                continue;
            }
            //两数之和是target，若字典中存在另一个数，则存入字典
            if (dictAnswer.containsKey((target - nums[i]))) {
                dictAnswer.put(target - nums[i], nums[i]);
            } else {
                if (dictAnswer.get(nums[i])==null){
                    dictAnswer.put(nums[i], null);
                }
            }
        }
        Iterator iterator = dictAnswer.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iterator.next();
            if (entry.getValue() != null) {
                List<Integer> list = new ArrayList<>();
                if (entry.getValue() > entry.getKey()) {
                    list.add(entry.getValue());
                    list.add(entry.getKey());
                } else {
                    list.add(entry.getKey());
                    list.add(entry.getValue());
                }
                answer.add(list);
            }
        }
        return answer;
    }
}