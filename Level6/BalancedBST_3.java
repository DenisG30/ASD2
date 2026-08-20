import org.junit.Test;
import static org.junit.Assert.*; 
    
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

        assertNotNull(bst.Root);
        assertEquals(42, bst.Root.NodeKey);
        assertNull(bst.Root.Parent);
        assertEquals(0, bst.Root.Level);
        assertNull(bst.Root.LeftChild);
        assertNull(bst.Root.RightChild);
    }

    @Test
    public void generateTree_oddNumberOfElements_shouldBeBalanced() {
        BalancedBST bst = new BalancedBST();
        int[] arr = {3, 1, 2}; 
        bst.GenerateTree(arr);

        assertNotNull(bst.Root);
        assertEquals(2, bst.Root.NodeKey); 
        assertTrue(bst.IsBalanced(bst.Root));
    }

    @Test
    public void generateTree_evenNumberOfElements_shouldBeBalanced() {
        BalancedBST bst = new BalancedBST();
        int[] arr = {4, 2, 1, 3}; 
        bst.GenerateTree(arr);

        assertNotNull(bst.Root);
        assertTrue(bst.IsBalanced(bst.Root));

    }

    @Test
    public void generateTree_alreadySorted_shouldWork() {
        BalancedBST bst = new BalancedBST();
        int[] arr = {1, 2, 3, 4, 5};
        bst.GenerateTree(arr);

        assertNotNull(bst.Root);
        assertTrue(bst.IsBalanced(bst.Root));
    }

    @Test
    public void generateTree_reverseSorted_shouldWork() {
        BalancedBST bst = new BalancedBST();
        int[] arr = {5, 4, 3, 2, 1};
        bst.GenerateTree(arr);

        assertNotNull(bst.Root);
        assertTrue(bst.IsBalanced(bst.Root));
    }

    @Test
    public void generateTree_withDuplicates_shouldWork() {
        BalancedBST bst = new BalancedBST();
        int[] arr = {2, 2, 1, 3, 3};
        bst.GenerateTree(arr);

        assertNotNull(bst.Root);
        assertTrue(bst.IsBalanced(bst.Root));
    }

    @Test
    public void isBalanced_nullRoot_shouldReturnTrue() {
        BalancedBST bst = new BalancedBST();
        assertTrue(bst.IsBalanced(null));
    }

    @Test
    public void isBalanced_singleNode_shouldReturnTrue() {
        BalancedBST bst = new BalancedBST();
        bst.GenerateTree(new int[]{10});
        assertTrue(bst.IsBalanced(bst.Root));
    }

    @Test
    public void isBalanced_balancedTree_shouldReturnTrue() {
        BalancedBST bst = new BalancedBST();
        int[] arr = {7, 3, 9, 1, 5, 8, 10};
        bst.GenerateTree(arr);
        assertTrue(bst.IsBalanced(bst.Root));
    }
}
    
