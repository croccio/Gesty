package com.croccio.gesty.controller;

import com.croccio.gesty.model.CondimentCategory;
import com.croccio.gesty.model.CondimentCategoryColumns;
import com.croccio.gesty.model.CondimentCategoryEntityManager;

import java.util.List;

import fr.xebia.android.freezer.async.Callback;

/**
 * Created by antonioscardigno on 24/11/17.
 */

public class CategoryCondimentController implements BaseController<CondimentCategory> {

    private CondimentCategoryEntityManager entityManager = new CondimentCategoryEntityManager();

    @Override
    public void save(CondimentCategory toSave, final SaveCallback<CondimentCategory> callback) {
        entityManager.addAsync(toSave, new Callback<CondimentCategory>() {
            @Override
            public void onSuccess(CondimentCategory data) {
                callback.onSaved(data);
            }

            @Override
            public void onError(CondimentCategory data) {
                callback.onNotSaved(data);
            }
        });
    }

    @Override
    public void delete(CondimentCategory toRemove, final DeleteCallback<CondimentCategory> callback) {
        entityManager.deleteAsync(toRemove, new Callback<CondimentCategory>() {
            @Override
            public void onSuccess(CondimentCategory data) {
                callback.onDeleted(data);
            }

            @Override
            public void onError(CondimentCategory data) {
                callback.onNotDeleted(data);
            }
        });
    }

    @Override
    public void update(CondimentCategory toUpdate, final UpdateCallback<CondimentCategory> callback) {
        entityManager.updateAsync(toUpdate, new Callback<CondimentCategory>() {
            @Override
            public void onSuccess(CondimentCategory data) {
                callback.onUpdated(data);
            }

            @Override
            public void onError(CondimentCategory data) {
                callback.onNotUpdated(data);
            }
        });
    }

    @Override
    public void retrieve(final RetrievedCallback<CondimentCategory> callback) {
        entityManager.select().sortDesc(CondimentCategoryColumns.name).async(new Callback<List<CondimentCategory>>() {
            @Override
            public void onSuccess(List<CondimentCategory> data) {
                callback.onRetrieved(data);
            }

            @Override
            public void onError(List<CondimentCategory> data) {
                callback.onCannotRetrieve();
            }
        });
    }

    public void retrieve(String name, final RetrieveFilteredCallback<CondimentCategory> callback) {
        entityManager.select().name().equalsTo(name).async(new Callback<List<CondimentCategory>>() {
            @Override
            public void onSuccess(List<CondimentCategory> data) {
                if (data.size() > 0) {
                    callback.onRetrieved(data.get(0));
                } else {
                    callback.onCannotRetrieve();
                }
            }

            @Override
            public void onError(List<CondimentCategory> data) {
                callback.onCannotRetrieve();
            }
        });
    }
}
