/*  
 *  This file is part of Tester.
 *
 *  Tester is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Tester is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Tester.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2013 Konrad Siek <konrad.siek@gmail.com>
 */
 
package pl.siek.tester;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationDefaultErrors extends ConfigurationDefault {

	public static final String ERROR_OPENING_LOG_FILE = "cannot open log file";
	public static final String ERROR_CLOSING_LOG_FILE = "cannot close log file";
	public static final String ERROR_WRITING_TO_LOG_FILE = "cannot write to log file";
	public static final String FILE_DOESNT_EXIST_OR_EMPTY = "file doesn't exist or empty";
	public static final String WRONG_FILE_FORMAT = "wrong file format";
	public static final String NO_QUESTION = "no_question";
	public static final String NO_VALID_ANSWERS = "no valid answers";
	public static final String OPERATION_UNSUCCESSFUL = "operation unsuccessfull";
	public static final String CONFIGURATION_FILE_ERROR = "Configuration File Error";
	public static final String ERROR_COPYING_FILE = "Error copying file";
	public static final String ERROR_WRITING_FILE = "Error writing file";
	public static final String CANNOT_MAKE_DIR = "cannot make dir";
	public static final String CONCERNING_INPUT = "concerning input";
	public static final String CONCERNING_MESSAGES = "concerning messages";
	public static final String CONCERNING_OPTIONS = "concerning options";
	public static final String CONCERNING_REGEX = "concerning regex";
	public static final String CONCERNING_RULES = "concerning rules";
	public static final String DELETING_NOT_SUCCESSFUL = "deleting not successful";
	public static final String DO_YOU_WANT_TO_REPLACE_IT = "do you want to replace it";
	public static final String ERROR_READING_FILE = "error reading file";
	public static final String ERROR = "error";
	public static final String FILE_ALREADY_EXISTS = "file already exists";
	public static final String INPUT_NOT_SET_SUCCESSFULLY = "input not set successfully";
	public static final String INVALID_ROOT_DIR = "invalid root dir";
	public static final String LOADING_DEFAULT_SETTINGS = "loading default settings";
	public static final String MALFORMED_URL = "malformed url";
	public static final String NO_CONNECTION_TO_URL = "no connection to url";
	public static final String NO_KEY = "no key";
	public static final String NO_STATE = "no state";
	public static final String NOT_POSSIBLE_OPTION_CHOICE = "not possible option choice";
	public static final String POSSIBLE_ENTRIES = "possible entries";
	public static final String UNKNOWN = "unknown";
	public static final String WARNING = "warning";
	public static final String CONCERNING = "concerning";
	public static final String CONCERNING_FILE_PATHS = "concerning file paths";
	public static final String CONCERNING_ERRORS = "concerning errors";

	public static Map<String, ConfigurationItem> getDefaultConfig() {
		var map = new HashMap<String, ConfigurationItem>();
		map.put(CONFIGURATION_FILE_ERROR, new ConfigurationItem(
				CONFIGURATION_FILE_ERROR, "Configuration File Error", null,
				null));
		map.put(ERROR_COPYING_FILE, new ConfigurationItem(ERROR_COPYING_FILE,
				"Error Copying File!", null, null));
		map.put(ERROR_WRITING_FILE, new ConfigurationItem(ERROR_WRITING_FILE,
				"Error Writing File!", null, null));
		map.put(CANNOT_MAKE_DIR, new ConfigurationItem(CANNOT_MAKE_DIR,
				"Cannot Create Directory", null, null));
		map.put(CONCERNING_INPUT, new ConfigurationItem(CONCERNING_INPUT,
				"Test Material", null, null));
		map.put(CONCERNING_MESSAGES, new ConfigurationItem(CONCERNING_MESSAGES,
				"Components and Message Captions", null, null));
		map.put(CONCERNING_OPTIONS, new ConfigurationItem(CONCERNING_OPTIONS,
				"Program Options", null, null));
		map
				.put(
						CONCERNING_REGEX,
						new ConfigurationItem(
								CONCERNING_REGEX,
								"Internal Regular Expressions/Internal Input and Output Format",
								null, null));
		map.put(CONCERNING_RULES, new ConfigurationItem(CONCERNING_RULES,
				"Data Input Substitution List", null, null));
		map.put(DELETING_NOT_SUCCESSFUL,
				new ConfigurationItem(DELETING_NOT_SUCCESSFUL,
						"Deleting Not Successful!", null, null));
		map.put(DO_YOU_WANT_TO_REPLACE_IT, new ConfigurationItem(
				DO_YOU_WANT_TO_REPLACE_IT,
				"Do you want to replace it<QUESTION>", null, null));
		map.put(ERROR_READING_FILE, new ConfigurationItem(ERROR_READING_FILE,
				"Error reading file:", null, null));
		map.put(ERROR, new ConfigurationItem(ERROR, "Error", null, null));
		map.put(FILE_ALREADY_EXISTS, new ConfigurationItem(FILE_ALREADY_EXISTS,
				"Such a file is already present:", null, null));
		map.put(INPUT_NOT_SET_SUCCESSFULLY, new ConfigurationItem(
				INPUT_NOT_SET_SUCCESSFULLY, "Option Not Updated!", null, null));
		map.put(INVALID_ROOT_DIR, new ConfigurationItem(INVALID_ROOT_DIR,
				"Root directory is invalid", null, null));
		map.put(LOADING_DEFAULT_SETTINGS, new ConfigurationItem(
				LOADING_DEFAULT_SETTINGS, "Default Settings are Loaded", null,
				null));
		map.put(MALFORMED_URL, new ConfigurationItem(MALFORMED_URL,
				"Malformed URL:", null, null));
		map.put(NO_CONNECTION_TO_URL, new ConfigurationItem(
				NO_CONNECTION_TO_URL, "No connection to URL:", null, null));
		map.put(NO_KEY, new ConfigurationItem(NO_KEY,
				"Cannot generate option without a key.", null, null));
		map.put(NO_STATE, new ConfigurationItem(NO_STATE,
				"Cannot generate option without a state.", null, null));
		map.put(NOT_POSSIBLE_OPTION_CHOICE, new ConfigurationItem(
				NOT_POSSIBLE_OPTION_CHOICE, "No such option can be set.", null,
				null));
		map.put(POSSIBLE_ENTRIES, new ConfigurationItem(POSSIBLE_ENTRIES,
				"Here is a list of possible entries:", null, null));
		map.put(UNKNOWN, new ConfigurationItem(UNKNOWN, "unknown", null, null));
		map.put(WARNING, new ConfigurationItem(WARNING, "Warning", null, null));
		map.put(CONCERNING, new ConfigurationItem(CONCERNING, "Concerning",
				null, null));
		map.put(CONCERNING_FILE_PATHS, new ConfigurationItem(
				CONCERNING_FILE_PATHS, "Concerning File Paths", null, null));
		map.put(CONCERNING_ERRORS, new ConfigurationItem(CONCERNING_ERRORS,
				"Concerning Error Messages", null, null));
		map.put(OPERATION_UNSUCCESSFUL, new ConfigurationItem(
				OPERATION_UNSUCCESSFUL, "Operation was Unsuccessful", null,
				null));
		map
				.put(
						NO_VALID_ANSWERS,
						new ConfigurationItem(
								NO_VALID_ANSWERS,
								"All answers are invalid. Cannot generate an item without answers.",
								null, null));
		map.put(NO_QUESTION, new ConfigurationItem(NO_QUESTION,
				"Cannot generate an item without question or file path.", null,
				null));
		map
				.put(
						WRONG_FILE_FORMAT,
						new ConfigurationItem(
								WRONG_FILE_FORMAT,
								"File contains invalid lines or is wrong format. First invalid line: ",
								null, null));
		map.put(FILE_DOESNT_EXIST_OR_EMPTY, new ConfigurationItem(
				FILE_DOESNT_EXIST_OR_EMPTY,
				"File contains no data or doesn't exist.", null, null));

		map.put(ERROR_OPENING_LOG_FILE, new ConfigurationItem(
				ERROR_OPENING_LOG_FILE,
				"Cannot open log file for writing.", null, null));

		map.put(ERROR_CLOSING_LOG_FILE, new ConfigurationItem(
				ERROR_CLOSING_LOG_FILE,
				"Cannot close log file.", null, null));

		map.put(ERROR_WRITING_TO_LOG_FILE, new ConfigurationItem(
				ERROR_WRITING_TO_LOG_FILE,
				"Cannot write to log file.", null, null));



		return map;
	}
}
