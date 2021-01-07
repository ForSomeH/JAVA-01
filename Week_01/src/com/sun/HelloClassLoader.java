package com.sun;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @author hongzhengwei
 * @desc 作业2：自定义的类加载器  	个Hello.class文件所有字节(x=255-x)
 * @date： 2020/11/21
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            // 加载并初
//            new HelloClassLoader().findClass("Hello").newInstance();
            Class<?> clazz = new HelloClassLoader().findClass("Hello");
            Method declaredMethod = clazz.getDeclaredMethod("hello");
            declaredMethod.invoke(clazz.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //通过类文件获取字节码
//		String myPath = "file:///Users/summer/IdeaProjects/JAVA-01/out/production/JAVA-01/com/sun/Hello.class";
        String myPath = "file:///Users/summer/IdeaProjects/JAVA-01/Week_01/Hello.xlass";

        byte[] cLassBytes = null;
        Path path = null;
        try {
            path = Paths.get(new URI(myPath));
            cLassBytes = Files.readAllBytes(path);
            for (byte cLassByte : cLassBytes) {
//                byte i = 255 - cLassByte;
            }
            //将字节转换为int计算
//			int old = byteArrayToInt(cLassBytes);
//			old = 255 - old;
//			cLassBytes = intToByteArray(old);
            for (int i = 0; i < cLassBytes.length; i++) {
                cLassBytes[i] = (byte) (255 - cLassBytes[i]);
            }
            Class clazz = defineClass(name, cLassBytes, 0, cLassBytes.length);
            Object obj = clazz.newInstance();
            Method method = clazz.getMethod("hello");
            method.invoke(obj);
            return clazz;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //通过加密获取字节码
//        String helloBase64 = "yv66vgAAADQAHwoABgARCQASABMIABQKABUAFgcAFwchbFZhcmlhYmxlVGFibGUBAAR0aGlzAQALTGp2bS9IZWxsbzsBAAg8Y2xGxvIENsYXNzIEluaXRpYWxpemVkIQcAHAwAHQAeAQAJanZtL0hlbGxvA YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAH ABAAkAAAAvAAEAAQAAAAUqtwABsQAAAAIACgAAAAYAAQAAAAMACwAAAA AAAACgACAAAABgAIAAcAAQAPAAAAAgAQ";
//        byte[] bytes = decode(helloBase64);
//        return defineClass(name, bytes, 0, bytes.length);
        return null;
    }

    public byte[] decodeByBase64(String base64) {
        return Base64.getDecoder().decode(base64);
    }
    public byte[] decodeBy255(String base64) {
        return Base64.getDecoder().decode(base64);
    }


}
