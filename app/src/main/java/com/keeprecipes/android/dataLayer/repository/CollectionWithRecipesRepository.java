package com.keeprecipes.android.dataLayer.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.keeprecipes.android.dataLayer.AppDatabase;
import com.keeprecipes.android.dataLayer.dao.CollectionWithRecipesDao;
import com.keeprecipes.android.dataLayer.dao.RecipeWithCollectionsDao;
import com.keeprecipes.android.dataLayer.entities.CollectionRecipeCrossRef;
import com.keeprecipes.android.dataLayer.entities.CollectionWithRecipes;
import com.keeprecipes.android.dataLayer.entities.RecipeWithCollections;

import java.util.List;
import java.util.concurrent.Executors;

public class CollectionWithRecipesRepository {

    CollectionWithRecipesDao collectionWithRecipesDao;
    RecipeWithCollectionsDao recipeWithCollectionsDao;

    private LiveData<List<CollectionWithRecipes>> collectionWithRecipesList;

    private LiveData<List<RecipeWithCollections>> recipeWithCollections;

    public CollectionWithRecipesRepository(Application application) {
        collectionWithRecipesDao = AppDatabase.getDatabase(application).collectionWithRecipesDao();
        recipeWithCollectionsDao = AppDatabase.getDatabase(application).recipeWithCollectionsDao();
        collectionWithRecipesList = collectionWithRecipesDao.getCollectionWithRecipes();
    }

    public LiveData<List<CollectionWithRecipes>> getCollectionWithRecipesList() {
        return collectionWithRecipesList;
    }

    public LiveData<List<RecipeWithCollections>> getRecipeWithCollections(long id) {
        recipeWithCollections = recipeWithCollectionsDao.getRecipeWithCollections(id);
        return recipeWithCollections;
    }

    public void insert(CollectionRecipeCrossRef collectionWithRecipes) {
        Executors.newSingleThreadExecutor().execute(() -> {
            collectionWithRecipesDao.insert(collectionWithRecipes);
        });
    }
}
