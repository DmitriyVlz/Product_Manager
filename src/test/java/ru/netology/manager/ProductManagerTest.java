package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Book book1 = new Book(1, "Книга 1", 230, "Автор 1");
    Book book2 = new Book(2, "Книга 2", 450, "Автор 2");
    Book book3 = new Book(3, "Книга 3", 500, "Автор 2");
    Smartphone phone1 = new Smartphone(4, "Телефон 1", 80000, "Производитель 1");
    Smartphone phone2 = new Smartphone(5, "Телефон 2", 50000, "Производитель 2");
    Smartphone phone3 = new Smartphone(6, "Телефон 3", 6000, "Производитель 3");

    @BeforeEach
    void setUp() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
    }

    @Test
    void shouldFindBookByNameIfExists() {
        String textToFind = "Книга 3";
        Product[] expected = new Product[]{book3};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindBookByNameIfNoExists() {
        String textToFind = "Книга 4";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindBookByAuthorIfExists() {
        String textToFind = "Автор 1";
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindBookByAuthorIfNoExists() {
        String textToFind = "Автор 4";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByNameIfExists() {
        String textToFind = "Телефон 2";
        Product[] expected = new Product[]{phone2};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindSmartphoneByNameIfNoExists() {
        String textToFind = "Производитель 5";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByProducerIfExists() {
        String textToFind = "Производитель 1";
        Product[] expected = new Product[]{phone1};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindSmartphoneByProducerIfNoExists() {
        String textToFind = "Производитель 4";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    // поиск всех книг одного автора
    @Test
    void shouldFindAllByAutor() {
        String textToFind = "Автор 2";
        Product[] expected = new Product[]{book2, book3};
        Product[] actual = manager.searchBy("Автор 2");
        assertArrayEquals(expected, actual);
    }


}