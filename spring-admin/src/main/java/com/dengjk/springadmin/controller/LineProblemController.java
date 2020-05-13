package com.dengjk.springadmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dengjk
 * @create 2020-05-13 16:30
 * @desc
 **/
@RestController
@RequestMapping("/line")
@Slf4j
public class LineProblemController {


    private static Object obj1 = new Object();

    private static Object obj2 = new Object();

    /**
     * 模拟cpu过高
     */
    @GetMapping("/highCpu")
    public void highCpu() {
        while (true) {
            log.error("进入死循环。。。");
        }
    }

    /**
     * 模拟堆内存溢出
     */
    @GetMapping("/oomHeapSpace")
    public void oomHeapSpace() {
        List<String> list = new ArrayList<>();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (true) {
            String count = atomicInteger.getAndIncrement() + "";
            list.add("dengjk" + count);
            log.error(count);
        }
    }


    /**
     * 栈内存溢出
     */
    @GetMapping("/stackOom")
    public void stackOom() {
        this.stackOom();
    }


    /**
     * 方法区内存溢出
     */
    @GetMapping("/constantOom")
    public void constantOom() {
        List<String> list = new ArrayList<>();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (true) {
            String count = atomicInteger.getAndIncrement() + "";
            list.add("dengjk" + count.intern());
            log.error(count);
        }
    }


    /**
     * 线程过多内存溢出
     */
    @GetMapping("/threadCountOom")
    public void threadCountOom() {
        for (int i = 0; i < 15000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


    /**
     * 死锁问题
     */
    @GetMapping("/deadlock")
    public void deadlock() {
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();
    }


    private static class Thread1 implements Runnable {
        @Override
        public void run() {
            synchronized (obj1) {
                log.error("Thread1 拿到了 obj1 的锁！");
                try {
                    /**停顿2秒的意义在于，让Thread2线程拿到obj2的锁*/
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2) {
                    System.out.println("Thread1 拿到了 obj2 的锁！");
                }
            }
        }
    }

    private static class Thread2 implements Runnable {
        @Override
        public void run() {
            synchronized (obj2) {
                log.error("Thread2 拿到了 obj2 的锁！");
                try {
                    /**停顿2秒的意义在于，让Thread1线程拿到obj1的锁*/
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1) {
                    System.out.println("Thread2 拿到了 obj1 的锁！");
                }
            }
        }
    }
}
