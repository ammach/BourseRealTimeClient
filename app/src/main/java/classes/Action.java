package classes;

import java.io.Serializable;

/**
 * Created by ammach on 5/11/2016.
 */
public class Action implements Serializable {

    private static final long serialVersionUID = 32L;

    String name;
    String price;
    String change;
    String day_high;
    String day_low;

    public Action(String name, String price, String change, String day_high, String day_low) {
        this.name = name;
        this.price = price;
        this.change = change;
        this.day_high = day_high;
        this.day_low = day_low;
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

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getDay_high() {
        return day_high;
    }

    public void setDay_high(String day_high) {
        this.day_high = day_high;
    }

    public String getDay_low() {
        return day_low;
    }

    public void setDay_low(String day_low) {
        this.day_low = day_low;
    }
}
