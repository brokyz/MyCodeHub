package com.broky.multiThread.safeThread;

/**
 * @author 13roky
 * @date 2021-04-22 8:34
 */
public class DeadLock {
    public static void main(String[] args) {
        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();

        new Thread() {
            public void run() {
                synchronized (s1) {
                    s1.append("a");
                    s2.append("1");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s2) {
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

        new Thread(new Runnable() {
            public void run() {
                synchronized (s2) {
                    s1.append("c");
                    s2.append("3");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s1) {
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();
    }
}
