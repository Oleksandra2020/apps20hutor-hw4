package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private final Trie trie;
    private static final int MINIMUMLENGTH = 2;
    private static final int CHANGETO = 3;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        ArrayList<String> allStrings = new ArrayList<>();
        for (String string: strings)
        {
            String[] partition = string.split(" ");
            allStrings.addAll(Arrays.asList(partition));
        }

        for (String string: allStrings)
        {
            if (string.length() > MINIMUMLENGTH)
            {
                Tuple tp = new Tuple(string, string.length());
                trie.add(tp);
            }
        }
        return trie.size();
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() >= MINIMUMLENGTH)
        {
            return trie.wordsWithPrefix(pref);
        } else
        {
            throw new IllegalArgumentException(
                    "Please, provide a longer prefix");
        }
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (k == MINIMUMLENGTH) { k = CHANGETO; }
        else if (k < MINIMUMLENGTH)
        {
            throw new IllegalArgumentException(
                    "Please, provide a longer prefix");
        }
        Iterable<String> words = trie.wordsWithPrefix(pref);
        ArrayList<String> result = new ArrayList<>();

        for (String word: words)
        {
            if (word.length() - pref.length() < k)
            {
                result.add(word);
            }
        }
        return result;
    }

    public int size() {
        return trie.size();
    }
}
