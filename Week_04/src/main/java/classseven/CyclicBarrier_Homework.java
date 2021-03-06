package classseven;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrier_Homework {

    private static volatile int result;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread thread = new Thread(() -> {
            result = Homework03.sum();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        thread.start();
//        int result = sum(); //这是得到的返回值

        // 确保  拿到result 并输出
        cyclicBarrier.await();
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }
}
