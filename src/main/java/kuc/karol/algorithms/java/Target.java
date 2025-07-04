package kuc.karol.algorithms.java;

public record Target(Integer value) {

    public boolean canHaveBetterMatchIn(TreeNode node, Closest closest) {
        return node != null && !value.equals(closest.value());
    }
}
