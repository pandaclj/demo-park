package com.panda.demo.leetcode.title_00146_LRU缓存机制;

import java.util.HashMap;
import java.util.Map;

/**
 * 最近最少使用
 * 链表头部为最新
 *
 * @author huixiangdou
 * @date 2020/5/18
 */
public class Answer_146_2 {
    static class LRUCache {

        static class DLinkedNode {
            private int key;
            private int value;
            private DLinkedNode prev;
            private DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        //新节点插入到头部
        public void addNode(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;

            head.next.prev = node;
            head.next = node;
        }

        //删除节点
        public void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }

        //获取并移除尾结点
        public DLinkedNode pollTail() {
            DLinkedNode node = tail.prev;
            removeNode(node);
            return node;
        }

        /**
         * 移到头节点
         *
         * @param node
         */
        public void moveToHead(DLinkedNode node) {
            removeNode(node);
            addNode(node);
        }

        private Map<Integer, DLinkedNode> cache = new HashMap<>();
        private int capacity;
        private DLinkedNode head;
        private DLinkedNode tail;


        public LRUCache(int capacity) {
            this.capacity = capacity;
            //可以省去判断是否为null的逻辑
            head = new DLinkedNode(); //伪头节点
            tail = new DLinkedNode(); //伪尾节点
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            //移动到头节点
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = new DLinkedNode(key, value);
            DLinkedNode old = cache.get(key);
            if (old == null) { //原来不存在
                if (cache.size() < this.capacity) { //还能存放
                    addNode(node);
                } else { //放不下，需要删除尾结点，把新节点插入到头部
                    DLinkedNode tailNode = pollTail();
                    cache.remove(tailNode.key);
                    addNode(node);
                }
                cache.put(key, node);
            } else { // 原来已经存在，则需要移动到头部
                moveToHead(node);
            }
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
