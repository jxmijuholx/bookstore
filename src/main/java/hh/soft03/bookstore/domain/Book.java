package hh.soft03.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class Book {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String author;
	private int PublishYear;
	private String isbn;
	private double price;

	  	public void setCategory(Category category) {
		this.category = category;
	}
		@ManyToOne
		@JsonIgnore
	    @JoinColumn(name = "categoryid")
	    private Category category;
	  	
	public Category getCategory() {
			return category;
		}

	public Book() {
		
	}

	public Book(String title, String author, int PublishYear, String isbn, double price,Category  category) {
		super();
		this.title = title;
		this.author = author;
		this.PublishYear = PublishYear;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getPublishYear() {
		return PublishYear;
	}

	public String getIsbn() {
		return isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setYear(int PublishYear) {
		this.PublishYear = PublishYear;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", PublishYear=" + PublishYear + ", isbn="
				+ isbn + ", price=" + price + ", category=" + category + "]";
	}


	

	
	
}