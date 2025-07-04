package kuc.karol.algorithms.kotlin

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

    companion object {
        fun insertRecursive(root: TreeNode?, value: Int): TreeNode {
            return root?.insertRecursive(value) ?: TreeNode(value = value)
        }
    }
}
