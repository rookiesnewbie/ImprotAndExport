package com.bojin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String args[]) {
        Thread t=new Thread(){
            public void  run(){
                dianping();

            }
        };
        t.start();
        System.out.print("dazhong");
    }
    static void dianping(){
        System.out.print("dianping");
    }
    static {
        System.out.print("6666");
    }
}
