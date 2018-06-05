import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class FoursumQuick {

    public static void main(String[] args) {
       sumFour();
    }

    public static void sumFour() {


            int N = StdIn.readInt();
            long[] a = new long[N];
            int x;
            for (x=0; x<N; x++){
                a[x]= StdIn.readLong();}
            long n = a.length;
            Arrays.sort(a);
            boolean sumOk = false;
                    for (int i = 0; i < n; i++) {
                        for (int j = i + 1; j < n; j++) {
                            for (int k = j + 1; k < n; k++) {
                                long w = a[i] + a[j] + (a[k]);
                                if(Arrays.binarySearch(a, k + 1, a.length, -w) > 0) {
                                    sumOk = true;
                                    
                                }
                            }
                        }
                    }

                    System.out.println(sumOk);
                    
                }
            
 


    public static void warmItUpDotExe(int x) {

        for (int i = 0; i < x; i++) {
            
                sumFour();
            
        }
    }

    public static double[] runForestRun(int x) {

        double[] timeList = new double[x];

        for (int i = 0; i < x; i++) {
                Stopwatch timer = new Stopwatch();
                sumFour();
                timeList[i] = timer.elapsedTime();

            } 
        
        return timeList;
    }

    public static void quickMaths(int x){

        Stopwatch timer = new Stopwatch();
        //int x = 1;
        double[] timeList = runForestRun(x);

        StdOut.println("Total time for " + x + " runs: "+ timer.elapsedTime());
        StdOut.println("Minimum: " + StdStats.min(timeList));
        StdOut.println("Maximum: " + StdStats.max(timeList));
        StdOut.println("Mean: " + StdStats.mean(timeList));
    }
}
