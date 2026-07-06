import java.io.*;
import java.util.*;


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
    BSTNode<T> Root; // корень дерева, или null
	
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
      // добавляем ключ-значение в дерево
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

      return false; // если ключ уже есть
    }
	
    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
    {
      // ищем максимальный/минимальный ключ в поддереве
      if (FromNode != null) {
        return findMinMaxRecursive(FromNode, FindMax);
      }
      return null;
    }
	
    public boolean DeleteNodeByKey(int key)
    {
      // удаляем узел по ключу
      if (Root == null) {
        return false;
      }

      BSTFind<T> findResult = FindNodeByKey(key);

      if (findResult.NodeHasKey) {
        deleteNode(findResult ,key);
        return true;
      }

      return false; // если узел не найден
    }

    public int Count()
    {
      if(Root != null){
        return countNode(Root);
      }
      return 0; // количество узлов в дереве
    }

    private void findNodeRecursive(BSTNode<T> node, int key, BSTFind<T> result) {
      if (node == null) {
        // Поиск дошёл до null — значит, узел не найден.
        // result.Node уже содержит родителя (последний не-null узел, который передали)
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
          // Находим минимальный узел в правом поддереве — он станет заменой
          BSTNode<T> success = FinMinMax(nodeToDelete.RightChild, false);

          // Копируем данные из преемника в удаляемый узел
          nodeToDelete.NodeKey = success.NodeKey;
          nodeToDelete.NodeValue = success.NodeValue;

          // Теперь удаляем сам узел-преемник (у него гарантированно нет левого ребёнка)
          nodeToDelete = success;
      }

    // Определяем единственного ребёнка (или null, если детей нет) — через if‑else вместо тернарного
      BSTNode<T> child;
      if (nodeToDelete.LeftChild != null) {
          child = nodeToDelete.LeftChild;
      } else {
          child = nodeToDelete.RightChild;
      }

    // Случай 2 и 3: 0 или 1 ребёнок
      if (child == null) {
          // Узел — лист: просто убираем ссылку у родителя
          removeNoChild(nodeToDelete);
      } else {
          // У узла один ребёнок: подцепляем ребёнка к родителю вместо удаляемого узла
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
    // Считаем текущий узел + узлы в левом поддереве + узлы в правом поддереве
    return 1 + countNode(node.LeftChild) + countNode(node.RightChild);
}
	
}
