/*
 * Copyright 2017 mk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mk.gdx.firebase.html.database;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import java.util.Map;

import mk.gdx.firebase.callbacks.CompleteCallback;
import mk.gdx.firebase.callbacks.DataCallback;
import mk.gdx.firebase.callbacks.TransactionCallback;
import mk.gdx.firebase.database.FilterType;
import mk.gdx.firebase.database.OrderByMode;
import mk.gdx.firebase.database.pojos.Filter;
import mk.gdx.firebase.database.pojos.OrderByClause;
import mk.gdx.firebase.distributions.DatabaseDistribution;
import mk.gdx.firebase.exceptions.DatabaseReferenceNotSetException;
import mk.gdx.firebase.html.database.queries.ConnectionStatusQuery;
import mk.gdx.firebase.html.database.queries.OnDataChangeQuery;
import mk.gdx.firebase.html.database.queries.PushQuery;
import mk.gdx.firebase.html.database.queries.ReadValueQuery;
import mk.gdx.firebase.html.database.queries.RemoveValueQuery;
import mk.gdx.firebase.html.database.queries.RunTransactionQuery;
import mk.gdx.firebase.html.database.queries.SetValueQuery;
import mk.gdx.firebase.html.database.queries.UpdateChildrenQuery;
import mk.gdx.firebase.listeners.ConnectedListener;
import mk.gdx.firebase.listeners.DataChangeListener;

/**
 * GWT Firebase API implementation.
 *
 * @see DatabaseDistribution
 */
public class Database implements DatabaseDistribution {

    private String refPath;
    private final Array<Filter> filters;
    private OrderByClause orderByClause;

    public Database() {
        filters = new Array<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConnect(final ConnectedListener connectedListener) {
        new ConnectionStatusQuery(this).withArgs(connectedListener).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabaseDistribution inReference(String databasePath) {
        refPath = databasePath;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(Object value) {
        new SetValueQuery(this).withArgs(value).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(Object value, CompleteCallback completeCallback) {
        new SetValueQuery(this).withArgs(value, completeCallback).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T, R extends T> void readValue(final Class<T> dataType, final DataCallback<R> callback) {
        new ReadValueQuery(this).with(filters).with(orderByClause).withArgs(dataType, callback).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T, R extends T> void onDataChange(final Class<T> dataType, final DataChangeListener<R> listener) {
        new OnDataChangeQuery(this).with(filters).with(orderByClause).withArgs(dataType, listener).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <V> DatabaseDistribution filter(FilterType filterType, V... filterArguments) {
        filters.add(new Filter(filterType, filterArguments));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabaseDistribution orderBy(OrderByMode orderByMode, String argument) {
        orderByClause = new OrderByClause(orderByMode, argument);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatabaseDistribution push() {
        new PushQuery(this).execute();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeValue() {
        new RemoveValueQuery(this).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeValue(final CompleteCallback completeCallback) {
        new RemoveValueQuery(this).withArgs(completeCallback).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateChildren(final Map<String, Object> data) {
        new UpdateChildrenQuery(this).withArgs(data).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateChildren(final Map<String, Object> data, final CompleteCallback completeCallback) {
        new UpdateChildrenQuery(this).withArgs(data, completeCallback).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T, R extends T> void transaction(final Class<T> dataType, final TransactionCallback<R> transactionCallback, final CompleteCallback completeCallback) {
        new RunTransactionQuery(this).withArgs(dataType, transactionCallback, completeCallback).execute();
    }

    /**
     * Firebase web api does not support this feature.
     * <p>
     *
     * @param enabled If true persistence will be enabled.
     */
    @Override
    public void setPersistenceEnabled(boolean enabled) {
        Gdx.app.log("GdxFireapp", "No such feature on firebase web platform.");
    }

    /**
     * Firebase web api does not support this feature.
     *
     * @param synced If true sync for specified database path will be enabled
     */
    @Override
    public void keepSynced(boolean synced) {
        Gdx.app.log("GdxFireapp", "No such feature on firebase web platform.");
    }

    /**
     * Gets firebase database path from local var or throw exception when it is null.
     *
     * @return Database reference path. Every action will be deal with it.
     * @throws DatabaseReferenceNotSetException It is thrown when user forgot to call {@link #inReference(String)}
     */
    String databaseReference() {
        if (refPath == null)
            throw new DatabaseReferenceNotSetException("Please call GdxFIRDatabase#inReference() first.");
        return refPath;
    }

    /**
     * Reset {@link #refPath} to initial state.
     * <p>
     * After each flow-terminate operation {@link #refPath} should be reset the initial value,
     * it forces the users to call {@link #inReference(String)} before each flow-terminate operation.
     * <p>
     * Flow-terminate operations are: <uL>
     * <li>{@link #setValue(Object)}</li>
     * <li>{@link #setValue(Object, CompleteCallback)}</li>
     * <li>{@link #readValue(Class, DataCallback)}</li>
     * <li>{@link #onDataChange(Class, DataChangeListener)}</li>
     * <li>{@link #updateChildren(Map)}</li>
     * <li>{@link #updateChildren(Map, CompleteCallback)}</li>
     * <li>{@link #transaction(Class, TransactionCallback, CompleteCallback)}</li>
     * </uL>
     */
    void terminateOperation() {
        refPath = null;
        filters.clear();
        orderByClause = null;
    }

}
