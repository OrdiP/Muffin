package com.muffin.theme.material.client.datetimepicker.constants;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2014 GwtBootstrap3
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

/**
 * Setter and getter for the language of the date time picker
 * <p/>
 * Be sure to load one language, it will use whatever is loaded last
 *
 * @author Joshua Godi
 * @see DateTimePickerLanguage
 */
public interface HasLanguage {
  void setLanguage(DateTimePickerLanguage language);

  DateTimePickerLanguage getLanguage();
}
