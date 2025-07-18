package kuc.karol.algorithms.java.bst;

public class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private final Integer value;

    public TreeNode(TreeNode left, TreeNode right, Integer value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public TreeNode(Integer value) {
        this(null, null, value);
    }

    public TreeNode left() {
        return left;
    }

    public TreeNode right() {
        return right;
    }

    public Integer value() {
        return value;
    }

    public TreeNode subtreeFor(Target target) {
        if (value.equals(target.value())) {
            return this;
        } else if (target.value() < value) {
            return left;
        } else {
            return right;
        }
    }

    public Integer findClosest(Target target) {
        Closest closest = new Closest(value);
        TreeNode node = this;

        while (target.canHaveBetterMatchIn(node, closest)) {
            Closest current = new Closest(node.value());
            if (current.isCloserToTargetThan(closest, target)) {
                closest = current;
            }
            node = node.subtreeFor(target);
        }
        return closest.value();
    }

    void insertRecursive(Integer value) {
        if (value < this.value) {
            if (this.left == null) {
                this.left = new TreeNode(value);
            } else {
                this.left.insertRecursive(value);
            }
        } else if(value > this.value) {
            if (this.right == null) {
                this.right = new TreeNode(value);
            } else {
                this.right.insertRecursive(value);
            }
        }
    }
}
