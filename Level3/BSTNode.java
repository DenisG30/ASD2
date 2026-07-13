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
	
    public BSTFind<T> FindNodeByKey(int key)
    {
      BSTFind<T> result = new BSTFind<>();

      if (Root != null) {
        findNodeRecursive(Root, key, result);
        return result;
      }

      return null;
    }
	
    public boolean AddKeyValue(int key, T val)
    {
      if (Root == null) {
        Root = new BSTNode<>(key, val, null);
        return true;
      }

      BSTFind<T> findResult = FindNodeByKey(key);

      if (!findResult.NodeHasKey) {
        BSTNode<T> parent = findResult.Node;
        BSTNode<T> newNode = new BSTNode<>(key, val, parent);

        if (findResult.ToLeft) {
            parent.LeftChild = newNode;
        } else {
            parent.RightChild = newNode;
        }
        return true;
      }
      return false; 
    }
	
    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
    {
      if (FromNode != null) {
        return findMinMaxRecursive(FromNode, FindMax);
      }
      return null;
    }
	
    public boolean DeleteNodeByKey(int key)
    {
      if (Root == null) {
        return false;
      }

      BSTFind<T> findResult = FindNodeByKey(key);

      if (findResult.NodeHasKey) {
        deleteNode(findResult ,key);
        return true;
      }
      return false;
    }

    public int Count()
    {
      if(Root != null){
        return countNode(Root);
      }
      return 0;
    }

    private void findNodeRecursive(BSTNode<T> node, int key, BSTFind<T> result) {
      if (node == null) {
        result.NodeHasKey = false;
        return;
      }

      if (key == node.NodeKey) {
        result.Node = node;
        result.NodeHasKey = true;
        return;
      }
      
      result.Node = node;

      if (key < node.NodeKey) {
        result.ToLeft = true;
        findNodeRecursive(node.LeftChild, key, result);
      } else {
        result.ToLeft = false;
        findNodeRecursive(node.RightChild, key, result);
      }
    }

    private BSTNode<T> findMinMaxRecursive(BSTNode<T> FromNode, boolean FindMax){
      if(FindMax) {
        return MaxRecursive(FromNode);
      }
      else {
        return MinRecursive(FromNode);
      }
    }

    private BSTNode<T> MinRecursive(BSTNode<T> FromNode) {
      
      if (FromNode.LeftChild == null) {
            return FromNode;
      }
      return MinRecursive(FromNode.LeftChild);  
    }

    private BSTNode<T> MaxRecursive(BSTNode<T> FromNode) {
      if (FromNode.RightChild == null) {
            return FromNode;
      }
      return MaxRecursive(FromNode.RightChild);
    }

    public void deleteNode(BSTFind<T> findNodeResult, int key) {
      BSTNode<T> nodeToDelete = findNodeResult.Node;

      if (nodeToDelete.LeftChild != null && nodeToDelete.RightChild != null) {
          BSTNode<T> success = FinMinMax(nodeToDelete.RightChild, false);

          nodeToDelete.NodeKey = success.NodeKey;
          nodeToDelete.NodeValue = success.NodeValue;
          nodeToDelete = success;
      }

      BSTNode<T> child;
      if (nodeToDelete.LeftChild != null) {
          child = nodeToDelete.LeftChild;
      } else {
          child = nodeToDelete.RightChild;
      }

      if (child == null) {
          removeNoChild(nodeToDelete);
      } else {
          removeWithChild(nodeToDelete, child);
      }
      
    }

    private void removeNoChild(BSTNode<T> node) {
    if (node == Root) {
        Root = null;
        return;
    }

    if (node.Parent.LeftChild == node) {
        node.Parent.LeftChild = null;
    } else {
        node.Parent.RightChild = null;
    }
  }

  private void removeWithChild(BSTNode<T> node, BSTNode<T> child) {
      if (node == Root) {
          Root = child;
          child.Parent = null;
          return;
      }

      if (node.Parent.LeftChild == node) {
          node.Parent.LeftChild = child;
      } else {
          node.Parent.RightChild = child;
      }
      child.Parent = node.Parent;
  }

  private int countNode(BSTNode<T> node) {
    if (node == null) {
        return 0;
    }
    return 1 + countNode(node.LeftChild) + countNode(node.RightChild);
  }

  
    public List<Integer> WideAllNodes() {
        List<Integer> result = new ArrayList<>();

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

    public ArrayList<BSTNode> DeepAllNodes() {
        int order; 
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
