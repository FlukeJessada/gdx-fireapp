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

package mk.gdx.firebase.ios.database.queries;

import bindings.google.firebasedatabase.FIRDatabaseReference;
import mk.gdx.firebase.callbacks.CompleteCallback;
import mk.gdx.firebase.database.validators.ArgumentsValidator;
import mk.gdx.firebase.database.validators.RemoveValueValidator;
import mk.gdx.firebase.ios.database.Database;
import mk.gdx.firebase.ios.database.IosDatabaseQuery;
import mk.gdx.firebase.ios.database.observers.FIRDatabaseReferenceCompleteObserver;

/**
 * Provides call to {@link FIRDatabaseReference#removeValue()}.
 */
public class RemoveValueQuery extends IosDatabaseQuery<Void> {
    public RemoveValueQuery(Database databaseDistribution) {
        super(databaseDistribution);
    }

    @Override
    protected void prepare() {
        super.prepare();
        if (!(query instanceof FIRDatabaseReference))
            throw new IllegalStateException(SHOULD_BE_RUN_WITH_DATABASE_REFERENCE);
    }

    @Override
    protected ArgumentsValidator createArgumentsValidator() {
        return new RemoveValueValidator();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Void run() {
        if (arguments.size == 0) {
            ((FIRDatabaseReference) query).removeValue();
        } else if (arguments.size == 1) {
            ((FIRDatabaseReference) query).removeValueWithCompletionBlock(new FIRDatabaseReferenceCompleteObserver((CompleteCallback) arguments.get(0)));
        }
        return null;
    }
}
