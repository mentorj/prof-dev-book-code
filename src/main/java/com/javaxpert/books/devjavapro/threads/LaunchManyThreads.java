package com.javaxpert.books.devjavapro.threads;

/**
 * Launch many threeads on your machine
 * Beware before launching this program,  increase the MAX_THREADS with  care!!.
 * Stop it quickly!!
 */
public class LaunchManyThreads {
    private final static int MAX_THREADS = 100;
    public static void main(String[] args) throws InterruptedException {

        System.out.println("launching many threads");
        for (int i = 0; i < MAX_THREADS; i++) {

            System.out.println("Launching threads from " + Thread.currentThread().getName());

            while (true) {
                System.out.println("Already launched " + Thread.getAllStackTraces().entrySet().size());
                Thread.sleep(500);
                Thread t = new Thread(() -> {
                    while (true) {
                        try {
                            Thread.sleep(1000);

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },"Thread-" + i);

                t.start();
            }
        }
    }
}
