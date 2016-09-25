import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BruteCollinearPoints {
    private Bag<Point[]> fourPoint = new Bag<Point[]>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new NullPointerException();

        for (int i = 0; i < points.length; i++) {
            if(points[i] == null)
                throw new NullPointerException();
        }

        Arrays.sort(points);
        // duplicate points
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i-1]) == 0)
                throw new IllegalArgumentException();
        }

        Point[] colPoints;
        for (int i = 0; i< points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        // test for collinearity
                        double slopePQ = points[i].slopeTo(points[j]);
                        double slopePR = points[i].slopeTo(points[k]);
                        double slopePS = points[i].slopeTo(points[l]);
                        if (slopePQ == slopePR && slopePR == slopePS) {
                            colPoints = new Point[4];
                            colPoints[0] = points[i];
                            colPoints[1] = points[j];
                            colPoints[2] = points[k];
                            colPoints[3] = points[l];
                            fourPoint.add(colPoints);
                        }
                    }
                }
            }
        }
    }

    public LineSegment[] segments() {


        LineSegment[] lineSegments = new LineSegment[fourPoint.size()];
        int index = 0;

        for (Point[] a: fourPoint) {
            Arrays.sort(a);

            LineSegment ls = new LineSegment(a[0], a[3]);
            lineSegments[index++] = ls;
        }
        return lineSegments;
    }

    public int numberOfSegments() {
        return fourPoint.size();
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        StdOut.print(collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }


}
