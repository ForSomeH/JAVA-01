package com.sun;

import com.sun.tool.FinalizeEscapeGC;
import com.sun.tool.ReferenceCountingGC;

public class Hello {

  public void test(){
     System.out.println("Carry yourself, hongzw!");
  }

    public static void main(String[] args) throws Throwable {
      //测试循环引用GC
//        ReferenceCountingGC.testGC();
        //测试gc逃脱
        FinalizeEscapeGC.testGC();
    }
}