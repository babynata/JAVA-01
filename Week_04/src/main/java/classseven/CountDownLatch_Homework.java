package classseven;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch_Homework {

    private static volatile int result;

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            result = Homework03.sum();
            countDownLatch.countDown();
        });
        thread.start();
//        int result = sum(); //这是得到的返回值

        // 确保  拿到result 并输出
        countDownLatch.await();
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }
}
