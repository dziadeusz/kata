package kuc.karol.algorithms.kotlin

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BinarySearchTreeTest {

    @Test
    fun `insertRecursive should create a new node when root is null`() {
        // given:
        val value = 10

        // when:
        val result = TreeNode.insertRecursive(null, value)

        // then:
        assertEquals(value, result.value)
        assertNull(result.left)
        assertNull(result.right)
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
        assertEquals(rootValue, result.value)
        assertNotNull(result.left)
        assertEquals(smallerValue, result.left?.value)
        assertNull(result.right)
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
        assertEquals(rootValue, result.value)
        assertNull(result.left)
        assertNotNull(result.right)
        assertEquals(largerValue, result.right?.value)
    }

    @Test
    fun `insertRecursive should not modify tree when inserting duplicate value`() {
        // given:
        val rootValue = 10
        val root = TreeNode(value = rootValue)

        // when:
        val result = root.insertRecursive(rootValue)

        // then:
        assertEquals(rootValue, result.value)
        assertNull(result.left)
        assertNull(result.right)
        assertSame(root, result) // Should return the same object for duplicates
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
        assertNotNull(root)
        assertEquals(10, root?.value)

        // Left subtree
        assertNotNull(root?.left)
        assertEquals(5, root?.left?.value)
        assertNotNull(root?.left?.left)
        assertEquals(3, root?.left?.left?.value)
        assertNull(root?.left?.right)

        // Right subtree
        assertNotNull(root?.right)
        assertEquals(15, root?.right?.value)
        assertNotNull(root?.right?.right)
        assertEquals(20, root?.right?.right?.value)
        assertNull(root?.right?.left)
    }
}
