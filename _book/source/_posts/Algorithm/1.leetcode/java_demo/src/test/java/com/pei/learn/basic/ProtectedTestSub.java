package com.pei.learn.basic;

class ProtectedTestSub extends ProtectedTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        ProtectedTestSub protectedTestSub = new ProtectedTestSub();
        protectedTestSub.clone();
        Object o = new Object();
//        o.clone(); 不能直接调用
        ProtectedTest protectedTest = new ProtectedTest();
//        protectedTest.clone();//不能直接调用
    }

    private void testClone() throws CloneNotSupportedException {
        Object clone = super.clone();
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
