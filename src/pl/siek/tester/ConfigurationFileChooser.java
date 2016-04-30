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

import javax.swing.UIManager;

public class ConfigurationFileChooser {
	/**
	 * Sets up file chooser by changing label texts. (for multiple languages
	 * etc.)
	 * 
	 * @param Configuration
	 *            messages containing texts to be put on components
	 * @version 2.1
	 * @author K.Siek
	 */
	public static void configure(Configuration messages) {
		UIManager.put("FileChooser.fileNameLabelText", messages
				.getConfig(ConfigurationDefaultMessages.FILE_NAME_LABEL_TEXT));
		UIManager.put("FileChooser.saveButtonText", messages
				.getConfig(ConfigurationDefaultMessages.SAVE_BUTTON_TEXT));
		UIManager.put("FileChooser.openButtonText", messages
				.getConfig(ConfigurationDefaultMessages.OPEN_BUTTON_TEXT));
		UIManager.put("FileChooser.cancelButtonText", messages
				.getConfig(ConfigurationDefaultMessages.CANCEL_BUTTON_TEXT));
		UIManager.put("FileChooser.updateButtonText", messages
				.getConfig(ConfigurationDefaultMessages.UPDATE_BUTTON_TEXT));
		UIManager.put("FileChooser.helpButtonText", messages
				.getConfig(ConfigurationDefaultMessages.HELP_BUTTON_TEXT));
		UIManager.put("FileChooser.fileNameLabelText", messages
				.getConfig(ConfigurationDefaultMessages.FILE_NAME_LABEL_TEXT));
		UIManager
				.put(
						"FileChooser.filesOfTypeLabelText",
						messages
								.getConfig(ConfigurationDefaultMessages.FILES_OF_TYPE_LABEL_TEXT));
		UIManager
				.put(
						"FileChooser.upFolderToolTipText",
						messages
								.getConfig(ConfigurationDefaultMessages.UP_FOLDER_TOOLTIP_TEXT));
		UIManager
				.put(
						"FileChooser.homeFolderToolTipText",
						messages
								.getConfig(ConfigurationDefaultMessages.HOME_FOLDER_TOOLTIP_TEXT));
		UIManager
				.put(
						"FileChooser.newFolderToolTipText",
						messages
								.getConfig(ConfigurationDefaultMessages.NEW_FOLDER_TOOLTIP_TEXT));
		UIManager
				.put(
						"FileChooser.listViewButtonToolTipText",
						messages
								.getConfig(ConfigurationDefaultMessages.LIST_VIEW_BUTTON_TOOLTIP_TEXT));
		UIManager
				.put(
						"FileChooser.detailsViewButtonToolTipText",
						messages
								.getConfig(ConfigurationDefaultMessages.DETAILS_VIEW_BUTTON_TOOLTIP_TEXT));
	}
}
