package dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.bookDao;
import enities.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class bookImpl implements bookDao {
	private String persistenceUnit = "quanLiThongTinSach_mssql";
	private EntityManager em;
	
	public bookImpl() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		em = emf.createEntityManager();
	}

//	Liệt kê danh sách các cuốn sách được viết bởi tác giả nào đó khi biết tên tác giả và
//	có điểm đánh giá từ mấy sao trở lên.
//	+ listRatedBooks(author: String, rating: int): List<Book>
	
	@Override
	public List<Book> listRatedBooks(String author, int rating) {
//		String query = "SELECT b FROM Book b join b.reviews r WHERE b.authors like :author AND r.rating >= :rating";
		String query = "SELECT b FROM Book b JOIN b.reviews r " +
                "WHERE EXISTS (SELECT a FROM b.authors a WHERE a LIKE :author) AND r.rating >= :rating";
//		String query = "SELECT b FROM Book b JOIN b.authors a JOIN b.reviews r WHERE a like :author AND r.rating >= :rating";
		List<Book> books = em.createQuery(query, Book.class).setParameter("author", "%"+author+"%").setParameter("rating", rating).getResultList();
//		String query = "SELECT b FROM Book b join b.reviews r WHERE :author member of b.authors AND r.rating >= :rating";
//		List<Book> books = em.createQuery(query, Book.class).setParameter("author", author)
//				.setParameter("rating", rating).getResultList();
		return books;
	}

//	Thống kê số cuốn sách được dịch sang ngôn ngữ khác của từng tác giả, kết quả sắp 
//	xếp theo tên tác giả.
//	+ countBooksByAuthor(): Map<String, Long>
	
	@Override
	public Map<String, Long> countBooksByAuthor() {
		String query = "select a,count(b) from BookTranslation b join b.authors a group by a";
		List<Object[]> list = em.createQuery(query, Object[].class).getResultList();
		Map<String, Long> map = new HashMap<String, Long>();
		for (Object[] objects : list) {
			map.put((String) objects[0], (Long) objects[1]);
		}
		return map;
	}

}
