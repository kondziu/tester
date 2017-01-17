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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConfigurationDefaultOptions extends ConfigurationDefault {

	public static final String SHOW_PERCENTAGE = "show percentage";
	public static final String START_TEST = "start test";
	public static final String ANSWER_WINDOW_WIDTH = "answer window width";
	public static final String QUESTION_WINDOW_HEIGHT = "question window height";
	public static final String ALLOW_EARLY_HELP = "allow early help";
	public static final String QUESTION_WINDOW_WIDTH = "question window width";
	public static final String DICTIONARY_ACCESSIBLE = "dictionary accessible";
	public static final String INDICATE_ERROR_ON_EXIT = "indicate error on exit";
	public static final String DEBUG = "debug";
	public static final String ERASE_REPETITION = "erase repetition";
	public static final String IGNORE_CASE = "ignore case";
	public static final String MODE = "mode";
	public static final String REPETITION_TYPE = "repetition type";
	public static final String RESIZIBLE_WINDOW = "resizible window";
	public static final String REVERSE = "reverse";
	public static final String SHOW_HINT = "show hint";
	public static final String SHUFFLE = "shuffle";
	public static final String STUDENT_CONFIGURES = "student configures";
	public static final String STUDENT_EDITS_OR_REMOVES = "student edits or removes";
	public static final String STUDENT_STOPS_TEST = "student stops test";
	public static final String USE_RULES = "use rules";
	public static final String SHOW_MENU = "show menu";
	public static final String SHOW_STATUS_AND_CLOCK = "show status and clock";
	public static final String STUDENT_MANAGES_FILES = "student manages files";
	public static final String CHOOSABLE_LANGUAGE = "enable choosable language";
	public static final String SAVE_CONFIG_ON_EXIT = "set config on exit";
	public static final String FILE_ENCODING = "file encoding";
	public static final String SAVE_LOG = "save log";

	public static Map getDefaultConfig() {
		Map map = new HashMap();

		Set onOff = new HashSet(2);
		onOff.add(new String("on"));
		onOff.add(new String("off"));

		Set standardForced = new HashSet(2);
		standardForced.add(new String("standard"));
		standardForced.add(new String("forced"));

		Set standardTest = new HashSet(2);
		standardTest.add(new String("standard"));
		standardTest.add(new String("test"));

		Set startTest = new HashSet(3);
		startTest.add("ask");
		startTest.add("local");
		startTest.add("web");

		map.put(SAVE_LOG, new ConfigurationItem(SAVE_LOG, "on", "log events to a file during tests", onOff));

		map.put(DEBUG, new ConfigurationItem(DEBUG, "off",
				"Shows debug messages - obsolete", onOff));
		map.put(ERASE_REPETITION, new ConfigurationItem(ERASE_REPETITION, "on",
				"enabled student to delete file containing repetition", onOff));
		map
				.put(
						IGNORE_CASE,
						new ConfigurationItem(
								IGNORE_CASE,
								"on",
								"case of characters will be taken into account during question-answer comparison",
								onOff));
		map
				.put(
						MODE,
						new ConfigurationItem(
								MODE,
								"standard",
								"standard (training) mode: students work on a question until it is correct;<BR> test mode: students only attempt each question once;",
								standardTest));
		map
				.put(
						REPETITION_TYPE,
						new ConfigurationItem(
								REPETITION_TYPE,
								"forced",
								"standard repetition: student is asked to correct his mistakes before he can attempt next test;<BR>forced repetition: student is corrects his mistakes before the test is finished;",
								standardForced));
		map.put(RESIZIBLE_WINDOW, new ConfigurationItem(RESIZIBLE_WINDOW,
				"off",
				"the window of main frame will be resizable (after restart)",
				onOff));
		map.put(REVERSE, new ConfigurationItem(REVERSE, "off",
				"test questions will be fed in reversed order", onOff));
		map.put(SHOW_HINT, new ConfigurationItem(SHOW_HINT, "on",
				"hint will be shown along with the question", onOff));
		map.put(SHUFFLE, new ConfigurationItem(SHUFFLE, "on",
				"test questions will be fed in random order", onOff));
		map
				.put(
						STUDENT_CONFIGURES,
						new ConfigurationItem(
								STUDENT_CONFIGURES,
								"on",
								"student can change program settings<BR>WATCH OUT! AFTER SETTING CONFIGURATION IS ONLY ACCESSIBLE THROUGH DIRECT FILE INPUT!",
								onOff));
		map.put(STUDENT_EDITS_OR_REMOVES, new ConfigurationItem(
				STUDENT_EDITS_OR_REMOVES, "off",
				"students can edit or remove test questions during the test",
				onOff));
		map.put(STUDENT_STOPS_TEST, new ConfigurationItem(STUDENT_STOPS_TEST,
				"on", null, onOff));
		map
				.put(
						USE_RULES,
						new ConfigurationItem(
								USE_RULES,
								"on",
								"some recognized contractable phrases will be matched also in contracted forms",
								onOff));
		map
				.put(
						INDICATE_ERROR_ON_EXIT,
						new ConfigurationItem(
								INDICATE_ERROR_ON_EXIT,
								"on",
								"if a file of configuration or input cannot be saved at the end an information will be shown",
								onOff));
		map.put(SHOW_MENU, new ConfigurationItem(SHOW_MENU, "on",
				"menu with advanced options will be shown", onOff));
		map
				.put(
						SHOW_STATUS_AND_CLOCK,
						new ConfigurationItem(
								SHOW_STATUS_AND_CLOCK,
								"on",
								"clock and a status bar will be shown at the bottom of the screen",
								onOff));
		map
				.put(
						STUDENT_MANAGES_FILES,
						new ConfigurationItem(
								STUDENT_MANAGES_FILES,
								"on",
								"student will be able to open the file manager tool to manage the test database on their own",
								onOff));
		map.put(CHOOSABLE_LANGUAGE, new ConfigurationItem(CHOOSABLE_LANGUAGE,
				"on", "language menu can be operated", onOff));
		map.put(SAVE_CONFIG_ON_EXIT, new ConfigurationItem(SAVE_CONFIG_ON_EXIT,
				"on", "attempt saving configuration when exiting the program",
				onOff));
		map
				.put(
						DICTIONARY_ACCESSIBLE,
						new ConfigurationItem(
								DICTIONARY_ACCESSIBLE,
								"on",
								"student can use dictionary to check words. This includes operation during actual time of the test!",
								onOff));
		map.put(ALLOW_EARLY_HELP, new ConfigurationItem(ALLOW_EARLY_HELP, "on",
				"student can press help before attempting an answer.", onOff));
		map
				.put(
						QUESTION_WINDOW_WIDTH,
						new ConfigurationItem(
								QUESTION_WINDOW_WIDTH,
								"default",
								"width of the window showing questions. Default size obtained by setting to \"default\"",
								new HashSet()));
		map
				.put(
						QUESTION_WINDOW_HEIGHT,
						new ConfigurationItem(
								QUESTION_WINDOW_HEIGHT,
								"default",
								"height of the window showing answers. Default size obtained by setting to \"default\"",
								new HashSet()));
		map
				.put(
						START_TEST,
						new ConfigurationItem(
								START_TEST,
								"ask",
								"What to do when test is started without a selected file: local dir, website, or display dialog with choice; default: ask",
								startTest));
		map.put(SHOW_PERCENTAGE, new ConfigurationItem(SHOW_PERCENTAGE, "on",
				"Enable or disable showing percentages; default: on",
				new HashSet()));
		map
				.put(
						FILE_ENCODING,
						new ConfigurationItem(
								FILE_ENCODING,
								"default",
								"Select file encoding for all opened files. Examples: default, WINDOWS-1250, UTF-8",
								new HashSet()));
		return map;
	}
}
