public class BruteCollinearPoints {
    public BruteCollinearPoints(Point[] points) {
        // nC4
        for(int i = 0; i< points.length - 3; i++) {
            for(int j = i + 1; j < points.length - 2; j++) {
                for(int k = j + 1; k < points.length - 1; k++) {
                    for(int l = k + 1; l < points.length; l++) {

                    }
                }
            }
        }
    }

}
