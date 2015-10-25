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
package com.xtesy.core.selenium.builder

/**
 * @author Wasiq B
 * @since 25-Oct-2015 4:14:02 pm
 */
class PageBuilder {
	private GroovyClassLoader loader
	String name
	private Class cls
	private def imports
	private def methods
	private def fields

	/**
	 * @author Wasiq B
	 * @since 25-Oct-2015 4:27:14 pm
	 * @param loader
	 */
	def PageBuilder (GroovyClassLoader loader) {
		this.loader = loader
		imports = []
		fields = [:]
		methods = [:]
	}

	/**
	 * @author Wasiq B
	 * @since 25-Oct-2015 4:25:43 pm
	 * @return
	 */
	def getCreatedClass() {
		def templateText = '''
<%imports.each {%>import $it\n <% } %>
class $name
{
<%fields.each {%>    $it.value $it.key \n<% } %>
}
'''
		def data = [name: name, imports: imports, fields: fields]
		def engine = new groovy.text.SimpleTemplateEngine()
		def template = engine.createTemplate(templateText)
		def result = template.make(data)
		cls = loader.parseClass(result.toString())
		methods.each {
			cls.metaClass."$it.key" = it.value
		}
		return cls
	}

	/**
	 * @author Wasiq B
	 * @since 25-Oct-2015 4:25:52 pm
	 * @param name
	 * @param closure
	 */
	void addMethod(String name, Closure closure) {
		methods[name] = closure
	}

	/**
	 * @author Wasiq B
	 * @since 25-Oct-2015 4:25:58 pm
	 * @param name
	 * @param type
	 */
	void addField(String name, Class type) {
		fields[name] = type.simpleName
	}

	/**
	 * @author Wasiq B
	 * @since 25-Oct-2015 4:26:06 pm
	 * @param importCls
	 */
	void addImports (Class importCls) {
		imports << "${importCls.getPackage().getName()}.${importCls.getSimpleName()}"
	}
}