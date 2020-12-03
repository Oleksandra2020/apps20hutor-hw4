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
    private final static int MinimumLength = 2;
    private final static int ToChange = 3;

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
            if (string.length() > MinimumLength)
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
        if (pref.length() >= MinimumLength)
        {
            return trie.wordsWithPrefix(pref);
        } else
        {
            throw new IllegalArgumentException(
                    "Please, provide a longer prefix");
        }
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (k == MinimumLength) { k = ToChange; }
        else if (k < MinimumLength)
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
