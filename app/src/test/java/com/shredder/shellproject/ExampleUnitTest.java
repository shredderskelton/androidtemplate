package com.shredder.shellproject;

import com.shredder.shellproject.model.Book;
import com.shredder.shellproject.model.BookComparator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ExampleUnitTest {

    @Test
    public void book_comparator_less_isCorrect() throws Exception {
        Book a = new Book("AARDVARK");
        Book z = new Book("ZORRO");
        int result = new BookComparator().compare(a, z);
        assertTrue(result < 0);
    }

    @Test
    public void book_comparator_more_isCorrect() throws Exception {
        Book a = new Book("DEADPOOL");
        Book z = new Book("CORRECT");
        int result = new BookComparator().compare(a, z);
        assertTrue(result > 0);
    }

    @Test
    public void book_comparator_equal_isCorrect() throws Exception {
        Book a = new Book("IRONAN");
        Book z = new Book("IRONMAN");
        int result = new BookComparator().compare(a, z);
        assertTrue(result == 0);
    }
}