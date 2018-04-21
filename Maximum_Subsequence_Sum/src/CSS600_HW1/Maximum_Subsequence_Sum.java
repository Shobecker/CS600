/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSS600_HW1;

import java.util.Random;
import java.time.Clock;
import java.lang.Math;

/**
 *
 * @author Elias
 */
public class Maximum_Subsequence_Sum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //table heading
        System.out.printf("%8s%17s%15s\n", "Size,", "Time in ms,", "Growth Rate");
        System.out.println("------------------------------");

        Random rand = new Random();
        int Size[] = new int[]{5000, 10000, 50000, 100000, 200000};
        long elapsedTime = 0;
        
        for (int i = 0; i < Size.length; i++) {
            int a[] = new int[Size[i]];
            for (int j = 0; j < Size[i]; j++) {
                int z = rand.nextInt(2000) - 1000;
                a[i] = z;
            }

            long startTime = System.nanoTime();
            //Change funcation call to test the different algorithms.
            maxSubSum4(a);
            long endTime = System.nanoTime();
            long elapsedTimePast = elapsedTime;
            elapsedTime = (endTime - startTime);
            System.out.println(Size[i] + ", " + elapsedTime / 1000000.0 + ", " + ((elapsedTime / 1000000.0) / (elapsedTimePast / 1000000.0)));
            //System.out.printf("%7s%18s%\n", n, myFormat.format(elapsedTime/1000000.0));
            
            /*System.out.println("\n");
                    
            startTime = System.nanoTime();
            //Change funcation call to test the different algorithms.
            maxSubSum3(a);
            endTime = System.nanoTime();
            elapsedTimePast = elapsedTime;
            elapsedTime = (endTime - startTime);
            System.out.println(Size[i] + ", " + elapsedTime / 1000000.0 + ", " + ((elapsedTime / 1000000.0) / (elapsedTimePast / 1000000.0)));
            
            System.out.println("\n");
            
            startTime = System.nanoTime();
            //Change funcation call to test the different algorithms.
            maxSubSum2(a);
            endTime = System.nanoTime();
            elapsedTimePast = elapsedTime;
            elapsedTime = (endTime - startTime);
            System.out.println(Size[i] + ", " + elapsedTime / 1000000.0 + ", " + ((elapsedTime / 1000000.0) / (elapsedTimePast / 1000000.0)));
            
            System.out.println("\n");
            
            startTime = System.nanoTime();
            //Change funcation call to test the different algorithms.
            maxSubSum1(a);
            endTime = System.nanoTime();
            elapsedTimePast = elapsedTime;
            elapsedTime = (endTime - startTime);
            System.out.println(Size[i] + ", " + elapsedTime / 1000000.0 + ", " + ((elapsedTime / 1000000.0) / (elapsedTimePast / 1000000.0)));
            */
        }
    }

    /**
     * Cubic maximum contiguous subsequence sum algorithm1.
     */

    public static int maxSubSum1(int[] a) {
        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int thisSum = 0;

                for (int k = i; k <= j; k++) {
                    thisSum += a[k];
                }

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * Quadratic maximum contiguous subsequence sum algorithm2.
     */
    public static int maxSubSum2(int[] a) {
        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {
            int thisSum = 0;
            for (int j = i; j < a.length; j++) {
                thisSum += a[j];

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * Recursive maximum contiguous subsequence sum algorithm. Finds maximum sum
     * in subarray spanning a[left..right]. Does not attempt to maintain actual
     * best sequence.
     */
    private static int maxSumRec(int[] a, int left, int right) {
        if (left == right) // Base case
        {
            if (a[left] > 0) {
                return a[left];
            } else {
                return 0;
            }
        }

        int center = (left + right) / 2;
        int maxLeftSum = maxSumRec(a, left, center);
        int maxRightSum = maxSumRec(a, center + 1, right);

        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += a[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }
        return Math.max(maxLeftSum, Math.max(maxRightSum, maxLeftBorderSum + maxRightBorderSum));
    }

    /**
     * Driver for divide-and-conquer maximum contiguous subsequence sum
     * algorithm3.
     *
     * @param a
     * @return
     */
    public static int maxSubSum3(int[] a) {
        return maxSumRec(a, 0, a.length - 1);
    }

    /**
     * Linear-time maximum contiguous subsequence sum algorithm4.
     *
     * @param a
     * @return
     */
    public static int maxSubSum4(int[] a) {
        int maxSum = 0, thisSum = 0;

        for (int j = 0; j < a.length; j++) {
            thisSum += a[j];

            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }

        return maxSum;
    }
}
