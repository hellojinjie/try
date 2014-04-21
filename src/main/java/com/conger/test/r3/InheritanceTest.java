package com.conger.test.r3;

import java.util.Date;

public class InheritanceTest
{

    class K extends J {

        private Date date;
        
        K() {
            this.date = new Date();
        }
        
        void overrideMe() {
            System.out.println(date);
        }
    }
    
    public static void main(String[] args) {
        K k = new InheritanceTest().new K();
    }
}


class J {
    J(){
        System.out.println(this.getClass());
        /* DO not do this */
        this.overrideMe();
    }
    void overrideMe() {
    }
}
