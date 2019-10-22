package heap;

/**
 * Created by hug.
 */
public class PrintHeapDemo {

    /**
     * Prints out a vey basic drawing of the given array of Objects assuming it
     * is a heap starting at index 1. You're welcome to copy and paste code
     * from this method into your code with a citation.
     */
    public static void printSimpleHeapDrawing(double[] heap) {
        int depth = ((int) (Math.log(heap.length) / Math.log(2)));
        int level = 0;
        int itemsUntilNext = (int) Math.pow(2, level);
        for (int j = 0; j < depth; j++) {
            System.out.print(" ");
        }

        for (int i = 1; i < heap.length; i++) {
            System.out.printf("%d ", (int) heap[i]);
            if (i == itemsUntilNext) {
                System.out.println();
                level++;
                itemsUntilNext += Math.pow(2, level);
                depth--;
                for (int j = 0; j < depth; j++) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }

    /**
     * Prints out a drawing of the given array of Objects assuming it is a heap
     * starting at index 1. You're welcome to copy and paste code from this
     * method into your code with a citation.
     */
    public static void printFancyHeapDrawing(Object[] items) {
        String drawing = fancyHeapDrawingHelper(items, 1, "");
        System.out.println(drawing);
    }

    /** Recursive helper method for toString. */
    private static String fancyHeapDrawingHelper(Object[] items, int index, String soFar) {
        if (index >= items.length || items[index] == null) {
            return "";
        } else {
            String toReturn = "";
            int rightIndex = 2 * index + 1;
            toReturn += fancyHeapDrawingHelper(items, rightIndex, "        " + soFar);
            if (rightIndex < items.length && items[rightIndex] != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + items[index] + "\n";
            int leftIndex = 2 * index;
            if (leftIndex < items.length && items[leftIndex] != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += fancyHeapDrawingHelper(items, leftIndex, "        " + soFar);
            return toReturn;
        }
    }

    public static void main(String[] args) {
        Integer[] example = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        ArrayHeapMinPQ minpq = new ArrayHeapMinPQ();
        minpq.add("3ali", 9);
        double[] out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.add("4veli", 8);
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.add("2deli", 6);
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.add("5ali", 5);
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.add("1veli", 4);
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.add("6deli", 6);
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.add("2ali", 2);
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.add("8veli", 3);
        out = minpq.returnList();
        printSimpleHeapDrawing(out);

        minpq.add("10deli", 2);

        out = minpq.returnList();
        printSimpleHeapDrawing(out);

        minpq.changePriority("3ali", 1);
        out = minpq.returnList();
        printSimpleHeapDrawing(out);

        minpq.changePriority("1veli", 7); //fails - infinite loop
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        System.out.println(minpq.getSmallest());
        System.out.println("null");


        minpq.removeSmallest();
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.removeSmallest();
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.removeSmallest();
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.removeSmallest();
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.removeSmallest();
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.removeSmallest();
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.removeSmallest();
        out = minpq.returnList();
        printSimpleHeapDrawing(out);
        minpq.removeSmallest();
        out = minpq.returnList();
        printSimpleHeapDrawing(out);

        // printFancyHeapDrawing(example);
    }
}
