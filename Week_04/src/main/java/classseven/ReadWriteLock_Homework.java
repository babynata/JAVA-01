package classseven;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock_Homework {

    private static volatile int result;

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        final Lock r = readWriteLock.readLock();
        final Lock w = readWriteLock.writeLock();


        Thread thread = new Thread(() -> {
            r.lock();
            result = Homework03.sum();
            r.unlock();

        });
        thread.start();
//        int result = sum(); //这是得到的返回值

        // 确保  拿到result 并输出
        w.lock();
        System.out.println("异步计算结果为："+result);
        w.unlock();

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }
}
