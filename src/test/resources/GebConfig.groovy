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
import org.openqa.selenium.firefox.FirefoxDriver

// Default Driver to use.
driver = { new FirefoxDriver() }
//baseUrl = "http://172.22.31.227/"
//website {
//	userId = "automationuser"
//	pwd = "aurionpro@2013"
//}

// Playback Settings.
highlightElementOnRun = true
delayWhenHighlightElement = 0.2
maximizeBrowser = true
browserResolution = "1024x768"

// TODO Start
// Logging.
logScreenshotForEachAction = false
logScreenshotWhenPlaybackFails = false
logActionsOnApplication = true
displayLogAfterTestComplete = false
// TODO End

// Delays.
delayBeforeMouseUp = 0.01
delayBeforeMouseMove = 0.01
delayBeforeMouseDown = 0.01
delayWhenHover = 0.01
delayBeforeAnyOperation = 0.01
delayBeforeKeyPress = 0.01
delayAfterKeyPress = 0.01
delayAfterWindowActivation = 0.01

// Environment wise settings.
//environments {
//	fit {
//		baseUrl = "http://172.22.31.227/"
//		website {
//			userId = "automationuser"
//			pwd = "aurionpro@2013"
//		}
//	}
//	normal {
//		baseUrl = "http://172.22.31.114:81/"
//		website {
//			userId = "admin"
//			pwd = "abc"
//		}
//	}
//}

// Other Geb Settings.
reportsDir = new File("target/geb-reports")
baseNavigatorWaiting = true
atCheckWaiting = true
includeCauseInMessage = true
cacheDriverPerThread = true

waiting {
	presets {
		slow {
			timeout = 30
			retryInterval = 1
		}
		quick { timeout = 1 }
	}
	timeout = 2
}