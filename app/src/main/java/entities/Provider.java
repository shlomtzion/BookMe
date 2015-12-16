package entities;


/**
 * Created by Shlomtzion Lahav and Naama Nudelman on 07/11/2015.
 */
public class Provider extends Person {

    private long idProvider;

    public Provider(){
        super();
        this.idProvider =0;
        super.privileging = Privileging.PROVIDER;
    }

    public Provider(String name, String phone, String address) {
        super(name, phone, address);
        this.idProvider =0;
        super.privileging = Privileging.PROVIDER;
    }

    public long getId_provider() {
        return idProvider;
    }

    public void setId_provider(long id_provider) {
        this.idProvider = id_provider;
    }

    @Override
    public String toString() {
        return "Provider{" + super.toString()+
                "id_provider=" + idProvider +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provider)) return false;

        Provider provider = (Provider) o;

        return getId_provider() == provider.getId_provider();

    }

    /*@Override
    public int hashCode() {
        return (int) (getId_provider() ^ (getId_provider() >>> 32));
    }*/
}
