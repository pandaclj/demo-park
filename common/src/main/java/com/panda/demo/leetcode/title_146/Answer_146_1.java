package com.panda.demo.leetcode.title_146;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 最近最少使用
 * 利用LinkedHashMap实现
 * 链表尾部为最新
 *
 * @author huixiangdou
 * @date 2020/5/18
 */
public class Answer_146_1 {
    static class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int maxSize;

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > this.maxSize;
        }

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.maxSize = capacity;
        }

        public int get(int key) {
            Integer result = super.get(key);
            return result == null ? -1 : result;
        }

        public void put(int key, int value) {
            super.put(key, value);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* 缓存容量 */);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));      // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        System.out.println(cache.get(4));// 返回  4
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
