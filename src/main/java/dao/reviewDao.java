package dao;


public interface reviewDao {
//	Cập nhật thêm một lượt đánh giá cho một cuốn sách, cập nhật thành công nếu cuốn 
//	sách và người đọc đã tồn tại, rating phải từ 1 đến 5 và bình luận không được để trống hay rỗng.
//	+ updateReviews(isbn: String, readerID: String, rating: int, comment: String): boolen
	public boolean updateReviews(String isbn, String readerID, int rating, String comment);
}
