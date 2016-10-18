import java.io.*;
import java.util.*;
import java.text.*;

//
public class Solution {
    static double array[];
    static void printA(){
        for(int i=0; i<array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    static void swap (int cx, int cy){             // swap 2 numbers in array given their coords/location
        double temp = array[cx];
        array[cx] = array[cy];
        array[cy] = temp;
        // System.out.println("Swapped; " + array[cy] + " and " + array[cx]);
        return;
    }
    static int partition(int l, int r){
        int i = l-1;
        for (int j = l; j<r; j++){
            if(array[j]>array[r]){
                // swap!
                i++;
                swap(i, j);
            }
        }
        swap(i+1,r);
        return (i+1);
    }
    static int randomizedP(int l, int r){
        int p = (int)((r - l) * Math.random()) + l;            // a random number between l and r
        // System.out.println("random, p = " + p);
        swap(p, r);
        return partition(l, r);
    }    
    static void quickSort(int l, int r){           // must be called after initializing array[] class variable
        if (l == r)             // array.length == 1
            return;
        int p = randomizedP(l, r);
        if (p > l+1)
            quickSort(l, p-1);
        if (p < r-1)
            quickSort(p+1, r);
        return;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        double p[] = new double[n];
        double x[] = new double[n];
        double y[] = new double[n];
        double maxi[] = new double[n];
        double sorted[] = new double[n];
        Solution.array = new double[n];
        double res = 0;
        // get all p values
        for (int i = 0; i < n; i++){
            p[i] = in.nextDouble();
        }
        // get all x values
        for (int i = 0; i < n; i++){
            x[i] = in.nextDouble();
        }
        // get all y values; also calculate individual maxi values
        for (int i=0; i < n; i++){
            y[i] = in.nextDouble();
            maxi[i] = p[i]*x[i] - (1-p[i])*y[i];
        }
        // now to sort maxi values in decreasing order and pick the first k values as long as they are over 0
        // quickSort(0, n-1);
        array = maxi;
        quickSort(0, n-1);
             
        for (int i=0; i< k; i++)
            {
            if (array[i]<0)         // the max value in sub-array of  ith iteration is negative; exit loop
                break;
            res += array[i];
        }
        NumberFormat formatter = new DecimalFormat("0.00");
        System.out.println(formatter.format(res));
    }
}