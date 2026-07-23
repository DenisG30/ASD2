import java.util.ArrayList;

public class aBST2 {

// Exercise 2
    public Integer findLCA(int key1, int key2) {
        Integer idx1 = FindKeyIndex(key1);
        Integer idx2 = FindKeyIndex(key2);

        if (idx1 == null || idx2 == null || idx1 < 0 || idx2 < 0) {
            return null;
        }

        while (idx1 != idx2) {
            if (idx1 > idx2) {
                idx1 = getParentIndex(idx1);
            } else {
                idx2 = getParentIndex(idx2);
            }
        }

        return Tree[idx1];
    }

    private int getParentIndex(int childIndex) {
        if (childIndex == 0) return 0; 
        return (childIndex - 1) / 2;
    }

// Exercise 3
    public ArrayList<Integer> WideAllNodes() {
        ArrayList<Integer> result = new ArrayList<>(Tree.length);

        for (Integer value : Tree) {
            if (value != null) {
                result.add(value);
            }
        }
        return result;
    }
}

/* Рефлексия
Задание №2 и №3
Решение понятно. Не подумал объединить методы поиска. Для каждого задания писал отдельную рекурсию.

Задание №4
Решение понятно. 
*/
