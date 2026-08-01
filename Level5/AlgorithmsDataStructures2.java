import java.util.*;

public class AlgorithmsDataStructures2 {
    public static int[] GenerateBBSTArray(int[] a) {
        if (a != null || a.length != 0) {
            return BBSTArray(a);
        }
        return null;
    }

    private static  int[] BBSTArray(int[] a) {
        Arrays.sort(a);
        int n = a.length;
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
            result[idx++] = a[mid];

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


