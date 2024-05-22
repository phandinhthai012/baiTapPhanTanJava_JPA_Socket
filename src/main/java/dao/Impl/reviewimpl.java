package dao.Impl;

import dao.reviewDao;
import enities.Book;
import enities.Person;
import enities.Reviews;
import jakarta.persistence.EntityManager;
import utils.hibernateUtil;

public class reviewimpl implements reviewDao {
	private EntityManager em;
	public reviewimpl() {
		em = hibernateUtil.getInstance().getEntityManager();
	}

	
//	Cập nhật thêm một lượt đánh giá cho một cuốn sách, cập nhật thành công nếu cuốn 
//	sách và người đọc đã tồn tại, rating phải từ 1 đến 5 và bình luận không được để trống hay rỗng.
	@Override
	public boolean updateReviews(String isbn, String readerID, int rating, String comment) {
		if (rating < 1 || rating > 5 || comment == null || comment.isEmpty()) {
			return false;
		}
		Book book = em.find(Book.class, isbn);
		if (book == null) {
			return false;
		}
		Person person = em.find(Person.class, readerID);
		if (person == null) {
			return false;
		}
		Reviews review = new Reviews();
		review.setBook(book);
		review.setPerson(person);
		review.setRating(rating);
		review.setComment(comment);
		try {
			em.getTransaction().begin();
			em.merge(review);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
//		String query = "update Review r set r.rating = :rating, r.comment = :comment where r.book.isbn = :isbn and r.readerID = :readerID";
//		em.getTransaction().begin();
//		int count = em.createQuery(query).setParameter("rating", rating).setParameter("comment", comment).setParameter("isbn", isbn).setParameter("readerID", readerID).executeUpdate();
//		em.getTransaction().commit();
//		if (count > 0) {
//			return true;
//		}
		return false;
	}

}
