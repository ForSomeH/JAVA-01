package com.summer.question;

/**
 * 八皇后问题，希望往里放 8 个棋子（皇后），每个棋子所在的行、列、对角线都不能有另一个棋子。
 * 解题思路
 * 回溯思想，每放一步之后进行判断是否合格
 * 用数组储存每一行的皇后的位置，可以快速进行判断是否存在皇后，也方便最后的输出
 */
public class Day20210104 {
    //用于存储皇后位置的数据，下标是行数，存储的值是皇后在每行的位置下标
    int[] queenLocation = new int[8];
    int type = 0;

    /**
     * 皇后移动
     *
     * @param row 移动的行数
     */
    public void queenMove(int row) {
        //因为数组下标最多是7 ，当下标等8时，意味着全部的皇后已经就位
        if (row == 8) {
            showQueenLocation();
            type++;
            return;
        }

        for (int column = 0; column < 8; column++) {
            if (isOk(row, column)) {
                //赋值皇后的位置
                queenLocation[row]=column;
                queenMove(row + 1);
            }
        }


    }

    /**
     * 打印八皇后的解法位置
     */
    private void showQueenLocation() {
        System.out.println("-------------------解法"+type+"-------------------------");
        for (int i = 0; i < queenLocation.length; i++) {
            for (int column = 0; column < 8; ++column) {
                if (queenLocation[i] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 判断皇后在当前位置停留是否满足条件
     *
     * @param row
     * @param column
     * @return
     */
    private boolean isOk(int row, int column) {
        //因为是从上往下遍历行数
        //因此左右当前行肯定没有皇后，先判断头顶是否有别的皇后
        for (int i = 0; i < row; i++) {
            if (queenLocation[i] == column) {
                return false;
            }
        }
        int leftIndex=column;
        int rightIndex=column;
        for (int i = row-1; i >= 0; i--) {
            //判断左上角是否有皇后
            --leftIndex;
            if (queenLocation[i]==leftIndex){
                return false;
            }
            ++rightIndex;
            //判断右上角是否有皇后
            if (queenLocation[i]==rightIndex){
                return false;
            }
        }
        return true;
    }
}
