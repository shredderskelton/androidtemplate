package com.shredder.shellproject.model;

import java.util.List;

public class BookFactory {
    private final List<String> titles;

    public BookFactory(List<String> titles) {
        this.titles = titles;
    }

    public Book getRandomBook() {
        long randomIndex = System.currentTimeMillis() % (long) (titles.size() - 1);
        String chosenBookTitle = titles.get((int) randomIndex);
        return new Book(chosenBookTitle);
    }

    public Book getRandomBoosdak() {
        long randomIndex = System.currentTimeMillis() % (long) (titles.size() - 1);
        String chosenBookTitle = titles.get((int) randomIndex);
        return new Book(chosenBookTitle);
    }
}
