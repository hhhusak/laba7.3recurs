import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);

    static void CreateRow(int[][] a, int i, int n, int min, int max, int j){
        a[i][j] = (int) (Math.random() * (max - min) + min);
        if(j < n - 1){
            CreateRow(a, i, n, min, max, j + 1);
        }
    }
    static void CreateRows(int[][] a, int k, int n, int min, int max, int i){
        CreateRow(a, i, n, min, max, 0);
        if(i < k - 1){
            CreateRows(a, k, n, min, max, i + 1);
        }
    }
    static void PrintRow(int[][] a, int i, int n, int j){
        System.out.printf("%d\t|  ", a[i][j]);
        if(j < n - 1){
            PrintRow(a, i, n, j + 1);
        } else {
            System.out.println();
        }
    }
    static void PrintRows(int[][] a, int k, int n, int i){
        PrintRow(a, i, n, 0);
        if(i < k - 1){
            PrintRows(a, k, n, i + 1);
        } else {
            System.out.println();
        }
    }

    static int Part1_sum(int[][] a, int k, int j, int i, int sum){
        if(j < k){
            sum += a[j][i];
            return Part1_sum(a, k, j + 1, i, sum);
        } else {
            return sum;
        }
    }

    static int Part1_lessT0Col(int[][] a, int k, int i, int j, int lessThan0){
        if(a[j][i] < 0){
            lessThan0++;
            return 0;
        } else if (i < k - 1){
            return Part1_lessT0Col(a, k, i + 1, j, lessThan0);
        }
        return lessThan0;
    }

    static int Part1_lessT0Cols(int[][] a, int k, int n, int i, int j, int sum){
        int lessThan0 = Part1_lessT0Col(a, k, 0, j, 0);
        if(lessThan0 == 0){
            sum = Part1_sum(a, k, 0, i, 0);
        }
        if(j < n - 1){
            return Part1_lessT0Cols(a, k, n, i, j + 1, sum);
        }
        return sum;
    }

    static int Part2_nextDiagonalSumCol(int[][] a, int i, int n, int count, int nextDiagonal, int j){
        if(j < n){
            if(i + j == count){
                nextDiagonal += Math.abs(a[i][j]);
            }
            return Part2_nextDiagonalSumCol(a, i, n, count, nextDiagonal, j + 1);
        } else {
            return nextDiagonal;
        }
    }

    static int Part2_compareToNext(int[][] a, int k, int n, int count, int nextDiagonal, int independent, int minimal, int i){
        nextDiagonal = Part2_nextDiagonalSumCol(a, i, n, count, nextDiagonal, 0);
        if(i < k - 1){
            if(count < independent){
                if(nextDiagonal < minimal){
                    minimal = nextDiagonal;
                }
                return Part2_compareToNext(a, n, k, count + 1, Part2_nextDiagonalSumCol(a, i + 1, n, count, nextDiagonal, 0), independent, minimal, i + 1);
            }
        }
        return minimal;
    }

    public static void main(String[] args) {
        System.out.print("K = "); int k = scan.nextInt();
        System.out.print("N = "); int n = scan.nextInt();

        int min = -10; int max = 10;
        int[][] a = new int[k][n];
        CreateRows(a, k, n, min, max, 0);
        PrintRows(a, k, n, 0);
        int sum = 0;
        sum = Part1_lessT0Cols(a, k, n, 0, 0, sum);
        if (sum == 0){
            System.out.println("There are no such columns");
        } else {
            System.out.println("sum = " + sum);
        }
        int minimal = Math.abs(a[0][0]) + Math.abs(a[k - 1][n - 1]);
        int count = 1;
        int independent = k > n ? k + 1 : n + 1;
        int nextDiagonal = 0;
        int iAmTooTiredDoingThisFML = Part2_compareToNext(a, k, n, count, nextDiagonal, independent, minimal, 0);
        System.out.println("minimal = " + iAmTooTiredDoingThisFML);
    }
}