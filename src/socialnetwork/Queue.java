package socialnetwork;

public class Queue {

    private Vector data;

    public Queue() {
        data = new Vector(9);
    }

    public void push(Object o) {

        data.addLast(o);
    }


    public Object pop() {
        if (data.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        Object value = data.getFirst();
        data.removeFirst();
        return value;
    }


    public Object top() {
        if (data.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return data.getFirst();
    }


    public int size() {
        return data.size();
    }


    public boolean empty() {
        return data.isEmpty();
    }
}

