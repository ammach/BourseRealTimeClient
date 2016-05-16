package classes;

import java.io.Serializable;

/**
 * Created by ammach on 5/11/2016.
 */
public class Change implements Serializable{

    private static final long serialVersionUID = 52L;

    String name;
    String price;
    String utctime;

    public Change(String name, String price, String utctime) {
        this.name = name;
        this.price = price;
        this.utctime = utctime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUtctime() {
        return utctime;
    }

    public void setUtctime(String utctime) {
        this.utctime = utctime;
    }

}
