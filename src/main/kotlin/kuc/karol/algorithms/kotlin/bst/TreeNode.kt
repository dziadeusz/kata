package kuc.karol.algorithms.kotlin.bst

import kotlin.math.abs

data class Target(val value: Int)

data class Closest(val value: Int) {
    infix fun canBeImprovedTowards(target: Target): ClosestTargetPair {
        return ClosestTargetPair(this, target)
    }

    fun isCloserThan(closest: Closest, target: Target) =
        abs(value - target.value) < abs(closest.value - target.value)

    infix fun isCloserTo(target: Target): ClosestTargetComparison {
        return ClosestTargetComparison(this, target)
    }
}

data class ClosestTargetPair(val closest: Closest, val target: Target) {
    infix fun within(node: TreeNode?): Boolean {
        return node != null && target.value != node.value
    }
}

data class ClosestTargetComparison(val closest: Closest, val target: Target) {
    infix fun than(otherClosest: Closest): Boolean {
        return closest.isCloserThan(otherClosest, target)
    }
}

data class TreeNode(
    val left: TreeNode? = null,
    val right: TreeNode? = null,
    val value: Int
) {
    fun insertRecursive(newValue: Int): TreeNode = when {
        newValue < value -> copy(left = left?.insertRecursive(newValue) ?: TreeNode(value = newValue))
        newValue > value -> copy(right = right?.insertRecursive(newValue) ?: TreeNode(value = newValue))
        else -> this
    }

    fun findClosestIterative(node: TreeNode, target: Target): Int {
        var currentNode: TreeNode? = node
        var closest = Closest(node.value)
        while (closest canBeImprovedTowards target within currentNode) {
            val maybeCloser = Closest(currentNode!!.value)
            if (maybeCloser isCloserTo target than closest) {
                closest = maybeCloser
            }
            currentNode = currentNode traverseDownTowards target
        }
        return closest.value
    }

    private infix fun traverseDownTowards(target: Target) =
        if (target.value == value) this
        else if (target.value < value) left
        else right


    companion object {
        fun insertRecursive(root: TreeNode?, value: Int): TreeNode {
            return root?.insertRecursive(value) ?: TreeNode(value = value)
        }
    }
}
