package heap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<PriorityNode> items;
    private HashMap<Integer, Integer> map; //key, index in heap
    // private int length;
    // private PriorityNode min;


    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        items.add(new PriorityNode(null, -1));
        map = new HashMap<>(0);
    }

    /*
    Here's a helper method and a method stub that may be useful. Feel free to change or remove
    them, if you wish.
     */

    /**
     * A helper method to create arrays of T, in case you're using an array to represent your heap.
     * You shouldn't need this if you're using an ArrayList instead.
     */
    @SuppressWarnings("unchecked")
    private T[] makeArray(int newCapacity) {
        return (T[]) new Object[newCapacity];
    }

    /**
     * A helper method for swapping the items at two indices of the array heap.
     */
    private void swap(int a, int b) {
        PriorityNode temp = items.get(a);
        items.set(a, items.get(b));
        items.set(b, temp);
        /*
        if (!items.get(b).isValid()){
            items.remove(b);
        }
        else if (!items.get(a).isValid()){
            items.remove(a);
        }

         */

    }

    private int swim(int k) {
        PriorityNode child;
        PriorityNode parent;
        while (k > 1 && this.items.get(k).getPriority() < this.items.get(k/2).getPriority()) { //?
            swap(k, k/2);
            k /= 2;
        }
        return k;
    }

    private int sink(int k) {
        int length = this.size();
        while (2*k <= length) {
            int left = 2*k;
            int right = 2*k+1;
            double parentPriority = this.items.get(k).getPriority();
            if (this.items.get(k * 2).getPriority()< parentPriority){
                if (right<=length && this.items.get(left).getPriority() > this.items.get(right).getPriority()) {
                    swap(right, k);
                    k = right;
                }
                else {
                    swap(left, k);
                    k = left;
                }
            }
            else {
                return k;
            }

        }
        return k;
    }
    /*
    private void remove(int k) {
        swap(k, length);
        swim(k);
        sink(k);
    }
    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeapOrdered(int k) {
        int length = this.size();
        if (k > length) {return true;}
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= length && items.get(k).getPriority()>items.get(left).getPriority())  {return false;}
        if (right <= length && items.get(k).getPriority()>items.get(right).getPriority()) {return false;}
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

*/

    /**
     * Adds an item with the given priority value.
     * Assumes that item is never null.
     * Runs in O(log N) time (except when resizing).
     * @throws IllegalArgumentException if item is already present in the PQ
     */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        PriorityNode newNode = new PriorityNode(item, priority);
        this.items.add(newNode);
        int index = swim(this.size());
        map.put(newNode.hashCode(), index); //putifnotpresent
        return;
        }

    /**
     * Returns true if the PQ contains the given item; false otherwise.
     * Runs in O(log N) time.
     */
    @Override
    public boolean contains(T item) {
        //System.out.println(item.hashCode());
        return map.containsKey(item.hashCode());
    }

    /**
     * Returns the item with the smallest priority.
     * Runs in O(log N) time.
     * @throws NoSuchElementException if the PQ is empty
     */
    @Override
    public T getSmallest() {
        if (this.size() < 1) {
            throw new NoSuchElementException();
        }
        return this.items.get(1).getItem();
    }

    /**
     * Removes and returns the item with the smallest priority.
     * Runs in O(log N) time (except when resizing).
     * @throws NoSuchElementException if the PQ is empty
     */
    @Override
    public T removeSmallest() {
        int length = this.size();
        if (length < 1) {
            throw new NoSuchElementException();
        }
        swap(1, length); //!!?
        PriorityNode old = this.items.remove(length);
        map.remove(old.hashCode()); //collision?
        sink(1);
        return old.getItem();
    }

    /**
     * Changes the priority of the given item.
     * Runs in O(log N) time.
     * @throws NoSuchElementException if the item is not present in the PQ
     */
    @Override
    public void changePriority(T item, double priority) {
        int subjectInd = map.get(item.hashCode());
        PriorityNode subject = items.get(subjectInd);
        subject.setPriority(priority);
        subjectInd = sink(swim(subjectInd));
        map.replace(item.hashCode(), subjectInd);
     }

    /**
     * Returns the number of items in the PQ.
     * Runs in O(log N) time.
     */
    @Override
    public int size() {
        return items.size()-1; //?
    }

    public double[] returnList() {
        double[] arrayOut;
        arrayOut = new double[items.size()];
        for (int i=0; i<items.size(); i++) {
            arrayOut[i] = items.get(i).getPriority();
        }
        return arrayOut;
    }


    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;
        private boolean valid;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
            this.valid = true;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        public int compareHash(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Integer.compare(this.hashCode(), other.hashCode());
        }

        public void setValid(boolean isTrue) {
            this.valid = isTrue;
        }

        public boolean isValid() {
            return this.valid;
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

}
