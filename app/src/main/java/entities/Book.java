package entities;

import java.util.Date;



/**
 * Created by Naama & Shlomtzion
 */
public class Book {

    private long idBook;
    private String name;// name's book
    private String author;// name's author for book
    private String publisher; //name's publisher
    private Date publication;// date of publication for book
    private double price;//price's book
    private int count;// count the book in melai
    private TypeBook typeBook;
    private int makingStairs=0;//number of stars per book

    public Book() {
        this.idBook=0;
        this.name="";
        this.author="";
        this.publisher="";
        this.publication=null;
        this.price=0;
        this.count=0;
        this.typeBook=null;
        this.makingStairs = 0;
    }//defult constractor

    public Book( String name, String author, String publisher, Date publication, double price, int count, TypeBook typeBook) {
        this.idBook=0;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publication = publication;
        this.price = price;
        this.count = count;
        this.typeBook = typeBook;
        this.makingStairs = 0;
    }

    public long getId_book() {
        return idBook;
    }

    public void setId_book(long id_book) {
        this.idBook = id_book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TypeBook getTypeBook() {
        return typeBook;
    }

    public void setTypeBook(TypeBook typeBook) {
        this.typeBook = typeBook;
    }

    public int getMakingStairs() {
        return makingStairs;
    }

    public void setMakingStairs(int makingStairs) {
        this.makingStairs = makingStairs;
    }

    @Override
    public String toString() {
        return "Book{" +
                "idBook=" + idBook +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publication=" + publication +
                ", price=" + price +
                ", count=" + count +
                ", typeBook=" + typeBook +
                ", number stars=" + makingStairs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        return idBook == book.idBook;

    }

        /*@Override
        public int hashCode() {
            return (int) (idBook ^ (idBook >>> 32));
        }*/
}


