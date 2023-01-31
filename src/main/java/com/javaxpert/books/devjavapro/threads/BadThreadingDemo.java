 package com.javaxpert.books.devjavapro.threads;

public class BadThreadingDemo {
    int counter = 0;

    static enum ActOnCounter{
        INC,
        DEC
    }
     void printCounter(ActOnCounter action) throws Exception {

        if(action==ActOnCounter.INC)
            counter++;
        else
            counter--;

        System.out.println(Thread.currentThread().getName() + "-->" + (counter));
    }

    static class MyThread extends Thread {
        private BadThreadingDemo obj = null;
        private ActOnCounter action;

        MyThread(BadThreadingDemo obj,ActOnCounter action) {
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
            BadThreadingDemo demo = new BadThreadingDemo();
            Thread t1 = new MyThread(demo,ActOnCounter.INC);
            t1.setName("MyThread-1");
            Thread t2 = new MyThread(demo,ActOnCounter.DEC);
            t2.setName("MyThread-2");
            t1.start();
            t2.start();
        }
    }
