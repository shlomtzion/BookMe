package entities;

/**
 * Created by UserWin on 10/11/2015.
 */
public abstract class Person {
    private String name;
    private String phone;
    private String address;
    protected Privileging privileging;

    public Person(){
        this.name ="";
        this.phone ="";
        this.address ="";
    }
    public Person(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    public Privileging getPrivileging() {return privileging;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'';
    }
}

