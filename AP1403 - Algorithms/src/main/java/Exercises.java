import java.util.Arrays;


public class Exercises {

    /*
        there is an array of positive integers as input of function and another integer for the target value
        all the algorithm should do is to find those two integers in array which their multiplication is the target
        then it should return an array of their indices
        e.g. {1, 2, 3, 4} with target of 8 -> {1, 3}

        note: you should return the indices in ascending order and every array's solution is unique
    */
    public int[] productIndices(int[] values, int target) {
        int n = values.length;
        int[][] indexedNums = new int[n][2];

        for (int i = 0; i < n; i++) {
            indexedNums[i][0] = values[i];
            indexedNums[i][1] = i;
        }

        Arrays.sort(indexedNums, (a, b) -> Integer.compare(a[0], b[0]));

        int left = 0, right = n - 1;
        while (left < right) {
            int product = indexedNums[left][0] * indexedNums[right][0];

            if (product == target) {
                int idx1 = indexedNums[left][1], idx2 = indexedNums[right][1];
                return new int[]{Math.min(idx1, idx2), Math.max(idx1, idx2)};
            } else if (product < target) {
                left++;
            } else {
                right--;
            }
        }

        return null;
    }

    /*
        given a matrix of random integers, you should do spiral traversal in it
        e.g. if the matrix is as shown below:
        1 2 3
        4 5 6
        7 8 9
        then the spiral traversal of that is:
        {1, 2, 3, 6, 9, 8, 7, 4, 5}

        so you should walk in that matrix in a curl and then add the numbers in order you've seen them in a 1D array
    */
    public int[] spiralTraversal(int[][] values, int rows, int cols) {
        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;
        int[] res = new int[rows * cols];
        int index = 0;

        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                res[index++] = values[top][i];
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                res[index++] = values[i][right];
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    res[index++] = values[bottom][i];
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    res[index++] = values[i][left];
                }
                left++;
            }
        }

        return res;
    }

    /*
        integer partitioning is a combinatorics problem in discreet maths
        the problem is to generate sum numbers which their summation is the input number

        e.g. 1 -> all partitions of integer 3 are:
        3
        2, 1
        1, 1, 1

        e.g. 2 -> for number 4 goes as:
        4
        3, 1
        2, 2
        2, 1, 1
        1, 1, 1, 1

        note: as you can see in examples, we want to generate distinct summations, which means 1, 2 and 2, 1 are no different
        you should generate all partitions of the input number and

        hint: you can measure the size and order of arrays by finding the pattern of partitions and their number
        trust me, that one's fun and easy :)

        if you're familiar with lists and arraylists, you can also edit method's body to use them instead of array
    */
    public int[][] intPartitions(int n) {
        int maxPartitions = countPartitions(n, n);
        int[][] result = new int[maxPartitions][];

        int[] partition = new int[n];
        int index = 0;

        storePartitions(n, n, partition, 0, result, new int[]{0});
        return result;
    }

    static void storePartitions(int n, int max, int[] partition, int partitionSize, int[][] result, int[] index) {
        if (n == 0) {
            result[index[0]] = new int[partitionSize];
            System.arraycopy(partition, 0, result[index[0]], 0, partitionSize);
            index[0]++;
            return;
        }

        for (int i = Math.min(max, n); i >= 1; i--) {
            partition[partitionSize] = i;
            storePartitions(n - i, i, partition, partitionSize + 1, result, index);
        }
    }

    static int countPartitions(int n, int max) {
        if (n == 0) return 1;

        int count = 0;
        for (int i = Math.min(max, n); i >= 1; i--) {
            count += countPartitions(n - i, i);
        }
        return count;
    }

    public static void main(String[] args) {
        // you can test your code here
    }
}
