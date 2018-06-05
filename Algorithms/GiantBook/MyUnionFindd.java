import edu.princeton.cs.algs4.*;
import java.util.HashSet;
 
public class MyUnionFindd {
 
private int[] id; // parent link (site indexed)
private int[] sz; // size of component for roots (site indexed)
private int[] in; // isolated nodes
private int count; // number of components
private HashSet<Integer> nonIsolatedComponents; 
private int totalNumNodes;
 private boolean gotNoIsolated;
private int giant; // keep track of biggest component size
 
public MyUnionFindd(int N)
{
totalNumNodes = N;
count = N;
nonIsolatedComponents = new HashSet<Integer>();
giant = 0;  
id = new int[N];
sz = new int[N];
in = new int[N];
for (int i = 0; i < N; i++){
 id[i] = i;
 sz[i] = 1;
 in[i] = i;
} 

gotNoIsolated=false;
 
}
 
 
public int getMaxComponentSize() {
  return giant;
}
   
public boolean isOneComponent() {
    boolean valid = false;
    if (count==1) {
    valid = true;  
   }
    return valid;
}
public void noIsolatedNodes(int p, int q) {
     
    this.nonIsolatedComponents.add(p);
    this.nonIsolatedComponents.add(q);
                
    if (nonIsolatedComponents.size()==totalNumNodes) {
      gotNoIsolated=true;  
    }   
} 
    public boolean noIsolated() {
        return gotNoIsolated;
    }

     
public int count()
{ return count; }
 
public boolean connected(int p, int q)
{ return find(p) == find(q); }
 
private int find(int p)
{ // Follow links to find a root.
while (p != id[p]) p = id[p];
return p;
}
 
public void union(int p, int q)
{
 
  int i = find(p);
  int j = find(q);
   if (i == j) return;
  noIsolatedNodes(p,q);


// Make smaller root point to larger one.
if (sz[i] < sz[j]) { 
    id[i] = j; sz[j] += sz[i];
    
    if (sz[j]>giant) {giant=sz[j];}
    }
 
else { id[j] = i; sz[i] += sz[j]; 
    
    if (sz[i]>giant) {giant=sz[i];}
     }
  
count--;
}
}