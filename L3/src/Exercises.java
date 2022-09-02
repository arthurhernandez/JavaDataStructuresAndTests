import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// ------------------------------------------------------------------------
// Solve the followign 5 eercises using the five approaches.
// Examples and explanations of the five approaches can be found in Main.java.
// ------------------------------------------------------------------------

public class Exercises {
    private static List<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,2,8,8,8));

    // ------------------------------------------------------------------------
    // Exercise I: multiply the elements in the list
    //
    // Thinking point:
    // Take note that there isn't a method using the map-reduce approach. Why?
    // ------------------------------------------------------------------------

    static int mul1 () {
        //for loop approach
        int sumFor = 1;
        for (int i=0; i<ints.size(); i++) {
            int k = ints.get(i);
            sumFor = sumFor * k;
        }
        return sumFor;
    }

    static int mul2(){
        //foreach approach
        int sumForeach = 1;
            for (int k : ints) {
                sumForeach *= k;
            }
        return sumForeach;
    }

    static int mul3(){
        //iterator approach
            ListIterator<Integer> iterator = ints.listIterator();
            int sumIterator = 1;
            while (iterator.hasNext()) {
                int k = iterator.next();
                sumIterator *= k;
            }
        return sumIterator;
    }

    static int mul5 () {
	//reduce approach
            int sumMapreduce = ints.stream().reduce(1, (sum,k) -> sum * k);
        return sumMapreduce;
    }

    // ------------------------------------------------------------------------
    // Exercise II: check if all elements in the list are even
    // ------------------------------------------------------------------------

    static boolean even1 () {
        //for loop approach
        boolean EorO = true;
        for (int i=0; i<ints.size(); i++) {
            boolean k = ints.get(i) % 2 == 0;
            EorO = EorO && k;
        }
        return EorO;

    }

    static boolean even2 () {
        //foreach approach
        boolean EorO = true;
        for (int k : ints) {
            EorO = EorO && (k % 2 == 0);
        }
        return EorO;

    }

    static boolean even3 () {
        //iterator approach
        ListIterator<Integer> iterator = ints.listIterator();
        boolean EorO = true;
        while (iterator.hasNext()) {
            int k = iterator.next();
            EorO = EorO && (k % 2 == 0);
        }
        return EorO;
    }

    static boolean even4 () {
        //map-reduce approach
        boolean sumMapreduce = ints.stream().map(k -> k % 2 == 0 ? true : false).reduce(true, (EorO, k) -> EorO && k);
        return sumMapreduce;
    }
    static boolean even5 () {
	//reduce approach
        boolean sumMapreduce = ints.stream().reduce(true, (EorO, k) -> EorO && (k % 2 == 0) , (EorO, k) -> EorO && k);
        return sumMapreduce;
    }

    // ------------------------------------------------------------------------
    // Exercise III: compute the maximum number in the list
    //
    // Thinking point:
    // Take note that there isn't a method using the map-reduce approach. Why?
    // ------------------------------------------------------------------------

    static int max1 () {
        int max = 0;
        for (int i=0; i<ints.size(); i++) {
            int k = ints.get(i);
            if(max < k){
             max = k;
            }
        }
        //for loop approach
        return max;
    }

    static int max2 () {
        //foreach approach
        int max = 0;
        for (int k : ints) {
            if(k > max){
             max = k;
            }
        }
        return max;
    }

    static int max3 () {
        //iterator appraoch
        ListIterator<Integer> iterator = ints.listIterator();
        int max = 0;
        while (iterator.hasNext()) {
            int k = iterator.next();
            if (max < k) {
                max = k;
            }
        }
        return max;
    }

    static int max5 () {
	//reduce approach
        int sumMapreduce = ints.stream().reduce(0, (max ,k) -> max < k ? k : max);
        return sumMapreduce;
    }

    // ------------------------------------------------------------------------
    // Exercise IV: count occurrences of c in the list
    // ------------------------------------------------------------------------

    static int count1 (int c) {
        //for loop approach
        int count = 0;
        for (int i=0; i<ints.size(); i++) {
            int k = ints.get(i);
            if(k == c){
                count++;
            }
        }
        return count;
    }

    static int count2 (int c) {
        //foreach approach
        int count = 0;
        for (int k : ints) {
            if(c == k) {
                count ++;
            }
        }
        return count;
    }

    static int count3 (int c) {
        //iterator approach
        ListIterator<Integer> iterator = ints.listIterator();
        int count = 0;
        while (iterator.hasNext()) {
            int k = iterator.next();
            if (c == k) {
                count++;
            }
        }
        return count;
    }

    static int count4 (int c) {
       // int sumMapreduce = ints.stream().reduce(0, (max ,k) -> max < k ? k : max);
	//map-reduce approach9
        int countMapreduce = ints.stream().map(k -> k == c).reduce(0, (count ,k) -> k ? count+1 : count, (count1, count2) -> count1 + count2);
            return countMapreduce;
    }

    static int count5 (int c) {
	//reduce approach
        int countMapreduce = ints.stream().map(k -> k == c).reduce(0, (count ,k) -> k ? count+1 : count, (count1, count2) -> count1 + count2);
        return countMapreduce;
    }

    // ------------------------------------------------------------------------
    // Exercise V: triplicate each element in the list
    //
    // Thinking point:
    // Take note that there isn't a solution for this using the reduce approach. Why?
    // ------------------------------------------------------------------------

    static List<Integer> trip1 () {
        //for loop approach
        List ls = new ArrayList<>();
        for (int i=0; i<ints.size(); i++) {
            int k = ints.get(i);
            ls.add(k);
            ls.add(k);
            ls.add(k);
        }
        return ls;
    }

    static List<Integer> trip2 () {
        //foreach approach
        List ls = new ArrayList<>();
        for (int k : ints) {
            ls.add(k);
            ls.add(k);
            ls.add(k);
        }
        return ls;
    }

    static List<Integer> trip3 () {
        //iterator approach
        List ls = new ArrayList<>();
        ListIterator<Integer> iterator = ints.listIterator();
        while (iterator.hasNext()) {
            int k = iterator.next();
            ls.add(k);
            ls.add(k);
            ls.add(k);
        }
        return ls;
    }
/*
    static List<Integer> trip4 () {
	//map-reduce approach
        return new List<Integer>();
    }
*/
}
