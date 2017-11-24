package com.croccio.gesty.controller;

import java.util.List;

/**
 * Created by antonioscardigno on 24/11/17.
 */

public interface BaseController<T> {
    void save(T toSave, SaveCallback<T> callback);

    void delete(T toRemove, DeleteCallback<T> callback);

    void update(T toUpdate, UpdateCallback<T> callback);

    void retrieve(RetrievedCallback<T> callback);

    public interface SaveCallback<T> {
        void onSaved(T saved);
        void onNotSaved(T notSaved);
    }

    public interface DeleteCallback<T> {
        void onDeleted(T deleted);
        void onNotDeleted(T notDeleted);
    }

    public interface UpdateCallback<T> {
        void onUpdated(T updated);
        void onNotUpdated(T notUpdated);
    }

    public interface RetrievedCallback<T> {
        void onRetrieved(List<T> retrieved);
        void onCannotRetrieve();
    }

    public interface RetrieveFilteredCallback<T> {
        void onRetrieved(T retrieved);
        void onCannotRetrieve();
    }
}
