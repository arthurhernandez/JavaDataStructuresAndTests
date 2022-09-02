import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class HashFunctionTest {

    void assertEqualsL (long i, long j) {
        assertEquals(i,j);
    }

    @Test
    public void hashMod () {

        HashFunction hf = new HashMod(13);
        assertEqualsL(5, hf.apply(5));
        assertEqualsL(5, hf.apply(18));
        assertEqualsL(5, hf.apply(5+13*9));
        assertEqualsL(0, hf.apply(13));
        assertEqualsL(8, hf.apply(8));


        assertEqualsL(3, hf.apply(29));
        assertEqualsL(3, hf.apply(16));
        assertEqualsL(2, hf.apply(98*4));
        assertEqualsL(4, hf.apply(4));
        assertEqualsL(0, hf.apply(0));
        assertEqualsL(6, hf.apply(32));
        assertEqualsL(6, hf.apply(6+13));
        assertEqualsL(3, hf.apply(68));


        HashFunction otherhf = new HashMod(20);
        assertEqualsL(6, hf.apply(32));
        assertEqualsL(6, hf.apply(6+13));
        assertEqualsL(3, hf.apply(68));
        assertEqualsL(2, hf.apply(98*4));
        assertEqualsL(4, hf.apply(4));
    }

    @Test
    public void hashModAfter () {

        HashFunction hf = new HashModThen(13, h -> 7 - h);
        assertEqualsL(2, hf.apply(5));
        assertEqualsL(2, hf.apply(18));
        assertEqualsL(2, hf.apply(5+13*9));
        assertEqualsL(7, hf.apply(13));


        assertEqualsL(6, hf.apply(27));
        assertEqualsL(3, hf.apply(5+ 6 * 2));
        assertEqualsL(11, hf.apply(61));

        hf.setBound(17);
        assertEqualsL(2, hf.apply(5));
        assertEqualsL(6, hf.apply(18));
        assertEqualsL(2, hf.apply(5+17*9));
        assertEqualsL(11, hf.apply(13));
        assertEqualsL(14, hf.apply(27));


        assertEqualsL(16, hf.apply(8));
        assertEqualsL(5, hf.apply(53));
        assertEqualsL(12, hf.apply(29));

    }

    @Test
    public void hashUniversal () {

        Random r = new Random(1);
        HashFunction hf = new HashUniversal(r, 31, 10);
        assertEqualsL(0, hf.apply(0));
        assertEqualsL(6, hf.apply(1));
        assertEqualsL(1, hf.apply(2));
        assertEqualsL(7, hf.apply(3));
        assertEqualsL(2, hf.apply(4));
        assertEqualsL(8, hf.apply(5));
        assertEqualsL(3, hf.apply(6));
        assertEqualsL(9, hf.apply(7));
        assertEqualsL(4, hf.apply(8));
        assertEqualsL(0, hf.apply(9));
        assertEqualsL(5, hf.apply(10));
        assertEqualsL(1, hf.apply(11));
        assertEqualsL(6, hf.apply(12));
        assertEqualsL(2, hf.apply(13));
        assertEqualsL(7, hf.apply(14));
        assertEqualsL(3, hf.apply(15));


        HashFunction hf2 = new HashUniversal(r, 15, 20);
        assertEqualsL(12, hf2.apply(12));
        assertEqualsL(14, hf2.apply(13));
        assertEqualsL(1, hf2.apply(14));
        assertEqualsL(3, hf2.apply(15));
        assertEqualsL(6, hf2.apply(39));
        assertEqualsL(8, hf2.apply(25));
        assertEqualsL(3, hf2.apply(0));

    }

}