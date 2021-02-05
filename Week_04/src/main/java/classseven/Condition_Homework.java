package classseven;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Condition_Homework {

    private static volatile int result;

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition waitForSum = reentrantLock.newCondition();
        Thread thread = new Thread(()->{
            reentrantLock.lock();
            result = Homework03.sum();
            waitForSum.signal();
            reentrantLock.unlock();
        });
        thread.start();

//        int result = sum(); //这是得到的返回值
        reentrantLock.lock();
        waitForSum.await();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
        reentrantLock.unlock();

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }


}
