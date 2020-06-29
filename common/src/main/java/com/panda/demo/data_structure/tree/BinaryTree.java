package com.panda.demo.data_structure.tree;

import java.util.*;

/**
 * 实现一颗二叉树
 * <p>
 * 给定一个数组，将数组转成二叉树，并根据前序、中序、后序打印
 * <p>
 * 性质1：二叉树第i层上的结点数目最多为2i-1(i>=1)
 * 性质2：深度为k的二叉树至多有2k-1个结点（k>=1）
 * 性质3：包含n个结点的二叉树的高度至少为(log2n)+1
 * 性质4：在任意一棵二叉树中，若终端结点的个数为n0，度为2的结点数为n2，则n0=n2+1
 * <p>
 * <p>
 * index=0的为根节点，左子节点为2*i+1，右子节点为2*i+2，父节点为(i-1)/2
 *
 * @author huixiangdou
 * @date 2020/6/2
 */
public class BinaryTree {

    private Integer[] datas;
    private TreeNode root;
    private PrintStrategy recursionPrintStrategy = new RecursionPrintStrategy(this);
    private PrintStrategy stackPrintStrategy = new RecursionPrintStrategy(this);

    public BinaryTree(Integer[] datas) {
        this.datas = datas;
        if (datas != null && datas.length > 0) {
            buildTree(datas, 0);
        }
    }

    /**
     * 构建树
     *
     * @param datas
     * @return
     */
    private TreeNode buildTree(Integer[] datas, int index) {
        TreeNode node = null;
        if (index < datas.length) {
            Integer val = datas[index];
            if (val == null) {
                return null;
            }
            node = new TreeNode(val);
            if (index == 0) {
                root = node;
            }
            node.left = buildTree(datas, 2 * index + 1);
            node.right = buildTree(datas, 2 * index + 2);
        }
        return node;
    }

    /**
     * 打印，可选先序，中序，后序
     *
     * @param order
     */
    public void print(OrderEnum order) {
        System.out.println("递归遍历");
        recursionPrintStrategy.print(order);
        System.out.println("栈遍历");
        stackPrintStrategy.print(order);
    }

    public Integer[] getDatas() {
        return datas;
    }

    public TreeNode getRoot() {
        return root;
    }

    /**
     * 树节点
     */
    private class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    private enum OrderEnum {
        PRE_ORDER, IN_ORDER, POST_ORDER, LEVEL_ORDER;
    }


    private static abstract class PrintStrategy {
        private BinaryTree tree;

        public PrintStrategy(BinaryTree tree) {
            this.tree = tree;
        }

        public void print(OrderEnum order) {
            List<String> result = new ArrayList<>(getTree().getDatas().length);
            switch (order) {
                case PRE_ORDER:
                    printByPreOrder(result);
                    break;
                case IN_ORDER:
                    printByInOrder(result);
                    break;
                case POST_ORDER:
                    printByPostOrder(result);
                    break;
                case LEVEL_ORDER:
                    printByLevelOrder(result);
                default:
            }

            System.out.println(order.name() + ":" + String.join(",", result));
        }

        /**
         * 先序
         *
         * @param result
         */
        public abstract void printByPreOrder(List<String> result);

        /**
         * 中序
         *
         * @param result
         */
        public abstract void printByInOrder(List<String> result);

        /**
         * 后序
         *
         * @param result
         */
        public abstract void printByPostOrder(List<String> result);

        /**
         * 层级遍历
         *
         * @param result
         */
        public abstract void printByLevelOrder(List<String> result);


        public BinaryTree getTree() {
            return tree;
        }
    }

    /**
     * 递归策略
     */
    private static class RecursionPrintStrategy extends PrintStrategy {

        public RecursionPrintStrategy(BinaryTree tree) {
            super(tree);
        }

        @Override
        public void printByPreOrder(List<String> result) {
            printByPreOrder(getTree().getRoot(), result);
        }

        private void printByPreOrder(TreeNode node, List<String> result) {
            if (node == null) {
                return;
            }
            result.add(String.valueOf(node.val));
            printByPreOrder(node.left, result);
            printByPreOrder(node.right, result);
        }


        @Override
        public void printByInOrder(List<String> result) {
            printByInOrder(getTree().getRoot(), result);
        }

        private void printByInOrder(TreeNode node, List<String> result) {
            if (node == null) {
                return;
            }
            printByInOrder(node.left, result);
            result.add(String.valueOf(node.val));
            printByInOrder(node.right, result);
        }

