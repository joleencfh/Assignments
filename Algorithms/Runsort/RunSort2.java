import edu.princeton.cs.algs4.*;
import java.util.Comparator;
 
public class RunSort2{
  
 public RunSort2(){
 }
 
  
 public static void main(String[] args){
  String[] a = StdIn.readAllStrings();
  //String[] a = {"S","E","A","R","C","H","E","A","A","M"};
  //String[] a = {"E","G","H","A","A","B","C","M","D","F", "G", "I"};
  //String[] a = {"S","E","A","A","A","B","C","M","D","F", "G", "I"};
  sort(a);
  printArr(a);
 }
  
  
  //Help from Sedgewick and Wayne, section 2.2
 private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
  for(int k = lo; k<=hi;k++){         
      aux[k] = a[k];
   }
  int i = lo, j = mid+1;      
  for (int k = lo; k <= hi; k++) {  
       if      (i > mid)              a[k] = aux[j++];  
       else if (j > hi)               a[k] = aux[i++];  
       else if (less(aux[j], aux[i])) a[k] = aux[j++];  
       else  a[k] = aux[i++];  
   }
 }
  
  
  // Help from Sedgewick and Wayne, section 2.2
 public static void sort(Comparable[] a){
  int size = a.length;     
  Comparable[] aux = new Comparable[size];  
  
  boolean done = false;
  while(done==false){
       
         int i = 0;
         
         //find runs pairs
      
         while(i<size-1) {
          
          // 1st run:
             int lo = i;
             while(i<size-1 &&  !less(a[i+1],a[i])) {i++;}
             int mid = i;
             if(lo == 0 && mid == size-1) {done=true; }
               if(mid == size-1) continue; //if there's no second run at this point

          // 2nd run:
             i++;
             while(i<size-1 && !less(a[i+1],a[i])){i++;}
             int hi = i;

          merge(a, aux, lo, mid, hi); // merging the two resulting runs

          // if this condition holds, the array would be completely sorted:
          if(lo == 0 && hi == size-1) { done=true; }
     
    i++;
         }
       }
  
  }
 

  
     
 //method to print the array  
 private static void printArr(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
  
 // If specified object is less the method returns -1, equal - 0, bigger - 1
 private static boolean less(Comparable x, Comparable y){
  return x.compareTo(y)<0;  
 }
 
 
}