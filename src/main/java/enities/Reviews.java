package enities;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.checkerframework.checker.units.qual.min;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reviews")
public class Reviews implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Min(1)
	@Max(5)
	@Column(name = "rating")
	private int rating;
	@Column(name = "comment")
	private String comment;
	@Id
	@ManyToOne()
	@JoinColumn(name = "ISBN")
	private Book book;
	@Id
	@ManyToOne()
	@JoinColumn(name = "person_id")
	private Person person;
	
	public Reviews() {
		super();
	}

	public Reviews(int rating, String comment) {
		super();
		this.rating = rating;
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Reviews [rating=" + rating + ", comment=" + comment + "]";
	}
	
}
