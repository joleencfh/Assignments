import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.*;


public class RandomQueue<Item> implements Iterable<Item>
{
    private int amountOfItems = 0;
    private Item[] listOfItem;

    public RandomQueue() { // create an empty random queue
        listOfItem = (Item[]) new Object[1];
    }
    public boolean isEmpty() {// is it empty?
        return amountOfItems == 0;
    }
    public int size() {// return the number of elements
        return amountOfItems;
    }
    public void enqueue(Item item) {// add an item
        //Resize the listOfItem. This makes the list 2 times bigger, so that it can hold more items.
        //It does this by calling our resize() method
        //Page 141
        if (amountOfItems == listOfItem.length) resize(2*listOfItem.length);
        listOfItem[amountOfItems++]=item;
    }
    public Item sample(){ // return (but do not remove) a random item
        int randomNumber = StdRandom.uniform(0, amountOfItems);
        return listOfItem[randomNumber];
    }
    public Item dequeue(){ // remove and return a random item
        //page 141:
        if(isEmpty())//throw exception if queue is empty
            throw new RuntimeException();
        int r = StdRandom.uniform(0, amountOfItems);

        exch(listOfItem, r, (amountOfItems-1));
        Item item = listOfItem[--amountOfItems];
        listOfItem[amountOfItems] = null; // Avoid loitering (see text).
        if (amountOfItems > 0 && amountOfItems == listOfItem.length/4) resize(listOfItem.length/2);
        return item;
    }
    //from page 245
    private void exch(Item[] listOfItem, int i, int j) {
        Item t = listOfItem[i];
        listOfItem[i] = listOfItem[j];
        listOfItem[j] = t;
    }

    private void resize(int max)
    //This piece of code is taken from the book. page 141.
    {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < amountOfItems; i++)
            temp[i] = listOfItem[i];
        listOfItem = temp;
    }

    public Iterator<Item> iterator() { // return an iterator over the items in random order
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private int i = amountOfItems;
        Item[] listOfItemClone = listOfItem.clone();

        public RandomQueueIterator() {

        }
        public boolean hasNext()  {return i > 0;}
        public void remove()      {throw new UnsupportedOperationException();  }
        public Item next() {
            if(isEmpty())
                throw new RuntimeException();
            int RNG = StdRandom.uniform(0, i);
            Item returnItem = listOfItemClone[RNG];
            listOfItemClone[RNG] = listOfItemClone[--i];
            listOfItemClone[i] = null;
            return  returnItem;

        }
    }




    // The main method below tests your implementation. Do not change it.
    public static void main(String args[])
    {
        // Build a queue containing the Integers 1,2,...,6:
        RandomQueue<Integer> Q= new RandomQueue<Integer>();
        for (int i = 1; i < 7; ++i) Q.enqueue(i); // autoboxing! cool!

        // Print 30 die rolls to standard output
        StdOut.print("Some die rolls: ");
        for (int i = 1; i < 30; ++i) StdOut.print(Q.sample() +" ");
        StdOut.println();

        // Let's be more serious: do they really behave like die rolls?
        int[] rolls= new int [10000];
        for (int i = 0; i < 10000; ++i)
            rolls[i] = Q.sample(); // autounboxing! Also cool!
        StdOut.printf("Mean (should be around 3.5): %5.4f\n", StdStats.mean(rolls));
        StdOut.printf("Standard deviation (should be around 1.7): %5.4f\n",
                StdStats.stddev(rolls));

        // Now remove 3 random values
        StdOut.printf("Removing %d %d %d\n", Q.dequeue(), Q.dequeue(), Q.dequeue());
        // Add 7,8,9
        for (int i = 7; i < 10; ++i) Q.enqueue(i);
        // Empty the queue in random order
        while (!Q.isEmpty()) StdOut.print(Q.dequeue() +" ");
        StdOut.println();

        // Let's look at the iterator. First, we make a queue of colours:
        RandomQueue<String> C= new RandomQueue<String>();
        C.enqueue("red"); C.enqueue("blue"); C.enqueue("green"); C.enqueue("yellow");

        Iterator<String> I= C.iterator();
        Iterator<String> J= C.iterator();

        StdOut.print("Two colours from first shuffle: "+I.next()+" "+I.next()+" ");

        StdOut.print("\nEntire second shuffle: ");
        while (J.hasNext()) StdOut.print(J.next()+" ");

        StdOut.println("\nRemaining two colours from first shuffle: "+I.next()+" "+I.next());
    }
}
