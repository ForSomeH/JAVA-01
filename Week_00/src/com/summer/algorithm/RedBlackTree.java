package com.summer.algorithm;

/**
 * @author  hongzhengwei
 * @create  2021/3/1 2:20 下午
 * @desc    红黑树的高度近似 log2n，所以它是近似平衡，插入、删除、查找操作的时间复杂度都是 O(logn)。
 * 根节点是黑色的；
 * 每个叶子节点都是黑色的空节点（NIL），也就是说，叶子节点不存储数据；
 * 任何相邻的节点都不能同时为红色，也就是说，红色节点是被黑色节点隔开的；
 * 每个节点，从该节点到达其可达叶子节点的所有路径，都包含相同数目的黑色节点；
 **/
public class RedBlackTree {
}
