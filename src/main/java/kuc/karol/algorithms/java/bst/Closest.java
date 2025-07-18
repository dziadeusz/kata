package kuc.karol.algorithms.java.bst;

record Closest(Integer value) {

    boolean isCloserToTargetThan(Closest other, Target target) {
        int thisDistance = Math.abs(value - target.value());
        int otherDistance = Math.abs(other.value() - target.value());
        return thisDistance < otherDistance;
    }
}