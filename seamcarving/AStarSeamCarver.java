package seamcarving;

import astar.AStarGraph;
import astar.AStarSolver;
import astar.ShortestPathsSolver;
import astar.WeightedEdge;
import edu.princeton.cs.algs4.Picture;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AStarSeamCarver implements SeamCarver {
    private Picture picture;
    private ShortestPathsSolver<Pixel> solverV;
    private ShortestPathsSolver<Pixel> solverH;

    private PixelGraph graphH;
    private PixelGraph graphV;

    private int W;
    private int H;


    public AStarSeamCarver(Picture picture) {
        if (picture == null) {
            throw new NullPointerException("Picture cannot be null.");
        }


        this.picture = new Picture(picture);

        W = width();
        H = height();
        System.out.println("W=" + W);
        System.out.println("L=" + H);

    }

    public Picture picture() {
        return new Picture(picture);
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public int width() {
        return picture.width();
    }

    public int height() {
        return picture.height();
    }

    public Color get(int x, int y) {
        return picture.get(x, y);
    }

    public double energy(int x, int y) {


        int xNext = (x + 1) % W;

        int rXNext = picture.get(xNext, y).getRed();
        int gXNext = picture.get(xNext, y).getGreen();
        int bXNext = picture.get(xNext, y).getBlue();
        int xPrev = (x - 1 + W) % W;

        int rXPrev = picture.get(xPrev, y).getRed();
        int gXPrev = picture.get(xPrev, y).getGreen();
        int bXPrev = picture.get(xPrev, y).getBlue();

        int rXDiff = rXNext - rXPrev;
        int gXDiff = gXNext - gXPrev;
        int bXDiff = bXNext - bXPrev;
        int xGrad = rXDiff * rXDiff + gXDiff * gXDiff + bXDiff * bXDiff;

        int yNext = (y + 1) % H;

        int rYNext = picture.get(x, yNext).getRed();
        int gYNext = picture.get(x, yNext).getGreen();
        int bYNext = picture.get(x, yNext).getBlue();
        int yPrev = (y - 1 + H) % H;

        int rYPrev = picture.get(x, yPrev).getRed();
        int gYPrev = picture.get(x, yPrev).getGreen();
        int bYPrev = picture.get(x, yPrev).getBlue();

        int rYDiff = rYNext - rYPrev;
        int gYDiff = gYNext - gYPrev;
        int bYDiff = bYNext - bYPrev;
        int yGrad = rYDiff * rYDiff + gYDiff * gYDiff + bYDiff * bYDiff;

        return Math.sqrt(xGrad + yGrad);
    }

    public int[] findHorizontalSeam() {
        //  instead of edgeTo, three options left down right
        //solve for
        Pixel start = new Pixel(-1, 0,  0);
        Pixel end = new Pixel(-1, -1,  0);
        graphH = new PixelGraph(true);
        solverH = new AStarSolver<Pixel>(graphH, start, end, 100);
        List<Pixel> soln = solverH.solution();
        int N = soln.size();
        int[] outV = new int[N]; //change to N-2 ??
        for (int i=0; i<N; i++){
            outV[i] = soln.get(i).y();
        }
        return outV;
    }

    public int[] findVerticalSeam() {
        Pixel start = new Pixel(0, -1,  0);
        Pixel end = new Pixel(-1, -1,  0);
        graphV = new PixelGraph(false);
        solverV = new AStarSolver<Pixel>(graphV, start, end, 100);
        List<Pixel> soln = solverV.solution();
        int N = soln.size();
        int[] outH = new int[N]; //change to N-2 ??
        for (int i=0; i<N; i++) {
            outH[i] = soln.get(i).x();
        }
        return outH;
    }

    /** Pixel Graph Class */

    private class PixelGraph implements AStarGraph<Pixel> {
        boolean horizontal;

        PixelGraph(boolean horizontal) {
            this.horizontal = horizontal;
        }

        @Override
        public List<WeightedEdge<Pixel>> neighbors(Pixel p) {
            List<WeightedEdge<Pixel>> neighborEdges = new ArrayList<>();
            if (horizontal){
                //use Pixel.horizontalNeighbors()
                List<Pixel> neighbors = p.horizontalNeighbors();
                for (Pixel n : neighbors) {
                    neighborEdges.add(new WeightedEdge<>(p, n, n.eng()));
                }
            }
            else {
                //use Pixel.verticalNeighbors()
                List<Pixel> neighbors = p.verticalNeighbors();
                for (Pixel n : neighbors) {
                    neighborEdges.add(new WeightedEdge<>(p, n, n.eng()));
                }
            }

            return neighborEdges;
        }

        @Override
        public double estimatedDistanceToGoal(Pixel s, Pixel goal) {
            //heuristic=0
            return 0;
        }
    }


    /** Pixel Class **/

    private final class Pixel {


        private int x;
        private int y;
        private double eng;


        Pixel(int x, int y) {
            this.x = x;
            this.y = y;
            this.eng = energy(x, y);
        }
        Pixel(int x, int y, double eng) {
            this.x = x;
            this.y = y;
            this.eng = eng;
        }

        int x() {
            return x;
        }

        int y() {
            return y;
        }

        double eng() {
            return eng;
        }

        List<Pixel> verticalNeighbors() {
            List<Pixel> neighbors = new ArrayList<>();
            if (eng == 500){
                return neighbors;
            }
            if (y == -1){ //vertical start node
                for (int i=0; i<W; i++){
                    neighbors.add(new Pixel(i, 0));
                }
            }
            else if (y == H-1) { //vertical end node
                //neighbors.add(new Pixel(x, y, 500)); //change to H! // put start node?
                return neighbors;
            }
            else {
                if (x >= 1) {
                    neighbors.add(new Pixel(x-1, y+1));
                }
                neighbors.add(new Pixel(x, y+1));
                if (x <= W-2) {
                    neighbors.add(new Pixel(x+1, y+1));
                }
            }
            return neighbors;
        }


        List<Pixel> horizontalNeighbors() {
            List<Pixel> neighbors = new ArrayList<>();
            if (eng == 500){
                return neighbors;
            }
            if (x == -1){ //horizontal start node
                for (int i=0; i<H; i++){
                    neighbors.add(new Pixel(0, i));
                }
            }
            else if (x == W-1){ //horizontal end node
                //neighbors.add(new Pixel(-1, -1)); //change to W!
                //neighbors.add(new Pixel(x, y, 500)); //change to H! // put start node?

                return neighbors;
            }
            else {
                if (y >= 1) {
                    neighbors.add(new Pixel(x+1, y-1));
                }
                neighbors.add(new Pixel(x+1, y));
                if (y <= H-2) {
                    neighbors.add(new Pixel(x+1, y+1));
                }
            }
            return neighbors;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pixel pixel = (Pixel) o;
            return Double.compare(pixel.x, x) == 0 &&
                    Double.compare(pixel.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return String.format("Pixel energy: %f, x: %d, y: %d", eng, x, y);
        }


    }
}
