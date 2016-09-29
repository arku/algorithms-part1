import edu.princeton.cs.algs4.Bag;

import java.util.Arrays;

public class BruteCollinearPoints {
    private Bag<Point[]> fourPoint = new Bag<Point[]>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new NullPointerException();

        Point[] copyPoints = new Point[points.length];
        System.arraycopy(points, 0, copyPoints, 0, copyPoints.length);

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new NullPointerException();
        }

        // duplicate copyPoints

        Arrays.sort(copyPoints);
        for (int i = 1; i < copyPoints.length; i++) {
            if (copyPoints[i].compareTo(copyPoints[i-1]) == 0)
                throw new IllegalArgumentException();
        }

        Point[] colPoints;
        for (int i = 0; i < copyPoints.length - 3; i++) {
            for (int j = i + 1; j < copyPoints.length - 2; j++) {
                for (int k = j + 1; k < copyPoints.length - 1; k++) {
                    for (int l = k + 1; l < copyPoints.length; l++) {
                        // test for collinearity
                        double slopePQ = copyPoints[i].slopeTo(copyPoints[j]);
                        double slopePR = copyPoints[i].slopeTo(copyPoints[k]);
                        double slopePS = copyPoints[i].slopeTo(copyPoints[l]);
                        if (slopePQ == slopePR && slopePR == slopePS) {
                            colPoints = new Point[4];
                            colPoints[0] = copyPoints[i];
                            colPoints[1] = copyPoints[j];
                            colPoints[2] = copyPoints[k];
                            colPoints[3] = copyPoints[l];
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

//        // read the n copyPoints from a file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        Point[] copyPoints = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            copyPoints[i] = new Point(x, y);
//        }

//        // draw the copyPoints
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : copyPoints) {
//            p.draw();
//        }
//        StdDraw.show();

//        // print and draw the line segments
//        BruteCollinearPoints collinear = new BruteCollinearPoints(copyPoints);
//        StdOut.print(collinear.numberOfSegments());
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();
    }


}
