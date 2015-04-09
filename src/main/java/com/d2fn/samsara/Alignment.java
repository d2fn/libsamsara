package com.d2fn.samsara;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Model a particular layout of numbers in fixed width blocks (of 10, 100, etc)
 * @see com.d2fn.samsara.Block
 * @author Dietrich Featherston
 */
public class Alignment {

    private final long blocksize;

    public Alignment(long blocksize) {
        this.blocksize = blocksize;
    }

    public final long getBlockSize() {
        return blocksize;
    }

    public final Block at(long value) {
        return new Block(this, value / blocksize * blocksize);
    }

    public final List<Block> getBlocks(long from, long to) {
        if(from > to) {
            long t = from;
            from = to;
            to = t;
        }
        List<Block> blocks = Lists.newArrayListWithExpectedSize((int)((to - from)/blocksize + 2));
        Block b = this.at(from);
        blocks.add(b);
        while(b.getTo() < to) {
            b = b.next();
            blocks.add(b);
        }
        return blocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alignment alignment = (Alignment) o;

        if (blocksize != alignment.blocksize) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (blocksize ^ (blocksize >>> 32));
    }

    public static final Alignment day    = new Alignment(24L*60L*60L*1000L);
    public static final Alignment hour   = new Alignment(60L*60L*1000L);
    public static final Alignment minute = new Alignment(60L*1000L);
}
