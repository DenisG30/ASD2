import java.util.*;

class BSTNode
{
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок	
    public int Level; // глубина узла
	
    public BSTNode(int key, BSTNode parent)
     {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
      }
}	

class BalancedBST
{
	public BSTNode Root; // корень дерева
		
	public BalancedBST() 
	{ 
		Root = null;
	}
		
	public void GenerateTree(int[] a) 
	{  
		if (a == null || a.length == 0) {
            Root = null;
            return;
        }

		Arrays.sort(a);
        int n = a.length;

		Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, n - 1});
		Root = null;

		while (!queue.isEmpty()) {
            int[] range = queue.poll();
            int l = range[0];
            int r = range[1];

            if (l > r) continue;

            int mid = l + (r - l) / 2;
            int key = a[mid];

            Root = insertIntoBalancedTree(Root, null, key, l, r, a, 0);
        }
	}

	public boolean IsBalanced(BSTNode root_node) 
	{  
		if(checkLevel(root_node) != -1) {
			return true;
		}
		return false;
	}

	private BSTNode insertIntoBalancedTree(BSTNode current, BSTNode parent, int key, int l, int r, int[] a, int level) {
        int mid = l + (r - l) / 2;
        int nodeKey = a[mid];

        BSTNode node = new BSTNode(nodeKey, parent);
		node.Level = level++;

        if (l <= mid - 1) {
            node.LeftChild = insertIntoBalancedTree(node.LeftChild, node, a[l + (mid - 1 - l) / 2], l, mid - 1, a, level);
            if (node.LeftChild != null) {
                node.LeftChild.Parent = node;
            }
        }

        if (mid + 1 <= r) {
            node.RightChild = insertIntoBalancedTree(node.RightChild, node, a[mid + 1 + (r - (mid + 1)) / 2], mid + 1, r, a, level);
            if (node.RightChild != null) {
                node.RightChild.Parent = node;
            }
        }

        return node;
    }

	 private int checkLevel(BSTNode node) {
        if (node == null) return 0;

        int leftLevel = checkLevel(node.LeftChild);
        if (leftLevel == -1) return -1; 

        int rightLevel = checkLevel(node.RightChild);
        if (rightLevel == -1) return -1; 

        if (Math.abs(leftLevel - rightLevel) > 1) {
            return -1; 
        }

        return Math.max(leftLevel, rightLevel) + 1;
    }
}  
