import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.function.BiFunction;
import java.util.stream.Stream;

// -------------------------------------------------------

/**
 *
 * The abstract class for the four hash tables we will implement. The
 * file HashTableTest has four test cases that should produce the same
 * information as Figures 5.5, 5.11, 5.13, and 5.18 in the book. You
 * should implement many more test cases!!!
 *
 */
abstract class HashTable {
    abstract int getCapacity();
    abstract void setCapacity(int capacity);

    abstract void insert (int key);
    abstract void delete (int key);
    abstract boolean search (int key);

    abstract void rehash ();
}

// -------------------------------------------------------

/**
 *
 * An implementation of a hash table that uses separate chaining. The
 * constructor should take one arguments 'hf' of type HashFunction.
 * The bound should be extracted from the HashFunction and an ArrayList
 * of that bound should be created in the constructor. Each
 * entry in that ArrayList should be initialized to an empty linked list.
 * The three methods to insert, delete, and search
 * should be implemented as described in the book. You should also
 * implement a method rehash that resizes the hash table to the next prime
 * after doubling the capacity.
 */
class HashSeparateChaining extends HashTable {
    // finish the implementation and remove the abstract annotation

    private int capacity;
    private HashFunction hf;
    private ArrayList<LinkedList<Integer>> chains;

    HashSeparateChaining(HashFunction hf) {
        this.capacity = hf.getBound();
        this.hf = hf;
        this.chains = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++)
            chains.add(i, new LinkedList<>());
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < capacity; i++) {
            result += "chain[" + i + "] = ";
            result += chains.get(i).toString();
            result += "\n";
        }
        return result;
    }

    @Override
    int getCapacity() {
        return this.capacity;
    }

    @Override
    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    void insert(int key) {
        int set = hf.apply(key);
        chains.get(set).add(key);
       // setCapacity(capacity++);
    }

    @Override
    void delete(int key){
        try {
            int set = hf.apply(key);
            int indexofList = chains.get(set).indexOf(key);
            chains.get(set).remove(indexofList);
            //    setCapacity(capacity--);
        } catch(Exception e) {
        }
    }

    @Override
    boolean search(int key) {
        int set = hf.apply(key);
        if (chains.get(set).contains(key)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    void rehash() {
        BigInteger otherCapacity = BigInteger.valueOf(2 * capacity);
        // use next probable prime
        int newCap = Integer.valueOf(String.valueOf(otherCapacity.nextProbablePrime()));
        ArrayList<LinkedList<Integer>> newChain = new ArrayList<>(newCap);
        hf.setBound(newCap);
        for (int j = 0; j < newCap; j++) {
            newChain.add(j, new LinkedList<>());
        }
        for(int i = 0; i < capacity;i++){
            for(int m = 0; m < chains.get(i).size();m++){
                int key2 = chains.get(i).get(m);
                int newHash = hf.apply(key2);
                newChain.get(newHash).add(key2);
            }
        }
        setCapacity(newCap);
    chains = newChain;
    }
}

// -------------------------------------------------------

/**
 *
 * Before implementing the next three variants of hash tables, we will
 * implement a small class hierarchy of hash tables entries. There are
 * three kinds of entries: an entry that contains an 'int' value, an
 * entry that is empty and hence available, and an entry that is
 * marked as deleted. A deleted entry is available for insertions but
 * cannot be marked as empty. See the book for details.
 *
 */

abstract class Entry {
    static Entry EMPTY = new Empty();
    static Entry DELETED = new Deleted();
    abstract boolean available ();
}

class Empty extends Entry {
    boolean available () { return true; }
    public String toString () { return ""; }
}

class Deleted extends Entry {
    boolean available () { return true; }
    public String toString () { return "X"; }
}

class Value extends Entry {
    private int i;
    Value (int i) { this.i = i; }
    int get () { return this.i; }
    boolean available () { return false; }
    public String toString () { return String.valueOf(this.i); }
}

// -------------------------------------------------------

/**
 *
 * This class, although abstract, will have a constructor and an
 * implementation of each of the three methods: insert, delete, and
 * search.
 *
 * The constructor should take two arguments: an
 * argument 'hf' of type HashFunction, and an argument 'f' of type
 * BiFunction<Integer,Integer,Integer>. The constructor should create
 * an ArrayList of the given size and initialize each slot in that
 * array with an Empty entry. The constructor should also define a
 * BiFunction<Integer,Integer,Integer> called 'dhf' as follows:
 *
 *   this.dhf = (key,index) -> (hf.apply(key) + f.apply(key,index)) % size;
 *
 * This auxiliary hash function applies the regular hash function 'hf'
 * and then modifies the result using the BiFunction 'f' that depends
 * on the current index in the hash table. The subclasses for linear
 * probing, quadratic probing, and double hashing, will each pass an
 * appropriate function 'f' to control the auxiliary function.
 *
 * Each of the methods insert, delete, and search takes a value 'key'
 * to process. Each of the methods will involve a loop that iterates
 * over all the positions in the ArrayList. At iteration 'i', an
 * integer position is calculated using:
 *
 *   int h = dhf.apply(key,i)
 *
 * For insert: if the position 'h' is available then the value 'key'
 * is stored.
 *
 * For delete: if position 'h' has an entry of kind 'Value' and if the
 * stored integer is identical to 'key' then the entry is marked as
 * deleted.
 *
 * For search: if position 'h' is Empty then the item is not found. If
 * position 'h' has an entry of kind 'Value' and if the stored value
 * is identical to 'key' then the item is found.
 *
 */

abstract class HashTableAux extends HashTable {
    private int capacity;
    private HashFunction hf;
    private BiFunction<Integer, Integer, Integer> f;
    private BiFunction<Integer, Integer, Integer> dhf;
    private ArrayList<Entry> slots;

    HashTableAux(HashFunction hf, BiFunction<Integer, Integer, Integer> f) {
        this.capacity = hf.getBound();
        this.hf = hf;
        this.f = f;
        this.dhf = (key, index) -> Math.floorMod(hf.apply(key) + f.apply(key, index), capacity);
        this.slots = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) this.slots.add(i, Entry.EMPTY);
    }

    // finish the implementation

    public String toString() {
        String result = "";
        for (int i = 0; i < capacity; i++) {
            result += "entry[" + i + "] = ";
            result += slots.get(i).toString();
            result += "\n";
        }
        return result;
    }

    @Override
    int getCapacity() {
        return this.capacity;
    }

    @Override
    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    void insert(int key) {
        for (int i = 0; i < capacity; i++) {
            int pos = dhf.apply((Integer)key, i);
            if (slots.get(pos).available()) {
                slots.set(pos, new Value(key));
                return;
            }
        }
        rehash();
        insert(key);
    }

    @Override
    void delete(int key) {
        for (int i = 0; i < capacity; i++) {
            int pos = dhf.apply((Integer) key, i);
            if (slots.get(pos) != Entry.EMPTY && slots.get(pos) != Entry.DELETED && Integer.parseInt(slots.get(pos).toString()) == key) {
                slots.set(pos, Entry.DELETED);
                return;
            }

        }

    }

    @Override
    boolean search(int key) {
        for (int i = 0; i < capacity; i++) {
            int pos = dhf.apply(key, i);
            if (slots.get(pos) != Entry.EMPTY && slots.get(pos) != Entry.DELETED && Integer.parseInt(slots.get(pos).toString()) == key) {
                    return true;
                }
            }
            /*
            if (slots.get(pos) instanceof Empty) {
                return false;
            }else if(slots.get(pos) instanceof Value){
                Value val = (Value) slots.get(pos);
                if(val.get() == key){
                    return true;
                }
            }
*/
        return false;
    }

    @Override
    void rehash() {
        BigInteger doubleCap = BigInteger.valueOf(capacity * 2);
        capacity = Integer.valueOf(String.valueOf(doubleCap.nextProbablePrime()));
        hf.setBound(capacity);
        dhf = (key, index) -> Math.floorMod(hf.apply(key) + f.apply(key, index), capacity);
        ArrayList<Entry> newSlots = slots;
        slots = new ArrayList<Entry>(capacity);
        for (int i = 0; i < capacity; i++) {
            slots.add(i, Entry.EMPTY);
        }
            /*
            for (int i = 0; i < capacity; i++) {
                    if(!slots.get(i).available()) {
                        int pos = hf.apply(Integer.valueOf(String.valueOf(slots.get(i))));
                        newSlots.set(pos, new Value(Integer.valueOf(String.valueOf(slots.get(i)))));
                        System.out.println(pos);
                    }
        }*/
            setCapacity(capacity);
        for (Entry e : newSlots) {
            if (e instanceof Value) {
                Value val = (Value) e;
                insert(val.get());
            }
        }
    }
}

// -------------------------------------------------------

class HashLinearProbing extends HashTableAux {
    HashLinearProbing(HashFunction hf){
            super(hf ,(key, index) -> index);
    }


}


// -------------------------------------------------------

class HashQuadProbing extends HashTableAux {
    // write the constructor and uncomment
    HashQuadProbing(HashFunction hf) {
        // write the constructor and uncomment
        super(hf ,(key, index) -> index * index);
    }
}


// -------------------------------------------------------


class HashDouble extends HashTableAux {
    // write the constructor and uncomment
    HashDouble(HashFunction hf, HashFunction hf2) {
        // write the constructor and uncomment
        super(hf ,(key, index) -> index * hf2.apply(key));
    }
}


// -------------------------------------------------------
