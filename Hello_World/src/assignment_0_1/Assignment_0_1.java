import java.text.DecimalFormat;
import java.time.Clock;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

/**
 *
 * @author Elias
 */
public class Assignment_0_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DecimalFormat myFormat = new DecimalFormat("##0.000000");
        System.out.printf("%8s%17s\n", "Size", "Time in ms");
        System.out.println("---------------");
        
        for(int n= 32; n <= 65536; n *=2) {
            int sum, i;
            //time of system as startTime before starting the loop
            long startTime = System.nanoTime();
            sum = 0;
            //first loop
            for(i = 0; i < n; i++) {
                sum++;
            }
            
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            
            System.out.printf("%7d%18s%\n", n, myFormat.format(elapsedTime/1000000.0));
        }
    }
}
