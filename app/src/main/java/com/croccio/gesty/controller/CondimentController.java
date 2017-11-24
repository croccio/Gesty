package com.croccio.gesty.controller;

import com.croccio.gesty.model.Condiment;
import com.croccio.gesty.model.CondimentColumns;
import com.croccio.gesty.model.CondimentEntityManager;

import java.util.List;

import fr.xebia.android.freezer.async.Callback;

/**
 * Created by antonioscardigno on 24/11/17.
 */

public class CondimentController implements BaseController<Condiment> {

    private CondimentEntityManager entityManager = new CondimentEntityManager();

    @Override
    public void save(Condiment toSave, final SaveCallback<Condiment> callback) {
        toSave.setPriority(entityManager.count());
        entityManager.addAsync(toSave, new Callback<Condiment>() {
            @Override
            public void onSuccess(Condiment data) {
                callback.onSaved(data);
            }

            @Override
            public void onError(Condiment data) {
                callback.onNotSaved(data);
            }
        });
    }

    @Override
    public void delete(Condiment toRemove, final DeleteCallback<Condiment> callback) {
        entityManager.deleteAsync(toRemove, new Callback<Condiment>() {
            @Override
            public void onSuccess(Condiment data) {
                callback.onDeleted(data);
            }

            @Override
            public void onError(Condiment data) {
                callback.onNotDeleted(data);
            }
        });
    }

    @Override
    public void update(Condiment toUpdate, final UpdateCallback<Condiment> callback) {
        entityManager.updateAsync(toUpdate, new Callback<Condiment>() {
            @Override
            public void onSuccess(Condiment data) {
                callback.onUpdated(data);
            }

            @Override
            public void onError(Condiment data) {
                callback.onNotUpdated(data);
            }
        });
    }

    @Override
    public void retrieve(final RetrievedCallback<Condiment> callback) {
        entityManager.select().sortDesc(CondimentColumns.name).async(new Callback<List<Condiment>>() {
            @Override
            public void onSuccess(List<Condiment> data) {
                callback.onRetrieved(data);
            }

            @Override
            public void onError(List<Condiment> data) {
                callback.onCannotRetrieve();
            }
        });
    }
}
