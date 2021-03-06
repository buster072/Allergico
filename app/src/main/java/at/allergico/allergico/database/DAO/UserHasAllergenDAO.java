package at.allergico.allergico.database.DAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import at.allergico.allergico.database.Manager.DBManager;
import at.allergico.allergico.database.POJO.AllergenPOJO;
import at.allergico.allergico.database.POJO.UserHasAllergenPOJO;
import at.allergico.allergico.database.POJO.UserPOJO;

/**
 * Created by Markus on 25.05.2015.
 */
public class UserHasAllergenDAO {
    /******** SINGLETON START ********/
    DBManager dbManager = DBManager.getInstance();
    private static UserHasAllergenDAO _instance;

    public static UserHasAllergenDAO getInstance() {
        if (_instance == null) {
            _instance = new UserHasAllergenDAO();
        }

        return _instance;
    }
    /******** SINGLETON END *********/


    private List<UserHasAllergenPOJO> UserHasAllergenList = new ArrayList<>();

    public List<UserHasAllergenPOJO> getUserHasAllergenList() {
        return UserHasAllergenList;
    }

    public UserHasAllergenDAO(){
        fillList();
    }

    private boolean fillList(){
        this.getUserHasAllergenList().clear();
        String jsonString = dbManager.getObject("UserHasAllergen");
        if(jsonString == null){return false;}
        System.out.print(jsonString);
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject[] jsonObjects = new JSONObject[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObjects[i] = jsonArray.getJSONObject(i);
            }
            for(JSONObject item : jsonObjects){
                UserHasAllergenPOJO userHasAllergen = new UserHasAllergenPOJO(
                        item.getInt("User_UserID"),
                        item.getInt("Allergen_AllergenID")

                );


                this.getUserHasAllergenList().add(userHasAllergen);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean reloadDataFromDB(){
        return fillList();
    }

    public List<AllergenPOJO> getAllergeneOfUser(int userID){
        AllergenDAO allergenDAO = AllergenDAO.getInstance();
        Iterator<UserHasAllergenPOJO> iter = UserHasAllergenList.listIterator();
        List<AllergenPOJO> returnList = new ArrayList<>();
        while(iter.hasNext()){
            UserHasAllergenPOJO item = iter.next();
            if(item.getUserID() == userID){
                returnList.add(allergenDAO.getAllergenByID(item.getAllergenID()));
            }
        }
        return returnList;
    }

    public List<UserPOJO> getUserOfAllergen(int allergenID){
        UserDAO userDAO = UserDAO.getInstance();
        Iterator<UserHasAllergenPOJO> iter = UserHasAllergenList.listIterator();
        List<UserPOJO> returnList = new ArrayList<>();
        while (iter.hasNext()){
            UserHasAllergenPOJO item = iter.next();
            if(item.getAllergenID() == allergenID){
                returnList.add(userDAO.getUserByID(item.getUserID()));
            }
        }
        return returnList;
    }

    public boolean addUserHasAllergen(UserHasAllergenPOJO pojo){
        this.getUserHasAllergenList().add(pojo);
        JSONObject addingUserHasAllergen = new JSONObject();
        try
        {
            addingUserHasAllergen.put("UserID", pojo.getUserID());
            addingUserHasAllergen.put("AllergenID", pojo.getAllergenID());

            boolean result = dbManager.addUserHasAllergen(addingUserHasAllergen.toString());
            if(result){
                this.getUserHasAllergenList().add(pojo);
            }
            reloadDataFromDB();
            return result;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return false;
        }


    }

    public boolean removeUserHasAllergen(UserHasAllergenPOJO pojo){
        this.getUserHasAllergenList().remove(pojo);
        dbManager.removeUserHasAllergen(pojo.getUserID(),pojo.getAllergenID());
        reloadDataFromDB();
        return true;
    }
}
