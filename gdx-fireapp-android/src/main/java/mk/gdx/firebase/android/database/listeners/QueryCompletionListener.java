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

package mk.gdx.firebase.android.database.listeners;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import mk.gdx.firebase.callbacks.CompleteCallback;

/**
 * Wraps {@link CompleteCallback} with {@link DatabaseReference.CompletionListener}
 */
public class QueryCompletionListener implements DatabaseReference.CompletionListener {

    private CompleteCallback completeCallback;

    public QueryCompletionListener(CompleteCallback completeCallback) {
        this.completeCallback = completeCallback;
    }

    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        if (databaseError != null) {
            completeCallback.onError(databaseError.toException());
        } else {
            completeCallback.onSuccess();
        }
    }
}
