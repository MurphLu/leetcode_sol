package org.ml.leetcode.daily;

import java.util.*;

public class PeekingIterator implements Iterator<Integer>{
    List<Integer> list;
    int index;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
        index = 0;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (index < list.size()){
            return list.get(index);
        }else {
            return null;
        }
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        return list.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }
}
