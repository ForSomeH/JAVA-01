package com.summer.algorithm;

/**
 我们有一个 8x8 的棋盘，希望往里放 8 个棋子（皇后），每个棋子所在的行、列、对角线都不能有另一个棋子。
 */
public class Cal8Queen {
    int[] result = new int[8];
    int type = 1;
    /**
     * 判断在x行，y列放置棋子是否合适
     * @param x
     * @param y
     * @return
     */
    boolean isOk(int x, int y){
        //结果集,下标表示哪一行，值表示在哪一列

        int leftUp=y-1;
        int rightUp=y+1;
        //从上向下放置，因此只需向上考察每一行是否符合规则即可
        for (int i=x-1;i>=0;--i){
            //若上一行的值刚好和当前列相同，则不可放置
            if(result[i]==y){
                return false;
            }
            //查看左上角对角线是否有值
            if (leftUp>=0){
                if (result[i]==leftUp){
                    return  false;
                }
            }
            //查看右上角对角线是否有值
            --leftUp;
            if (rightUp>=0){
                if (result[i]==rightUp){
                    return  false;
                }
            }
            ++rightUp;
        }
        return true;
    }

    public  void cal8queens(int row) {
        if (row==8){
            //8个棋子都放好了，打印出来
            printQueens(result);
            return;
        }
        for (int i=0;i<8;++i){
            if (isOk(row,i)){
                result[row]=i;
                cal8queens(row+1);
            }
        }
    }



    private void printQueens(int[] result) { // 打印出一个二维矩阵
        type++;

        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("解法"+type);
    }

}
