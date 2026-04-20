package socialnetwork;

public class Dictionary {

    private class DictionaryPair implements Comparable {
        private Object key;
        private Object value;

        public DictionaryPair(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }


        public int compareTo(Object o) {
            DictionaryPair p = (DictionaryPair) o;
            return ((Comparable) key).compareTo(p.getKey());
        }
    }

    private Vector data;  // stores DictionaryPair objects
    private int count;    // number of used elements in Vector

    public Dictionary() {
        data = new Vector(10);
        count = 0;
    }

    public int size() {
        return count;
    }

    public int findPosition(Object key) {
        for (int i = 0; i < count; i++) {
            DictionaryPair pair = (DictionaryPair) data.get(i);
            if (pair.getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }


    public void add(Object key, Object value) {
        if (count == data.capacity()) {
            throw new IllegalStateException("Dictionary is full");
        }
        int position = findPosition(key);

        if (position >= 0) {

            DictionaryPair pair = (DictionaryPair) data.get(position);
            pair.setValue(value);
            return;
        }

        // key does not exist,  add new pair
        data.set(count, new DictionaryPair(key, value));
        count++;
    }

    public Object find(Object key) {
        int position = findPosition(key);
        if (position >= 0) {
            return ((DictionaryPair) data.get(position)).getValue();
        }
        return null;
    }

    public Object getValueAt(int index) {
        if (index < 0 || index >= count) return null;
        DictionaryPair pair = (DictionaryPair) data.get(index);
        return pair.getValue();
    }

    public void removeKey(Object key) {
        int position = findPosition(key);
        if (position < 0) return; // key not found

        // shift left
        for (int i = position; i < count - 1; i++) {
            data.set(i, data.get(i + 1));
        }


        data.set(count - 1, null); // clear last slot

        count--;
    }
}

