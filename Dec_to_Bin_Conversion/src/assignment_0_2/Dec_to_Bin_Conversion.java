/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_0_2;

/**
 *
 * @author Elias
 */
public class Dec_to_Bin_Conversion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n = 124;
        convert(n);
    }

    public static void convert(int n) {
        if (n <= 1) {
            System.out.println(n);
        } else {
            convert(n / 2);
            System.out.println(n % 2);
        }
    }
}

