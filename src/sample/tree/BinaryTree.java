package sample.tree;

public class BinaryTree {

    private TreeNode head = null;
    private TreeNode current = null;

    public void setTree(TreeNode head){
        this.head = head;
        current = head;
    }

    public TreeNode next(String answer) {
        if(current.getChildren().containsKey(answer)){
            current = current.getChildren().get(answer);
            return current;
        } else {
            System.out.println("Что-то пошло не так");
            return null;
        }
    }

    public TreeNode prev() {
        current = current.getParent();
        return current;
    }

    public TreeNode getCurrent(){
        return current;
    }

    public void startFromHead(){
        current = head;
    }

}
