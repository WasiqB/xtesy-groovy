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
package com.xtesy.core.ui.selenium.interactions

import geb.Browser
import groovy.util.logging.Log4j2

import org.openqa.selenium.*
import org.openqa.selenium.interactions.*

/**
 * @author wasiq.bhamla
 * @since Oct 15, 2015 4:02:46 PM
 */
@Log4j2
class GenericActions {
	private final Browser browser
	private def orgStyle = ""

	private def style = { element -> element.@style }
	GenericActions(Browser browser) {
		this.browser = browser
	}

	/**
	 * @author wasiq.bhamla
	 * @since Oct 16, 2015 4:38:11 PM
	 * @param element
	 * @return
	 */
	def click (element) {
		log.entry ()
		Actions action = new Actions(browser.driver)
		browser.config.rawConfig.with {
			pause (delayBeforeAnyOperation)
			hover element
			pause (delayBeforeMouseDown)
			action.clickAndHold (element.firstElement ()).perform ()
			pause (delayBeforeMouseUp)
			action.release (element.firstElement ()).perform ()
		}
		log.exit ()
	}

	/**
	 * @author wasiq.bhamla
	 * @since Oct 14, 2015 12:55:39 PM
	 * @param element
	 * @return
	 */
	def click (element, identifier) {
		log.entry ()
		highlight element, identifier
		unhighlight element
		click element
		log.info "Clicked on [${identifier}]"
		log.exit ()
	}

	/**
	 * @author wasiq.bhamla
	 * @since Oct 16, 2015 7:38:37 PM
	 * @param element
	 * @param text
	 * @return
	 */
	def enterText (element, text) {
		log.entry ()
		Actions action = new Actions (browser.driver)
		browser.config.rawConfig.with {
			text.each { c ->
				pause (delayBeforeKeyPress)
				element << c
				pause (delayAfterKeyPress)
			}
		}
		log.exit ()
	}

	/**
	 * @author wasiq.bhamla
	 * @since Oct 14, 2015 12:55:15 PM
	 * @param element
	 * @param text
	 * @return
	 */
	def enterText (element, text, identifier) {
		try {
			log.entry ()
			highlight element, identifier
			click element
			element.firstElement ().clear ()
			enterText element, text
			log.info "[${text}] entered in [${identifier}] field"
		}
		finally {
			unhighlight element
			log.exit ()
		}
	}

	/**
	 * @author wasiq.bhamla
	 * @since Oct 14, 2015 12:56:14 PM
	 * @param element
	 * @return
	 */
	def highlight (element, identifier) {
		log.entry ()
		if (browser.config.rawConfig.highlightElementOnRun) {
			orgStyle = style element
			JavascriptExecutor jsExec = browser.driver as JavascriptExecutor
			jsExec.executeScript ("arguments[0].setAttribute('style', arguments[1]);", element.firstElement(), "color: red; border: 3px solid red;")
			pause browser.config.rawConfig.delayWhenHighlightElement
			log.info "[${identifier}] field highlighted"
		}
		log.exit ()
	}

	/**
	 * @author wasiq.bhamla
	 * @since Oct 16, 2015 4:38:02 PM
	 * @param element
	 * @return
	 */
	def hover (element) {
		log.entry ()
		Actions action = new Actions (browser.driver)
		browser.config.rawConfig.with {
			pause (delayBeforeMouseMove)
			action.moveToElement (element.firstElement ()).perform ()
			pause (delayWhenHover)
		}
		log.exit ()
	}

	/**
	 * @author wasiq.bhamla
	 * @since Oct 14, 2015 12:55:24 PM
	 * @param secs
	 * @return
	 */
	def pause (secs) {
		log.entry ()
		if (secs && secs > 0) {
			log.trace "Pausing for [${secs}] secs"
			def originalMilliseconds = System.currentTimeMillis()
			browser.page.waitFor(secs + 1) {
				(System.currentTimeMillis() - originalMilliseconds) > (secs * 1000)
			}
			log.trace "Resuming after [${secs}] secs"
		}
		log.exit ()
	}

	/**
	 * @author wasiq.bhamla
	 * @since Oct 14, 2015 12:55:54 PM
	 * @param element
	 * @return
	 */
	def unhighlight (element) {
		log.entry ()
		if (browser.config.rawConfig.highlightElementOnRun) {
			JavascriptExecutor jsExec = browser.driver as JavascriptExecutor
			jsExec.executeScript ("arguments[0].setAttribute('style', arguments[1]);", element.firstElement(), orgStyle)
		}
		log.exit ()
	}
}