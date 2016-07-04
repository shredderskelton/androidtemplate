package com.shredder.shellproject.model;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book lhs, Book rhs) {
        return lhs.getTitle().compareTo(rhs.getTitle());
    }
}
