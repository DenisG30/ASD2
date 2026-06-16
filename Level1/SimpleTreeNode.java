import java.util.*;

public class SimpleTreeNode<T>
{
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null
	
    public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
    {
      NodeValue = val;
      Parent = parent;
      Children = null;
    }
}
	
class SimpleTree<T>
{
    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root)
    {
        Root = root;
    }
	
    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
    {
        if (ParentNode == null || NewChild == null) {
            throw new IllegalArgumentException("ParentNode and NewChild must be not null");
        }

        if (NewChild.Parent != null) {
            throw new IllegalStateException("Use MoveNode");
        }

        if (ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
        }

        ParentNode.Children.add(NewChild);

        NewChild.Parent = ParentNode;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
    {
        if (NodeToDelete == null) {
            return; 
        }

        if (NodeToDelete == Root) {
            Root = null;
            return;
        }

        SimpleTreeNode<T> parent = NodeToDelete.Parent;

        if (parent == null) {
            return;
        }

        if (parent.Children != null) {
            parent.Children.remove(NodeToDelete);
        }

        NodeToDelete.Parent = null;
    }

   public List<SimpleTreeNode<T>> GetAllNodes()
    {
        List<SimpleTreeNode<T>> result = new ArrayList<>();

        if (Root != null) {
            walkOfTreeForGetAllNodes(Root, result);
            return result;
        }

        return null;
    }
	
   public List<SimpleTreeNode<T>> FindNodesByValue(T val)
   {
        List<SimpleTreeNode<T>> result = new ArrayList<>();

        if (Root != null) {
            walkOfTreeForFindNodesByValue(Root, val, result);
            return result;
        }

        return null;
   }
   
    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
    {   
        

        if (OriginalNode == null || NewParent == null) {
            throw new IllegalArgumentException("OriginalNode and NewParent must be not null");
        }
        if (OriginalNode == NewParent) {
            throw new IllegalArgumentException("The nodes must be different");
        }

        if (OriginalNode == Root) {
            throw new IllegalStateException("Use a non root node");
        }

        if(NewParent.Parent == OriginalNode) {
            NewParent.Parent = null;
            OriginalNode.Children.remove(NewParent);
            AddChild(OriginalNode.Parent,  NewParent);
        }
/* 
        Set<SimpleTreeNode<T>> childsOfOriginalNode = new HashSet<>();
		if (comparisonOfNodes(OriginalNode, NewParent, childsOfOriginalNode)) {
        	throw new IllegalStateException("It is not possible to set an ancestor as a descendant of a descendant)");
    	}

        SimpleTreeNode<T> oldParent = OriginalNode.Parent;

        if (oldParent != null && oldParent.Children != null) {
            oldParent.Children.remove(OriginalNode);
        }
        OriginalNode.Parent = null; 

        AddChild(NewParent, OriginalNode);
*/
        DeleteNode(OriginalNode);
        AddChild(NewParent,  OriginalNode);
    }
   
    public int Count()
    {
        if (Root != null) {
            return countNode(Root);
        }
   
	    return 0;
    }

    public int LeafCount()
    {
        if (Root != null) {
            return countLeaf(Root);
        }
    
	    return 0;
    }

    private void walkOfTreeForGetAllNodes(SimpleTreeNode<T> node, List<SimpleTreeNode<T>> list) {
        if (node == null) {
            return;
        }

        list.add(node);

        if (node.Children != null) {
            for (SimpleTreeNode<T> child : node.Children) {
                walkOfTreeForGetAllNodes(child, list);
            }
        }
    }


    private void walkOfTreeForFindNodesByValue(SimpleTreeNode<T> node, T val, List<SimpleTreeNode<T>> list) {
        if (node == null) {
            return;
        }

        if (Objects.equals(node.NodeValue, val)) {
            list.add(node);
        }

        if (node.Children != null) {
            for (SimpleTreeNode<T> child : node.Children) {
                walkOfTreeForFindNodesByValue(child, val, list);
            }
        }
    }


    private int countNode(SimpleTreeNode<T> node) {
        if (node == null) {
            return 0;
        }

        int count = 1;

        if (node.Children != null) {
            for (SimpleTreeNode<T> child : node.Children) {
                count += countNode(child);
            }
        }

        return count;
    }


    private int countLeaf(SimpleTreeNode<T> node) {
        if (node == null) {
            return 0;
        }

        if (node.Children == null || node.Children.isEmpty()) {
            return 1;
        }

        int count = 0;
        for (SimpleTreeNode<T> child : node.Children) {
            count += countLeaf(child);
        }
        return count;
    }
}
