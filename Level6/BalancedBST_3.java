import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;    
    
public class BalancedBSTTest {

    @Test
    public void generateTree_nullArray_shouldSetRootNull() {
        BalancedBST bst = new BalancedBST();
        bst.GenerateTree(null);
        assertNull(bst.Root);
    }

    @Test
    public void generateTree_emptyArray_shouldSetRootNull() {
        BalancedBST bst = new BalancedBST();
        bst.GenerateTree(new int[]{});
        assertNull(bst.Root);
    }

    @Test
    public void generateTree_singleElement_shouldBuildCorrectTree() {
        BalancedBST bst = new BalancedBST();
        int[] arr = {42};
        bst.GenerateTree(arr);

        assertNotNull(bst.Root, "Root не должен быть null");
        assertEquals(42, bst.Root.NodeKey);
        assertNull(bst.Root.Parent);
        assertEquals(0, bst.Root.Level);
        assertNull(bst.Root.LeftChild);
        assertNull(bst.Root.RightChild);

        verifyTreeStructure(bst.Root, null, 0);
        assertEquals(1, countNodes(bst.Root));
        assertArrayContentsInTree(arr, bst.Root);
    }

    @Test
    public void generateTree_oddNumberOfElements_shouldBeBalanced() {
        BalancedBST bst = new BalancedBST();
        int[] arr = {3, 1, 2}; // после сортировки: [1, 2, 3]
        bst.GenerateTree(arr);

        assertNotNull(bst.Root);
        assertEquals(2, bst.Root.NodeKey); // середина — корень
        assertTrue(bst.IsBalanced(bst.Root), "Дерево должно быть сбалансированным");

        verifyTreeStructure(bst.Root, null, 0);
        assertEquals(3, countNodes(bst.Root));
        assertArrayContentsInTree(arr, bst.Root);
    }

    @Test
    void generateTree_evenNumberOfElements_shouldBeBalanced() {
        BalancedBST bst = new BalancedBST();
        int[] arr = {4, 2, 1, 3}; // после сортировки: [1, 2, 3, 4]
        bst.GenerateTree(arr);

        assertNotNull(bst.Root);
        assertTrue(bst.IsBalanced(bst.Root), "Дерево должно быть сбалансированным");

        verifyTreeStructure(bst.Root, null, 0);
        assertEquals(4, countNodes(bst.Root));
        assertArrayContentsInTree(arr, bst.Root);
    }

    @Test
    void generateTree_alreadySorted_shouldWork() {
        BalancedBST bst = new BalancedBST();
        int[] arr = {1, 2, 3, 4, 5};
        bst.GenerateTree(arr);

        assertNotNull(bst.Root);
        assertTrue(bst.IsBalanced(bst.Root));

        verifyTreeStructure(bst.Root, null, 0);
        assertEquals(5, countNodes(bst.Root));
        assertArrayContentsInTree(arr, bst.Root);
    }

    @Test
    void generateTree_reverseSorted_shouldWork() {
        BalancedBST bst = new BalancedBST();
        int[] arr = {5, 4, 3, 2, 1};
        bst.GenerateTree(arr);

        assertNotNull(bst.Root);
        assertTrue(bst.IsBalanced(bst.Root));

        verifyTreeStructure(bst.Root, null, 0);
        assertEquals(5, countNodes(bst.Root));
        assertArrayContentsInTree(arr, bst.Root);
    }

    @Test
    void generateTree_withDuplicates_shouldWork() {
        BalancedBST bst = new BalancedBST();
        int[] arr = {2, 2, 1, 3, 3};
        bst.GenerateTree(arr);

        assertNotNull(bst.Root);
        assertTrue(bst.IsBalanced(bst.Root));

        verifyTreeStructure(bst.Root, null, 0);
        assertEquals(5, countNodes(bst.Root));
        assertArrayContentsInTree(arr, bst.Root);
    }


    // --- Тесты IsBalanced ---

    @Test
    void isBalanced_nullRoot_shouldReturnTrue() {
        BalancedBST bst = new BalancedBST();
        assertTrue(bst.IsBalanced(null));
    }

    @Test
    void isBalanced_singleNode_shouldReturnTrue() {
        BalancedBST bst = new BalancedBST();
        bst.GenerateTree(new int[]{10});
        assertTrue(bst.IsBalanced(bst.Root), "Одноузловое дерево сбалансировано");
    }

    @Test
    void isBalanced_balancedTree_shouldReturnTrue() {
        BalancedBST bst = new BalancedBST();
        int[] arr = {7, 3, 9, 1, 5, 8, 10};
        bst.GenerateTree(arr);
        assertTrue(bst.IsBalanced(bst.Root), "Сбалансированное дерево должно проходить проверку");
    }

}
    
