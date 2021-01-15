package com.sun.tool;

/**
 * 测试循环引用的依赖
 */
public class ReferenceCountingGC {
    public Object instance=null;
    public static final int _1MB=1024*1024;
    /**
     * 这个成员属性就是为了沾点内存，以便查看GC是否成功回收过
     */
    private byte[] bigSize =new byte[2*_1MB];

    public static void  testGC(){
        ReferenceCountingGC objA=new ReferenceCountingGC();
        ReferenceCountingGC objB=new ReferenceCountingGC();
        objA.instance=objB;
        objB.instance=objA;
        objA=null;
        objB=null;
        //此时gc，查看是否能回收
        System.gc();

    }


}
