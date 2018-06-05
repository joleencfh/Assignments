import edu.princeton.cs.algs4.*;

public class GiantBookExperiments{

 private static int R; // number of rounds
 private static int N; // number of nodes
 
 // arrays to calculate statistics
 private static double[] giantA;
 private static double[] noIsolatedA;
 private static double[] oneComponentA;
 private static double[] timeA;

 private static boolean hasGiant;
 private static boolean hasOne;
 private static boolean hasNoIsolated;

  
 public static void main(String[] args){
  
  StdOut.println("How many N?");  int N = StdIn.readInt(); double n = (double) N;
  StdOut.println("How many R?");  int R = StdIn.readInt();
  
  Stopwatch sw;
  Stopwatch sw1;
  MyUnionFindd uf;

  
  giantA = new double[R];
  noIsolatedA = new double[R];
  oneComponentA = new double[R];
  timeA = new double[R];
  
  sw1 = new Stopwatch();

  
  for (int j=0; j<R; j++) {
   int count = 0;
   
   sw = new Stopwatch();
   uf = new MyUnionFindd(N);

   hasGiant = false;
   hasNoIsolated = false;
   hasOne = false;

   while (hasOne==false) {
    int p = StdRandom.uniform(0, N);
    int q = StdRandom.uniform(0, N);
    
    if (!uf.connected(p, q)) {
     uf.union(p, q);
    }
    if (!hasGiant&&uf.getMaxComponentSize()>=(n*0.1)) {
     giantA[j]=count;
     timeA[j] = sw.elapsedTime();
     hasGiant = true;
    }
    if (!hasNoIsolated&&uf.noIsolated()) {
     noIsolatedA[j]=count;
     hasNoIsolated = true;
    }
    if (!hasOne&&uf.isOneComponent()) {
     oneComponentA[j]=count;
     hasOne = true;
    }
    count++;
   }
   
   
  }
     StdOut.println("Total time : " +sw1.elapsedTime());
  
  // calculate statistics
  StdOut.println("Time : mean " + StdStats.mean(timeA) + "  stddev " + StdStats.stddev(timeA));
  StdOut.println("Giant : mean " + StdStats.mean(giantA) + " stddev " + StdStats.stddev(giantA));
  StdOut.println("Non isolated : mean " + StdStats.mean(noIsolatedA) + " stddev " + StdStats.stddev(noIsolatedA));
  StdOut.println("One big component : mean " + StdStats.mean(oneComponentA) + " stddev " + StdStats.stddev(oneComponentA));
 }

 
 }
