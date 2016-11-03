package ru.zhedgehog.stepikorg.webserver.dbService.dataSets;

/**
 * Created by Папа on 02.11.2016.
 */
@SuppressWarnings("UnusedDeclaration")
public class UsersDataSet {
    private long id;
    private String name;
    private String pass;

    public UsersDataSet(long id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}

