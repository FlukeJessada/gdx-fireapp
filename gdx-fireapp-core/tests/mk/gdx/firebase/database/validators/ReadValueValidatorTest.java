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

package mk.gdx.firebase.database.validators;

import com.badlogic.gdx.utils.Array;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import mk.gdx.firebase.callbacks.DataCallback;
import mk.gdx.firebase.listeners.DataChangeListener;

import static org.junit.Assert.*;

public class ReadValueValidatorTest {

    @Test
    public void validate() {
        // Given
        ReadValueValidator validator = new ReadValueValidator();
        Array arguments = new Array();
        arguments.addAll(String.class, Mockito.mock(DataCallback.class));

        // When
        validator.validate(arguments);

        // Then
        // no exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_error() {
        // Given
        ReadValueValidator validator = new ReadValueValidator();
        Array arguments = new Array();

        // When
        validator.validate(arguments);

        // Then
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_error2() {
        // Given
        ReadValueValidator validator = new ReadValueValidator();
        Array arguments = new Array();
        arguments.addAll(String.class);

        // When
        validator.validate(arguments);

        // Then
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_error3() {
        // Given
        ReadValueValidator validator = new ReadValueValidator();
        Array arguments = new Array();
        arguments.addAll(String.class, String.class);

        // When
        validator.validate(arguments);

        // Then
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_error4() {
        // Given
        ReadValueValidator validator = new ReadValueValidator();
        Array arguments = new Array();
        arguments.addAll(String.class, Mockito.mock(DataCallback.class), Mockito.mock(DataCallback.class));

        // When
        validator.validate(arguments);

        // Then
        Assert.fail();
    }
}