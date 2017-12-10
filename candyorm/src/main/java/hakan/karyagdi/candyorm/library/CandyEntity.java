package hakan.karyagdi.candyorm.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import hakan.karyagdi.candyorm.library.annotations.DatabaseField;
import hakan.karyagdi.candyorm.library.db.CandyDb;

/**
 * Created by hakan on 12/10/17.
 */

public  class CandyEntity< T extends  CandyEntity>  {




    public long save(Context context)  {
        return save(CandyDb.getInstance(context).getWritableDatabase(),this);
    }

    private long save (SQLiteDatabase db, CandyEntity object)
    {
        Field[] fields = object.getClass().getDeclaredFields();
        ContentValues contentValues = new ContentValues();
        for (Field field:fields) {
            Annotation[] annotations=field.getDeclaredAnnotations();
            for (Annotation annotation:annotations) {
                DatabaseField databaseField = (DatabaseField) annotation;
                String name = ((DatabaseField) annotation).FieldName();
                String value =String.valueOf(runGetter(field,object));
                contentValues.put(name,value);
            }
        }

        return db.insert(object.getClass().getSimpleName(),null,contentValues);

    }


//    public static IModel update(IModel model) {
//        return null;
//    }
//
//
//    public static IModel delete(IModel model) {
//        return null;
//    }
//
//
//    public static IModel getValueByKey(String key) {
//        return null;
//    }
//
//
//    public static IModel getValueByKey(int key) {
//        return null;
//    }
//
//
//    public static List<IModel> getAllValues() {
//        return null;
//    }
//
//
//    public static List<IModel> selectRawQuery(String query) {
//        return null;
//    }


    public static void executeRawQuery(String query) {

    }

    private static Object runGetter(Field field, CandyEntity o) {
        // MZ: Find the correct method
        for (Method method : o.getClass().getMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                    // MZ: Method found, run it
                    try {
                        return method.invoke(o);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }
        }


        return null;
    }
}
