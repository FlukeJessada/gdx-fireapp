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

package mk.gdx.firebase.android.auth;

import com.google.firebase.auth.FirebaseAuth;

import mk.gdx.firebase.distributions.AuthUserDistribution;
import mk.gdx.firebase.promises.FuturePromise;
import mk.gdx.firebase.promises.Promise;

/**
 * @see AuthUserDistribution
 */
public class User implements AuthUserDistribution {

    @Override
    public Promise<Void> updateEmail(String newEmail) {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            throw new IllegalStateException();
        }
        return FuturePromise.of(new VoidPromiseConsumer<>(FirebaseAuth.getInstance().getCurrentUser()
                .updateEmail(newEmail)));
    }

    @Override
    public Promise<Void> sendEmailVerification() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            throw new IllegalStateException();
        }
        return FuturePromise.of(new VoidPromiseConsumer<>(FirebaseAuth.getInstance().getCurrentUser()
                .sendEmailVerification()));
    }

    @Override
    public Promise<Void> updatePassword(char[] newPassword) {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            throw new IllegalStateException();
        }
        return FuturePromise.of(new VoidPromiseConsumer<>(FirebaseAuth.getInstance().getCurrentUser()
                .updatePassword(new String(newPassword))));
    }

    @Override
    public Promise<Void> delete() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            throw new IllegalStateException();
        }
        return FuturePromise.of(new VoidPromiseConsumer<>(FirebaseAuth.getInstance().getCurrentUser().delete()));
    }
}
