package single;

/**
 * @author  hongzhengwei
 * @create  2021/2/19 2:23 下午
 * @desc    懒汉式，线程不安全
 *          是否 Lazy 初始化：是
 *          是否多线程安全：否
 **/
public class Singleton01 {
    private static Singleton01 instance;

    private Singleton01() {
    }

    //加同步锁，即线程安全
    public static synchronized Singleton01 getInstance() {
        if (instance == null) {
            instance = new Singleton01();
        }
        return instance;
    }
}
