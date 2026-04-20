package socialnetwork;

public class CircularVector {

    private Vector data ;
    private int first ;
    private int count ;
    public CircularVector ()
    {
        count = 0;
        first = 0;
        data = new Vector (5);
    }

    public int size ()
    {

        return count ;
    }


    public void AddFirst(Object element)
    {
        if (count == data.capacity()) {
            throw new IllegalStateException("CircularVector full");
        }

        first = (first - 1 + data.capacity()) % data.capacity();
        data.set(first, element);
        count++;
    }


    public void AddLast ( Object element )
    {
        if (count == data.capacity()) {
            throw new IllegalStateException("CircularVector is full");
        }
        int last = (first + count)  % data.capacity();
        data.set(last, element);
        count++;

    }
    public Object GetFirst ()
    {
        return data.get(first);
    }
    public Object GetLast ()
    {
        int  lastElement = (first + count-1)  % data.capacity();
        return data.get(lastElement);
    }
    public void RemoveFirst ()
    {
        if( count > 0)
        {
            first = ( first +1)% data.capacity();
            count --;
        }
    }
    public void RemoveLast ()
    {
        if(count > 0)
        {
            int last = (first + count-1)% data.capacity();
            data.set(last,null);
            count --;
        }

    }
    public String toString ()
    {
        String s = "[";
        for (int i =0; i < count ; i ++)
        {
            int index = ( first + i ) % data.capacity();
            s += data . get ( index ).toString ();
            s += " ";
        }
        s += "]";
        return s ;
    }

}

