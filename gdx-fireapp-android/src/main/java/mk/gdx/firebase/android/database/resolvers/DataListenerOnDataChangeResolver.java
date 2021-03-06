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

package mk.gdx.firebase.android.database.resolvers;

import com.google.firebase.database.DataSnapshot;

import mk.gdx.firebase.database.pojos.OrderByClause;
import mk.gdx.firebase.listeners.DataChangeListener;

/**
 * Resolves data listener with ordering preserved.
 */
public class DataListenerOnDataChangeResolver {

    private DataListenerOnDataChangeResolver() {
        //
    }

    // TODO - docs
    @SuppressWarnings("unchecked")
    public static <T, E extends T> void resolve(Class<T> dataType, OrderByClause orderByClause, DataSnapshot dataSnapshot, DataChangeListener<E> dataChangeListener) {
        if (DataSnapshotOrderByResolver.shouldResolveOrderBy(orderByClause, dataType, dataSnapshot)) {
            dataChangeListener.onChange((E) DataSnapshotOrderByResolver.resolve(dataSnapshot));
        } else {
            dataChangeListener.onChange((E) dataSnapshot.getValue());
        }
    }
}
