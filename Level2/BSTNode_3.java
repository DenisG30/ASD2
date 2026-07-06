import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;    
    
public class BSTNodeTest {

    @Test
    public void testFindExistKey() {
        BST<Integer> bst = new BST<>(null);

        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(20, 20);
        bst.AddKeyValue(3, 3);
        bst.AddKeyValue(7, 7);
        bst.AddKeyValue(15, 15);
        bst.AddKeyValue(25, 25);

        BSTFind<Integer> result = bst.FindNodeByKey(7);

        assertTrue(result.NodeHasKey);
        assertNotNull(result.Node);
        assertEquals(7, (int) result.Node.NodeKey);
        assertSame(result.Node, bst.Root.LeftChild.RightChild);
    }   

    @Test
    public void testFindKeyInsertToLeft() {
        BST<Integer> bst = new BST<>(null);

        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(20, 20);

        BSTFind<Integer> result = bst.FindNodeByKey(8);

        assertFalse(result.NodeHasKey);
        assertNotNull(result.Node);
        assertEquals(10, (int) result.Node.NodeKey);
        assertTrue(result.ToLeft);
    }

    @Test
    public void testFindKeyInsertToRight() {
        BST<Integer> bst = new BST<>(null);

        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);

        BSTFind<Integer> result = bst.FindNodeByKey(12);

