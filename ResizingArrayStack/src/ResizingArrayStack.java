import java.util.Scanner;

public class ResizingArrayStack<Item> {
    private Item[] array;
    private int capacity = 0;

    public ResizingArrayStack() {
        array = (Item[]) new Object[1];
    }

    public boolean isEmpty() { return capacity == 0; }

    public void push(Item str) {
        if (this.size() == array.length) {
            Item[] copy = (Item[]) new Object[2 * this.size()];
            for (int i = 0; i < capacity; i++) {
                copy[i] = array[i];
            }
            array = copy;
        }
        array[capacity++] = str;
    }

    public int size() {
        return capacity;
    }

    public Item pop() {

        Item popped = null;
            popped = array[--capacity];
            array[capacity] = null;
            if(this.size() > 0 && this.size() == array.length / 4) {
                Item[] copy = (Item[]) new Object[array.length/2];
                for (int i = 0; i < capacity; i++) {
                    copy[i] = array[i];
                }
                array = copy;
            }

        return popped;
    }

    public static void main(String[] args) {
        ResizingArrayStack<Character> charStack;
        charStack = new ResizingArrayStack<Character>();
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            char character = scanner.next().charAt(0);
            if(character == '-')
                System.out.print(charStack.pop());
            else
                charStack.push(character);
        }
    }
}