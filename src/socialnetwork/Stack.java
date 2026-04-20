package socialnetwork;

import java.util.EmptyStackException;

public class Stack {

    private Vector data ;
    public Stack ()
    {
        data = new Vector(7);
    }
    public void push ( Object o )
    {
        data.addLast(o);
    }
    public Object pop ()
    {
        if(data.isEmpty())
        {
            throw new IllegalStateException("Stack is empty");
        }
        Object simba = data.getLast();;
        data.removeLast();
        return simba;

    }
    public Object top ()
    {
        if(data.isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        return data.getLast();

    }
    public int size ()
    {
        return data.size();

    }
    public boolean empty ()
    {
        return data.isEmpty();
    }

}

