package com.sun;

/**
 * @author hongzhengwei
 * @desc 作业1：分析字节码作业
 * @date： 2020/11/21
 */
public class MyHelloWorld {
	static {
		System.out.println("show test!");
	}
	public static void test(){
		System.out.println("洪政伟太帅了！");
	}

	public void addString(){
		String name="hongzw";
		name+="zheng";
		name=name+"wei";

	}
	public static void main(String[] args) {
		test();
		System.out.println("Hello Class Initialized!");
		int end = 5;
		int result = 0;
		for (int i = 0; i < end; i++) {
			if (i == 0) {
				result = result + 3;
			}
			if (i == 1) {
				result = result - 1;
			}
			if (i == 2) {
				result = result * 4;
			}
			if (i == 3) {
				result = result / 2;
			}
		}
	}
}
