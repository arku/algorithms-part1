import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by zarak on 2016-09-15.
 */
public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        private Node getNext() {
            return next;
        }

        private Item getItem() {
            return item;
        }

        private Node getPrev() { return prev; }
    }

    public Deque() {
    }

    public int size() {
        return size;
    }

    private Node getFirst() {
        return first;
    }

    private Node getLast() {
        return last;
    }

    public boolean isEmpty() {

       return size == 0;
   }

   public void addFirst(Item item) {
       if (item == null) throw new NullPointerException();
       Node copy = first;

       first = new Node();
       first.item = item;

       first.next = copy;

       if (copy != null) copy.prev = first;
       else last = first;

       this.size += 1;
   }

   public void addLast(Item item) {
       if (item == null) throw new NullPointerException();

       Node copy = last;
       last = new Node();
       last.item = item;
       if (copy != null) {
           copy.next = last;
           last.prev = copy;
       }
       else
           first = last;
       this.size++;
   }

   public Item removeFirst() {
       if (isEmpty()) throw new NoSuchElementException();
       else {
           Item item = first.item;
           first = first.next; // null if its empty

           if (first == null) last = first;
           else first.prev = null;

           this.size--;
           return item;
       }
   }

   public Item removeLast() {
       if (isEmpty()) throw new NoSuchElementException();
       else {
           Item item = last.item;
           last = last.prev;

           if (last == null) first = null;
           else last.next = null;

           this.size--;
           return item;
       }
   }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();

        deque.addFirst("a1");
        deque.addFirst("a2");
        deque.addFirst("a3");
        deque.addFirst("a4");
        deque.addFirst("a5");
        deque.addFirst("a6");
        deque.addFirst("a7");
        deque.addLast("a8");
        deque.addLast("a9");

        for (String s : deque)
            System.out.print(s + " ");
    }
}
