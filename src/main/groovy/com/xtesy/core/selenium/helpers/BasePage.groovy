/**
 * Copyright 2015, Wasiq Amjad Bhamla.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xtesy.core.selenium.helpers

import geb.Browser
import geb.Page
import groovy.util.logging.Log4j2

import com.xtesy.core.selenium.interactions.GenericActions

/**
 * @author Wasiq B
 * @since 17-Oct-2015 7:41:04 pm
 */
@Log4j2
class BasePage extends Page {
	protected GenericActions actions = null

	/*
	 * (non-Javadoc)
	 * @see geb.Page#init(geb.Browser)
	 */
	@Override
	public Page init (Browser browser) {
		actions = new GenericActions (browser)
		return super.init (browser)
	}
}