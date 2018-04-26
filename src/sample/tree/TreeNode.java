package sample.tree;

import sample.Question;

import java.util.Map;

public class TreeNode {
    private Question value;
    private Map<String, TreeNode> children;
    private TreeNode parent;

    public TreeNode(Question value, TreeNode parent) {
        this.value = value;
        this.parent = parent;
    }

    public Question getValue() {
        return value;
    }

    public TreeNode getParent() {
        return parent;
    }

    public Map<String, TreeNode> getChildren(){
        return children;
    }

    public void setChildren(Map<String, TreeNode> children) {
        this.children = children;
    }
}
