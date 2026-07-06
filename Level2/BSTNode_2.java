import java.util.ArrayList;
import java.util.List;

public class BSTNode_2 {

// Exercise 1
    public boolean equalTrees(BST<T> other) {
        if (this == other) return true;
        if (this == null || other == null) return false;      
        
        return treesEqual(this.Root, other.Root);
    }

    private boolean treesEqual(BSTNode<T> node1, BSTNode<T> node2) {

        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1.NodeKey != node2.NodeKey) {
            return false;
        }

        if (!node1.NodeValue.equals(node2.NodeValue)) {
            return false;
        }

        boolean leftEqual = treesEqual(node1.LeftChild, node2.LeftChild);
        if (!leftEqual) return false; 

        return treesEqual(node1.RightChild, node2.RightChild);
    }


// Exercise 2
    public List<List<Integer>> FindPathsByLength(int tLength) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (Root == null) {
            return result;
        }

        List<Integer> current = new ArrayList<>();
        findRecursive(Root, tLength, current, result);
        
        return result;
    }

    private void findRecursive(BSTNode<T> node, int targetLength, List<Integer> current, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        current.add(node.NodeKey);

        boolean isLeaf; 
        if(node.LeftChild == null && node.RightChild == null) {
            isLeaf = true;
        };

        if (isLeaf) {
            if (current.size() == targetLength) {
                result.add(new ArrayList<>(current));
            }
            current.remove(current.size() - 1);
            return;
        }

        if (node.LeftChild != null) {
            findRecursive(node.LeftChild, targetLength, current, result);
        }
        if (node.RightChild != null) {
            findRecursive(node.RightChild, targetLength, current, result);
        }

        current.remove(current.size() - 1);
    }


// Exercise 3
    public List<Integer> FindForSums() {
        List<List<Integer>> arrayPath = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        
        if (Root == null) {
            return null;
        }

        List<Integer> current = new ArrayList<>();
        List<Integer> indexMaxSum = new ArrayList<>();
        findRecursiveForSums(Root, current, arrayPath, indexMaxSum);
        
        
        int indexListWithMaxSum = 0; 
        for(int i = 0; i < indexMaxSum.size() - 1; i++) {
            if(indexMaxSum.get(i) > indexListWithMaxSum) {
                indexListWithMaxSum = indexMaxSum.get(i);
            } 
        }


        return arrayPath.get(indexListWithMaxSum);
    }

    private void findRecursiveForSums(BSTNode<T> node, List<Integer> current, List<List<Integer>> arrayPath, List<Integer> indexMaxSum) {
        if (node == null) {
            return;
        }

        current.add(node.NodeKey);

        boolean isLeaf; 
        if(node.LeftChild == null && node.RightChild == null) {
            isLeaf = true;
        };

        if (isLeaf) {
            arrayPath.add(new ArrayList<>(current));
            int sum = 0;
            for(int i = 0; i < current.size(); i++) {
                sum += current.get(i); 
            }
            indexMaxSum.add(sum);

            current.remove(current.size() - 1);
            return;
        }

        if (node.LeftChild != null) {
            findRecursiveForSums(node.LeftChild, current, arrayPath, indexMaxSum);
        }
        if (node.RightChild != null) {
            findRecursiveForSums(node.RightChild, current, arrayPath, indexMaxSum);
        }

        current.remove(current.size() - 1);
    }

// Exercise 4
    public boolean Symmetric() {
        if (Root == null) {
            return true;
        }
        return isSymmetric(Root.LeftChild, Root.RightChild);
    }

    private boolean isSymmetric(BSTNode<T> left, BSTNode<T> right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left.NodeKey != right.NodeKey) {
            return false;
        }
        
        return isSymmetric(left.LeftChild, right.RightChild) && isSymmetric(left.RightChild, right.LeftChild);
    }
}
