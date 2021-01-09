package com.summer.question;

/**
 * 01背包算法，动态规划
 * 核心思路是找出状态的叠加，并且可以合并相同的状态量
 * 先用回溯算法实现
 * 然后找出可以动态规划记录的
 */
public class Day0107_01Package {

    //最大值的记录
    public int maxWeight = -1;
    //现有的石头数
    private int[] stones = new int[]{-2, -1, 1, 2, 3, 4, 5, 6};
    //背包最大的承受重量
    private int maxPackage = 9;

    /**
     * 捡起装入下标为i的石头，以及当前的总重量
     *
     * @param index
     * @param weight
     */
    public void pickStone(int index, int weight) {
        //如果石头已经选择完毕，或者重量已经超过背包最大重量，返回
        if (index == stones.length || weight > maxPackage) {
            return;
        }
        if (weight > maxWeight) {
            maxWeight = weight;
        }
        //不拣这块石头
        pickStone(index + 1, weight);
        //拣这块石头
        pickStone(index + 1, weight + stones[index]);
    }

    /**
     * 备忘录记录已选择过的状态
     */
    private boolean[][] records = new boolean[5][10];

    /**
     * 选择最大重量的石头
     *
     * @param index
     * @param weight
     */
    public void pickStoneCache(int index, int weight) {
        //如果石头已经选择完毕，或者重量已经超过背包最大重量，返回
        if (index == stones.length || weight > maxPackage) {
            return;
        }
        if (weight > maxWeight) {
            maxWeight = weight;
        }
        if (records[index][weight]) {
            return;
        }
        records[index][weight] = true;
        //不拣这块石头
        pickStone(index + 1, weight);
        //拣这块石头
        pickStone(index + 1, weight + stones[index]);
    }


    /**s
     * 选择最有价值的装法
     *
     * @param weight
     * @return
     * @
     */
    public int pickMaxValue(int[] weights, int[] values, int weight, int value) {
        //记录所有装法的价值
        int[][] valueRecords = new int[5][weight + 1];
        //初始化每一种装法的价值
        for (int i = 0; i < valueRecords.length; i++) {
            for (int i1 = 0; i1 < valueRecords[i].length; i1++) {
                valueRecords[i][i1] = -1;
            }
        }
        //循环每一个石头
        valueRecords[0][0] = 0;
        //动态规划，状态转移
        for (int i = 0; i < weights.length; i++) {
            //不装这一个钻石
            for (int j = 0; j < weight; j++) {
                if (valueRecords[i - 1][j] >= 0) {
                    valueRecords[i][j] = valueRecords[i - 1][j];
                }
            }
            //装这一个钻石
            for (int j = 0; j < weight; j++) {
                if (valueRecords[i - 1][j] >= 0) {
                    //状态为上一层状态+这一次的状态
                    int newValue = valueRecords[i - 1][j] + values[i];
                    if (newValue > valueRecords[i][j + weights[i]]) {
                        valueRecords[i][j + weights[i]] = newValue;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= weight; ++j) {
            if (valueRecords[weights.length - 1][j] > maxvalue) {
                maxvalue = valueRecords[weights.length - 1][j];
            }
        }
        return maxvalue;
    }


}