        assertFalse(result.NodeHasKey);
        assertNotNull(result.Node);
        assertEquals(10, (int) result.Node.NodeKey);
        assertFalse(result.ToLeft);
    }

    @Test
    public void testFindEmptyTree() {
        BST<Integer> bst = new BST<>(null);

        BSTFind<Integer> result = bst.FindNodeByKey(42);
        assertNull(result);

        bst.AddKeyValue(10, 10);

        result = bst.FindNodeByKey(10);
        assertNotNull(result);
    }

    @Test
    public void testAddNewKeyLeftChild() {
        BST<Integer> bst = new BST<>(null);

        BSTFind<Integer> result = bst.FindNodeByKey(42);
        assertNull(result);
        assertEquals(0, bst.Count());

        assertTrue(bst.AddKeyValue(5, 5));
        assertEquals(1, bst.Count());
        assertTrue(bst.FindNodeByKey(5).NodeHasKey);

        assertTrue(bst.AddKeyValue(3, 3));
        assertEquals(2, bst.Count());

        BSTFind<Integer> find3 = bst.FindNodeByKey(3);
        assertTrue(find3.NodeHasKey);

        BSTNode<Integer> node5 = bst.FindNodeByKey(5).Node;
        BSTNode<Integer> node3 = find3.Node;

        assertSame(node5.LeftChild, node3);
        assertNull(node5.RightChild);
        assertSame(node3.Parent, node5);
    }

    @Test
    public void testAddNewKeyRightChild() {
        BST<Integer> bst = new BST<>(null);

        BSTFind<Integer> result = bst.FindNodeByKey(42);
        assertNull(result);
        assertEquals(0, bst.Count());

        assertTrue(bst.AddKeyValue(10, 10));
        assertEquals(1, bst.Count());

        assertTrue(bst.AddKeyValue(20, 20));
        assertEquals(2, bst.Count());

        BSTFind<Integer> find20 = bst.FindNodeByKey(20);
        assertTrue(find20.NodeHasKey);

        BSTNode<Integer> node10 = bst.FindNodeByKey(10).Node;
        BSTNode<Integer> node20 = find20.Node;

        assertNull(node10.LeftChild);
        assertSame(node10.RightChild, node20);
        assertSame(node20.Parent, node10);
    }

    @Test
    public void testAddExistKeyNoChange() {
        BST<Integer> bst = new BST<>(null);

        assertTrue(bst.AddKeyValue(7, 7));
        assertEquals(1, bst.Count());
        assertTrue(bst.FindNodeByKey(7).NodeHasKey);

        int initialCount = bst.Count();
        BSTNode<Integer> originalNode = bst.FindNodeByKey(7).Node;

        assertFalse(bst.AddKeyValue(7, 99));
        assertEquals(initialCount, bst.Count());

        BSTFind<Integer> findResult = bst.FindNodeByKey(7);
        assertTrue(findResult.NodeHasKey);

        assertSame(originalNode, findResult.Node);

        assertEquals(7, (int) originalNode.NodeValue);

        assertNull(originalNode.LeftChild);
        assertNull(originalNode.RightChild);
        assertNull(originalNode.Parent);
    }

    @Test
    public void testFinMinMaxMin() {
        BST<Integer> bst = new BST<>(null);

        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(20, 20);
        bst.AddKeyValue(3, 3);
        bst.AddKeyValue(7, 7);
        bst.AddKeyValue(15, 15);
        bst.AddKeyValue(25, 25);

        BSTNode<Integer> minFromRoot = bst.FinMinMax(bst.Root, false);

        assertNotNull(minFromRoot);
        assertEquals(3, (int) minFromRoot.NodeKey);
        assertNull(minFromRoot.LeftChild);
        assertNotNull(minFromRoot.Parent);
        assertEquals(5, (int) minFromRoot.Parent.NodeKey);
    }

    @Test
    public void testFinMinMaxMax() {
        BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(20, 20);
        bst.AddKeyValue(3, 3);
        bst.AddKeyValue(7, 7);
        bst.AddKeyValue(15, 15);
        bst.AddKeyValue(25, 25);

        BSTNode<Integer> maxFromRoot = bst.FinMinMax(bst.Root, true);

        assertNotNull(maxFromRoot);
        assertEquals(25, (int) maxFromRoot.NodeKey);
        assertNull(maxFromRoot.RightChild); 
        assertNotNull(maxFromRoot.Parent);
        assertEquals(20, (int) maxFromRoot.Parent.NodeKey);
    }

    @Test
    public void testFinMinMax_Min() {
        BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(20, 20);
        bst.AddKeyValue(3, 3);
        bst.AddKeyValue(7, 7);
        bst.AddKeyValue(15, 15);
        bst.AddKeyValue(25, 25);

        BSTFind<Integer> find5Result = bst.FindNodeByKey(5);
        assertTrue(find5Result.NodeHasKey);
        BSTNode<Integer> node5 = find5Result.Node;

        BSTNode<Integer> minInSubtree = bst.FinMinMax(node5, false);

        assertNotNull(minInSubtree);
        assertEquals(3, (int) minInSubtree.NodeKey);
        assertNull(minInSubtree.LeftChild);
        assertNotNull(minInSubtree.Parent);
        assertEquals(5, (int) minInSubtree.Parent.NodeKey);
    }

    @Test
    public void testFinMinMax_Max() {
        BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(20, 20);
        bst.AddKeyValue(3, 3);
        bst.AddKeyValue(7, 7);
        bst.AddKeyValue(15, 15);
        bst.AddKeyValue(25, 25);

        BSTFind<Integer> find20Result = bst.FindNodeByKey(20);
        assertTrue(find20Result.NodeHasKey);
        BSTNode<Integer> node20 = find20Result.Node;

        BSTNode<Integer> maxInSubtree = bst.FinMinMax(node20, true);

        assertNotNull(maxInSubtree);
        assertEquals(25, (int) maxInSubtree.NodeKey);
        assertNull(maxInSubtree.RightChild);
        assertNotNull(maxInSubtree.Parent);
        assertEquals(20, (int) maxInSubtree.Parent.NodeKey);
    }

    @Test
    public void testDeleteLeafNode() {
        BST<Integer> bst = new BST<>(null);

        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(3, 3);
        bst.AddKeyValue(7, 7);

        int initialCount = bst.Count();

        BSTFind<Integer> findBefore = bst.FindNodeByKey(3);
        assertTrue(findBefore.NodeHasKey);
        assertNotNull(findBefore.Node);

        BSTNode<Integer> node3Before = findBefore.Node;
        BSTNode<Integer> parent5 = node3Before.Parent;
        assertSame(parent5.LeftChild, node3Before);

        boolean result = bst.DeleteNodeByKey(3);

        assertTrue(result);

        BSTFind<Integer> findAfter = bst.FindNodeByKey(3);
        assertFalse(findAfter.NodeHasKey);

        assertEquals(initialCount - 1, bst.Count());

        assertNull(parent5.LeftChild);
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        BST<Integer> bst = new BST<>(null);

        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(7, 7);

        int initialCount = bst.Count();

        BSTFind<Integer> find5Before = bst.FindNodeByKey(5);
        assertTrue(find5Before.NodeHasKey);
        BSTNode<Integer> node5 = find5Before.Node;
        BSTNode<Integer> node7 = node5.RightChild;
        assertNotNull(node7);
        assertSame(node7.Parent, node5);

        boolean result = bst.DeleteNodeByKey(5);
        assertTrue(result);

        BSTFind<Integer> find5After = bst.FindNodeByKey(5);
        assertFalse(find5After.NodeHasKey);

        assertEquals(initialCount - 1, bst.Count());

        BSTNode<Integer> root = bst.Root;
        assertSame(root.LeftChild, node7);
        assertSame(node7.Parent, root);
    }


    @Test
    public void testDeleteNodeWithTwoChildren() {
        BST<Integer> bst = new BST<>(null);

        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(20, 20);
        bst.AddKeyValue(3, 3);
        bst.AddKeyValue(7, 7);
        bst.AddKeyValue(15, 15);
        bst.AddKeyValue(25, 25);

        int initialCount = bst.Count();

        BSTFind<Integer> find10Before = bst.FindNodeByKey(10);
        assertTrue(find10Before.NodeHasKey);
        BSTNode<Integer> node10 = find10Before.Node;

        BSTNode<Integer> successor = bst.FinMinMax(node10.RightChild, false);
        assertEquals(15, (int) successor.NodeKey);

        boolean result = bst.DeleteNodeByKey(10);
        assertTrue(result);

        BSTFind<Integer> find10After = bst.FindNodeByKey(10);
        assertFalse(find10After.NodeHasKey);

        assertEquals(initialCount - 1, bst.Count());

        assertEquals(15, (int) bst.Root.NodeKey);
        assertNull(bst.Root.Parent);

        BSTNode<Integer> node5 = bst.FindNodeByKey(5).Node;
        BSTNode<Integer> node20 = bst.FindNodeByKey(20).Node;

        assertSame(bst.Root.LeftChild, node5);
        assertSame(bst.Root.RightChild, node20);
        assertSame(node5.Parent, bst.Root);
        assertSame(node20.Parent, bst.Root);

        BSTFind<Integer> find15InRightSubtree = bst.FindNodeByKey(15);
        assertTrue(find15InRightSubtree.NodeHasKey); 
        assertSame(find15InRightSubtree.Node, bst.Root);
    }


    @Test
    public void testDeleteNonExistKey() {
        BST<Integer> bst = new BST<>(null);

        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(20, 20);

        int initialCount = bst.Count();
        BSTNode<Integer> rootBefore = bst.Root;

        boolean result = bst.DeleteNodeByKey(999);
        assertFalse(result);

        assertEquals(initialCount, bst.Count());

        assertSame(rootBefore, bst.Root);

        assertTrue(bst.FindNodeByKey(10).NodeHasKey);
        assertTrue(bst.FindNodeByKey(5).NodeHasKey);
        assertTrue(bst.FindNodeByKey(20).NodeHasKey);
    }   
}
    
