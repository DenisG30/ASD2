// Exercise 3
public void InvertTree() {
        Root = invertNode(Root);
    }

    private BSTNode<T> invertNode(BSTNode<T> node) {
        if (node == null) {
            return null;
        }

        BSTNode<T> leftInverted = invertNode(node.LeftChild);
        BSTNode<T> rightInverted = invertNode(node.RightChild);

        node.LeftChild = rightInverted;
        node.RightChild = leftInverted;

        if (node.LeftChild != null) {
            node.LeftChild.Parent = node;
        }
      
        if (node.RightChild != null) {
            node.RightChild.Parent = node;
        }
      
        return node;
    }

// Exercise 4
public int GetLevelWithMaxSum() {
    if (Root == null) {
        return -1;
    }

    Queue<BSTNode<T>> queue = new LinkedList<>();
    
    queue.add(Root);

    int levelWithMaxSum = 0;         
    long maxSum = Long.MIN_VALUE; 
    
    int currentLevel = 0;

    for (; !queue.isEmpty(); ) {
        int nodesInLevel = queue.size(); 
        long currentLevelSum = 0;        

        for (int i = 0; i < nodesInLevel; i++) {
            BSTNode<T> node = queue.poll();
            
            currentLevelSum += node.NodeKey;

            if (node.LeftChild != null) {
                queue.add(node.LeftChild);
            }
            if (node.RightChild != null) {
                queue.add(node.RightChild);
            }
        }

        if (currentLevelSum > maxSum) {
            maxSum = currentLevelSum;
            levelWithMaxSum = currentLevel;
        }

        currentLevel++;
    }

    return levelWithMaxSum;
}


// Exercise 5
public void BuildFromPreAndInOrder(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || preorder.length != inorder.length) {
            Root = null;
            return;
        }

        HashMap<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        int[] preIndex = {0};

        Root = buildSubtree(preorder, inorder, 0, inorder.length - 1, inorderIndexMap, preIndex);
    }

    private BSTNode<T> buildSubtree(int[] preorder,
                                    int[] inorder,
                                    int inStart,
                                    int inEnd,
                                    HashMap<Integer, Integer> inorderIndexMap,
                                    int[] preIndex) {

        if (inStart > inEnd) {
            return null;
        }

        int rootKey = preorder[preIndex[0]];
        preIndex[0]++;

        BSTNode<T> node = new BSTNode<>(rootKey, null, null);

        int inPos = inorderIndexMap.get(rootKey);

        node.LeftChild = buildSubtree(preorder, inorder, inStart, inPos - 1, inorderIndexMap, preIndex);

        node.RightChild = buildSubtree(preorder, inorder, inPos + 1, inEnd, inorderIndexMap, preIndex);

        if (node.LeftChild != null) {
            node.LeftChild.Parent = node;
        }
        if (node.RightChild != null) {
            node.RightChild.Parent = node;
        }

        return node;
    }
