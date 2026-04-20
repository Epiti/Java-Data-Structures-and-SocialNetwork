package socialnetwork;

public class PriorityQueue {


    private class PriorityPair implements Comparable {
        private Object element;
        private Comparable priority;

        public PriorityPair(Object element, Comparable priority) {
            this.element = element;
            this.priority = priority;
        }

        @Override
        public int compareTo(Object o) {
            PriorityPair secondPair = (PriorityPair) o;
            return this.priority.compareTo(secondPair.priority);
        }
    }




    private LinkedList data;


    public PriorityQueue() {
        data = new LinkedList();
    }


    public void push(Object o, Comparable priority) {
        PriorityPair pair = new PriorityPair(o, priority);
        data.addSorted(pair);
    }


    public Object pop() {
        if (data.size() == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        PriorityPair firstPair = (PriorityPair) data.getFirst();
        data.removeFirst();
        return firstPair.element;
    }


    public Object top() {
        if (data.size() == 0) {
            throw new IllegalStateException("Queue is empty");
        }


        PriorityPair firstPair = (PriorityPair) data.getFirst();
        return firstPair.element;
    }
}


