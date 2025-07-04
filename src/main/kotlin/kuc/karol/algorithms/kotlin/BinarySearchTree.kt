package kuc.karol.algorithms.kotlin

data class TreeNode(
    val left: TreeNode? = null,
    val right: TreeNode? = null,
    val value: Int
)

class BinarySearchTree {

    fun insertRecursive(root: TreeNode?, value: Int): TreeNode = when {
        root == null -> TreeNode(value = value)
        value < root.value -> root.copy(left = insertRecursive(root.left, value))
        value > root.value -> root.copy(right = insertRecursive(root.right, value))
        else -> root
    }
}
