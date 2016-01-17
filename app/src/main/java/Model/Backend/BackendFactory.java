package Model.Backend;
//import android.content.Context;

import android.content.Context;

/**
 * Created by UserWin on 12/11/2015.
 */
public final class BackendFactory {

        static Backend instance = null;
        //public static String mode = "lists";
        public static String mode = "sql";

    public final static Backend getInstance(Context context) {
            if (mode == "lists") {
                if (instance == null)
                    instance = new Model.dataSource.Databaselist(context);
                return instance;
            }
            if (mode == "sql") {
                if (instance == null) instance = new Model.dataSource.DatabaseSQL(context);
                return instance;
            }
            /*if (mode == "Service") {
                if (instance == null) instance = new Model.dataSource.DatabeseService();
                return instance;
            }*/ else {
                return null;
            }
        }
    }