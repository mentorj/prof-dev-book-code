package com.javaxpert.books.devjavapro.threads;

public class BetterThreadingDemo {
    int counter = 0;

    static enum ActOnCounter{
        INC,
        DEC
    }
    synchronized void printCounter(BetterThreadingDemo.ActOnCounter action) throws Exception {
        notify();
        if(action== BetterThreadingDemo.ActOnCounter.INC)
            counter++;
        else
            counter--;

        System.out.println(Thread.currentThread().getName() + "-->" + (counter));
        wait();
    }

    static class MyThread extends Thread {
        private BetterThreadingDemo obj = null;
        private BetterThreadingDemo.ActOnCounter action;

        MyThread(BetterThreadingDemo obj, BetterThreadingDemo.ActOnCounter action) {
            this.obj = obj;
            this.action=action;
        }

        public void run() {
            while (true) {
                try {
                    obj.printCounter(action);
                    Thread.sleep(1);
                } catch (Exception ex) {

                }
            }
        }
    }

    public static void main(String[] args) {
        BetterThreadingDemo demo = new BetterThreadingDemo();
        Thread t1 = new MyThread(demo, ActOnCounter.INC);
        t1.setName("MyThread-1");
        Thread t2 = new MyThread(demo, ActOnCounter.DEC);
        t2.setName("MyThread-2");
        t1.start();
        t2.start();
    }
}
