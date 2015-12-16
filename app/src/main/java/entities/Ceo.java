package entities;

/**
 * Created by UserWin on 10/11/2015.
 */
public class Ceo extends Person {



    public Ceo(){
        super();
        super.privileging = Privileging.CEO;
    }

    public Ceo(String name, String phone, String address) {
        super(name, phone, address);
        super.privileging = Privileging.CEO;
    }

    @Override
    public String toString() {
        return "Ceo{" + super.toString() +
                "}";
    }
}
