package eni_ecole.fr.lokacarsite.constant;

import eni_ecole.fr.lokacarsite.beans.User;

/**
 * Created by pbontempi2017 on 27/06/2017.
 */

public class Constant {

    public final static String DATABASE_NAME = "LokaCarSite.db";

    public final static int DATABASE_VERSION = 1;

    // utilisateur connecté
    public static User user = null;

    public static final String ID_CAR_BRAND = "idcarbrand";
    public static final String ID_CAR = "idcar";

    public static final int ADD_CAR = 4501;
    public static final int SAVE_CAR = 4502;
    public static final int MODIFY_CAR = 4504;
    public static final int DELETE_CAR = 4505;
    public static final int DETAIL_CAR = 4506;
    public static final int ON_SAVE_CAR = 4508;

    public static final int ADD_CLIENT = 4601;
    public static final int ADD_USER = 4701;




}
