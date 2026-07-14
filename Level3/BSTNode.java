class BSTNode<T>
{
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок	
	
    public BSTNode(int key, T val, BSTNode<T> parent)
    {
      NodeKey = key;
      NodeValue = val;
      Parent = parent;
      LeftChild = null;
      RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T>
{
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;
	
    // true если узел найден
    public boolean NodeHasKey;
	
    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;
	
    public BSTFind() { Node = null; }
}

class BST<T>
{
    BSTNode<T> Root;
	
    public BST(BSTNode<T> node)
    {
	  Root = node;
    }
  
    public ArrayList<BSTNode> WideAllNodes() {
        ArrayList<Integer> result = new ArrayList<>();

        if (Root == null) {
            return result;
        }

        Queue<BSTNode<T>> queue = new LinkedList<>();
        queue.add(Root);

        for (; !queue.isEmpty(); ) {
            BSTNode<T> node = queue.poll();
            result.add(node.NodeKey);

            if (node.LeftChild != null) {
                queue.add(node.LeftChild);
            }
            if (node.RightChild != null) {
                queue.add(node.RightChild);
            }
        }
        return result;
    }

    public ArrayList<BSTNode> DeepAllNodes(int order) {
        //int order; 
		ArrayList<BSTNode> result = new ArrayList<>();
        if (Root == null) {
            return result;
        }

        switch (order) {
            case 0:
                resultInOrder(Root, result);
                break;
            case 1:
                resultPostOrder(Root, result);
                break;
            case 2:
                resultPreOrder(Root, result);
                break;
            default:
                return result;
        }
        return result;
    }

    private void resultInOrder(BSTNode node, ArrayList<BSTNode> list) {
        if (node == null) {
            return;
        }
        resultInOrder(node.LeftChild, list);
        list.add(node);
        resultInOrder(node.RightChild, list);
    }

    private void resultPostOrder(BSTNode node, ArrayList<BSTNode> list) {
        if (node == null) {
            return;
        }
        resultPostOrder(node.LeftChild, list);
        resultPostOrder(node.RightChild, list);
        list.add(node);
    }

    private void resultPreOrder(BSTNode node, ArrayList<BSTNode> list) {
        if (node == null) {
            return;
        }
        list.add(node);
        resultPreOrder(node.LeftChild, list);
        resultPreOrder(node.RightChild, list);
    }
}
