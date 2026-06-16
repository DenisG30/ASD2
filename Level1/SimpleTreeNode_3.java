import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
    
public class SimpleTreeNodeTest {
        
    @Test
    public void testAddChildren() {
        SimpleTree<String> tree = new SimpleTree<>(new SimpleTreeNode<>("Root", null));
        SimpleTreeNode<String> child = new SimpleTreeNode<>("Child", null);

        tree.AddChild(tree.Root, child);

        List<SimpleTreeNode<String>> found = tree.FindNodesByValue("Child");
        assertEquals(1, found.size() );

        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("Child2", null);
        
        tree.AddChild(tree.Root, child2);
        found = tree.FindNodesByValue("Child2");

        assertEquals(1, found.size() );
    }


    @Test
    public void testDeleteNode() {
        SimpleTree<String> tree = new SimpleTree<>(new SimpleTreeNode<>("Root", null));
        SimpleTreeNode<String> child = new SimpleTreeNode<>("Child", null);
        
        tree.AddChild(tree.Root, child);
        tree.DeleteNode(child);

        List<SimpleTreeNode<String>> found = tree.FindNodesByValue("Child");

        assertEquals(0, found.size() );

    }

    @Test
    public void testDeleteNodeGrandson() {
        SimpleTree<String> tree = new SimpleTree<>(new SimpleTreeNode<>("Root", null));
        SimpleTreeNode<String> child = new SimpleTreeNode<>("Child", null);
        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("Child2", null);
        SimpleTreeNode<String> child3 = new SimpleTreeNode<>("Child3", null);
        SimpleTreeNode<String> childNull = new SimpleTreeNode<>(null, null);
    
        tree.AddChild(tree.Root, child);
        tree.AddChild(tree.Root, child2);
        tree.AddChild(child2, child3);
        tree.AddChild(child, childNull);

        assertEquals(5, tree.Count() );

        tree.DeleteNode(child2);
        List<SimpleTreeNode<String>> found = tree.FindNodesByValue("Child3");
        assertEquals(0, found.size() );
    }

    @Test
    public void testFindNodesByValue() {
        SimpleTree<String> tree = new SimpleTree<>(new SimpleTreeNode<>("Root", null));
        
        SimpleTreeNode<String> child1 = new SimpleTreeNode<>("3", null);
        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("4", null);
        SimpleTreeNode<String> child3 = new SimpleTreeNode<>("5", null);
        SimpleTreeNode<String> child4 = new SimpleTreeNode<>("3", null);
        SimpleTreeNode<String> child5 = new SimpleTreeNode<>("4", null);
        SimpleTreeNode<String> child6 = new SimpleTreeNode<>("4", null);

        tree.AddChild(tree.Root, child1);
        tree.AddChild(tree.Root, child2);
        tree.AddChild(child2, child3);
        tree.AddChild(child2, child4);
        tree.AddChild(child3, child5);
        tree.AddChild(child4, child6);

        List<SimpleTreeNode<String>> answer = new ArrayList<>();
        answer.add(child1);
        answer.add(child4);

        List<SimpleTreeNode<String>> result = tree.FindNodesByValue("3");

        assertEquals(answer.size(), result.size());
        
        for (SimpleTreeNode<String> node : answer) {
            assertTrue(result.contains(node));
        }
    }

    @Test
    public void testFindNodesByValueZero() {
        SimpleTree<String> tree0 = new SimpleTree<>(new SimpleTreeNode<>("Root", null));
        SimpleTreeNode<String> root0 = tree0.Root;
        
        tree0.DeleteNode(root0);
        assertNull(tree0.FindNodesByValue("3"));
    }

    @Test
    public void testMoveNode() {
        SimpleTree<String> tree = new SimpleTree<>(new SimpleTreeNode<>("Root", null));
        
        SimpleTreeNode<String> parent1 = new SimpleTreeNode<>("Parent1", null);
        SimpleTreeNode<String> parent2 = new SimpleTreeNode<>("Parent2", null);

        SimpleTreeNode<String> child1 = new SimpleTreeNode<>("Child1", null);
        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("Child2", null);

        tree.AddChild(tree.Root, parent1);
        tree.AddChild(tree.Root, parent2);      
        tree.AddChild(parent1, child1);   
        tree.AddChild(child1, child2); 
        
        List<SimpleTreeNode<String>> childsOfParent1 = new ArrayList<>(parent1.Children);
        List<SimpleTreeNode<String>> childsOfParent2;
        
        if (parent2.Children != null) {
            childsOfParent2 = new ArrayList<>(parent2.Children);
        } else {
            childsOfParent2 = new ArrayList<>();
        }

        assertTrue(childsOfParent1.contains(child1));
        assertFalse(childsOfParent2.contains(child1));

        tree.MoveNode(child1, parent2);

        assertNotNull(parent1.Children);
        assertFalse(parent1.Children.contains(child1));

        assertNotNull(parent2.Children);
        assertTrue(parent2.Children.contains(child1));
        assertSame(parent2, child1.Parent);

        assertSame(child1, child2.Parent);

        assertEquals(5, tree.Count());
    }

    @Test
    public void testMoveNodeParentIsNewChildOfChild() {
        SimpleTree<String> tree = new SimpleTree<>(new SimpleTreeNode<>("Root", null));
        
        SimpleTreeNode<String> parent1 = new SimpleTreeNode<>("Parent1", null);
        SimpleTreeNode<String> parent2 = new SimpleTreeNode<>("Parent2", null);
        SimpleTreeNode<String> child1 = new SimpleTreeNode<>("Child1", null);
        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("Child2", null);

        tree.AddChild(tree.Root, parent1);
        tree.AddChild(tree.Root, parent2);
        tree.AddChild(parent1, child1);   
        tree.AddChild(child1, child2);  

        assertEquals(1, child1.Children.size());
        assertTrue(tree.Root.Children.contains(parent1));
        
        tree.MoveNode(parent1, child1);

        assertEquals(2, child1.Children.size());

        List<SimpleTreeNode<String>> childsOfParent1 = new ArrayList<>(parent1.Children);
        List<SimpleTreeNode<String>> childsOfRoot = new ArrayList<>(tree.Root.Children);

        assertTrue(childsOfParent1.isEmpty());

        assertTrue(childsOfRoot.contains(child1));

        assertFalse(childsOfRoot.contains(parent1));

        assertFalse(parent1.Children.contains(child1));

        assertEquals(0, parent1.Children.size());

        assertEquals(tree.Root, child1.Parent);

        assertEquals(5, tree.Count());
    }


    @Test
    public void testCount() {
        SimpleTree<String> treeEmpty = new SimpleTree<>(null);

        assertEquals(0, treeEmpty.Count());
        assertEquals(0, treeEmpty.LeafCount());

        SimpleTree<String> tree = new SimpleTree<>(new SimpleTreeNode<>("Root", null));

        assertEquals(1, tree.Count());
        assertEquals(1, tree.LeafCount());
        
        SimpleTreeNode<String> child1 = new SimpleTreeNode<>("Child1", null);
        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("Child2", null);
        SimpleTreeNode<String> child3 = new SimpleTreeNode<>("Child3", null);

        tree.AddChild(tree.Root, child1);
        tree.AddChild(child1, child2);      
        tree.AddChild(child2, child3);
        
        assertEquals(4, tree.Count());
        assertEquals(1, tree.LeafCount());
    }
}