        @Override
        public void printByPostOrder(List<String> result) {
            printByPostOrder(getTree().getRoot(), result);
        }

        private void printByPostOrder(TreeNode node, List<String> result) {
            if (node == null) {
                return;
            }
            printByPostOrder(node.left, result);
            printByPostOrder(node.right, result);
            result.add(String.valueOf(node.val));
        }

        @Override
        public void printByLevelOrder(List<String> result) {
            TreeNode root = getTree().getRoot();
            if (root != null) {
                result.add(String.valueOf(root.val));
            }
            printByLevelOrder(getTree().getRoot(), result);
        }

        private void printByLevelOrder(TreeNode node, List<String> result) {
            if (node == null) {
                return;
            }

            if (node.left != null) {
                result.add(String.valueOf(node.left.val));
            }
            if (node.right != null) {
                result.add(String.valueOf(node.right.val));
            }

            printByLevelOrder(node.left, result);
            printByLevelOrder(node.right, result);
        }


    }

    /**
     * 栈策略
     */
    private static class StackPrintStrategy extends PrintStrategy {

        public StackPrintStrategy(BinaryTree tree) {
            super(tree);
        }

        /**
         * 根节点先入栈，只要栈不为空，先从栈中取数据，取出后，右节点先入栈，左节点再入栈
         *
         * @param result
         */
        @Override
        public void printByPreOrder(List<String> result) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(getTree().getRoot());

            while (!stack.isEmpty()) {
                TreeNode current = stack.pop();
                result.add(String.valueOf(current.val));

                if (current.right != null) {
                    stack.push(current.right);
                }

                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }

        /**
         * 先把10放入栈，栈顶为10
         * 有左节点5，把5压入栈，栈顶为5
         * 有左节点3，把3压入栈，栈顶为3
         * 没有左节点了，node为null，取出栈顶元素3，打印3，此时栈顶变为5
         * 没有右节点，node为null，弹出栈顶元素5，打印5，此时栈顶变为10
         * 有右节点，把7压入栈，栈顶为7
         * 没有左节点，node为null，弹出栈顶元素7，打印7，此时栈顶变为10
         * 没有右节点，node为null，弹出栈顶元素10，打印10，此时栈为空
         * 有右节点。。。
         *
         * @param result
         */
        @Override
        public void printByInOrder(List<String> result) {
            Stack<TreeNode> stack = new Stack<>();

            TreeNode node = getTree().getRoot();

            while (node != null || !stack.isEmpty()) {
                //直到遇到node为null
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    result.add(String.valueOf(stack.pop().val));
                    node = node.right;
                }
            }
        }

        /**
         * 先把10放入栈，栈顶为10
         * 有左节点，把5压入栈，栈顶为5
         * 有左节点，把3压入栈，栈顶为3
         * 没有左节点，node为null，弹出栈顶元素3
         * 看弹出元素有没有右节点，如果有，将右节点压入栈
         * 没有右节点，则打印元素3，将node设为null
         * 进入下一轮，再弹出栈顶元素5
         * 有右节点，把5再压入栈，把7也压入栈
         *
         * @param result
         */
        @Override
        public void printByPostOrder(List<String> result) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode node = getTree().getRoot();

            TreeNode pre = null;
            while (node != null || !stack.isEmpty()) {
                //直到遇到node为null
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    node = stack.pop();

                    if (node.right != null) {
                        stack.push(node);
                        node = node.right;
                        stack.push(node.right);
                        node = node.left;
                    } else if (node.right == null || pre == node.right) {
                        result.add(String.valueOf(node.val));
                        pre = node;
                        node = null;
                    }
                }
            }
        }

        @Override
        public void printByLevelOrder(List<String> result) {
            Queue<TreeNode> queue = new LinkedList<>();

            TreeNode root = getTree().getRoot();
            if (root != null) {
                queue.add(root);
            }

            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                result.add(String.valueOf(node.val));

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }

    public static void main(String[] args) {
        /**
         *            10
         *         5     15
         *       3   7      18
         *
         *   先序：10,5,3,7,15,18
         *   中序：3,5,7,10,15,18
         *   后序：3,7,5,18,15,10
         */
        Integer[] datas = new Integer[]{10, 5, 15, 3, 7, null, 18};
        BinaryTree tree = new BinaryTree(datas);

        tree.print(OrderEnum.PRE_ORDER);
        tree.print(OrderEnum.IN_ORDER);
        tree.print(OrderEnum.POST_ORDER);
        tree.print(OrderEnum.LEVEL_ORDER);
    }
}
