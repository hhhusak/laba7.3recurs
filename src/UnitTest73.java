import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest73 {

    @Test
    void createRows() {
        int min = -10; int max = 10; int k = 5; int n = 4;
        int[][] a = new int[k][n];
        Main.CreateRows(a, k, n, min, max, 0);
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                assertTrue(min <= a[i][j] && a[i][j] <= max);
            }
        }
    }
}