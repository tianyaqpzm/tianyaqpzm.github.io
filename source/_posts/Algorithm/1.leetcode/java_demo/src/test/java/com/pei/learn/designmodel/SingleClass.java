package com.pei.learn.designmodel;

public class SingleClass {

//    是否延迟 取决与 静态？

}

class Foo {

    //    volatile : 保持可见性
    private volatile Help help = new Help();

    public Help getHelp() {
        if (help == null) {
            synchronized (this) {
                if (help == null) {
                    help = new Help();
                }
            }
        }
        return help;
    }
}

/**
 * 利用局部变量，保证线程安全
 */
class FooWay2 {

    private Help help = new Help();

    public Help getHelp() {
        Help h = help;
        if (h == null) {
            synchronized (this) {
                h = help;
                if (h == null) {
                    h = new Help();
                    help = h;
                }
            }
        }
        return h;
    }
}


/**
 * 静态初始化, 没有延迟初始化
 */
class Foo2 {
    private static Help help = new Help();

    public Help getHelp() {
        return help;
    }
}

/**
 * 内部类, 延迟初始化
 */
class Foo3 {

    private static class Holder {
        static Help help = new Help();
    }

    public Help getHelp() {
        return Holder.help;
    }
}

/**
 * 任何一个空对象，都需要调用creatHelp() 建立必要都happens-before关系
 */
class Foo4 {

    private final ThreadLocal<Foo4> perThreadInstance = new ThreadLocal<Foo4>();

    private Help help = null;

    public Help getHelp() {
        if (perThreadInstance.get() == null) {
            crateHelp();
        }
        return help;
    }

    private synchronized void crateHelp() {
        if (help == null) {
            help = new Help();
        }
        perThreadInstance.set(this);
    }
}

class Help {

}