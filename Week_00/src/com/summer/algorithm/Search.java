package com.summer.algorithm;

public class Search {
    public static void main(String[] args) {
        Search search = new Search();
        int[] nums = new int[]{1, 3, 4, 5, 7, 9, 11, 13, 15, 17};
        search.halfSearch(nums, 1);
    }

    boolean flag = false;

    /**
     * @author hongzhengwei
     * @create 2021/3/1 2:30 下午
     * @desc 二分查找
     * 递归公式
     * f(p,q)=f(p,(p+q)/2)
     **/
    public boolean halfSearch(int[] nums, int target) {
        halfSearch(nums, target,  0, nums.length);
        return flag;
    }

    private void halfSearch(int[] nums, int target, int low, int high) {
        if (low > high) {
            return ;
        }
        int half = low + ((high - low) >> 1);
        if (nums[half] > target) {
            halfSearch(nums, target, low, half - 1);
        } else if (nums[half] < target) {
            halfSearch(nums, target, half + 1, high);
        } else {
           flag=true;
        }


    }


}
