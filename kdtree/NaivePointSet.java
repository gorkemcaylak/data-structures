package kdtree;

import java.util.List;

/**
 * Naive nearest neighbor implementation using a linear scan.
 */
public class NaivePointSet implements PointSet {
    List<Point> set;
    int size;
    /**
     * Instantiates a new NaivePointSet with the given points.
     * @param points a non-null, non-empty list of points to include
     *               (makes a defensive copy of points, so changes to the list
     *               after construction don't affect the point set)
     */
    public NaivePointSet(List<Point> points) {
        set = points;
        size = set.size();
    }

    /**
     * Returns the point in this set closest to (x, y) in O(N) time,
     * where N is the number of points in this set.
     */
    @Override
    public Point nearest(double x, double y) {
        if (size == 0) {
            return null;
        }
        Point smallest = set.get(0);
        Point temp;
        double smallestDist = smallest.distanceSquaredTo(x, y);
        double tempDist;
        for (int i=1; i<size; i++) {
            temp = set.get(i);
            tempDist = temp.distanceSquaredTo(x, y);
            if (tempDist < smallestDist) {
                smallest = temp;
                smallestDist = tempDist;
            }
        }
        return smallest;
    }
}
