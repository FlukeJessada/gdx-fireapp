/*
 * Copyright 2018 mk
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

package mk.gdx.firebase.html.auth;

import mk.gdx.firebase.callbacks.CompleteCallback;
import mk.gdx.firebase.distributions.AuthUserDistribution;

/**
 * @see AuthUserDistribution
 */
public class User implements AuthUserDistribution {

    @Override
    public void updateEmail(String newEmail, CompleteCallback callback) {
        if (AuthJS.firebaseUser().isNULL()) {
            throw new IllegalStateException();
        }
        AuthJS.firebaseUser().updateEmail(newEmail, callback);
    }

    @Override
    public void sendEmailVerification(CompleteCallback callback) {
        if (AuthJS.firebaseUser().isNULL()) {
            throw new IllegalStateException();
        }
        AuthJS.firebaseUser().sendEmailVerification(callback);
    }

    @Override
    public void updatePassword(char[] newPassword, CompleteCallback callback) {
        if (AuthJS.firebaseUser().isNULL()) {
            throw new IllegalStateException();
        }
        AuthJS.firebaseUser().updatePassword(new String(newPassword), callback);
    }

    @Override
    public void delete(CompleteCallback callback) {
        if (AuthJS.firebaseUser().isNULL()) {
            throw new IllegalStateException();
        }
        AuthJS.firebaseUser().delete(callback);
    }

    @Override
    public void reload(CompleteCallback callback) {
        if (AuthJS.firebaseUser().isNULL()) {
            throw new IllegalStateException();
        }
        AuthJS.firebaseUser().reload(callback);
    }
}
