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

public class ConfigurationDefaultMessages extends ConfigurationDefault {

	public static String NO_MATCH = "no match";
	public static String FIND = "find";
	public static String SET_DIRECTORIES = "set default";
	public static String SEARCH = "search";
	public static String DICTIONARY = "dictionary";
	public static String INFORMATION = "information";
	public static String END_TEST = "end test";
	public static String DO_YOU_WANT_TO_REMOVE = "do you want to remove";
	public static String SAVE_CHANGES = "save changes";
	public static String CANCEL_BUTTON_TEXT = "cancel button text";
	public static String DETAILS_VIEW_BUTTON_TOOLTIP_TEXT = "details view button tooltip text";
	public static String FILE_NAME_LABEL_TEXT = "file name label text";
	public static String FILES_OF_TYPE_LABEL_TEXT = "files of type label text";
	public static String HELP_BUTTON_TEXT = "help button text";
	public static String HOME_FOLDER_TOOLTIP_TEXT = "home folder tooltip text";
	public static String LIST_VIEW_BUTTON_TOOLTIP_TEXT = "list view button tooltip text";
	public static String NEW_FOLDER_TOOLTIP_TEXT = "new folder tooltip text";
	public static String OPEN_BUTTON_TEXT = "open button text";
	public static String SAVE_BUTTON_TEXT = "save button text";
	public static String UP_FOLDER_TOOLTIP_TEXT = "up folder tooltip text";
	public static String UPDATE_BUTTON_TEXT = "update button text";

	public static String BROWSE = "browse";
	public static String ARE_YOU_SURE_YOU_WANT_TO_REMOVE = "Are you sure you want to remove";
	public static String ABOUT_AUTHOR = "about author";
	public static String ABOUT_DATE = "about date";
	public static String ABOUT_DESCRIPTION = "about description";
	public static String ABOUT_TITLE = "about title";
	public static String ABOUT = "about";
	public static String ADD_OPTION_TITLE = "add option title";
	public static String ADD = "add";
	public static String ADDRESS = "address";
	public static String ARE_YOU_SURE_YOU_WANT_TO_STOP = "are you sure you want to stop";
	public static String CANCEL = "cancel";
	public static String CAPTIONS_AND_MESSAGES_TOOLTIP = "captions and messages tooltip";
	public static String CAPTIONS_AND_MESSAGES = "captions and messages";
	public static String CLOSE = "close";
	public static String CONFIGURATION_TITLE = "configuration title";
	public static String CONFIGURATION = "configuration";
	public static String CONFIRM = "confirm";
	public static String DEFAULT = "default";
	public static String DESCRIPTION = "description";
	public static String DOWNLOAD = "download";
	public static String DYNAMIC_TREE_TITLE = "dynamic tree title";
	public static String EDIT_QUESTION = "edit question";
	public static String ERASE_REPETITION = "erase repetition";
	public static String ERROR_MESSAGE_FILES = "error message files";
	public static String ERROR_MESSAGES_TOOLTIP = "error messages tooltip";
	public static String ERROR_MESSAGES = "error messages";
	public static String FILE_PATHS_TOOLTIP = "file paths tooltip";
	public static String FILE_PATHS = "file paths";
	public static String FILE = "file";
	public static String FORCED_REPETITION = "forced repetition";
	public static String FORM_TITLE = "form title";
	public static String GENERAL_OPTIONS_TOOLTIP = "general options tooltip";
	public static String GENERAL_OPTIONS = "general options";
	public static String GOOD_WORK_TITLE = "good work title";
	public static String GOOD_WORK = "good work";
	public static String HANDBOOK = "handbook";
	public static String HELP_ME = "help me";
	public static String HELP = "help";
	public static String HINT = "hint";
	public static String IGNORE_CASE = "ignore case";
	public static String INTERNET = "internet";
	public static String KEY = "key";
	public static String LANGUAGE_VERSION_TITLE = "language version title";
	public static String LANGUAGE_VERSION = "language version";
	public static String MANAGE = "manage";
	public static String MESSAGE_AND_CAPTION_FILES = "message and caption files";
	public static String MKDIR = "mkdir";
	public static String OK = "ok";
	public static String OPEN = "open";
	public static String POOR_WORK_TITLE = "poor work title";
	public static String POOR_WORK = "poor work";
	public static String POSSIBLE_OPTIONS = "possible options";
	public static String PREFERENCES = "preferences";
	public static String QUICK_VIEW = "quick view";
	public static String QUIT = "quit";
	public static String REGULAR_EXPRESSIONS_TOOLTIP = "regular expressions tooltip";
	public static String REGULAR_EXPRESSIONS = "regular expressions";
	public static String RELOAD = "reload";
	public static String REMOVE = "remove";
	public static String REVERSE = "reverse";
	public static String RULES_TOOLTIP = "rules tooltip";
	public static String RULES = "rules";
	public static String SAVE = "save";
	public static String SCORE = "score";
	public static String SET_ADDRESS_FOR_DOWNLOAD = "set address for download";
	public static String SET = "set";
	public static String SHUFFLE = "shuffle";
	public static String START_TEST = "start test";
	public static String STATE = "state";
	public static String STATISTICS = "statistics";
	public static String STOP = "stop";
	public static String TEST = "test";
	public static String TRAINING_MODE = "training mode";
	public static String USE_RULES = "use rules";
	public static String WHERE_WOULD_YOU_LIKE_TO_SAVE = "where would you like to save";
	public static String WOULD_YOU_LIKE_TO_SAVE = "would you like to save";
	public static String QUESTION = "question";
	public static String ANSWERS = "answers";
	public static String TYPE = "type";
	public static String OUT_OF_REPETITION = "out of repetition";
	public static String PROGRESSING_TO_INPUT = "progressing to input";
	public static String DOING_REPETITION = "doing repetition";
	public static String REPETITION = "repetition";
	public static String CHOOSE_A_DIFFERENT_FILE = "choose a different file";

