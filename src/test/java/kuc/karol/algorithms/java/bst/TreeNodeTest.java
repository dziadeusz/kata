package kuc.karol.algorithms.java.bst;

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
    @Test
    public void findNodeWithSmallestValueShouldReturnNodeWithSmallestValueInTree() {
        // given:
        TreeNode root = createTestTree();
        // The smallest value in the test tree is 3

        // when:
        TreeNode result = root.findNodeWithSmallestValue();

        // then:
        assertNotNull(result);
        assertEquals(3, result.value());
    }

    @Test
    public void findNodeWithSmallestValueShouldReturnSelfForSingleNodeTree() {
        // given:
        TreeNode root = new TreeNode(10);

        // when:
        TreeNode result = root.findNodeWithSmallestValue();

        // then:
        assertNotNull(result);
        assertEquals(10, result.value());
        assertSame(root, result);
    }

    @Test
    public void findNodeWithSmallestValueShouldReturnLeftmostNodeInComplexTree() {
        // given:
        TreeNode root = new TreeNode(10);
        root.insertRecursive(5);
        root.insertRecursive(15);
        root.insertRecursive(3);
        root.insertRecursive(7);
        root.insertRecursive(12);
        root.insertRecursive(20);
        root.insertRecursive(1);
        // The smallest value in this tree is 1

        // when:
        TreeNode result = root.findNodeWithSmallestValue();

        // then:
        assertNotNull(result);
        assertEquals(1, result.value());
    }

    @Test
    public void deleteShouldRemoveLeafNode() {
        // given:
        TreeNode root = new TreeNode(10);
        root.insertRecursive(5);
        root.insertRecursive(15);
        root.insertRecursive(3);
        root.insertRecursive(7);

        // when:
        root = root.delete(3); // Delete leaf node 3

        // then:
        assertEquals(10, root.value());
        assertNotNull(root.left());
        assertEquals(5, root.left().value());
        assertNull(root.left().left()); // 3 should be removed
        assertNotNull(root.left().right());
        assertEquals(7, root.left().right().value());
        assertNotNull(root.right());
        assertEquals(15, root.right().value());
    }

    @Test
    public void deleteShouldRemoveNodeWithLeftChildOnly() {
        // given:
        TreeNode root = new TreeNode(10);
        root.insertRecursive(5);
        root.insertRecursive(15);
        root.insertRecursive(3);

        // when:
        root = root.delete(5); // Delete node 5 which has only left child (3)

        // then:
        assertEquals(10, root.value());
        assertNotNull(root.left());
        assertEquals(3, root.left().value()); // 5 should be replaced by 3
        assertNotNull(root.right());
        assertEquals(15, root.right().value());
    }

    @Test
    public void deleteShouldRemoveNodeWithRightChildOnly() {
        // given:
        TreeNode root = new TreeNode(10);
        root.insertRecursive(5);
        root.insertRecursive(15);
        root.insertRecursive(20);

        // when:
        root = root.delete(15); // Delete node 15 which has only right child (20)

        // then:
        assertEquals(10, root.value());
        assertNotNull(root.left());
        assertEquals(5, root.left().value());
        assertNotNull(root.right());
        assertEquals(20, root.right().value()); // 15 should be replaced by 20
    }

    @Test
    public void deleteShouldRemoveNodeWithTwoChildren() {
        // given:
        TreeNode root = new TreeNode(10);
        root.insertRecursive(5);
        root.insertRecursive(15);
        root.insertRecursive(3);
        root.insertRecursive(7);
        root.insertRecursive(12);
        root.insertRecursive(20);

        // when:
        root = root.delete(15); // Delete node 15 which has two children (12 and 20)

        // then:
        assertEquals(10, root.value());
        assertNotNull(root.left());
        assertEquals(5, root.left().value());
        assertNotNull(root.right());
        assertEquals(20, root.right().value()); // 15 should be replaced by its successor 20
        assertNotNull(root.right().left());
        assertEquals(12, root.right().left().value());
        assertNull(root.right().right()); // 20's original position should be empty
    }

    @Test
    public void deleteShouldHandleSpecialCaseWhereSuccessorIsRightChild() {
        // given:
        TreeNode root = new TreeNode(10);
        root.insertRecursive(5);
        root.insertRecursive(15); // 15 has no left child, so it's its own successor

        // when:
        root = root.delete(10); // Delete root which has two children, but right child is the successor

        // then:
        assertEquals(15, root.value()); // Root should be replaced by 15
        assertNotNull(root.left());
        assertEquals(5, root.left().value());
        assertNull(root.right()); // 15's original position should be empty
    }

    @Test
    public void deleteShouldRemoveRootNodeWithNoChildren() {
        // given:
        TreeNode root = new TreeNode(10);

        // when:
        root = root.delete(10); // Delete root with no children

        // then:
        assertNull(root); // Tree should be empty
    }

    @Test
    public void deleteShouldHandleNonExistentValue() {
        // given:
        TreeNode root = createTestTree();
        // Tree structure: 10 as root, 5 and 15 as children, 3 as child of 5, 20 as child of 15

        // when:
        root = root.delete(7); // 7 doesn't exist in the tree

        // then:
        // Tree structure should remain unchanged
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
