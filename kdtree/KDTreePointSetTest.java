package kdtree;

import org.junit.Test;

import java.util.List;

public class KDTreePointSetTest {
    List<Point> pointList = List.of(
            new Point(1, 3),
            new Point(1, 4),
            new Point(1, 2.4),
            new Point(2, 3),
            new Point(2, -3),
            new Point(-2, -3.1),
            new Point(0, 0),
            new Point(-1, -1),
            new Point(-111, 100),
            new Point(30, -100)
            );
    PointSet pointSet = new NaivePointSet(pointList);
    PointSet kdPointSet = new KDTreePointSet(pointList);

    @Test
    public void basicTest() {
        Point myPoint;

        myPoint = pointSet.nearest(2, 2);
        System.out.println(myPoint.toString());
        myPoint = pointSet.nearest(2, -2);
        System.out.println(myPoint.toString());
        myPoint = pointSet.nearest(1, 4);
        System.out.println(myPoint.toString());
        myPoint = pointSet.nearest(3, -100);
        System.out.println(myPoint.toString());
        myPoint = pointSet.nearest(0, 0);
        System.out.println(myPoint.toString());

        myPoint = kdPointSet.nearest(2, 2);  //THIS ONE DOES NOT MATCH NAIVE!
        System.out.println(myPoint.toString());
        myPoint = kdPointSet.nearest(2, -2);
        System.out.println(myPoint.toString());
        myPoint = kdPointSet.nearest(1, 4);
        System.out.println(myPoint.toString());
        myPoint = kdPointSet.nearest(3, -100);
        System.out.println(myPoint.toString());
        myPoint = kdPointSet.nearest(0, 0);
        System.out.println(myPoint.toString());






    }
}
