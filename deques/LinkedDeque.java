package deques;

public class LinkedDeque<T> implements Deque<T> {
    private int size;
    private Node sentinel;

    public LinkedDeque() {
        this.sentinel = new Node(null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        size = 0;
    }

    private class Node {
        private T value;
        private Node next;
        private Node prev;

        Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    public void addFirst(T item) {
        //anything???
        Node newNode = new Node(item);
        newNode.next = sentinel.next;
        sentinel.next = newNode;
        newNode.prev = sentinel;
        newNode.next.prev = newNode;


        size += 1;
    }

    public void addLast(T item) {
        Node newNode = new Node(item);
        newNode.prev = sentinel.prev;
        sentinel.prev = newNode;  //?
        newNode.next = sentinel;
        newNode.prev.next = newNode;
        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node removed = sentinel.next;
        sentinel.next = removed.next;
        sentinel.next.prev = sentinel;
        removed.next = null;
        removed.prev = null;
        size -= 1;

        return removed.value;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node removed = sentinel.prev;
        sentinel.prev = removed.prev;
        sentinel.prev.next = sentinel;
        removed.next = null;
        removed.prev = null;
        size -= 1;
        return removed.value;
    }

    public T get(int index) {
        if ((index > size) || (index < 0)) {
            return null;
        }
        Node current = this.sentinel;
        if (index <= size/2) {
            for (int i=0; i<index+1; i++) {
                current = current.next;
            }
        }
        else {
            for (int i=0; i<size-index; i++) {
                current = current.prev;
            }
        }
        return current.value;
    }

    public int size() {
        return size;
    }
}
