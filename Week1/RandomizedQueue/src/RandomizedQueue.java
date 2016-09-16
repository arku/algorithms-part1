/**
 * Created by zarak on 2016-09-16.
 */
public class RandomizedQueue<Item> {
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

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (numItems == array.length) {
            resize(2 * numItems);
        }
        array[numItems++] = item;
    }

    private void resize(int size) {
        Item[] copy = (Item[]) new Object[size];
        for(int i = 0; i < numItems; i++)
            copy[i] = array[i];
        array = copy;
    }


}
