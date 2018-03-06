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

package mk.gdx.firebase.android.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import mk.gdx.firebase.database.OrderByResolver;
import mk.gdx.firebase.database.pojos.OrderByClause;

/**
 * Applies OrderByClause to the Query instance.
 */
public class AndroidOrderByResolver implements OrderByResolver<DatabaseReference, Query>
{
    @Override
    public Query resolve(OrderByClause orderByClause, DatabaseReference target)
    {
        switch (orderByClause.getOrderByMode()) {
            case ORDER_BY_CHILD:
                return target.orderByChild(orderByClause.getArgument());
            case ORDER_BY_KEY:
                return target.orderByKey();
            case ORDER_BY_VALUE:
                return target.orderByValue();
            default:
                throw new IllegalStateException();
        }
    }
}