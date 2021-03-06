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

package mk.gdx.firebase.database.validators;


import com.badlogic.gdx.utils.Array;

import mk.gdx.firebase.distributions.DatabaseDistribution;
import mk.gdx.firebase.listeners.ConnectedListener;

/**
 * Validates arguments for {@link DatabaseDistribution#onConnect(ConnectedListener)}
 */
public class OnConnectionValidator implements ArgumentsValidator {

    private static final String MESSAGE = "The first argument should be null or ConnectedListener";

    @Override
    public void validate(Array<Object> arguments) {
        if (arguments.size == 0 || (arguments.get(0) != null && !(arguments.get(0) instanceof ConnectedListener)))
            throw new IllegalArgumentException(MESSAGE);
    }
}
