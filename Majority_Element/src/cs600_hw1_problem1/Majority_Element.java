/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs600_hw1_problem1;

import java.io.*;
import java.util.*;

/**
 *
 * @author Elias
 */
public class Majority_Element {

    /**
     * @param args the command line arguments
     */
    public Scanner x;

    public static void main(String[] args) {
        // TODO code application logic here
        File file1 = new File("Majex1.txt");
        File file2 = new File("Majex2.txt");
        File file3 = new File("Majex3.txt");
        File file4 = new File("Majex4.txt");

        //List <Integer> e = new ArrayList<>();
        //List <Integer> f = new ArrayList<>();
        //List <Integer> g = new ArrayList<>();
        //List <Integer> h = new ArrayList<>();
        int a[] = new int[99];
        int b[] = new int[99];
        int c[] = new int[845];
        int d[] = new int[845];

        filedata(file1, a);
        filedata(file2, b);
        filedata(file3, c);
        filedata(file4, d);

        System.out.println("Method 1:");
        System.out.println("Majex1 with Method 1 returns: " + method1(a));
        System.out.println("Majex2 with Method 1 returns: " + method1(b));
        System.out.println("Majex3 with Method 1 returns: " + method1(c));
        System.out.println("Majex4 with Method 1 returns: " + method1(d));
        
        System.out.println("\n Method 2:");
        System.out.println("Majex1 with Method 2 returns: " + method2(a));
        System.out.println("Majex2 with Method 2 returns: " + method2(b));
        System.out.println("Majex3 with Method 2 returns: " + method2(c));
        System.out.println("Majex4 with Method 2 returns: " + method2(d));

        System.out.println("\n Method 3:");
        System.out.println("Majex1 with Method 3 returns: " + method3(a));
        System.out.println("Majex2 with Method 3 returns: " + method3(b));
        System.out.println("Majex3 with Method 3 returns: " + method3(c));
        System.out.println("Majex4 with Method 3 returns: " + method3(d));
        
        //String currentPath = file1.getAbsolutePath();
        //System.out.println("Current path is: " + currentPath);
    }

    public static void filedata(File file, int a[]) {
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }

        int count = 0;
        boolean foundInts = false; //flag to see if there are any integers

        while (scan.hasNextLine()) { //Note change
            String currentLine = scan.nextLine();
            //split into words
            String words[] = currentLine.split(" ");

            //For each word in the line
            for (String str : words) {
                try {
                    int num = Integer.parseInt(str);
                    a[count] = num;
                    count++;
                    foundInts = true;
                    //System.out.println("Found: " + num);
                } catch (NumberFormatException nfe) {

                }; //word is not an integer, do nothing
            }
        } //end while 

        if (!foundInts) {
            System.out.println("No numbers found.");
        } else {
            //System.out.println("Total: " + count);
        }

        // close the scanner
        scan.close();
    }

    public static int method1(int[] a) {
        if (null == a || a.length == 0) {
            return -1;
        }

        int ele = 0, count = 1, n = a.length;
        for (int i = 0; i < n; i++) {
            count = 1;
            for (int j = 0; j < n; j++) {
                ele = a[i];
                if (ele == a[j]) {
                    count++;
                }
                if (count > (n / 2)) {
                    return ele;
                }
            }
        }

        System.out.println("NO Majority Element");
        return -1;
    }

    public static int method2(int[] a) {
        if (null == a || a.length == 0) {
            return -1;
        }

        int n = a.length, k = n / 2;
        if (n == 1) {
            return a[0];
        } else if (n == 2) {
            if (a[0] == a[1]) {
                return a[0];
            } else {
                return -1;
            }
        }

        int elel = method2(Arrays.copyOfRange(a, 0, k));
        int eler = method2(Arrays.copyOfRange(a, k + 1, n));

        if (elel == -1 && eler >= 0) {
            return eler;
        } else if (eler == -1 && elel >= 0) {
            return elel;
        }
        
        if (elel == eler) {
            return elel;
        } else {
            return -1;
        }
/*
        if (elel == eler) {
            return elel;
        }

        //int lcount = getFrequency(a, elel);
        //int rcount = getFrequency(a, eler);
        if (lcount > k + 1) {
            return elel;
        } else if (rcount > k + 1) {
            return eler;
        } else {
            //System.out.println("No Majority Element");
            return -1;
        }
*/
    }

    private static int getFrequency(int[] a, int major) {
        // Scan the element in a[1..n] in every recursive call,
        int count = 0;
        for (int ele : a) {
            if (ele == major) {
                count++;
            }
            if (count > a.length / 2) {
                break;
            }
        }
        return count;
    }

    public static int method3(int[] a) {
        if (null == a || a.length == 0) {
            return -1;
        }
        int result = 0, count = 0;

        for (int i = 0; i < a.length; i++) {
            if (count == 0) {
                result = a[i];
                count = 1;
            } else if (result == a[i]) {
                count++;
            } else {
                count--;
            }
        }

        if (count <= 0) {
            return -1;
        } else {
            //System.out.println(count);
            return result;
        }
    }
}
