package ua.edu.ucu.immutable;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public ImmutableLinkedList() { }

    public ImmutableLinkedList(Object[] givenlList)
    {
        int n = givenlList.length;
        this.size = givenlList.length;
        Node[] list = new Node[n];
        for (int i = 0; i < n; i++)
        {
            Node node = new Node(givenlList[i], null);
            list[i] = node;
            if (i > 0)
            {
                list[i-1].next = node;
            }
        }
        if (size > 0)
        {
            this.head = list[0];
            this.tail = list[size()-1];
        }
    }

    public static class Node
    {
        private final Object data;
        private Node next;

        public Node(Object data, Node next)
        {
            this.data = data;
            this.next = next;
        }
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
        Object[] tempList = {e};
        return addAll(index, tempList);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        checkIndex(index);
        Object[] newList = new Object[size() + c.length];
        Node node = head;
        int i = 0;
        while (i != index)
        {
            newList[i] = node.data;
            node = node.next;
            i++;
        }
        int j = 0;
        while (j < c.length)
        {
            newList[i+j] = c[j];
            j++;
        }
        while (node != null)
        {
            newList[j+i] = node.data;
            node = node.next;
            i++;
        }
        return new ImmutableLinkedList(newList);
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        Node node = head;
        Object found = null;
        int i = 0;
        while (node != null)
        {
            if (i == index)
            {
                found = node.data;
                break;
            }
            node = node.next;
            i += 1;
        }
        return found;
    }

    @Override
    public ImmutableList remove(int index) {
        checkIndex(index);
        Object[] newList = new Object[size()-1];
        Node node = head;
        int i = 0;
        int j = 0;
        while (node != null)
        {
            if (i != index)
            {
                newList[j] = node.data;
                j++;
            }
            node = node.next;
            i++;
        }
        return new ImmutableLinkedList(newList);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        checkIndex(index);
        Object[] newList = new Object[size()];
        Node node = head;
        int i = 0;
        while (node != null)
        {
            if (i == index)
            {
                newList[i] = e;
            } else
            {
                newList[i] = node.data;
            }
            node = node.next;
            i++;
        }
        return new ImmutableLinkedList(newList);
    }

    @Override
    public int indexOf(Object e) {
        Node node = head;
        int i = 0;
        while (node != null)
        {
            if (node.data == e)
            {
                return i;
            }
            node = node.next;
            i++;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] newList = new Object[size()];
        Node node = head;
        int i = 0;
        while (node != null)
        {
            newList[i] = node.data;
            node = node.next;
            i++;
        }
        return newList;
    }

    public ImmutableLinkedList addFirst(Object e)
    {
        return (ImmutableLinkedList) add(0, e);
    }

    public ImmutableLinkedList addLast(Object e)
    {
        return (ImmutableLinkedList) add(size(), e);
    }

    public Object getFirst()
    {
        return get(0);
    }

    public Object getLast()
    {
        return get(size()-1);
    }

    public ImmutableLinkedList removeFirst()
    {
        return (ImmutableLinkedList) remove(0);
    }

    public  ImmutableLinkedList removeLast()
    {
        return (ImmutableLinkedList) remove(size()-1);
    }

    public String toString() {
        return Arrays.toString(toArray());
    }
}
