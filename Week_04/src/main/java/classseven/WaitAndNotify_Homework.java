package classseven;

public class WaitAndNotify_Homework {

    private static volatile int result;

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        WaitAndNotify_Homework waitAndNotify = new WaitAndNotify_Homework();
        Thread thread = new Thread(waitAndNotify::sum);
        thread.start();
        waitAndNotify.print();

//        int result = sum(); //这是得到的返回值

        // 确保  拿到result 并输出
//        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    private synchronized void print() throws InterruptedException {
        wait();
        System.out.println("异步计算结果为："+result);
    }

    private synchronized void sum() {
        result = fibo(36);
        notifyAll();
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
