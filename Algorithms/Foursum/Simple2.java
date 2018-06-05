import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class Simple2 {

    public static void main(String[] args) {
       sumFourSlow();
    }

    public static void sumFourSlow() {


            int N = StdIn.readInt();
            long[] a = new long[N];
            int b;
            for (b=0; b<N; b++){
                a[b]= StdIn.readLong();}
            long n = a.length;
            Arrays.sort(a);
            boolean sumOk = false;
                    for (int i = 0; i < n; i++) {
                        for (int j = i + 1; j < n; j++) {
                            for (int k = j + 1; k < n; k++) {
                                for (int x = k + 1; x < n; x++) {
                                    if (a[i] + a[j] + a[k] + a[x] == 0) {
                                    sumOk = true;
                                    
                                }
                            }
                        }
                    }
                    
                }
             System.out.println(sumOk);
    }


    public static void warmItUpDotExe(int x) {

        for (int i = 0; i < x; i++) {
            
                sumFourSlow();
            
        }
    }

    public static double[] runForestRun(int x) {

        double[] timeList = new double[x];

        for (int i = 0; i < x; i++) {
                Stopwatch timer = new Stopwatch();
                sumFourSlow();
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
