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

package mk.gdx.firebase.auth;


import mk.gdx.firebase.PlatformDistributor;
import mk.gdx.firebase.distributions.AuthUserDistribution;
import mk.gdx.firebase.promises.Promise;

/**
 * Distribution for Firebase user methods.
 */
class GdxFIRUser extends PlatformDistributor<AuthUserDistribution> implements AuthUserDistribution {

    @Override
    public Promise<Void> updateEmail(String newEmail) {
        return platformObject.updateEmail(newEmail);
    }

    @Override
    public Promise<Void> sendEmailVerification() {
        return platformObject.sendEmailVerification();
    }

    @Override
    public Promise<Void> updatePassword(char[] newPassword) {
        return platformObject.updatePassword(newPassword);
    }

    @Override
    public Promise<Void> delete() {
        return platformObject.delete();
    }

    @Override
    protected String getIOSClassName() {
        return "mk.gdx.firebase.ios.auth.User";
    }

    @Override
    protected String getAndroidClassName() {
        return "mk.gdx.firebase.android.auth.User";
    }

    @Override
    protected String getWebGLClassName() {
        return "mk.gdx.firebase.html.auth.User";
    }
}
