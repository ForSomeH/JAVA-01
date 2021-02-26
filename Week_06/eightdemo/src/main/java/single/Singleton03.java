package single;

/**
 * @author hongzhengwei
 * @create 2021/2/19 2:32 下午
 * @desc 4、双检锁/双重校验锁（DCL，即 double-checked locking）
 * JDK 版本：JDK1.5 起
 * <p>
 * 是否 Lazy 初始化：是
 * <p>
 * 是否多线程安全：是
 * <p>
 * 实现难度：较复杂
 * <p>
 * 描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 * getInstance() 的性能对应用程序很关键。
 **/
public class Singleton03 {

    private volatile static Singleton03 singleton;

    private Singleton03() {
    }

    public static Singleton03 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton03.class) {
                if (singleton == null) {
                    singleton = new Singleton03();
                }
            }
        }
        return singleton;
    }

}
