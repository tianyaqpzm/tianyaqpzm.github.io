package com.roncoo.education.redis;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {

    @Test
    public void test_lru() {
        LinkedHashMap<Long, Object> linkedHashMap = new LinkedHashMap<Long, Object>(100, .75F, true) {
            protected boolean removeEldestRntry(Map.Entry eldest) {
                return size() > 100;
            }
        };
    }

    class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int CACHE_SIZE;

        public LRUCache(int cacheSize) {
            super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75F, true);
            CACHE_SIZE = cacheSize;
        }

        protected boolean removeEldestRntry(Map.Entry eldest) {
            return size() > 100;
        }
    }
}
