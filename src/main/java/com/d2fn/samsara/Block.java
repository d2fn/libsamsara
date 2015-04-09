package com.d2fn.samsara;

/**
 * Represents an individual block of numbers in an aligned layout
 * @see com.d2fn.samsara.Alignment
 * @author Dietrich Featherston
 */
public class Block {

    private final Alignment alignment;
    private final long start;

    public Block(Alignment a, long start) {
        this.alignment = a;
        this.start = start;
    }

    public Block next() {
        return new Block(alignment, start + alignment.getBlockSize());
    }

    public Block prev() {
        return new Block(alignment, start - alignment.getBlockSize());
    }

    public long getFrom() {
        return start;
    }

    public long getTo() {
        return start + alignment.getBlockSize() - 1;
    }

    public boolean overlaps(Block b) {
        return overlaps(b.getFrom(), b.getTo());
    }

    public boolean overlaps(long from, long to) {
        // this block wholly fits inside
        if(getFrom() >= from && getTo() <= to)
            return true;
            // the input block wholly fits inside this one
        else if(getFrom() <= from && getTo() >= to)
            return true;
            // the input block sits on the left edge
        else if(from <= getFrom() && to >= getFrom())
            return true;
            // the input block site on the right edge
        else if(from <= getTo() && to >= getTo())
            return true;
        return false;
    }

    public String toString() {
        return "(" + getFrom() + ", " + getTo() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        if (start != block.start) return false;
        if (!alignment.equals(block.alignment)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 31 * alignment.hashCode() + (int) (start ^ (start >>> 32));
    }
}
