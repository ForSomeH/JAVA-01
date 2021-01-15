package com.sun.tool;

/**
 * 测试逃脱gc
 * 单纯是为了妥协C++
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_BOOK=null;
    public void isAlive(){
        System.out.println("我还活着！");
    }

    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        System.out.println("开始执行finalize方法");
        FinalizeEscapeGC.SAVE_BOOK=this;
    }

    public static void  testGC() throws Throwable {
        SAVE_BOOK =new FinalizeEscapeGC();
        //对象第一次成功拯救自己
        SAVE_BOOK=null;
        System.gc();
        //因为finalize方法优先级很低，暂停等待它
        Thread.sleep(500);
        if (SAVE_BOOK==null){
            System.out.println("我走了");
        }else{
            SAVE_BOOK.isAlive();
        }


        //对象第二次拯救自己
        SAVE_BOOK=null;
        System.gc();
        Thread.sleep(500);
        if (SAVE_BOOK==null){
            System.out.println("我走了");
        }else{
            SAVE_BOOK.isAlive();
        }
    }
}
