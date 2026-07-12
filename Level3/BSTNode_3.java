public class sTest {
        
   @Test
    public void testWideAllNodes_LeftSkewedTree() {
         BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(8, 8);
        bst.AddKeyValue(6, 6);
        bst.AddKeyValue(4, 4);

        List<Integer> path = bst.WideAllNodes();
        List<Integer> expected = List.of(10, 8, 6, 4);
        assertEquals(expected, path);
    }

    @Test
    public void testWideAllNodes_NormalTree() {
        BST<Integer> bst = new BST<>(null);

        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(20, 20);
        bst.AddKeyValue(3, 3);
        bst.AddKeyValue(7, 7);
        bst.AddKeyValue(15, 15);
        bst.AddKeyValue(25, 25);

        List<Integer> path = bst.WideAllNodes();

        List<Integer> expected = List.of(10, 5, 20, 3, 7, 15, 25);
        assertEquals(expected, path);
    }

    @Test
    public void testWideAllNodes_EmptyTree() {
        BST<Integer> bst = new BST<>(null);
        List<Integer> path = bst.WideAllNodes();
        assertTrue(path.isEmpty());
    }

    @Test
    public void testWideAllNodes_SingleNode() {
        BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(42, 42);

        List<Integer> path = bst.WideAllNodes();
        assertEquals(List.of(42), path);
    }

    @Test
    public void testDeepAllNodes_PreOrder() {
        BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(20, 20);

        ArrayList<BSTNode> nodes = bst.DeepAllNodes(2); 
        
        List<Integer> keys = new ArrayList<>();
        for (BSTNode<Integer> n : nodes) keys.add(n.NodeKey);

        assertEquals(List.of(10, 5, 20), keys);
    }

    @Test
    public void testDeepAllNodes_InOrder() {
        BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(20, 20);

        ArrayList<BSTNode> nodes = bst.DeepAllNodes(0); 
        
        List<Integer> keys = new ArrayList<>();
        for (BSTNode<Integer> n : nodes) keys.add(n.NodeKey);

        assertEquals(List.of(5, 10, 20), keys);
    }

    @Test
    public void testDeepAllNodes_PostOrder() {
        BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(20, 20);

        ArrayList<BSTNode> nodes = bst.DeepAllNodes(1);
        
        List<Integer> keys = new ArrayList<>();
        for (BSTNode<Integer> n : nodes) keys.add(n.NodeKey);

        assertEquals(List.of(5, 20, 10), keys);
    }

    @Test
    public void testDeepAllNodes_InvalidParameter() {
        BST<Integer> bst = new BST<>(null);
        ArrayList<BSTNode> nodes = bst.DeepAllNodes(99);
   
        assertTrue(nodes.isEmpty());
    }
}



