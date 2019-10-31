package kdtree;

import java.util.List;

public class KDTreePointSet implements PointSet {
    // TODO: add fields as necessary
    Node first;
    /**
     * Instantiates a new KDTree with the given points.
     * @param points a non-null, non-empty list of points to include
     *               (makes a defensive copy of points, so changes to the list
     *               after construction don't affect the point set)
     */
    public KDTreePointSet(List<Point> points) {
        // TODO: replace this with your code
        create2Dtree(points);
        //throw new UnsupportedOperationException("Not implemented yet; replace this with your code.");
    }

    /**
     * Returns the point in this set closest to (x, y) in (usually) O(log N) time,
     * where N is the number of points in this set.
     */
    @Override
    public Point nearest(double x, double y) {
        // TODO: replace this with your code
        Point nearest = null;
        nearest = nearestHelper(first, x, y, nearest, true);
        return nearest;
        //throw new UnsupportedOperationException("Not implemented yet; replace this with your code.");
    }

    private Point nearestHelper(Node node, double x, double y, Point currentBest, boolean up) {
        if (node == null) {
            return currentBest;
        }

        if (currentBest == null) { // || node.myPoint.distanceSquaredTo(currentBest)>node.myPoint.distanceSquaredTo(x, y)) {
            currentBest = node.myPoint;
        }
        double currentDistance = currentBest.distanceSquaredTo(x, y);
        double newDistance =node.myPoint.distanceSquaredTo(x, y);
        if (currentDistance > newDistance) { // can be merged with null!!!!
            currentBest = node.myPoint;
        }

        if (up && node.myPoint.y() <= y) { //WHAT TO DO IN EQUALITY?
            currentBest = nearestHelper(node.right, x, y, currentBest, !up);
        }
        else if (up && node.myPoint.y() > y) {
            currentBest = nearestHelper(node.left, x, y, currentBest, !up);
        }
        else if (!up && node.myPoint.x() <= x) {//WHAT TO DO IN EQUALITY?
            currentBest = nearestHelper(node.right, x, y, currentBest, !up);
        }
        else if (!up && node.myPoint.x() > x) {
            currentBest = nearestHelper(node.left, x, y, currentBest, !up);
        }
        return currentBest;

    }
    private void create2Dtree(List<Point> points) {
        //first = new Node(points.get(0));
        for (int i = 0; i < points.size(); i++) {
            System.out.println(points.get(i).toString());
            add(points.get(i));
        }

    }

  /*  private void traverseTree (Point inPoint) {
        //boolean upDown = true;

    }
   */
    public void add(Point put) {
      //  boolean upDown = true;
        if (first == null) {
            first = new Node(put);
            System.out.println("first=" + first.myPoint.toString());
            return;
        }
        add(first, put, true);
        return;
    }

    public void add(Node node, Point put, boolean up) {
        //up = !up;
//        if (node == null) {
//            Node node.myPoint = put; //exception?
//        }
        if (up && node.myPoint.y() <= put.y()) { //WHAT TO DO IN EQUALITY?
            System.out.println("y larger, right");
            if (node.right == null) {
                node.right = new Node(put);
                return;
            }
            add(node.right, put, !up);//should go up = false
        }
        else if (up && node.myPoint.y() > put.y()) {
            System.out.println("y smaller, left");
            if (node.left == null) {
                node.left = new Node(put);
                return;
            }
            add(node.left, put, !up);//should go up = false

        }
        else if (!up && node.myPoint.x() <= put.x()) {//WHAT TO DO IN EQUALITY?
            System.out.println("x larger, right");

            if (node.right == null) {
                node.right = new Node(put);
                return;
            }
            add(node.right, put, !up); //should go up = true
        }
        else if (!up && node.myPoint.x() > put.x()) {
            System.out.println("x smaller, left");

            if (node.left == null) {
                node.left = new Node(put);
                return;
            }
            add(node.left, put, !up);//should go up = true
        }
    }

    private class Node {
        public Point myPoint;
        public Node left;
        public Node right;
        public Node(Point in){
            myPoint = in;
            left = null;
            right = null;
        }

    }
}
