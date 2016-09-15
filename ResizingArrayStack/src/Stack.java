import java.util.Scanner;

/**
 * Created by zarak on 2016-09-13.
 */
public class Stack<Item> {
    private Node head;
    private int capacity;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() { return head == null; }

    public void push(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = head;
        head = newNode;
        capacity++;
    }

    public Item pop() {
        Item item = head.item;
        head = head.next;
        capacity--;
        return item;
    }

    public int size() { return capacity; }

    public static void main(String[] args) {
        Stack<Character> charStack;
        charStack = new Stack<Character>();
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
