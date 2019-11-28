package astar;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @see ShortestPathsSolver for more method documentation
 */
public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private TreeMapMinPQ<Vertex> minPQ;
    private HashMap<Vertex, Double> distTo;
    private List<Vertex> solutionList;
    private HashMap<Vertex, Vertex> edgeTo;

    private Vertex goal;
    private Stopwatch sw;
    private double timeSpent;
    private int numStateExplored = 0;
    private double solutionWeight= 0;

    /**
     * Immediately solves and stores the result of running memory optimized A*
     * search, computing everything necessary for all other methods to return
     * their results in constant time. The timeout is given in seconds.
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) { //boolean option) {
        minPQ = new TreeMapMinPQ<>();
        solutionList = new LinkedList<>();
        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        sw = new Stopwatch();
        //double timeout = 100;
        goal = end;
        minPQ.add(start, 0);
        distTo.put(start, 0.0);
        edgeTo.put(start, null);
        Vertex next = start;
        Vertex prev;
        double newDistance;
        while (!minPQ.isEmpty()) {
            next = minPQ.removeSmallest();
            numStateExplored++;

            if (next == null) {
                timeSpent = sw.elapsedTime();
                outcome = SolverOutcome.UNSOLVABLE;
                return;
            }
            if (next.equals(goal)) {
                outcome = SolverOutcome.SOLVED;
                break;
            }
            for (WeightedEdge<Vertex> each : input.neighbors(next)) {
                if (!distTo.containsKey(each.to())) { //insert vertex if not present in lists
                    newDistance =distTo.get(each.from()) + each.weight();
                    minPQ.add(each.to(), newDistance + input.estimatedDistanceToGoal(each.to(), goal));
                    distTo.put(each.to(), newDistance);
                    edgeTo.put(each.to(), each.from());
                }
                else if (distTo.get(each.to()) > distTo.get(each.from()) + each.weight()) { //update if better
                    newDistance =distTo.get(each.from()) + each.weight();
                    minPQ.changePriority(each.to(), newDistance + input.estimatedDistanceToGoal(each.to(), goal));
                    distTo.replace(each.to(), newDistance);
                    edgeTo.replace(each.to(), each.from());
                }
            }
            if (timeout < sw.elapsedTime()) {
                outcome = SolverOutcome.TIMEOUT;
                return;
            }
        }
        solutionWeight = distTo.get(next);
        solutionList.add(0, next); //push front
        prev = next;
        while (!prev.equals(start)) {
            prev = edgeTo.get(prev);
            if (prev.equals(start)) {
                break;
            }
            solutionList.add(0, prev); //push front
        }
        //outcome = SolverOutcome.SOLVED;
        timeSpent = sw.elapsedTime();
        // if (outcome == SolverOutcome.SOLVED) {
        //            solutionWeight = distTo.get(next);
        //            solutionList.add(0, next); //push front
        //            prev = next;
        //            while (!prev.equals(start)) {
        //                prev = edgeTo.get(prev);
        //                solutionList.add(0, prev); //push front
        //            }
        //            //outcome = SolverOutcome.SOLVED;
        //            timeSpent = sw.elapsedTime();
        //}
        //        else {
        //            outcome = SolverOutcome.UNSOLVABLE;
        //        }

    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solutionList;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight; //CHANGE THIS
    }

    /** The total number of priority queue removeSmallest operations. */
    @Override
    public int numStatesExplored() {
        return numStateExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
