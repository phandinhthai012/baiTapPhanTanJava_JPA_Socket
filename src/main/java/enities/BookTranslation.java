package enities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="book_translations")
public class BookTranslation extends Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "translate_name",columnDefinition = "nvarchar(255)")
	private String translateName;
	@Column(name = "language")
	private String language;
	public BookTranslation() {
		super();
	}
	public BookTranslation(String iSBN, String name, int publishYear, int numberOfPage, double price,
			Set<String> authors, Publisher publisher, String translateName, String language) {
		super(iSBN, name, publishYear, numberOfPage, price, authors, publisher);
		this.translateName = translateName;
		this.language = language;
	}
	public String getTranslateName() {
		return translateName;
	}
	public void setTranslateName(String translateName) {
		this.translateName = translateName;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return super.toString() + "BookTranslation [translateName=" + translateName + ", language=" + language + "]";
//		return "BookTranslation [translateName=" + translateName + ", language=" + language + ", toString()="
//				+ super.toString() + "]";
	}
	
	
}
