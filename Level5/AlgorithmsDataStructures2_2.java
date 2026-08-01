import java.util.*;

// №3
public class AlgorithmsDataStructures2 {
    public static int[] deleteFromArrayBST(int[] tree, int key) {
        if (tree == null || tree.length == 0) {
            return null;
        }

        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(tree, 0, inorder);

        boolean removed = false;
        for (int i = 0; i < inorder.size(); i++) {
            if (inorder.get(i) == key) {
                inorder.remove(i);
                removed = true;
                break;
            }
        }
        if (!removed) {
            return Arrays.copyOf(tree, tree.length);
        }

        return buildBalancedArrayBST(inorder);
    }

    private static void inorderTraversal(int[] tree, int i, List<Integer> result) {
        if (i >= tree.length || tree[i] == 0 ) {
            return;
        }

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < tree.length) {
            inorderTraversal(tree, left, result);
        }
        result.add(tree[i]);
        if (right < tree.length) {
            inorderTraversal(tree, right, result);
        }
    }

   
    private static int[] buildBalancedArrayBST(List<Integer> sorted) {
        int n = sorted.size();
        if (n == 0) return new int[0];

        int[] result = new int[n];
        int idx = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, n - 1});

        while (!queue.isEmpty()) {
            int[] range = queue.poll();
            int l = range[0];
            int r = range[1];

            if (l > r) continue;

            int mid = l + (r - l) / 2;
            result[idx++] = sorted.get(mid);

            if (l <= mid - 1) {
                queue.offer(new int[]{l, mid - 1});
            }

            if (mid + 1 <= r) {
                queue.offer(new int[]{mid + 1, r});
            }
        }
        return result;
    }
}

// №4
/*
Невозможно отсортировать за константное время. Да и сбаллансированное дерево уже отсортировано. 
Получается требование нелогично, но отреагировать необходимо.
Загуглив что такое CRUD, предполагаю, для реализации нет нужды использовать сложные алгоритмы, но на интервью спрашивают...
*/

/*
Рефлексия.

№3
Решение понятно и аналогично.

№4
Решение понятно и аналогичнло.

№5
Решение понятно. 
*/
