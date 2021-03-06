package at.allergico.allergico.database.POJO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 22/05/2015.
 */
public class AllergenPOJO {
    private int allergenID;
    private String description;
    private char abbreviation;
    private List<UserPOJO> users = new ArrayList<>();

    /**
     * Default constructor - set all values to default
     */
    public AllergenPOJO() {
        this.allergenID = -1;
        this.description = "???";
        this.abbreviation = '.';
    }

    /**
     * Constructor - creates object with given attributes
     * @param allergenID
     * @param description
     * @param abbreviation
     */
    public AllergenPOJO(int allergenID, String description, char abbreviation) {
        this.allergenID = allergenID;
        this.description = description;
        this.abbreviation = abbreviation;
        this.users = new ArrayList<>();
    }

    public int getAllergenID() {
        return allergenID;
    }

    public void setAllergenID(int allergenID) {
        this.allergenID = allergenID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(char abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<UserPOJO> getUsers() {
        return users;
    }

    public void setUsers(List<UserPOJO> users) {
        this.users = users;
    }
}
