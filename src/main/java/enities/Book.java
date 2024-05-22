package enities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.Generated;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

@Entity
@Table(name="books")
@Inheritance(strategy = InheritanceType.JOINED)
public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ISBN", unique = true)
	private String ISBN;
	@Column(name = "name",columnDefinition = "nvarchar(255)")
	private String name;
	@Column(name = "publish_year")
	private int publishYear;
	@Column(name = "num_of_pages")
	private int numberOfPage;
	private double price;
	@ElementCollection
	@CollectionTable(name = "books_authors",joinColumns = @JoinColumn(name = "ISBN") )
	@Column(name = "author")
	private Set<String> authors;
	@ManyToOne()
	@JoinColumn(name = "publisher_id")
	private Publisher publisher;
	@OneToMany(mappedBy = "book")
	private List<Reviews> reviews;
	public Book() {
		super();
	}
	public Book(String iSBN, String name, int publishYear, int numberOfPage, double price, Set<String> authors,
			Publisher publisher) {
		super();
		ISBN = iSBN;
		this.name = name;
		this.publishYear = publishYear;
		this.numberOfPage = numberOfPage;
		this.price = price;
		this.authors = authors;
		this.publisher = publisher;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}
	public int getNumberOfPage() {
		return numberOfPage;
	}
	public void setNumberOfPage(int numberOfPage) {
		this.numberOfPage = numberOfPage;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Set<String> getAuthors() {
		return authors;
	}
	public void setAuthors(Set<String> authors) {
		this.authors = authors;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public List<Reviews> getReviews() {
		return reviews;
	}
	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}
	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", name=" + name + ", publishYear=" + publishYear + ", numberOfPage="
				+ numberOfPage + ", price=" + price + ", authors=" + authors + ", publisher=" + publisher + "]";
	}
	
	
	
	
}
