package sample.tree;

import sample.Question;

public class TreeNode {
    Question value;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    TreeNode(Question value, TreeNode parent) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = parent;
    }

    public Question getValue() {
        return value;
    }

    public TreeNode getParent() {
        return parent;
    }
}
