package sample.tree;

import sample.Question;

public class BinaryTree {

    private TreeNode head = null;
    private TreeNode current = null;

    public void add(Question value) {
        head = addRecursive(head, value, head);
        current = (current == null ? head : current);
    }

    private TreeNode addRecursive(TreeNode current, Question value, TreeNode parent) {
        if(current == null) {
            return new TreeNode(value, parent);
        }

        if(value.compareTo(current.value) < 0) {
            current.left = addRecursive(current.left, value, current);
        } else if(value.compareTo(current.value) > 0) {
            current.right = addRecursive(current.right, value, current);
        } else {
            return current;
        }

        return current;
    }

    public TreeNode next(Direction direction) {
        TreeNode result;
        if(direction == Direction.LEFT) {
            result = current.left;
        } else if(direction == Direction.RIGHT) {
            result = current.right;
        } else {
            result = null;//такого быть не должно
            System.err.println("Ты еблана кусок. В BinaryTree.next(Direction direction) ты как уебан передал null. Чего ты добивался, гавна кусок?");
        }
        current = result;
        return current;
    }

    public TreeNode prev() {
        current = current.parent;
        return current;
    }

    public TreeNode getCurrent(){
        return current;
    }

    public void startFromHead(){
        current = head;
    }

    public enum Direction {
        LEFT, RIGHT
    }

}
