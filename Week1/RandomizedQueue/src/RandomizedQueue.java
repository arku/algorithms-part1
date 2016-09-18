import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by zarak on 2016-09-16.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int numItems;
    private Item[] array;

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return numItems == 0;
    }

    public int size() {
        return numItems;
    }

    private Item getItem(int index) {
        return array[index];
    }
    private int getLength() {
        return array.length;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (numItems == array.length) {
            resize(2 * numItems);
        }
        array[numItems++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        if (size() == array.length / 4) resize(array.length / 2);
        int randIndex = StdRandom.uniform(numItems);

        Item item = array[randIndex];
        array[randIndex] = array[--numItems];
        array[numItems] = null;

        return item;

    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int randIndex = StdRandom.uniform(numItems);
        return array[randIndex];
    }

    private void resize(int size) {
        Item[] copy = (Item[]) new Object[size];
        for (int i = 0; i < numItems; i++)
            copy[i] = array[i];
        array = copy;
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<Item> {
        private int current;
        private Item[] shuffled = (Item[]) new Object[numItems];

        public QueueIterator() {
            for (int i = 0; i < numItems; i++) {
                shuffled[i] = array[i];
            }
            StdRandom.shuffle(shuffled);
        }

        public boolean hasNext() {
            return current < numItems;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return shuffled[current++];
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");

        for (int i = 0; i < 4; i++) {
            for (String s : queue)
                StdOut.print(s + " ");
            StdOut.println();
        }

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        for (String s : queue)
            StdOut.print(s + " ");
    }
}
