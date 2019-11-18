package kdtree;

import java.util.List;

public class KDTreePointSet implements PointSet {
    private Node first;
    /**
     * Instantiates a new KDTree with the given points.
     * @param points a non-null, non-empty list of points to include
     *               (makes a defensive copy of points, so changes to the list
     *               after construction don't affect the point set)
     */
    public KDTreePointSet(List<Point> points) {
        create2Dtree(points);
    }

    /**
     * Returns the point in this set closest to (x, y) in (usually) O(log N) time,
     * where N is the number of points in this set.
     */
    @Override
    public Point nearest(double x, double y) {
        Point nearest = first.myPoint;
        boolean up = true;
        nearest = nearestHelper(first, x, y, nearest, up);
        return nearest;
    }

    private Point nearestHelper(Node node, double x, double y, Point currentBest, boolean up) {
        if (node == null) {
            return currentBest;
        }
        double currentDistance = currentBest.distanceSquaredTo(x, y); //optimize
        double newDistance = node.myPoint.distanceSquaredTo(x, y);
        if (currentDistance > newDistance) {
            currentBest = node.myPoint;
        }
        if (up && node.myPoint.y() <= y) { //WHAT TO DO IN EQUALITY?
            currentBest = nearestHelper(node.right, x, y, currentBest, !up);
            currentDistance = currentBest.distanceSquaredTo(x, y);
            if (currentDistance > Math.pow(Math.abs(y-node.myPoint.y()), 2)) { //equal?
                currentBest = nearestHelper(node.left, x, y, currentBest, !up);
            }
        }
        else if (up && node.myPoint.y() > y) {
            currentBest = nearestHelper(node.left, x, y, currentBest, !up);
            currentDistance = currentBest.distanceSquaredTo(x, y);
            if (currentDistance > Math.pow(Math.abs(y-node.myPoint.y()), 2)) {
                currentBest = nearestHelper(node.right, x, y, currentBest, !up);
            }
        }
        else if (!up && node.myPoint.x() <= x) {//WHAT TO DO IN EQUALITY?
            currentBest = nearestHelper(node.right, x, y, currentBest, !up);
            currentDistance = currentBest.distanceSquaredTo(x, y);
            if (currentDistance > Math.pow(Math.abs(x-node.myPoint.x()), 2)) {
                currentBest = nearestHelper(node.left, x, y, currentBest, !up);
            }
        }
        else if (!up && node.myPoint.x() > x) {
            currentBest = nearestHelper(node.left, x, y, currentBest, !up);
            currentDistance = currentBest.distanceSquaredTo(x, y);
            if (currentDistance > Math.pow(Math.abs(x-node.myPoint.x()), 2)) {
                currentBest = nearestHelper(node.right, x, y, currentBest, !up);
            }
        }
        return currentBest;

    }
    private void create2Dtree(List<Point> points) {
        if (points == null){
            return;
        }
        for (Point point : points) {
            add(point);
        }

    }

    public void add(Point put) {
        if (first == null) {
            first = new Node(put);
            return;
        }
        boolean up = true;
        add(first, put, up);
        return;
    }

    public void add(Node node, Point put, boolean up) {
        /*
        if (node == null) {
            Node node.myPoint = put; //exception?
        }
        */
        if (up && node.myPoint.y() <= put.y()) {
            //System.out.println("y larger, right");
            if (node.right == null) {
                node.right = new Node(put);
                return;
            }
            add(node.right, put, !up);
        }
        else if (up && node.myPoint.y() > put.y()) {
            //System.out.println("y smaller, left");
            if (node.left == null) {
                node.left = new Node(put);
                return;
            }
            add(node.left, put, !up);

        }
        else if (!up && node.myPoint.x() <= put.x()) {
            //System.out.println("x larger, right");

            if (node.right == null) {
                node.right = new Node(put);
                return;
            }
            add(node.right, put, !up);
        }
        else if (!up && node.myPoint.x() > put.x()) {
            //System.out.println("x smaller, left");

            if (node.left == null) {
                node.left = new Node(put);
                return;
            }
            add(node.left, put, !up);
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
