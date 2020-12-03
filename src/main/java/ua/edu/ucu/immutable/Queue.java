package ua.edu.ucu.immutable;

public class Queue {
    private ImmutableLinkedList queue;

    public Queue() {
        this.queue = new ImmutableLinkedList();
    }

    public ImmutableLinkedList getQueue()
    {
        return queue;
    }

    public Object peek()
    {
        return queue.getFirst();
    }

    public Object dequeue()
    {
        Object first = queue.getFirst();
        queue = queue.removeFirst();
        return first;
    }

    public void enqueue(Object e)
    {
        queue = queue.addLast(e);
    }

}