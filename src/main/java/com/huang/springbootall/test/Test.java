package com.huang.springbootall.test;

/**
 * @program: springbootall
 * @description: ceshi
 * @author: hsrxxx
 * @create: 2020-12-28 15:11
 **/
public class Test {
    public static void main(String[] args) {
        User user = new User();
        Thread t1 = new Thread(new MyThread1(user));
        Thread t2 = new Thread(new MyThread1(user));
        t1.start();
        t2.start();
    }
}

class MyThread1 implements Runnable{

    private User user;

    public MyThread1(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        user.withdraw(5000);
        System.out.println(user.getRMB());
    }
}
