package classsix;

public class ThreadDemo {

    public static void main(String[] args){
        Object oo = new Object();

        MyThread thread = new MyThread("thread --");
        thread.setOo(oo);
        thread.start();

        synchronized (oo) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }
}

class MyThread extends Thread {

    private Object oo;

    private String threadName;

    public MyThread(String threadName) {
        this.threadName = threadName;
    }

    public void setOo(Object oo) {
        this.oo = oo;
    }

    @Override
    public void run() {
        synchronized (oo) {
            for (int i = 0; i < 100; i++) {
                System.out.println(threadName + i);
            }
        }
    }
}
