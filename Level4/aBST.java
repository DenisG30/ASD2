import java.util.*;

 class aBST
{
    public Integer Tree []; 
	
    public aBST(int depth)
    {
      if (depth <= 0) {
          Tree = new Integer[0];
          return;
      }

      int tree_size = (1 << depth) - 1;
      Tree = new Integer[ tree_size ];
      for(int i=0; i<tree_size; i++) Tree[i] = null;
    }
	
    public Integer FindKeyIndex(int key)
    {
      if (Tree.length  != 0) {
        return findRecursive(0, key);
      }
      return null; 
    }
	
    public int AddKey(int key)
    {
      Integer result = FindKeyIndex(key);

      if (result == null) {
            return -1;
        }

        if (result >= 0) {
          Tree[result] = key;
            return result;
        }

        int insertIndex = -result;
        
        if (insertIndex < Tree.length && Tree[insertIndex] == null) {
            Tree[insertIndex] = key;
            return insertIndex;
        }

        return -1;
    }

    private Integer findRecursive(int currentIndex, int key) {
      if (currentIndex >= Tree.length) {
          return null;
      }
      
      Integer currentValue = Tree[currentIndex];

      if (currentValue == null) {
            return -currentIndex;
      }

      if (key == currentValue) {
            return currentIndex;
      }

      if (key < currentValue) {
        int leftChildIndex = 2 * currentIndex + 1;
        if (leftChildIndex >= Tree.length) {
          return null; 
        }
        return findRecursive(leftChildIndex, key);
      } else {
        int rightChildIndex = 2 * currentIndex + 2;
        if (rightChildIndex >= Tree.length) {
          return null;
        }
        return findRecursive(rightChildIndex, key);
      }
    }
}

 
