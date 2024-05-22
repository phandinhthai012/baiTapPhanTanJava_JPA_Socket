package mainTest;

import java.util.List;
import java.util.Map;

import dao.bookDao;
import dao.reviewDao;
import dao.Impl.bookImpl;
import dao.Impl.reviewimpl;
import enities.Book;

public class testDAO {
	public static void main(String[] args) {
		bookDao bookDao = new bookImpl();
//		reviewDao reviewDao = new reviewimpl();
//		Robert C. Martin
		List<Book> list = bookDao.listRatedBooks("Robert", 3);
		System.out.println(list.size());
		list.forEach(book -> {
			System.out.println(book);
		});
//		
//		Map<String, Long> map = bookDao.countBooksByAuthor();
//		System.out.println(map.size());
//		for (String key : map.keySet()) {
//			System.out.println(key + " : " + map.get(key));
//		}
//		boolean result = reviewDao.updateReviews("888-0132350800", "21", 4, "Good book for learning javascript good for beginner");
//		System.out.println(result);
	}
}
