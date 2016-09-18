import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by zarak on 2016-09-18.
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int counter  = 0;

        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        
        for (String s: rq) {
            if (counter++ < k)
                StdOut.println(s);
            else
                break;
        }
    }
}
