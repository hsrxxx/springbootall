package com.huang.springbootall.test;

/**
 * @program: springbootall
 * @description: 生产者和消费者
 * @author: hsrxxx
 * @create: 2020-12-28 17:22
 **/
public class WaitNotify {
    public static void main(String[] args) {
        int[] integer = new int[3];
        integer[0] = 0;
        Thread t1 = new Thread001(integer);
        Thread t2 = new Thread002(integer);
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}

class Thread001 extends Thread {

    private int[] integer;

    public Thread001(int[] integer) {
        this.integer = integer;
    }

    @Override
    public void run() {
        while (true){
            synchronized (integer){
                if (integer[0] % 2 != 0){
                    try {
                        integer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                integer[0] = ++integer[0];
                System.out.println(Thread.currentThread().getName() + "--->" + integer[0]);
                integer.notify();
            }
        }
    }
}

class Thread002 extends Thread {

    private int[] integer;

    public Thread002(int[] integer) {
        this.integer = integer;
    }

    @Override
    public void run() {
        while (true){
            synchronized (integer){
                if (integer[0] % 2 != 1){
                    try {
                        integer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                integer[0] = ++integer[0];
                System.out.println(Thread.currentThread().getName() + "--->" + integer[0]);
                integer.notify();
            }
        }
    }
}
