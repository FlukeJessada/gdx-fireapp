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

package mk.gdx.firebase.html.database.queries;

import mk.gdx.firebase.database.validators.ArgumentsValidator;
import mk.gdx.firebase.html.database.Database;
import mk.gdx.firebase.html.database.GwtDatabaseQuery;

/**
 * Provides push javascript execution.
 */
public class PushQuery extends GwtDatabaseQuery {
    public PushQuery(Database databaseDistribution) {
        super(databaseDistribution);
    }

    @Override
    protected void runJS() {
        // TODO - update db distribution databaseReferencePath
        push(databaseReferencePath);
    }

    @Override
    protected ArgumentsValidator createArgumentsValidator() {
        return null;
    }

    /**
     * Calls firebase.database().push method.
     * <p>
     * You can read more here: <a href="https://firebase.google.com/docs/reference/js/firebase.database.Reference#push">https://firebase.google.com/docs/reference/js/firebase.database.Reference#push</a>
     *
     * @param reference Reference path, not null
     * @return A new reference path
     */
    public static native String push(String reference) /*-{
        return $wnd.firebase.database().ref(reference).push().path["Q"].join("/");
    }-*/;
}
