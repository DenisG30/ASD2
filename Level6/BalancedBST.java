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
		Root = buildBalancedBST(a, 0, a.length - 1, null);
		assignLevels(Root, 0);
	}

	private BSTNode buildBalancedBST(int[] a, int l, int r, BSTNode parent) {
		if (l > r) return null;
		int mid = l + (r - l) / 2;
		BSTNode node = new BSTNode(a[mid], parent);
		node.LeftChild = buildBalancedBST(a, l, mid - 1, node);
		node.RightChild = buildBalancedBST(a, mid + 1, r, node);
		return node;
	}

	private void assignLevels(BSTNode node, int level) {
		if (node == null) return;
		node.Level = level;
		assignLevels(node.LeftChild, level + 1);
		assignLevels(node.RightChild, level + 1);
	}

	public boolean IsBalanced(BSTNode root_node) 
	{  
		if(checkLevel(root_node) != -1) {
			return true;
		}
		return false;
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
