package entities;

/**
 * Created by UserWin on 10/11/2015.
 */
public class Invitation {
    private long idClient;
    private long idProvider;
    private long idBook;
    private int count;//count of book
    private double totalPrice;//total price
    private boolean delivery;// if the client want delivery

    public Invitation(){
        this.idClient =0;
        this.idProvider =0;
        this.idBook =0;
        this.count =0;
        this.totalPrice =0;
        this.delivery =false;
    }
    public Invitation(long idClient, long idProvider, long idBook, int count, double totalPrice, boolean delivery) {
        this.idClient = idClient;
        this.idProvider = idProvider;
        this.idBook = idBook;
        this.count = count;
        this.totalPrice = totalPrice;
        this.delivery = delivery;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public long getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(long idProvider) {
        this.idProvider = idProvider;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long id_book) {
        this.idBook = idBook;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "idClient=" + idClient +
                ", idProvider=" + idProvider +
                ", idBook=" + idBook +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                ", delivery=" + delivery +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invitation)) return false;

        Invitation that = (Invitation) o;

        if (getIdClient() != that.getIdClient()) return false;
        if (getIdProvider() != that.getIdProvider()) return false;
        return getIdBook() == that.getIdBook();

    }

    /*@Override
    public int hashCode() {
        int result = (int) (getIdClient() ^ (getIdClient() >>> 32));
        result = 31 * result + (int) (getIdProvider() ^ (getIdProvider() >>> 32));
        result = 31 * result + (int) (getIdBook() ^ (getIdBook() >>> 32));
        return result;
    }*/
}
