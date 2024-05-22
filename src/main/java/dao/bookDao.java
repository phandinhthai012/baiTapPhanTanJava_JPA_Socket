package dao;

import java.util.List;
import java.util.Map;

import enities.Book;

public interface bookDao {
//	Liệt kê danh sách các cuốn sách được viết bởi tác giả nào đó khi biết tên tác giả và
//	có điểm đánh giá từ mấy sao trở lên.
//	+ listRatedBooks(author: String, rating: int): List<Book>
	public List<Book> listRatedBooks(String author, int rating);
//	Thống kê số cuốn sách được dịch sang ngôn ngữ khác của từng tác giả, kết quả sắp 
//	xếp theo tên tác giả.
//	+ countBooksByAuthor(): Map<String, Long>
	public Map<String, Long> countBooksByAuthor();
}
