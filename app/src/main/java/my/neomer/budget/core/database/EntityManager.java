package my.neomer.budget.core.database;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import my.neomer.budget.models.Entity;

public interface EntityManager<IdType, EntityType extends Entity<IdType> > {

    @NonNull
    String getTableName();

    @Nullable
    EntityType getById(IdType id);

    @NonNull
    List<EntityType> findAll();

}
