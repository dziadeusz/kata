package kuc.karol.algorithms.java.bst;

import java.util.Objects;
import java.util.stream.Stream;

public class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private Integer value;

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
        } else if (value > this.value) {
            if (this.right == null) {
                this.right = new TreeNode(value);
            } else {
                this.right.insertRecursive(value);
            }
        }
    }

    TreeNode delete(Integer toDelete) {
        if (toDelete < value && left != null) {
            left = left.delete(toDelete);
            return this;
        }
        if (toDelete > value && right != null) {
            right = right.delete(toDelete);
            return this;
        }
        // If we reach here and the value doesn't match, the node to delete doesn't exist
        if (toDelete != value) {
            return this; // Return unchanged if node to delete is not found
        }
        if (hasNoChildren()) return null;
        var children = Stream.of(left, right).filter(Objects::nonNull).toList();
        if (children.size() == 1) {
            return children.getFirst();
        }
        TreeNode successor = null;
        if (right() != null) {
            successor = right.findNodeWithSmallestValue();
            if(successor == right) {
                this.right = null;
            } else {
                this.right = this.right.delete(successor.value);
            }
            this.value = successor.value;
        }

        return this;
    }

    private boolean hasNoChildren() {
        return left == null && right == null;
    }


    TreeNode findNodeWithSmallestValue() {
        TreeNode current = this;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    TreeNode findNodeWithBiggestValue() {
        TreeNode current = this;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }
}
