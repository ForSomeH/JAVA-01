package com.sun;

import java.lang.reflect.Method;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode;

/**
 * @author hongzhengwei
 * @desc 作业2：自定义的类加载器  	个Hello.class文件所有字节(x=255-x)
 * @date： 2020/11/21
 */
public class HelloClassLoader extends ClassLoader {
	public static void main(String[] args) {
		try {
			// 加载并初
			new HelloClassLoader().findClass("com.sun.MyHelloWorld").newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		//通过类文件获取字节码
		String myPath = "file:////Users/hongzhengwei/myfile/JavaStudy/out/production/JavaStudy/com/sun/MyHelloWorld.class";
		byte[] cLassBytes = null;
		Path path = null;
		try {
			path = Paths.get(new URI(myPath));
			cLassBytes = Files.readAllBytes(path);
			//将字节转换为int计算
			int old = byteArrayToInt(cLassBytes);
			old = 255 - old;
			cLassBytes = intToByteArray(old);
			Class clazz = defineClass(name, cLassBytes, 0, cLassBytes.length);
			Object obj = clazz.newInstance();
			Method method = clazz.getMethod("test");
			method.invoke(obj);
			return clazz;
		} catch (Exception e) {
		}
		//通过加密获取字节码
//		String helloBase64 = "yv66vgAAADQAHwoABgARCQASABMIABQKABUAFgcAFwcAGAEABjxpbhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQALTGp2bS9IZWxsbzsBAAg8Y2xpbml0PgEACGxvIENsYXNzIEluaXRpYWxpemVkIQcAHAwAHQAeAQAJanZtL0hlbGxvAQAQamF2YS9YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgABAAkAAAAvAAEAAQAAAAUqtwABsQAAAAIACgAAAAYAAQAAAAMACwAAAAwAAQAAAAUAAAAACgACAAAABgAIAAcAAQAPAAAAAgAQ";
//		byte[] bytes = decode(helloBase64);
//		return defineClass(name, bytes, 0, bytes.length);
		return null;
	}

	public byte[] decode(String base64) {
		return Base64.getDecoder().decode(base64);
	}


	/**
	 * int到byte[] 由高位到低位
	 *
	 * @param i 需要转换为byte数组的整行值。
	 * @return byte数组
	 */
	public static byte[] intToByteArray(int i) {
		byte[] result = new byte[4];
		result[0] = (byte) ((i >> 24) & 0xFF);
		result[1] = (byte) ((i >> 16) & 0xFF);
		result[2] = (byte) ((i >> 8) & 0xFF);
		result[3] = (byte) (i & 0xFF);
		return result;
	}

	/**
	 * byte[]转int
	 *
	 * @param bytes 需要转换成int的数组
	 * @return int值
	 */
	public static int byteArrayToInt(byte[] bytes) {
		int value = 0;
		for (int i = 0; i < 4; i++) {
			int shift = (3 - i) * 8;
			value += (bytes[i] & 0xFF) << shift;
		}
		return value;
	}
}
