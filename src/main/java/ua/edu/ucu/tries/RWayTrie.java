package ua.edu.ucu.tries;
import ua.edu.ucu.immutable.Queue;

import java.util.ArrayList;

public class RWayTrie implements Trie {

    private static final int R = 256;
    private Node root = new Node();

    public static class Node
    {
        private Object data;
        private final Node[] next = new Node[R];

        public Node() { }
    }

    public RWayTrie() { }

    @Override
    public void add(Tuple t) {
        root = add(root, t.term, t.weight, 0);
    }

    private Node add(Node x, String key, int data, int d)
    {
        if (x == null)
        {
            x = new Node();
        }
        if (d == key.length())
        {
            x.data = data;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = add(x.next[c], key, data, d+1);
        return x;
    }

    public Integer get(String word)
    {
        Node x = get(root, word, 0);
        if (x == null)
        {
            return null;
        }
        return (int) x.data;
    }

    public Node get(Node x, String word, int d)
    {
        if (x == null)
        {
            return null;
        }
        if (d == word.length())
        {
            return x;
        }
        char c = word.charAt(d);
        return get(x.next[c], word, d+1);
    }

    @Override
    public boolean contains(String word) {
        return get(word) != null;
    }

    @Override
    public boolean delete(String word) {
        {  return delete(root, word, 0) != null; }
    }

    private Node delete(Node x, String key, int d)
    {
        if (x == null)
        {
            return null;
        }
        if (d == key.length())
        {
            x.data = null;
        }
        else
        {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }
        if (x.data != null)
        {
            return x;
        }
        for (char c = 0; c < R; c++)
        {
            if (x.next[c] != null) return x;
        }
        return null;
    }

    @Override
    public Iterable<String> words() {
        {
            return wordsWithPrefix("");
        }
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue q = new Queue();
        collect(get(root, s, 0), s, q);

        ArrayList<String> result = new ArrayList<>();
        while (!q.getQueue().isEmpty())
        {
            result.add((String) q.dequeue());
        }
        return result;
    }

    private void collect(Node x, String pre, Queue q)
    {
        if (x == null)
        {
            return;
        }
        if (x.data != null)
        {
            q.enqueue(pre);
        }
        for (char c = 0; c < R; c++)
        {
            collect(x.next[c], pre + c, q);
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(Node node)
    {
        int result = 0;
        if (node == null)
        {
            return 0;
        } else
        {
            result += 1;
        }
        for (Node n: node.next)
        {
            result += size(n);
        }
        return result;
    }

}
