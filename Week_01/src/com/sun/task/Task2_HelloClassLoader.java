package com.sun.task;

import com.sun.tool.Decode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author hongzhengwei
 * @desc 作业2：自定义的类加载器  Hello.class文件所有字节(x=255-x)
 * @date： 2021/01/08
 */
public class Task2_HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws  NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //源文件路径
        String Path = "file:///Users/summer/IdeaProjects/JAVA-01/Week_01/Hello.xlass";
        //加载的类名
        String className="Hello";
        //执行的方法名
        String methodName="hello";
        //获取该类
        Class<?> clazz = new Task2_HelloClassLoader().findClass(className, Path,"255");
        //反射初始化加载
        Method declaredMethod = clazz.getDeclaredMethod(methodName);
        declaredMethod.invoke(clazz.newInstance());

        //加载自己的类
        //源文件路径
        String myPath = "file:///Users/summer/IdeaProjects/JAVA-01/out/production/JAVA-01/com/sun/Hello.class";
        //加载的类名
        String myClassName="com.sun.Hello";
        //执行的方法名
        String myMethodName="test";
        //获取该类
        Class<?> myClazz = new Task2_HelloClassLoader().findClass(myClassName, myPath,null);
        //反射初始化加载
        Method myDeclaredMethod = myClazz.getDeclaredMethod(myMethodName);
        myDeclaredMethod.invoke(myClazz.newInstance());
    }

    /**
     *
     * @param name 类名
     * @param filePath 源文件的路径
     * @param type 加密的类型
     * @return
     */
    protected Class<?> findClass(String name, String filePath,String type) {
        try {
            Path path = Paths.get(new URI(filePath));
            byte[] cLassBytes = Files.readAllBytes(path);
            new Decode().decode(cLassBytes,type);
            Class clazz = defineClass(name, cLassBytes, 0, cLassBytes.length);
            return clazz;
        } catch (NoSuchFileException e){
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
