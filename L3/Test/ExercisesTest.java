import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ExercisesTest {
    private static List<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,2,8,8,8));
    @Test
    public void mul1() {
        assertEquals(1*2*3*4*5*6*7*8*9*2*8*8*8, Exercises.mul1());
    }
    @Test
    public void mul2(){
        assertEquals(Exercises.mul1(), Exercises.mul2());
    }
    @Test
    public void mul3(){
        assertEquals(Exercises.mul2(), Exercises.mul3());
    }
    @Test
    public void mul5(){
        assertEquals(Exercises.mul3(), Exercises.mul5());
    }
    @Test
    public void even1(){
        assertEquals(false, Exercises.even1());
    }
    @Test
    public void even2(){
        assertEquals(Exercises.even1(), Exercises.even2());
    }
    @Test
    public void even3(){
        assertEquals(Exercises.even2(), Exercises.even3());
    }
    @Test
    public void even4(){
        assertEquals(Exercises.even4(), Exercises.even4());
    }
    @Test
    public void even5(){
        assertEquals(Exercises.even5(), Exercises.even5());
    }
    @Test
    public void max1(){
        assertEquals(9, Exercises.max1());
    }
    @Test
    public void max2(){
        assertEquals(Exercises.max1(), Exercises.max2());
    }
    @Test
    public void max3(){
        assertEquals(Exercises.max2(), Exercises.max1());
    }
    @Test
    public void count1(){
        assertEquals(4, Exercises.count1(8));
    }
    @Test
    public void count2(){
        assertEquals(2, Exercises.count1(2));
    }
    @Test
    public void count3(){
        assertEquals(1, Exercises.count1(5));
    }
    @Test
    public void count4(){
        assertEquals(4, Exercises.count1(8));
    }
    @Test
    public void trip1(){
        assertEquals(39, Exercises.trip1().size());
    }
    @Test
    public void trip2(){
        assertEquals(Exercises.trip1().size(), Exercises.trip2().size());
    }
    @Test
    public void trip3(){
        assertEquals(Exercises.trip2().size(), Exercises.trip3().size());
    }

}