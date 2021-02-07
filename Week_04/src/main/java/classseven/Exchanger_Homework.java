package classseven;

import java.util.concurrent.Exchanger;

public class Exchanger_Homework {

    private static volatile int result;

    private static Exchanger<Integer> exchanger = new Exchanger<>();

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        Thread thread = new Thread(() -> {
            try {
                result = Homework03.sum();
                exchanger.exchange(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
//        int result = sum(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+exchanger.exchange(result));

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }
}
