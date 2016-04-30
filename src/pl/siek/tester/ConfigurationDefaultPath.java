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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationDefaultPath {

	public static String FILE_CHOOSER_DEFAULT_PATH = "file chooser default path";
	public static String DICTIONARIES = "dictionaries";
	public static String DEFAULT_ADDRESS = "default address";
	public static String DIR_ICON = "dir icon";
	public static String ERROR_DIRECTORY = "error directory";
	public static String ERRORS = "errors";
	public static String FILE_ICON = "file icon";
	public static String GOOD_ICON = "good icon";
	public static String INPUT_DIRECTORY = "input directory";
	public static String INPUT = "input";
	public static String MESSAGE_DIRECTORY = "message directory";
	public static String MESSAGES = "messages";
	public static String OPTIONS = "options";
	public static String PATH = "path";
	public static String POOR_ICON = "poor icon";
	public static String REGEX = "regex";
	public static String REPETITION = "repetition";
	public static String ROOT_ICON = "root icon";
	public static String RULES = "rules";
	public static String STATISTICS = "statistics";
	public static String MEDIA_PLAYER = "media player";
	public static String HANDBOOK = "handbook";

	public static Map getDefaultConfig() {
		Map map = new HashMap();
		map.put(DEFAULT_ADDRESS, new ConfigurationItem(DEFAULT_ADDRESS,
				"http://www.program2005.republika.pl/",
				"default address shown in the download dialogue box", null));
		map.put(DIR_ICON, new ConfigurationItem(DIR_ICON, "icons"
				+ File.separator + "dir.gif",
				"directory icon (for file manager use)", null));
		map.put(ERROR_DIRECTORY, new ConfigurationItem(ERROR_DIRECTORY, "lang"
				+ File.separator + "errors",
				"folder containing language files (concerning error messages)",
				null));
		map.put(ERRORS, new ConfigurationItem(ERRORS, "lang" + File.separator
				+ "errors" + File.separator + "english.txt",
				"currently used error message language file", null));
		map.put(FILE_ICON, new ConfigurationItem(FILE_ICON, "icons"
				+ File.separator + "file.gif",
				"file icon (for file manager use)", null));
		map.put(GOOD_ICON, new ConfigurationItem(GOOD_ICON, "icons"
				+ File.separator + "good.gif",
				"icon shown when answer was matched", null));
		map
				.put(
						INPUT_DIRECTORY,
						new ConfigurationItem(
								INPUT_DIRECTORY,
								"tests",
								"directory where tests are stored (file manager rot directory)",
								null));
		map.put(INPUT, new ConfigurationItem(INPUT, "new.txt",
				"currently selected input", null));
		map
				.put(
						MESSAGE_DIRECTORY,
						new ConfigurationItem(
								MESSAGE_DIRECTORY,
								"lang" + File.separator + "msgs",
								"folder containing language files (concerning messages)",
								null));
		map.put(MESSAGES, new ConfigurationItem(MESSAGES, "lang"
				+ File.separator + "msgs" + File.separator + "english.txt",
				"currently used message language file", null));
		map.put(OPTIONS, new ConfigurationItem(OPTIONS, "files"
				+ File.separator + "options.txt",
				"file containing general options", null));
		map.put(PATH,
				new ConfigurationItem(PATH, "files" + File.separator
						+ "paths.txt",
						"file containing configuration file paths", null));
		map.put(POOR_ICON, new ConfigurationItem(POOR_ICON, "icons"
				+ File.separator + "poor.gif",
				"icon shown when answer was not matched", null));
		map
				.put(
						REGEX,
						new ConfigurationItem(
								REGEX,
								"files" + File.separator + "regex.txt",
								"file containing regular expressions (input file format)",
								null));
		map.put(REPETITION, new ConfigurationItem(REPETITION, "tests"
				+ File.separator + "repeat.txt",
				"file where repetition is stored", null));
		map.put(ROOT_ICON, new ConfigurationItem(ROOT_ICON, "icons"
				+ File.separator + "root.gif",
				"root icon (for file manager use)", null));
		map.put(RULES, new ConfigurationItem(RULES, "files" + File.separator
				+ "rules.txt", "file containing rules", null));
		map.put(STATISTICS, new ConfigurationItem(STATISTICS, "stats"
				+ File.separator + "stats.txt", "file containing statistics",
				null));
		map
				.put(
						MEDIA_PLAYER,
						new ConfigurationItem(
								MEDIA_PLAYER,
								"vlc",
								"multimedia player used for opening the handbook presentation as well as files",
								null));
		map.put(HANDBOOK, new ConfigurationItem(HANDBOOK, "handbook.avi",
				"path to handbook video presentation", null));
		map
				.put(
						DICTIONARIES,
						new ConfigurationItem(
								DICTIONARIES,
								"tests",
								"paths to directories holding dictionary files separated by a default system separator <br> (Windows ';', Unix ':', etc.)",
								null));
		map.put(FILE_CHOOSER_DEFAULT_PATH, new ConfigurationItem(
				FILE_CHOOSER_DEFAULT_PATH, "." + File.separator,
				"default path for the file chooser component", null));

		return map;
	}
}
