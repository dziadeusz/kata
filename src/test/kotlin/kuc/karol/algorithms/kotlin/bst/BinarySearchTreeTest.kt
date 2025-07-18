package kuc.karol.algorithms.kotlin.bst

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BinarySearchTreeTest {

    private fun createTestTree(): TreeNode {
        var root: TreeNode? = null
        root = TreeNode.insertRecursive(root, 10)
        root = root.insertRecursive(5)
        root = root.insertRecursive(15)
        root = root.insertRecursive(3)
        root = root.insertRecursive(20)
        return root
    }

    @Test
    fun `findClosestRecursive should return exact match when target value exists in tree`() {
        // given:
        val root = createTestTree()
        val target = Target(10)

        // when:
        val result = root.findClosestIterative(root, target)

        // then:
        Assertions.assertEquals(10, result)
    }

    @Test
    fun `findClosestRecursive should return closest value when target value does not exist in tree`() {
        // given:
        val root = createTestTree()
        val target = Target(11)

        // when:
        val result = root.findClosestIterative(root, target)

        // then:
        Assertions.assertEquals(10, result)
    }

    @Test
    fun `findClosestRecursive should return closest value when target is between two nodes`() {
        // given:
        val root = createTestTree()
        val target = Target(13)

        // when:
        val result = root.findClosestIterative(root, target)

        // then:
        Assertions.assertEquals(15, result)
    }

    @Test
    fun `findClosestRecursive should return closest value when target is less than all nodes`() {
        // given:
        val root = createTestTree()
        val target = Target(1)

        // when:
        val result = root.findClosestIterative(root, target)

        // then:
        Assertions.assertEquals(3, result)
    }

    @Test
    fun `findClosestRecursive should return closest value when target is greater than all nodes`() {
        // given:
        val root = createTestTree()
        val target = Target(25)

        // when:
        val result = root.findClosestIterative(root, target)

        // then:
        Assertions.assertEquals(20, result)
    }

    @Test
    fun `insertRecursive should create a new node when root is null`() {
        // given:
        val value = 10

        // when:
        val result = TreeNode.insertRecursive(null, value)

        // then:
        Assertions.assertEquals(value, result.value)
        Assertions.assertNull(result.left)
        Assertions.assertNull(result.right)
    }

    @Test
    fun `insertRecursive should add smaller value to the left`() {
        // given:
        val rootValue = 10
        val smallerValue = 5
        val root = TreeNode(value = rootValue)

        // when:
        val result = root.insertRecursive(smallerValue)

        // then:
        Assertions.assertEquals(rootValue, result.value)
        Assertions.assertNotNull(result.left)
        Assertions.assertEquals(smallerValue, result.left?.value)
        Assertions.assertNull(result.right)
    }

    @Test
    fun `insertRecursive should add larger value to the right`() {
        // given:
        val rootValue = 10
        val largerValue = 15
        val root = TreeNode(value = rootValue)

        // when:
        val result = root.insertRecursive(largerValue)

        // then:
        Assertions.assertEquals(rootValue, result.value)
        Assertions.assertNull(result.left)
        Assertions.assertNotNull(result.right)
        Assertions.assertEquals(largerValue, result.right?.value)
    }

    @Test
    fun `insertRecursive should not modify tree when inserting duplicate value`() {
        // given:
        val rootValue = 10
        val root = TreeNode(value = rootValue)

        // when:
        val result = root.insertRecursive(rootValue)

        // then:
        Assertions.assertEquals(rootValue, result.value)
        Assertions.assertNull(result.left)
        Assertions.assertNull(result.right)
        Assertions.assertSame(root, result) // Should return the same object for duplicates
    }

    @Test
    fun `insertRecursive should build a multi-level tree with multiple insertions`() {
        // given:
        var root: TreeNode? = null

        // when:
        // Build a tree: 10 as root, 5 and 3 to the left, 15 and 20 to the right
        root = TreeNode.insertRecursive(root, 10)
        root = root.insertRecursive(5)
        root = root.insertRecursive(15)
        root = root.insertRecursive(3)
        root = root.insertRecursive(20)

        // then:
        Assertions.assertNotNull(root)
        Assertions.assertEquals(10, root.value)

        // Left subtree
        Assertions.assertNotNull(root.left)
        Assertions.assertEquals(5, root.left?.value)
        Assertions.assertNotNull(root.left?.left)
        Assertions.assertEquals(3, root.left?.left?.value)
        Assertions.assertNull(root.left?.right)

        // Right subtree
        Assertions.assertNotNull(root.right)
        Assertions.assertEquals(15, root.right?.value)
        Assertions.assertNotNull(root.right?.right)
        Assertions.assertEquals(20, root.right?.right?.value)
        Assertions.assertNull(root.right?.left)
    }
}