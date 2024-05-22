package unitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.bookDao;
import dao.reviewDao;
import dao.Impl.bookImpl;
import dao.Impl.reviewimpl;
import enities.Book;

class testCRUD {
	static bookDao bookDao;
	static reviewDao reviewDao;

	@BeforeAll
	static void setUpBeforeClass() {
		bookDao = new bookImpl();
		reviewDao = new reviewimpl();
	}

	@Test
	void testlistRatedBooks() {
		List<Book> list = bookDao.listRatedBooks("Robert", 3);
		int expected = 3;
		int actual = list.size();
		assertEquals(expected, actual);
		String expectedISBN = "888-0132350800";
		assertTrue(list.stream().anyMatch(book -> book.getISBN().equals(expectedISBN)));
//        boolean l = list.contains(new Book("888-0132350800", "Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", "Prentice Hall", 2008, 464, "English"));

	}
	
	@Test
	void testcountBooksByAuthor() {
		int expected = 11;
		Map<String, Long> map = bookDao.countBooksByAuthor();
//		int actual = bookDao.countBooksByAuthor().size();
		assertEquals(expected, map.size());
		String expectedAuthor = "Robert C. Martin";
		int expectedcount =2;
		assertTrue(map.containsKey(expectedAuthor));
		assertEquals(expectedcount, map.get(expectedAuthor));
	}
	
	@Test
	void testupdateReviews() {
		boolean result = reviewDao.updateReviews("888-0132350800", "21", 4, "Good book for learning javascript good for beginner");
		assertTrue(result);
		boolean result2 = reviewDao.updateReviews("888-0132350800-adv", "21", 6, "Good book for learning javascript good for beginner");
		assertFalse(result2);
	}
	

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

}
