package com.d2fn.samsara;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlignmentTest {

    Alignment a10;

    @Before
    public void before() {
        a10 = new Alignment(10L);
    }

    @Test
    public void testBlockSize() {
        // make sure the alignment reports the correct block size
        assertEquals(a10.getBlockSize(), 10L);
    }

    @Test
    public void testBlockRange() {
        // make sure the block returned for a given value has the correct bounds
        Block b = a10.at(3);
        assertEquals(b.getFrom(), 0);
        assertEquals(b.getTo(), 9);

        b = a10.at(0);
        assertEquals(b.getFrom(), 0);
        assertEquals(b.getTo(), 9);

        b = a10.at(9);
        assertEquals(b.getFrom(), 0);
        assertEquals(b.getTo(), 9);

        b = a10.at(10);
        assertEquals(b.getFrom(), 10);
        assertEquals(b.getTo(), 19);
    }

    @Test
    public void testBlockRangeGeneration() {
        List<Block> blocks = a10.getBlocks(5, 25);
        assertEquals(blocks.size(), 3);
        assertEquals(blocks.get(0).getFrom(), 0);
        assertEquals(blocks.get(0).getTo(), 9);
        assertEquals(blocks.get(1).getFrom(), 10);
        assertEquals(blocks.get(1).getTo(), 19);
        assertEquals(blocks.get(2).getFrom(), 20);
        assertEquals(blocks.get(2).getTo(), 29);
    }

    @Test
    public void testEquals() {
        Alignment a = new Alignment(10L);
        Alignment b = new Alignment(20L);
        Alignment c = new Alignment(10L);
        assertFalse(a.equals(b));
        assertTrue(a.equals(c));
        assertFalse(b.equals(c));
    }

    @Test
    public void testSetOperations() {
        Set<Alignment> s = Sets.newHashSet();
        s.add(new Alignment(10L));
        s.add(new Alignment(10L)); // dup
        s.add(new Alignment(20L));
        assertEquals(2, s.size());
    }
}
