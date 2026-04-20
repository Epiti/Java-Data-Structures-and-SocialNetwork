package socialnetwork;

public class LinkedList {

    private class ListElement {
        private Object el1;
        private ListElement el2;



        public ListElement(Object el, ListElement nextElement) {
            el1 = el;
            el2 = nextElement;
        }

        public ListElement(Object el) {
            this(el, null);
        }

        public Object first() {
            return el1;
        }

        public ListElement rest() {
            return el2;
        }

        public void setFirst(Object value) {
            el1 = value;
        }

        public void setRest(ListElement value) {
            el2 = value;
        }
    }

    private ListElement head;
    private int count;

    public LinkedList() {
        head = null;
        count = 0;
    }

    public void addFirst(Object o) {

        head = new ListElement(o, head);
        count++;
    }

    public Object getFirst() {
        if (head == null) throw new IllegalStateException("List is empty");
        return head.first();
    }


    public Object get(int n) {
        if (n < 0 || n >= count) throw new IndexOutOfBoundsException();
        ListElement d = head;
        while (n > 0) {
            d = d.rest();
            n--;
        }
        return d.first();
    }

    public Object getIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index");
        }

        ListElement current = head;
        int i = 0;

        while (current != null && i < index) {
            current = current.rest();
            i++;
        }

        if (current == null) {
            throw new IndexOutOfBoundsException("Index too large");
        }

        return current.first();
    }

    public String toString() {
        String s = "(";
        ListElement d = head;
        while (d != null) {
            s += d.first().toString();
            s += " ";
            d = d.rest();
        }
        s += ")";
        return s;
    }

    public int size(){
        return count;
    }


    public Object getLast() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }

        ListElement current = head;
        while (current.rest() != null) {
            current = current.rest();
        }

        return current.first();
    }

    public void addLast(Object o) {
        ListElement newNode = new ListElement(o);

        if (head == null) {
            head = newNode;
            count ++;
            return;
        }

        ListElement current = head;
        while (current.rest() != null) {
            current = current.rest();
        }

        current.setRest(newNode);
        count++;
    }

    public void removeFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        head = head.rest();
        count--;
    }

    public void removeLast() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }

        // only 1 element
        if (head.rest() == null) {
            head = null;
            count --;
            return;
        }

        ListElement current = head;
        while (current.rest().rest() != null) {
            current = current.rest();
        }


        current.setRest(null);
        count--;
    }


    public boolean contains(Object o) {
        ListElement current = head;

        while (current != null) {
            if (current.first().equals(o)) {
                return true;
            }
            current = current.rest();
        }

        return false;
    }


    public void addSorted(Comparable o) {

        //empty list
        if (head == null) {
            head = new ListElement(o, null);
            count++;
            return;
        }

        Comparable firstValue = (Comparable) head.first();


        if (firstValue.compareTo(o) > 0) {
            head = new ListElement(o, head);   // insert at front
            count++;
            return;
        }

        // insert in middle/end
        ListElement current = head;

        while (current.rest() != null) {
            Comparable nextValue = (Comparable) current.rest().first();

            if (nextValue.compareTo(o) >= 0) break;

            current = current.rest();
        }

        current.setRest(new ListElement(o, current.rest()));
        count++;
    }


    public void fropple() {}



}

