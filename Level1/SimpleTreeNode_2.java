import java.util.*;

public class SimpleTreeNode<T>
{
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null
    public int Level;
	
    public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
    {
      NodeValue = val;
      Parent = parent;
      Children = null;
      Level = 0;
    }
}
	
class SimpleTree<T>
{
    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root)
    {
        Root = root;
    }

  // Exercise 1
    public void SetLevels() {
        if (Root != null) {
            setLevelsRecursive(Root, 0);
        }
        return;
    }

  
    private void setLevelsRecursive(SimpleTreeNode<T> node, int level) {
        if (node == null) return;

        node.Level = level; // записываем уровень текущего узла

        if (node.Children != null) {
            for (SimpleTreeNode<T> child : node.Children) {
                setLevelsRecursive(child, level + 1); // детям уровень +1
            }
        }
    }

  // Exercise 2
  /*
  Считать уровни поддерева,  когда  измененяем родителя. 
  Например, при добавлении детей ведём счёт уровня сразу. А если меняем родителя то знаем уровень родителя и дальше спускаемся по его ветке и устанавливаем уровни рекурсивно. Используем только ветку, а не всё дерево.
  */
}
