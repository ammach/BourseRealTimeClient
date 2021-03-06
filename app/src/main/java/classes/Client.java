package classes;

import java.io.Serializable;

/**
 * Created by ammach on 5/11/2016.
 */
public class Client implements Serializable {

    private static final long serialVersionUID = 42L;

    String name;
    String password;

    public Client(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
