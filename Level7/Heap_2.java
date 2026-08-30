// 4
public int getMaxInRange(int minVal, int maxVal) {
    if (HeapArray == null || size == 0 || minVal > maxVal) {
        return -1;
    }

    int maxFound = -1;
    boolean found = false;

    for (int i = 0; i < size; i++) {
        int val = HeapArray[i];
        if (val >= minVal && val <= maxVal) {
            if (!found || val > maxFound) {
                maxFound = val;
                found = true;
            }
        }
    }

    return found ? maxFound : -1;
}

// 5
// Нет решения.

//6
public void mergeWith(Heap other) {
    if (other == null) {
        return;
    }

    while (other.getSize() > 0) {
        int val = other.GetMax(); 
        if (val != -1) {
            this.Add(val);
        }
    }
}
