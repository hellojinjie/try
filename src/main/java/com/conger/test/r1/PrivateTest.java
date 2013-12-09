package com.conger.test.r1;

@SuppressWarnings("unused")
public class PrivateTest {

    private int j;
    
    public class Inner {
        private int i;
        private void inn() {
            j = 9;
        }
    }
    
    public void test() {
        Inner inner = new Inner();
        inner.i = 3;
        final int d = 0;
        new Inner () {
            public void v() {
                j = 0;
                j = d;
            }
        };
    }
}
