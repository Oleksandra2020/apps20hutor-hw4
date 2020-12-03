package ua.edu.ucu.immutable;

// У результаті змін, що призводять на модифікації (зміну стану)
// колекції має повртатись нова колекція
public interface ImmutableList {

    ImmutableList add(Object e); //додає елемент у кінець колекції

    //додає елемент до колекції за індексом,
    // та кидає виключну ситуацію, якщо індекс виходить за межі колекції
    ImmutableList add(int index, Object e);

    //додає масив елементів у кінець колекції
    ImmutableList addAll(Object[] c);

    // додає масив елементів починаючи з зазначеного індекса,
    // та кидає виключну ситуацію, якщо індекс виходить за межі колекції
    ImmutableList addAll(int index, Object[] c);

    //повертає елемент за індексом,
    // та кидає виключну ситуацію, якщо індекс виходить за межі колекції
    Object get(int index);

    //видаляє елемент за індексом, та кидає виключну ситуацію,
    // якщо індекс виходить за межі колекції
    ImmutableList remove(int index);

    //змінює значення елементу за індексом,
    // та кидає виключну ситуацію, якщо індекс виходить за межі колекції
    ImmutableList set(int index, Object e);

    //шукає індекс елемента
    // (повертає індекс першого який знайшов, або -1 у випадку відсутності)
    int indexOf(Object e);

    int size(); //розмір колекції

    ImmutableList clear(); //очищує вміст колекції

    boolean isEmpty(); //якщо у колеції нема елементів то повертає true

    Object[] toArray(); //перетворює колекцію до масиву обєктів

    //повертає рядок, де через кому відображаютсься елементи колекції
    @Override
    String toString();
}