	public static Map getDefaultConfig() {
		Map map = new HashMap();
		// map.put(ARE_YOU_SURE_YOU_WANT_TO_REMOVE,new
		// ConfigurationItem(ARE_YOU_SURE_YOU_WANT_TO_REMOVE,"Are you sure you want to remove",null,null));
		map.put(ARE_YOU_SURE_YOU_WANT_TO_REMOVE, new ConfigurationItem(
				ARE_YOU_SURE_YOU_WANT_TO_REMOVE,
				"Are you sure you want to remove", null, null));
		map.put(ABOUT_AUTHOR, new ConfigurationItem(ABOUT_AUTHOR,
				"by Konrad Siek", null, null));
		map.put(ABOUT_DATE, new ConfigurationItem(ABOUT_DATE, "(issue date "
				+ Main.DATE + ")", null, null));
		map.put(ABOUT_DESCRIPTION, new ConfigurationItem(ABOUT_DESCRIPTION,
				"a language learning assistant", null, null));
		map.put(ABOUT_TITLE, new ConfigurationItem(ABOUT_TITLE,
				"Tester Program v" + Main.VERSION, null, null));
		map.put(ABOUT, new ConfigurationItem(ABOUT, "About", null, null));
		map.put(ADD_OPTION_TITLE, new ConfigurationItem(ADD_OPTION_TITLE,
				"Add option", null, null));
		map.put(ADD, new ConfigurationItem(ADD, "Add", null, null));
		map.put(ADDRESS, new ConfigurationItem(ADDRESS, "Address", null, null));
		map.put(ARE_YOU_SURE_YOU_WANT_TO_REMOVE, new ConfigurationItem(
				ARE_YOU_SURE_YOU_WANT_TO_REMOVE,
				"Are you sure you want to remove", null, null));
		map.put(ARE_YOU_SURE_YOU_WANT_TO_STOP, new ConfigurationItem(
				ARE_YOU_SURE_YOU_WANT_TO_STOP,
				"Are you sure you want to stop current work<QUESTION>", null,
				null));
		map.put(CANCEL, new ConfigurationItem(CANCEL, "Cancel", null, null));
		map
				.put(
						CAPTIONS_AND_MESSAGES_TOOLTIP,
						new ConfigurationItem(
								CAPTIONS_AND_MESSAGES_TOOLTIP,
								"Panel for captions and messages which are displayed on buttons, labels etc.",
								null, null));
		map.put(CAPTIONS_AND_MESSAGES, new ConfigurationItem(
				CAPTIONS_AND_MESSAGES, "Captions and Messages", null, null));
		map.put(CLOSE, new ConfigurationItem(CLOSE, "Close", null, null));
		map.put(CONFIGURATION_TITLE, new ConfigurationItem(CONFIGURATION_TITLE,
				"Tester v" + Main.VERSION + " Configuration", null, null));
		map.put(CONFIGURATION, new ConfigurationItem(CONFIGURATION,
				"Configuration", null, null));
		map.put(CONFIRM, new ConfigurationItem(CONFIRM, "Confirm", null, null));
		map.put(DEFAULT, new ConfigurationItem(DEFAULT, "default", null, null));
		map.put(DESCRIPTION, new ConfigurationItem(DESCRIPTION, "Description",
				null, null));
		map.put(DOWNLOAD, new ConfigurationItem(DOWNLOAD, "Download", null,
				null));
		map.put(DYNAMIC_TREE_TITLE, new ConfigurationItem(DYNAMIC_TREE_TITLE,
				"File Manager", null, null));
		map.put(EDIT_QUESTION, new ConfigurationItem(EDIT_QUESTION,
				"Edit Question", null, null));
		map.put(ERASE_REPETITION, new ConfigurationItem(ERASE_REPETITION,
				"Erase Repetition", null, null));
		map.put(ERROR_MESSAGE_FILES, new ConfigurationItem(ERROR_MESSAGE_FILES,
				"Error Message Files", null, null));
		map
				.put(
						ERROR_MESSAGES_TOOLTIP,
						new ConfigurationItem(
								ERROR_MESSAGES_TOOLTIP,
								"Panel for error message texts which show up when something goes wrong.",
								null, null));
		map.put(ERROR_MESSAGES, new ConfigurationItem(ERROR_MESSAGES,
				"Error Messages", null, null));
		map
				.put(
						FILE_PATHS_TOOLTIP,
						new ConfigurationItem(
								FILE_PATHS_TOOLTIP,
								"Panel for paths of configuration files necessary for ensuring the correct working of the program.",
								null, null));
		map.put(FILE_PATHS, new ConfigurationItem(FILE_PATHS, "File Paths",
				null, null));
		map.put(FILE, new ConfigurationItem(FILE, "File", null, null));
		map.put(FORCED_REPETITION, new ConfigurationItem(FORCED_REPETITION,
				"Force Repetition", null, null));
		map.put(FORM_TITLE, new ConfigurationItem(FORM_TITLE,
				"Tester Program v" + Main.VERSION, null, null));
		map
				.put(
						GENERAL_OPTIONS_TOOLTIP,
						new ConfigurationItem(
								GENERAL_OPTIONS_TOOLTIP,
								"Panel for general options having to do with how the program runs.",
								null, null));
		map.put(GENERAL_OPTIONS, new ConfigurationItem(GENERAL_OPTIONS,
				"General Options", null, null));
		map.put(GOOD_WORK_TITLE, new ConfigurationItem(GOOD_WORK_TITLE, "Good",
				null, null));
		map.put(GOOD_WORK, new ConfigurationItem(GOOD_WORK, "Good work!", null,
				null));
		map.put(HANDBOOK, new ConfigurationItem(HANDBOOK, "User's Handbook",
				null, null));
		map.put(HELP_ME, new ConfigurationItem(HELP_ME, "Help Me", null, null));
		map.put(HELP, new ConfigurationItem(HELP, "Help", null, null));
		map.put(HINT, new ConfigurationItem(HINT, "Hint", null, null));
		map.put(IGNORE_CASE, new ConfigurationItem(IGNORE_CASE, "Ignore Case",
				null, null));
		map.put(INTERNET, new ConfigurationItem(INTERNET, "Internet", null,
				null));
		map.put(KEY, new ConfigurationItem(KEY, "Key", null, null));
		map.put(LANGUAGE_VERSION_TITLE, new ConfigurationItem(
				LANGUAGE_VERSION_TITLE, "Language Version", null, null));
		map.put(LANGUAGE_VERSION, new ConfigurationItem(LANGUAGE_VERSION,
				"Language Version", null, null));
		map.put(MANAGE, new ConfigurationItem(MANAGE, "Manage", null, null));
		map.put(MESSAGE_AND_CAPTION_FILES, new ConfigurationItem(
				MESSAGE_AND_CAPTION_FILES, "Message and Caption Files", null,
				null));
		map.put(MKDIR, new ConfigurationItem(MKDIR, "Make Directory", null,
				null));
		map.put(OK, new ConfigurationItem(OK, "OK", null, null));
		map.put(OPEN, new ConfigurationItem(OPEN, "Open", null, null));
		map.put(POOR_WORK_TITLE, new ConfigurationItem(POOR_WORK_TITLE,
				"Well...", null, null));
		map.put(POOR_WORK, new ConfigurationItem(POOR_WORK,
				"Almost right! Try again.", null, null));
		map.put(POSSIBLE_OPTIONS, new ConfigurationItem(POSSIBLE_OPTIONS,
				"Possible Options", null, null));
		map.put(PREFERENCES, new ConfigurationItem(PREFERENCES, "Preferences",
				null, null));
		map.put(QUICK_VIEW, new ConfigurationItem(QUICK_VIEW, "Quick View",
				null, null));
		map.put(QUIT, new ConfigurationItem(QUIT, "Quit", null, null));
		map.put(REGULAR_EXPRESSIONS_TOOLTIP, new ConfigurationItem(
				REGULAR_EXPRESSIONS_TOOLTIP,
				"Format specification for input files.", null, null));
		map.put(REGULAR_EXPRESSIONS, new ConfigurationItem(REGULAR_EXPRESSIONS,
				"Regular Expressions", null, null));
		map.put(RELOAD, new ConfigurationItem(RELOAD, "Reload", null, null));
		map.put(REMOVE, new ConfigurationItem(REMOVE, "Remove", null, null));
		map.put(REVERSE, new ConfigurationItem(REVERSE, "Reverse", null, null));
		map.put(RULES_TOOLTIP, new ConfigurationItem(RULES_TOOLTIP,
				"List of equivalent forms.", null, null));
		map.put(RULES, new ConfigurationItem(RULES, "Rules", null, null));
		map.put(SAVE, new ConfigurationItem(SAVE, "Save", null, null));
		map.put(SCORE, new ConfigurationItem(SCORE, "Score:", null, null));
		map.put(SET_ADDRESS_FOR_DOWNLOAD, new ConfigurationItem(
				SET_ADDRESS_FOR_DOWNLOAD, "Set address for download:", null,
				null));
		map.put(SET, new ConfigurationItem(SET, "Set", null, null));
		map.put(SHUFFLE, new ConfigurationItem(SHUFFLE, "Shuffle", null, null));
		map.put(START_TEST, new ConfigurationItem(START_TEST, "Start Test",
				null, null));
		map.put(STATE, new ConfigurationItem(STATE, "State", null, null));
		map.put(STATISTICS, new ConfigurationItem(STATISTICS, "Statistics",
				null, null));
		map.put(STOP, new ConfigurationItem(STOP, "Stop", null, null));
		map.put(TEST, new ConfigurationItem(TEST, "Test", null, null));
		map.put(TRAINING_MODE, new ConfigurationItem(TRAINING_MODE,
				"Training Mode", null, null));
		map.put(USE_RULES, new ConfigurationItem(USE_RULES, "Use Rules", null,
				null));
		map.put(WHERE_WOULD_YOU_LIKE_TO_SAVE, new ConfigurationItem(
				WHERE_WOULD_YOU_LIKE_TO_SAVE,
				"Where would you like to save your progress<QUESTION>", null,
				null));
		map.put(WOULD_YOU_LIKE_TO_SAVE, new ConfigurationItem(
				WOULD_YOU_LIKE_TO_SAVE,
				"Would you like to save your progress<QUESTION>", null, null));
		map.put(QUESTION, new ConfigurationItem(QUESTION, "Question", null,
				null));
		map.put(ANSWERS, new ConfigurationItem(ANSWERS, "Answers", null, null));
		map.put(TYPE, new ConfigurationItem(TYPE, "Type", null, null));
		map.put(BROWSE, new ConfigurationItem(TYPE, "Browse", null, null));
		map.put(SAVE_CHANGES, new ConfigurationItem(SAVE_CHANGES,
				"Do you want to save changes to test file?", null, null));
		map.put(CANCEL_BUTTON_TEXT, new ConfigurationItem(CANCEL_BUTTON_TEXT,
				"Cancel", null, null));
		map.put(DETAILS_VIEW_BUTTON_TOOLTIP_TEXT, new ConfigurationItem(
				DETAILS_VIEW_BUTTON_TOOLTIP_TEXT, "Details", null, null));
		map.put(FILE_NAME_LABEL_TEXT, new ConfigurationItem(
				FILE_NAME_LABEL_TEXT, "File Name:", null, null));
		map.put(FILES_OF_TYPE_LABEL_TEXT, new ConfigurationItem(
				FILES_OF_TYPE_LABEL_TEXT, "Files of Type:", null, null));
		map.put(HELP_BUTTON_TEXT, new ConfigurationItem(HELP_BUTTON_TEXT,
				"Help", null, null));
		map.put(HOME_FOLDER_TOOLTIP_TEXT, new ConfigurationItem(
				HOME_FOLDER_TOOLTIP_TEXT, "Home Directory", null, null));
		map.put(LIST_VIEW_BUTTON_TOOLTIP_TEXT, new ConfigurationItem(
				LIST_VIEW_BUTTON_TOOLTIP_TEXT, "List", null, null));
		map.put(NEW_FOLDER_TOOLTIP_TEXT, new ConfigurationItem(
				NEW_FOLDER_TOOLTIP_TEXT, "New Directory", null, null));
		map.put(OPEN_BUTTON_TEXT, new ConfigurationItem(OPEN_BUTTON_TEXT,
				"Open", null, null));
		map.put(SAVE_BUTTON_TEXT, new ConfigurationItem(SAVE_BUTTON_TEXT,
				"Save", null, null));
		map.put(UP_FOLDER_TOOLTIP_TEXT, new ConfigurationItem(
				UP_FOLDER_TOOLTIP_TEXT, "Up One Level", null, null));
		map.put(UPDATE_BUTTON_TEXT, new ConfigurationItem(UPDATE_BUTTON_TEXT,
				"Update", null, null));
		map.put(DO_YOU_WANT_TO_REMOVE, new ConfigurationItem(
				DO_YOU_WANT_TO_REMOVE, "Do you want to remove element", null,
				null));
		map.put(OUT_OF_REPETITION, new ConfigurationItem(OUT_OF_REPETITION,
				"There is no more repetition.", null, null));
		map.put(PROGRESSING_TO_INPUT, new ConfigurationItem(
				PROGRESSING_TO_INPUT, "Progressing to New Material.", null,
				null));
		map.put(DOING_REPETITION, new ConfigurationItem(DOING_REPETITION,
				"Progressing to Repetition.", null, null));
		map.put(REPETITION, new ConfigurationItem(REPETITION, "Repetition",
				null, null));
		map.put(INFORMATION, new ConfigurationItem(INFORMATION, "Information",
				null, null));
		map.put(END_TEST, new ConfigurationItem(END_TEST,
				"This is the end of the test.", null, null));
		map.put(CHOOSE_A_DIFFERENT_FILE,
				new ConfigurationItem(CHOOSE_A_DIFFERENT_FILE,
						"Choose a different file.", null, null));
		map.put(DICTIONARY, new ConfigurationItem(DICTIONARY, "Dictionary",
				null, null));
		map.put(SEARCH, new ConfigurationItem(SEARCH, "Search: ", null, null));
		map.put(FIND, new ConfigurationItem(FIND, "Find", null, null));
		map.put(SET_DIRECTORIES, new ConfigurationItem(SET_DIRECTORIES,
				"Set Directories", null, null));
		map.put(NO_MATCH, new ConfigurationItem(NO_MATCH,
				"No such item found.", null, null));

		return map;
	}

}
