package com.pei.learn.basic.maplearn;

import org.junit.jupiter.api.DisplayName;

import java.util.*;


public class TreeMapTest {

    @DisplayName("默认按key排序")
    void test_compara() {
        TreeMap<Integer,Integer> map2= new TreeMap<Integer,Integer>(new Comparator<Integer>(){
            /*
             * int compare(Object o1, Object o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            public int compare(Integer a,Integer b){
                return b-a;
            }
        });
    }

    @DisplayName("默认按key排序")
    void test_compara_value() {
        Map<String,String> map = new TreeMap<String,String>();
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            //升序排序
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        list.sort(new MapValueComparator());

    }


}


//比较器
class MapValueComparator implements Comparator<Map.Entry<String, String>> {
    @Override
    public int compare(Map.Entry<String, String> me1, Map.Entry<String, String> me2) {
        return me1.getValue().compareTo(me2.getValue());
    }
}