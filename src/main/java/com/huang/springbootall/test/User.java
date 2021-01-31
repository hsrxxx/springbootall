package com.huang.springbootall.test;

/**
 * @program: springbootall
 * @description: 账户类
 * @author: hsrxxx
 * @create: 2020-12-28 15:10
 **/
public class User {
    private String name = "zhangsan";
    private double RMB = 10000;

    public User() {
    }

    public User(String name, long RMB) {
        this.name = name;
        this.RMB = RMB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRMB() {
        return RMB;
    }

    public void setRMB(double RMB) {
        this.RMB = RMB;
    }

    public void withdraw(double RMB){

        // 线程同步代码块
        synchronized (this){
            double before = this.getRMB();
            double after = before - RMB;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setRMB(after);
        }
    }
}

