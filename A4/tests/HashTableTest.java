import org.junit.Test;
import org.junit.Ignore;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HashTableTest {


    @Test
    public void hashSeparateChaining() {
        Random r = new Random(1);
        HashFunction hf = new HashUniversal(r, 31, 10);
        HashTable ht = new HashSeparateChaining(hf);
        ht.insert(1);
        ht.insert(12);
        ht.insert(25);
        ht.insert(8);
        ht.insert(8);
        ht.insert(17);
        ht.insert(0);
        ht.insert(45);
        ht.insert(6);
        ht.insert(7);
        ht.insert(8);
        assertTrue(ht.search(12));
        assertTrue(ht.search(0));
        assertTrue(ht.search(17));
        assertFalse(ht.search(4));
        assertTrue(ht.search(1));
        assertTrue(ht.search(8));
        assertFalse(ht.search(2));
        ht.insert(22);
        System.out.println("Before rehashing:");
        System.out.println(ht);
        System.out.println("Capacity Before rehashing:  " + ht.getCapacity());
        ht.rehash();
        System.out.println("After rehashing, before deleting:");
        System.out.println(ht);
        System.out.println("Capacity After Rehashing:  " + ht.getCapacity());
        ht.delete(12);
        ht.delete(6);
        ht.delete(8);
        ht.delete(8);
        assertFalse(ht.search(12));
        assertFalse(ht.search(6));
        System.out.println("After deleting: ");
        System.out.println(ht);

        HashFunction hf2 = new HashUniversal(r, 31, 10);
        HashTable htt2 = new HashSeparateChaining(hf2);
        htt2.insert(1);
        htt2.insert(12);
        htt2.insert(25);
        htt2.insert(8);
        htt2.insert(13);
        htt2.insert(17);
        htt2.insert(9);
        htt2.insert(45);
        htt2.insert(1);
        htt2.insert(12);
        htt2.insert(25);
        htt2.insert(8);
        htt2.insert(8);
        htt2.insert(17);
        htt2.insert(5);
        htt2.insert(45);
        htt2.insert(7);
        htt2.insert(81);
        assertTrue(htt2.search(1));
        assertTrue(htt2.search(17));
        assertTrue(htt2.search(5));
        assertFalse(htt2.search(54));
        assertFalse(htt2.search(0));
        assertFalse(htt2.search(4));
        htt2.delete(45);
        assertTrue(htt2.search(45));

    }

    @Test
    public void fig55() {

        HashFunction hf = new HashMod(10);
        HashTable ht = new HashSeparateChaining(hf);
        ht.insert(0);
        ht.insert(81);
        ht.insert(64);
        ht.insert(49);
        ht.insert(9);
        ht.insert(36);
        ht.insert(25);
        ht.insert(16);
        ht.insert(4);
        ht.insert(1);
        assertTrue(ht.search(36));
        assertTrue(ht.search(25));
        assertTrue(ht.search(1));
        assertTrue(ht.search(4));
        assertTrue(ht.search(9));
        assertTrue(ht.search(16));
        assertFalse(ht.search(34));
        System.out.println("Fig. 5.5");
        System.out.println(ht);
        ht.rehash();


        ht.delete(81);
        ht.delete(64);
        ht.delete(16);
        ht.delete(9);
        System.out.println("Fig. 5.5 deleting one in every list with 2 values");
        System.out.println(ht);
        assertTrue(ht.search(36));
        assertTrue(ht.search(25));
        assertTrue(ht.search(1));
        assertTrue(ht.search(4));
        assertFalse(ht.search(9));
        assertFalse(ht.search(16));
        ht.rehash();
        System.out.println("Fig. 5.5 rehashed");
        System.out.println(ht);

    }


    @Test
    public void fig511 () {

        HashFunction hf = new HashMod(10);
        HashTable ht = new HashLinearProbing(hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.11");
        System.out.println(ht);

        HashFunction hf2 = new HashMod(10);
        HashTable ht2 = new HashLinearProbing(hf2);
        ht2.insert(89);
        ht2.insert(18);
        ht2.insert(49);
        ht2.insert(58);
        ht2.insert(69);
        System.out.println("Fig. 5.11");
        System.out.println(ht2);

        HashFunction hf21 = new HashMod(20);
        HashTable ht22 = new HashLinearProbing(hf21);
        ht22.insert(65);
        ht22.insert(35);
        ht22.insert(55);
        ht22.insert(87);
        ht22.insert(23);
        System.out.println("Fig. 5.11");
        System.out.println(ht22);
        assertTrue(ht22.search(65));
        ht22.delete(65);
        assertTrue(ht22.search(35));
        assertTrue(ht22.search(55));
        assertFalse(ht22.search(4));
        System.out.println("Fig. 5.11");
        System.out.println(ht22);


    }

    @Test
    public void fig513 () {

        HashFunction hf = new HashMod(7);
        HashTable ht = new HashQuadProbing (hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);

        System.out.println("Fig. 5.13");
        System.out.println(ht);
        ht.insert(26);
        ht.insert(72);
        ht.insert(95);
        System.out.println("Before rehash");
        System.out.println(ht);
        ht.delete(89);
        ht.delete(26);
        System.out.println("Before rehash w deletes ");
        System.out.println(ht);
        ht.rehash();
        System.out.println("After rehash");
        System.out.println(ht);

        HashFunction hf2 = new HashMod(10);
        HashTable ht2 = new HashQuadProbing (hf2);
        ht2.insert(23);
        ht2.insert(54);
        ht2.insert(76);
        ht2.insert(34);
        ht2.insert(56);
        ht2.insert(23);
        ht2.insert(54);
        ht2.insert(76);
        ht2.insert(34);
        ht2.insert(56);
        System.out.println("Fig. 5.13");
        System.out.println(ht2);
        ht2.insert(2);
        ht2.insert(72);
        ht2.insert(9);
        System.out.println("Before rehash");
        System.out.println(ht2);
        ht2.delete(83);
        ht2.delete(27);
        ht2.delete(54);
        assertTrue(ht2.search(54));
        ht2.delete(36);
        ht2.delete(54);
        ht2.delete(76);
        assertTrue(ht2.search(76));
        assertFalse(ht2.search(36));
        assertFalse(ht2.search(83));
        assertTrue(ht2.search(56));
        System.out.println("Before rehash w deletes ");
        System.out.println(ht2);
        ht2.rehash();
        System.out.println("After rehash");
        System.out.println(ht2);
    }

    @Test
    public void fig518 () {

        HashFunction hf = new HashMod(10);
        HashFunction hf2 = new HashModThen(7, h -> 7 - h);
        HashTable ht = new HashDouble (hf, hf2);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.18");
        System.out.println(ht);

        assertTrue(ht.search(89));
        assertFalse(ht.search(6));
        assertTrue(ht.search(18));
        System.out.println("Fig. 5.18");
        System.out.println(ht);

        System.out.println("Test: ");
        HashFunction hashm = new HashMod(15);
        HashFunction hm2 = new HashModThen(6, h -> 6 - h);
        HashTable hmdouble = new HashDouble (hashm, hm2);
        hmdouble.insert(69);
        hmdouble.insert(45);
        hmdouble.insert(665);
        hmdouble.insert(87);
        hmdouble.insert(23);
        hmdouble.insert(22);
        hmdouble.insert(65);
        hmdouble.insert(3);
        assertTrue(hmdouble.search(3));
        assertTrue(hmdouble.search(69));
        hmdouble.delete(69);
        assertFalse(hmdouble.search(69));
        System.out.println("Fig. 5.18");
        System.out.println(ht);





    }

}