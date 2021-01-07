package com.summer.question;

/**
 * 01背包算法，动态规划
 * 核心思路是找出状态的叠加，并且可以合并相同的状态量
 * 先用回溯算法实现
 * 然后找出可以动态规划记录的
 */
public class Day0107_01Package {

    //最大值的记录
    public  int maxWeight=-1;
    //现有的石头数
    private int[] stones=new int[]{-2,-1,1,2,3,4,5,6};
    //背包最大的承受重量
    private int maxPackage=9;

    /**
     * 捡起装入下标为i的石头，以及当前的总重量
     * @param index
     * @param weight
     */
   public void pickStone(int index,int weight){
        //如果石头已经选择完毕，或者重量已经超过背包最大重量，返回
        if (index==stones.length||weight>maxPackage){
            return;
        }
        if (weight>maxWeight){
            maxWeight=weight;
        }
        //不拣这块石头
        pickStone(index+1,weight);
        //拣这块石头
        pickStone(index+1,weight+stones[index]);
    }

    /**
     * 备忘录记录已选择过的状态
     */
    private boolean[][] records=new boolean[5][10];

    public void pickStoneCache(int index,int weight){
        //如果石头已经选择完毕，或者重量已经超过背包最大重量，返回
        if (index==stones.length||weight>maxPackage){
            return;
        }
        if (weight>maxWeight){
            maxWeight=weight;
        }
        if (records[index][weight]){
            return;
        }
        records[index][weight]=true;
        //不拣这块石头
        pickStone(index+1,weight);
        //拣这块石头
        pickStone(index+1,weight+stones[index]);
    }

    public void pickMaxValue(int index,int weight){
        //如果石头已经选择完毕，或者重量已经超过背包最大重量，返回
        if (index==stones.length||weight>maxPackage){
            return;
        }
        if (weight>maxWeight){
            maxWeight=weight;
        }
        if (records[index][weight]){
            return;
        }
        records[index][weight]=true;
        //不拣这块石头
        pickStone(index+1,weight);
        //拣这块石头
        pickStone(index+1,weight+stones[index]);
    }




}
