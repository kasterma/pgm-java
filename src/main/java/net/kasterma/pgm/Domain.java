package net.kasterma.pgm;

import lombok.Getter;

/**
 * Domain of a variable.
 *
 * For now we assume the domain is identified by an integer (id).  The only
 * property of a domain is its size; the domain then consists of the values
 * 0..(size - 1).
 */
@Getter
public class Domain {
    private final int id;
    private final int size;

    public Domain(int id, int size) {
        this.id = id;
        this.size = size;
    }
}
