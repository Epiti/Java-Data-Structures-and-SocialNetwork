package socialnetwork;

public class Vector
{
    private Object data[];
    private int count;

    public Vector(int capacity)
    {
        data = new Object[capacity];
        count = 0;
    }

    public int capacity(){
        return data.length;
    }


    public int size()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return count == 0;

    }

    public Object get(int index)
    {
        if(index < 0 || index >= count){
            throw new IndexOutOfBoundsException ("Index out of bounds: " + index);
        }

        return data[index];
    }

    public void set(int index, Object obj)
    {
        if(index < 0 || index >= data.length){
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        data[index] = obj;
        if (index >= count) {
            count = index + 1;
        }
    }

    public boolean contains(Object obj) // was boolean
    {
        for(int i=0;i<count;i++)
        {
            if(data[i].equals(obj)){  //two Integer objects with same value might not be ==
                return true;
            }
        }

        return false;
    }

    public void addFirst(Object item)
    {
        if(count == data.length){

            throw new IllegalStateException("Vector is full");
        }
        for(int i=count - 1; i >= 0;i--){
            data[i+1] = data[i];

        }
        data[0] = item;
        count++;


    }

    public void addLast(Object o)
    {
        if(count == data.length){
            throw new IllegalStateException("Vector is full");
        }
        data[count] = o;
        count++;
    }

	/*
	public boolean binarySearch(Object key)
	{
	int start = 0;
	int end = count - 1;
	while(start <= end)
	{
		int middle = (start + end + 1) / 2;
		if(key < data[middle]) end = middle -1;
		else if(key > data[middle]) start = middle + 1;
		else return true;
	}
	return false;
	}
	*/

    public Object getFirst()
    {
        if (isEmpty()) throw new IllegalStateException("Vector is empty");
        return data[0];


    }

    public Object getLast()
    {
        if (isEmpty()) throw new IllegalStateException("Vector is empty");
        return data[count-1];

    }

    public void removeLast()
    {
        if(isEmpty()) {
            throw new IllegalStateException ("Vector is empty");
        }
        data[count-1] = null;
        count--;
    }

    public void removeFirst()
    {
        if(isEmpty()){
            throw new IllegalStateException("Vector is empty");
        }
        for(int i = 0; i < count - 1; i++){
            data[i] = data[i+1];
        }
        data[count-1] = null;
        count--;

    }


    public String toString(){
        String newString = "[";
        for(int i=0;i<count;i++){
            newString = newString + " " + data[i];  // n^2 (copies previous string)

        }
        newString = newString + "]";

        return newString;
    }

    public void reverse(){
        int first = 0;
        int last  = count -1;
        while(first < last){
            Object keepFirst = data[first];
            data[first] = data[last];
            data[last] = keepFirst;
            first++;
            last--;
        }
    }

    public Vector repeat(){
        Vector newVector = new Vector(count * 2);  // n for time complexity (size of vector)
        for(int i=0;i<count;i++){
            newVector.data[newVector.count] = data[i];
            newVector.count =  newVector.count + 1;

            newVector.data[newVector.count] = data[i];
            newVector.count =  newVector.count + 1;
        }
        return newVector;
    }



    public Vector interleave(Vector v2){

        if(this.count != v2.count){
            throw new IllegalStateException("Vectors don't have the same size"); //  better  throw an error??
        }
        Vector newVector = new Vector(count * 2);
        for(int i=0;i<count;i++){
            newVector.data[newVector.count] = this.data[i];
            newVector.count ++;

            newVector.data[newVector.count] = v2.data[i];
            newVector.count =  newVector.count + 1;

        }
        return newVector;
    }



}




