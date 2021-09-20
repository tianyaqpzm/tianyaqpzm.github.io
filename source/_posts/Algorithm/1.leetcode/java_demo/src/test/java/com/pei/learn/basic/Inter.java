package com.pei.learn.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Inter {

    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("", "value");
        System.out.println(hashMap.containsKey(null));

        Map treeMap = new TreeMap<String, String>();
        treeMap.put("test", null);
        System.out.println(treeMap.get("test"));
    }
}
