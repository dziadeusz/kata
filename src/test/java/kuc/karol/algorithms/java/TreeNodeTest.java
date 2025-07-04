package kuc.karol.algorithms.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TreeNodeTest {

    @Test
    public void findClosestShouldReturnExactMatchWhenTargetValueExistsInTree() {
        // given:
        TreeNode root = createTestTree();
        Target target = new Target(10);

        // when:
        Integer result = root.findClosest(target);

        // then:
        assertEquals(10, result);
    }

    @Test
    public void findClosestShouldReturnClosestValueWhenTargetValueDoesNotExistInTree() {
        // given:
        TreeNode root = createTestTree();
        Target target = new Target(11);

        // when:
        Integer result = root.findClosest(target);

        // then:
        assertEquals(10, result);
    }

    @Test
    public void findClosestShouldReturnClosestValueWhenTargetIsBetweenTwoNodes() {
        // given:
        TreeNode root = createTestTree();
        Target target = new Target(13);

        // when:
        Integer result = root.findClosest(target);

        // then:
        assertEquals(15, result);
    }

    @Test
    public void findClosestShouldReturnClosestValueWhenTargetIsLessThanAllNodes() {
        // given:
        TreeNode root = createTestTree();
        Target target = new Target(1);

        // when:
        Integer result = root.findClosest(target);

        // then:
        assertEquals(3, result);
    }

    @Test
    public void findClosestShouldReturnClosestValueWhenTargetIsGreaterThanAllNodes() {
        // given:
        TreeNode root = createTestTree();
        Target target = new Target(25);

        // when:
        Integer result = root.findClosest(target);

        // then:
        assertEquals(20, result);
    }

    private TreeNode createTestTree() {
        TreeNode root = new TreeNode(10);
        root.insertRecursive(5);
        root.insertRecursive(15);
        root.insertRecursive(3);
        root.insertRecursive(20);
        return root;
    }


    @Test
    public void insertRecursiveRecursiveShouldAddSmallerValueToTheLeft() {
        // given:
        Integer rootValue = 10;
        Integer smallerValue = 5;
        TreeNode root = new TreeNode(rootValue);

        // when:
        root.insertRecursive(smallerValue);

        // then:
        assertEquals(rootValue, root.value());
        assertNotNull(root.left());
        assertEquals(smallerValue, root.left().value());
        assertNull(root.right());
    }

    @Test
    public void insertRecursiveRecursiveShouldAddLargerValueToTheRight() {
        // given:
        Integer rootValue = 10;
        Integer largerValue = 15;
        TreeNode root = new TreeNode(rootValue);

        // when:
        root.insertRecursive(largerValue);

        // then:
        assertEquals(rootValue, root.value());
        assertNull(root.left());
        assertNotNull(root.right());
        assertEquals(largerValue, root.right().value());
    }

    @Test
    public void insertRecursiveShouldNotModifyTreeWhenInsertingDuplicateValue() {
        // given:
        Integer rootValue = 10;
        TreeNode root = new TreeNode(null, null, rootValue);

        // when:
        root.insertRecursive(rootValue);

        // then:
        assertEquals(rootValue, root.value());
        assertNull(root.left());
        assertNull(root.right());
        assertSame(root, root);
    }

    @Test
    public void insertRecursiveRecursiveShouldBuildMultiLevelTreeWithMultipleInsertions() {
        // given:
        TreeNode root = new TreeNode(10);

        // when:
        root.insertRecursive(5);
        root.insertRecursive(15);
        root.insertRecursive(3);
        root.insertRecursive(20);

        // then:
        assertEquals(10, root.value());

        assertNotNull(root.left());
        assertEquals(5, root.left().value());
        assertNotNull(root.left().left());
        assertEquals(3, root.left().left().value());
        assertNull(root.left().right());

        assertNotNull(root.right());
        assertEquals(15, root.right().value());
        assertNotNull(root.right().right());
        assertEquals(20, root.right().right().value());
        assertNull(root.right().left());
    }
}
