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

package mk.gdx.firebase.database;

import com.badlogic.gdx.utils.Array;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import mk.gdx.firebase.listeners.DataChangeListener;

public class DataListenersManagerTest {

    @Test
    public void addNewListener() {
        // Given
        DataListenersManager<DataChangeListener> listenersManager = new DataListenersManager<>();
        DataChangeListener dataChangeListener = Mockito.mock(DataChangeListener.class);
        String path = "/test";

        // When
        listenersManager.addNewListener(path, dataChangeListener);

        // Then
        Assert.assertNotNull(listenersManager.getListeners(path));
        Assert.assertTrue(listenersManager.getListeners(path).size == 1);
        Assert.assertTrue(listenersManager.getListeners(path).get(0) == dataChangeListener);
    }

    @Test
    public void removeListenersForPath() {
        // Given
        DataListenersManager<DataChangeListener> listenersManager = new DataListenersManager<>();
        DataChangeListener dataChangeListener = Mockito.mock(DataChangeListener.class);
        String path = "/test";
        listenersManager.addNewListener(path, dataChangeListener);

        // When
        listenersManager.removeListenersForPath(path);

        // Then
        Assert.assertNotNull(listenersManager.getListeners(path));
        Assert.assertTrue(listenersManager.getListeners(path).size == 0);
    }

    @Test
    public void hasListeners() {
        // Given
        DataListenersManager<DataChangeListener> listenersManager = new DataListenersManager<>();
        DataChangeListener dataChangeListener = Mockito.mock(DataChangeListener.class);
        String path = "/test";
        String wrongPath = "/test_wrong";
        listenersManager.addNewListener(path, dataChangeListener);

        // When
        boolean hasListeners = listenersManager.hasListeners(path);
        boolean hasListeners2 = listenersManager.hasListeners(wrongPath);

        // Then
        Assert.assertTrue(hasListeners);
        Assert.assertFalse(hasListeners2);
    }

    @Test
    public void getListeners() {
        // Given
        DataListenersManager<DataChangeListener> listenersManager = new DataListenersManager<>();
        DataChangeListener dataChangeListener = Mockito.mock(DataChangeListener.class);
        String path = "/test";
        String wrongPath = "/test_wrong";
        listenersManager.addNewListener(path, dataChangeListener);

        // When
        Array<DataChangeListener> listenerArray = listenersManager.getListeners(path);
        Array<DataChangeListener> listenerArray2 = listenersManager.getListeners(wrongPath);

        // Then
        Assert.assertNotNull(listenerArray);
        Assert.assertTrue(listenerArray.size == 1);
        Assert.assertNotNull(listenerArray2);
        Assert.assertTrue(listenerArray2.size == 0);
    }
}