/*
 * Copyright 2014 Nathan Jones
 *
 * This file is part of "EditorConfig Eclipse".
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
package com.ncjones.ece;

public enum ConfigType {

	INDENT_STYLE("Indent Style", new EnumValueParser(IndentStyleOption.class)),

	INDENT_SIZE("Indent Size", ValueParser.POSITIVE_INT_VALUE_PARSER),

	TAB_WIDTH("Tab Width", ValueParser.POSITIVE_INT_VALUE_PARSER),

	END_OF_LINE("End of Line", new EnumValueParser(EndOfLineOption.class)),

	CHARSET("Charset", ValueParser.IDENTITY_VALUE_PARSER),

	TRIM_TRAILING_WHITESPACE("Trim Trailing Whitespace", ValueParser.BOOLEAN_VALUE_PARSER),

	INSERT_FINAL_NEWLINE("Insert Final Newline", ValueParser.BOOLEAN_VALUE_PARSER);

	private final String label;

	private final ValueParser valueParser;

	private ConfigType(final String label, final ValueParser valueParser) {
		this.label = label;
		this.valueParser = valueParser;
	}

	public String getDisplayLabel() {
		return label;
	}

	public ConfigValue createConfigValue(final String value) {
		final Object parsedValue = valueParser.parse(value);
		if (parsedValue == null) {
			return null;
		}
		return new ConfigValue(this, parsedValue);
	}

}
