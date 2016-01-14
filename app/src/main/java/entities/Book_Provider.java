package entities;

/**
 * Created by UserWin on 10/11/2015.
 */
public class Book_Provider {
    private  long idBook;
    private long idProvider;

    public Book_Provider(){
        this.idBook =0;
        this.idProvider =0;
    }

    public Book_Provider(long idBook, long idProvider) {
        this.idBook = idBook;
        this.idProvider = idProvider;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long id_book) {
        this.idBook = idBook;
    }

    public long getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(long idProvider) {
        this.idProvider = idProvider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book_Provider)) return false;

        Book_Provider that = (Book_Provider) o;

        if (getIdBook() != that.getIdBook()) return false;
        return getIdProvider() == that.getIdProvider();

    }

    /*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book_Provider)) return false;

        Book_Provider that = (Book_Provider) o;

        if (getIdBook() != that.getIdBook()) return false;
        return getIdProvider() == that.getIdProvider();

    }*/
}
