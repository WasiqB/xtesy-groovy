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
package com.xtesy.core.ui.selenium.helpers

import geb.testng.GebReportingTest
import groovy.util.logging.Log4j2

import org.openqa.selenium.Dimension
import org.testng.annotations.BeforeSuite

import com.xtesy.core.ui.selenium.interactions.GenericActions

/**
 * @author Wasiq B
 * @since 17-Oct-2015 7:42:42 pm
 */
@Log4j2
class BaseTestNG extends GebReportingTest {
	protected GenericActions action

	/**
	 * @author wasiq.bhamla
	 * @since Oct 16, 2015 8:14:59 PM
	 */
	@BeforeSuite
	void setup () {
		action = new GenericActions (browser)
		def window = browser.driver.manage ().window()
		browser.config.rawConfig.with {
			action.pause (delayAfterWindowActivation)
			if (maximizeBrowser) {
				window.maximize ()
				log.info "Window Maximized."
			}
			if (browserResolution) {
				def dimensions = browserResolution.split('x')
				Dimension d = new Dimension (dimensions[0] as int, dimensions[1] as int)
				window.size = d
				log.info "Browser Resolution set to [${browserResolution}]"
			}
		}
	}
}