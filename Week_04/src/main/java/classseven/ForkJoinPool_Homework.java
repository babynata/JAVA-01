package classseven;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPool_Homework {
    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        RecursiveTask<Integer> task = new MyForkJoinTask(36);
        int result = forkJoinPool.invoke(task);
//        int result = sum(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }


    private static class MyForkJoinTask extends RecursiveTask<Integer> {

        private final int n;

        public MyForkJoinTask(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 2) {
                return n;
            }
            MyForkJoinTask task_1 = new MyForkJoinTask(n - 1);
            task_1.fork();
            MyForkJoinTask task_2 = new MyForkJoinTask(n - 2);
            return task_2.compute() + task_1.join();
        }
    }
}
