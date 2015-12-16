package entities;

/**
 * Created by UserWin on 12/11/2015.
 */
public class Client_Provider {
    private long idClient;
    private long idProvider;

    public Client_Provider(long idClient, long idProvider) {
        this.idClient = idClient;
        this.idProvider = idProvider;
    }

    public Client_Provider() {
        this.idClient =0;
        this.idProvider =0;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client_Provider)) return false;

        Client_Provider that = (Client_Provider) o;

        if (getIdClient() != that.getIdClient()) return false;
        return getIdProvider() == that.getIdProvider();

    }

    /*@Override
    public int hashCode() {
        int result = (int) (getIdClient() ^ (getIdClient() >>> 32));
        result = 31 * result + (int) (getIdProvider() ^ (getIdProvider() >>> 32));
        return result;
    }*/
}
