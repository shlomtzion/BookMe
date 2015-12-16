package entities;

import java.io.Serializable;

/**
 * Created by UserWin on 05/11/2015.
 */
public class Client extends Person implements Serializable {

    private String email;
    private long idClient;


    public Client(){
        super();
        this.email ="";
        this.idClient=0;
        super.privileging = Privileging.CLIENT;
    }
    public Client(String name, String phone, String address, String email ) {
        super(name, phone, address);
        this.email = email;
        this.idClient=0;
        super.privileging = Privileging.CLIENT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Client{" + super.toString()+
                "email='" + email + '\'' +
                ", idClient=" + idClient +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        return getIdClient() == client.getIdClient();

    }

    /*@Override
    public int hashCode() {
        return (int) (getIdClient() ^ (getIdClient() >>> 32));
    }*/
}

