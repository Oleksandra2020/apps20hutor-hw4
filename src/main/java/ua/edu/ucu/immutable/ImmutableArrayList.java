package ua.edu.ucu.immutable;

import java.util.Arrays;

public final class ImmutableArrayList implements ImmutableList {
    private final Object[] array;

    public ImmutableArrayList() {
        int initialCapacity = 0;
        this.array = new Object[initialCapacity];
    }

    public ImmutableArrayList(Object[] givenArray)
    {
        this.array = givenArray.clone();
    }

    public void checkIndex(int index)
    {
        if (index > size())
        {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableList add(Object e) {
        return add(size(), e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        Object[] tempArr = {e};
        return addAll(index, tempArr);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        checkIndex(index);
        Object[] newArray = new Object[size() + c.length];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(c, 0, newArray, index, c.length);
        System.arraycopy(array, index, newArray,
                index + c.length, size()-index);
        return new ImmutableArrayList(newArray);
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public ImmutableList remove(int index) {
        checkIndex(index);
        Object[] newArray = new Object[size()-1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index+1, newArray, index, size()-index-1);
        return new ImmutableArrayList(newArray);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        checkIndex(index);
        Object[] newArray = new Object[size()];
        System.arraycopy(array, 0, newArray, 0, size());
        newArray[index] = e;
        return new ImmutableArrayList(newArray);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == e)
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        return array.clone();
    }

    public String toString() {
        return Arrays.toString(toArray());
    }
}
