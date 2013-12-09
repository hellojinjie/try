package com.conger.test.r1;

public class InnerClassTest {

    private int k;

    public void kk() {
        
        class K {
            public K() {}
        }
        
        int l = 0;
        Inner i = new Inner() {
        
            {
                k = 0;
            }
            
            void li() {
                
            }
        };
        
        Runnable r = new Runnable() {
            public void run() {
                k = 0;
            }
        };
    }

    class Inner {
        private void i() {
            k = 0;
        }
    }
}
