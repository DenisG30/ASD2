public class sTest {
        
   @Test
    public void testWideAllNodes_LeftSkewedTree() {
        BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(8, 8);
        bst.AddKeyValue(6, 6);
        bst.AddKeyValue(4, 4);

        ArrayList<BSTNode> nodes = bst.WideAllNodes();
        assertEquals(4, nodes.size());

        List<Integer> actualKeys = keys(nodes);
        List<Integer> expectedKeys = List.of(10, 8, 6, 4);
        assertEquals(expectedKeys, actualKeys);
    }

    @Test
    public void testWideAllNodes_RightSkewedTree() {
        BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(1, 1);
        bst.AddKeyValue(3, 3);
        bst.AddKeyValue(5, 5);
        bst.AddKeyValue(7, 7);

        ArrayList<BSTNode> nodes = bst.WideAllNodes();
        assertEquals(4, nodes.size());

        List<Integer> actualKeys = keys(nodes);
        List<Integer> expectedKeys = List.of(1, 3, 5, 7);
        assertEquals(expectedKeys, actualKeys);
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

        ArrayList<BSTNode> nodes = bst.WideAllNodes();
        assertEquals(7, nodes.size());

        List<Integer> actualKeys = keys(nodes);
        List<Integer> expectedKeys = List.of(10, 5, 20, 3, 7, 15, 25);
        assertEquals(expectedKeys, actualKeys);
    }

    @Test
    public void testWideAllNodes_EmptyTree() {
        BST<Integer> bst = new BST<>(null);
        ArrayList<BSTNode> nodes = bst.WideAllNodes();

        assertTrue(nodes.isEmpty());
        assertEquals(0, nodes.size());
    }

    @Test
    public void testWideAllNodes_SingleNode() {
        BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(42, 42);

        ArrayList<BSTNode> nodes = bst.WideAllNodes();
        assertEquals(1, nodes.size());

        BSTNode node = nodes.get(0);
        assertNotNull(node);

        assertTrue(Integer.valueOf(42).equals(node.NodeKey));
    }


    @Test
    public void testWideAllNodes_LeftSkewedTre1e() {
        BST<Integer> bst = new BST<>(null);
        bst.AddKeyValue(10, 10);
        bst.AddKeyValue(8, 8);
        bst.AddKeyValue(6, 6);
        bst.AddKeyValue(4, 4);

        ArrayList<BSTNode> nodes = bst.WideAllNodes();
        assertEquals(4, nodes.size());

        List<Integer> actualKeys = keys(nodes);
        List<Integer> expectedKeys = List.of(10, 8, 6, 4);
        assertEquals(expectedKeys, actualKeys);
    }

    private List<Integer> keys(ArrayList<BSTNode> nodes) {
        List<Integer> keys = new ArrayList<>();
        for (BSTNode node : nodes) {
            assertNotNull(node);
            keys.add((Integer) node.NodeKey);
        }
        return keys;
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
    



