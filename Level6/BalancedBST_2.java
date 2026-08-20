// №2
private boolean isValidBST(BSTNode node, long minBound, long maxBound) {
    if (node == null) {
        return true;
    }

    int key = node.NodeKey;

    if (key <= minBound || key >= maxBound) {
        return false;
    }

    boolean leftChild = isValidBST(node.LeftChild, minBound, key);
    boolean rightChild = isValidBST(node.RightChild, key, maxBound);

    return leftChild && rightChild;
}

// №3
private int checkBalanceAndHeight(BSTNode node) {
    if (node == null) {
        return 0;
    }

    int leftHeight = checkBalanceAndHeight(node.LeftChild);
    if (leftHeight == -1) {
        return -1; 
    }

    int rightHeight = checkBalanceAndHeight(node.RightChild);
    if (rightHeight == -1) {
        return -1; 
    }

    if (Math.abs(leftHeight - rightHeight) > 1) {
        return -1; 
    }

    return Math.max(leftHeight, rightHeight) + 1;
}

/*Рефлексия
№2
Использовал итеративный подход, вместо рекурсивного. Ошибочно получается.
№3
Здесь тоже. Получается не BFS, а простой линейный обход массива. Работает с массивом без пропусков. 
*/
