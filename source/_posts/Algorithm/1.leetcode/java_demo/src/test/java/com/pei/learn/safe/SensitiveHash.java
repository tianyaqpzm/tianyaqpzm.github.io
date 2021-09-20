package com.pei.learn.safe;

import java.util.Hashtable;

public class SensitiveHash {
    Hashtable<Integer, String> ht = new Hashtable<Integer, String>();

    void removeEntry(Object key) {
        check("removeKeyPermission");
//        定义该方法时敏感的， 暴露给外部， 所以要检查调用者 是否有removeKeyPermission 权限
        ht.remove(key);
    }

    //    安全检查的方法必须时private 或final，防止被复写，
    private void check(String dt) {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkSecurityAccess(dt);
        }
    }
}
