package com.summer.algorithm;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class DNode {
        DNode pre;
        DNode next;
        String key;
        String value;

        public DNode() {
        }

        public DNode(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<String, DNode> cache = new HashMap<>();
    private int maxSize;
    private int size;
    //哨兵模式
    private DNode head, tail;

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.head = new DNode();
        this.tail = new DNode();
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 读取缓存
     * 先从缓存读取，若存在，则移动到头部
     *
     * @param key
     * @return
     */
    public String get(String key) {
        DNode dNode = cache.get(key);
        if (dNode == null) {
            return "缓存不存在";
        }
        removeToHead(dNode);
        return dNode.value;
    }

    /**
     * 存入缓存
     */
    public void put(String key, String value) {
        DNode dNode = cache.get(key);
        if (dNode != null) {
            dNode.value = value;
            removeToHead(dNode);
        } else {
            dNode = new DNode(key, value);
            cache.put(key, dNode);
            addToHead(dNode);
            ++size;
            if (size > maxSize) {
                //移除前一个
                cache.remove(tail.pre.key);
                removeNode(tail.pre);
                --size;
            }

        }
    }


    private void removeNode(DNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void removeToHead(DNode node) {
        removeNode(node);
        addToHead(node);
    }


    /**
     * 将已有的节点移动到头部
     *
     * @param dNode
     */
    private void addToHead(DNode dNode) {
        dNode.pre = head;
        dNode.next = head.next;
        head.next.pre = dNode;
        head.next = dNode;
    }


}
