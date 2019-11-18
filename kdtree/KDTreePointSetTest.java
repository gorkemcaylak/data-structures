package kdtree;

import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class KDTreePointSetTest {

//    List<Point> pointList = List.of(
//            new Point(1, 3),
//            new Point(1, 4),
//            new Point(1, 2.4),
//            new Point(2, 3),
//            new Point(2, -3),
//            new Point(-2, -3.1),
//            new Point(0, 0),
//            new Point(-1, -1),
//            new Point(-111, 100),
//            new Point(2, -1),
//            new Point(2.1, -3),
//            new Point(2, 3.1),
//            new Point(30, 100),
//            new Point(1, 3)
//            );
//    PointSet pointSet = new NaivePointSet(pointList);
//    PointSet kdPointSet = new KDTreePointSet(pointList);

    @Test
    public void basicTest() {
        Point myPoint;

//        myPoint = pointSet.nearest(2, 2);
//        System.out.println(myPoint.toString());
//        myPoint = pointSet.nearest(2, -2);
//        System.out.println(myPoint.toString());
//        myPoint = pointSet.nearest(1, 4);
//        System.out.println(myPoint.toString());
//        myPoint = pointSet.nearest(3, -100);
//        System.out.println(myPoint.toString());
//        myPoint = pointSet.nearest(0, 0);
//        System.out.println(myPoint.toString());
//
//        myPoint = kdPointSet.nearest(2, 2);  //THIS ONE DOES NOT MATCH NAIVE!
//        System.out.println(myPoint.toString());
//        myPoint = kdPointSet.nearest(2, -2);
//        System.out.println(myPoint.toString());
//        myPoint = kdPointSet.nearest(1, 4);
//        System.out.println(myPoint.toString());
//        myPoint = kdPointSet.nearest(3, -100);
//        System.out.println(myPoint.toString());
//        myPoint = kdPointSet.nearest(0, 0);
//        System.out.println(myPoint.toString());






    }

    @Test
    public void randomComparedTest() {
        Point myPoint;

//        Point myPoint;
//        PointSet pointRandSet;
//        PointSet kdPointRandSet;
//        int seed = 373; // or your favorite number
//        Random random = new Random(seed);
//        List<Point> pointList2 = new ArrayList<>();
//        for (int i = 0; i < 1000000; i += 1) {
//            double x = random.nextDouble();
//            double y = random.nextDouble();
//            x *= 100;
//            x -= 50;
//            y *= 100;
//            y -= 50;
//            myPoint = new Point(x, y);
//            pointList2.add(myPoint);
//        }
//
//        pointRandSet = new NaivePointSet(pointList2);
//        kdPointRandSet = new KDTreePointSet(pointList2);
//
//        for (int i = 0; i < 1000; i += 1) {
//            double x = random.nextDouble();
//            x -= 50;
//            double y = random.nextDouble();
//            y -= 50;
//            assertEquals(pointRandSet.nearest(x, y), kdPointRandSet.nearest(x, y));
//        }








        }
}
