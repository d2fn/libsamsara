package com.d2fn.samsara;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * BlockTest
 * @author Dietrich Featherston
 */
public class BlockTest {

    private Alignment a10;
    private Alignment a12;

    @Before
    public void before() {
        a10 = new Alignment(10);
        a12 = new Alignment(12);
    }

    @Test
    public void testBlockNavigation() {

        // test that block navigation forward and backwards works
        Block b = a10.at(3);
        assertEquals(b.getFrom(), 0);
        assertEquals(b.getTo(), 9);

        Block n = b.next();
        assertEquals(n.getFrom(), 10);
        assertEquals(n.getTo(), 19);

        Block p = n.prev();
        assertEquals(p.getFrom(), 0);
        assertEquals(p.getTo(), 9);
    }

    @Test
    public void testBlockOverlap() {
        Block b1 = a10.at(15);
        Block b2 = a12.at(15);
        assertTrue(b1.overlaps(b2));
        assertFalse(b1.overlaps(b2.next()));
        assertFalse(b2.overlaps(b1.prev()));
    }

    @Test
    public void testEquals() {
        long blocksize = a10.getBlockSize() * a12.getBlockSize();
        Alignment largeBlocks = new Alignment(blocksize);
        Block a = largeBlocks.at(blocksize);
        Block b = a10.at(blocksize);
        Block c = a12.at(blocksize);
        Block d = a10.at(blocksize);

        assertTrue(b.equals(d));
        assertFalse(a.equals(b));
        assertFalse(b.equals(c));
        assertFalse(c.equals(d));
    }

    @Test
    public void testSetOperations() {
        long largeBlockSize = a10.getBlockSize() * a12.getBlockSize();
        Alignment largeBlocks = new Alignment(largeBlockSize);
        Set<Block> s = Sets.newHashSet();
        s.add(a10.at(100));
        s.add(a12.at(200));
        s.add(a10.at(100)); // dup
        s.add(a12.at(200)); // dup
        s.add(largeBlocks.at(largeBlockSize));
        s.add(a10.at(largeBlockSize));
        s.add(a12.at(largeBlockSize));
        assertEquals(5, s.size());
    }
}
