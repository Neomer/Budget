package my.neomer.budget.core.database;

import android.database.Cursor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import my.neomer.budget.models.DatabaseField;
import my.neomer.budget.models.Entity;

public abstract class AbstractEntityManager<IdType, EntityType extends Entity<IdType>> implements EntityManager<IdType, EntityType>  {

    protected void map(Cursor c, EntityType entity) {
        Class cl = entity.getClass();
        for (Field f : cl.getDeclaredFields()) {
            String fieldName = null;
            if (f.isAnnotationPresent(DatabaseField.class)) {
                for(Annotation a : f.getDeclaredAnnotations()) {
                    if (a instanceof DatabaseField) {
                        fieldName = ((DatabaseField) a).Name();
                    }
                }
            } else {
                fieldName = f.getName();
            }
            for (int col = 0; col < c.getColumnCount(); col++) {
                if (c.getColumnName(col).equals(fieldName)) {

                }
            }

        }
    }

}
