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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import tt.config.annotations.exceptions.AnnotationException;
import tt.config.exceptions.ConfigException;
import tt.options.KeyBindings;
import tt.options.MagicWords;
import tt.options.Mnemonics;

public class ConfigurationStorage {
	/**
	 * Initial COnfiguration holds all configuration loaded from all
	 * configuration files.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	public Configuration pathConfiguration;
	public Configuration regexConfiguration;
	public Configuration optionConfiguration;
	public Configuration messageConfiguration;
	public Configuration rulesConfiguration;
	public Configuration errorConfiguration;

	public final MagicWords magicWord;
	public final KeyBindings keys;
	public final Mnemonics mnemonics;

	/**
	 * loads configuration as specified in file ".\config.txt"
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @throws AnnotationException 
	 * @throws ConfigException 
	 * @throws IllegalAccessException 
	 */
	public ConfigurationStorage() throws IllegalAccessException, ConfigException, AnnotationException {
		// this.loadConfigs("files/files.cfg");
		this.magicWord = new MagicWords();
		this.keys = new KeyBindings();
		this.mnemonics = new Mnemonics();
		this.loadConfigs("config.txt");
	}

	/**
	 * loads configuration as specified in file
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 *            file path
	 * @throws AnnotationException 
	 * @throws ConfigException 
	 * @throws IllegalAccessException 
	 */
	public ConfigurationStorage(String pathConfigurationFilePath) throws IllegalAccessException, ConfigException, AnnotationException {
		this.magicWord = new MagicWords();
		this.keys = new KeyBindings();
		this.mnemonics = new Mnemonics();
		this.loadConfigs(pathConfigurationFilePath);
	}

	/**
	 * loads configuration as specified in file - internal function
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 *            file path
	 */
	private int loadConfigs(String pathConfigurationFilePath) {
		// PATH CONFIG
		this.pathConfiguration = new Configuration(pathConfigurationFilePath);
		try {
			this.pathConfiguration.loadFile();
			this.pathConfiguration = new Configuration(ConfigurationDefault
					.fillHoles(pathConfiguration.getDetailedConfig(),
							ConfigurationDefaultPath.getDefaultConfig()));
		} catch (Exception e) {
			Map tempErrors = ConfigurationDefaultErrors.getDefaultConfig();
			JOptionPane
					.showMessageDialog(
							null,
							(String) ((ConfigurationItem) tempErrors
									.get(ConfigurationDefaultErrors.ERROR_READING_FILE))
									.getState()
									+ " \""
									+ pathConfigurationFilePath
									+ "\n("
									+ (String) ((ConfigurationItem) tempErrors
											.get(ConfigurationDefaultErrors.CONCERNING))
											.getState()
									+ " "
									+ (String) ((ConfigurationItem) tempErrors
											.get(ConfigurationDefaultErrors.CONCERNING_FILE_PATHS))
											.getState()
									+ ")\n"
									+ (String) ((ConfigurationItem) tempErrors
											.get(ConfigurationDefaultErrors.LOADING_DEFAULT_SETTINGS))
											.getState(),
							(String) ((ConfigurationItem) tempErrors
									.get(ConfigurationDefaultErrors.CONFIGURATION_FILE_ERROR))
									.getState(), JOptionPane.ERROR_MESSAGE);
			this.pathConfiguration = new Configuration(ConfigurationDefaultPath
					.getDefaultConfig());
		}

		// ERROR CONFIG
		this.errorConfiguration = new Configuration(this.pathConfiguration
				.getConfig("errors"));
		try {
			this.errorConfiguration.loadFile();
			this.errorConfiguration = new Configuration(ConfigurationDefault
					.fillHoles(errorConfiguration.getDetailedConfig(),
							ConfigurationDefaultErrors.getDefaultConfig()));
		} catch (Exception e) {
			this.errorConfiguration = new Configuration(
					ConfigurationDefaultErrors.getDefaultConfig());
			JOptionPane
					.showMessageDialog(
							null,
							this.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.ERROR_READING_FILE)
									+ " \""
									+ this.pathConfiguration
											.getConfig(ConfigurationDefaultPath.ERRORS)
									+ "\"\n("
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.CONCERNING)
									+ " "
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.CONCERNING_ERRORS)
									+ ")\n"
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.LOADING_DEFAULT_SETTINGS),
							this.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.CONFIGURATION_FILE_ERROR),
							JOptionPane.ERROR_MESSAGE);
		}

		// MESSAGE CONFIG
		this.messageConfiguration = new Configuration(this.pathConfiguration
				.getConfig("messages"));
		try {
			this.messageConfiguration.loadFile();
			this.messageConfiguration = new Configuration(ConfigurationDefault
					.fillHoles(messageConfiguration.getDetailedConfig(),
							ConfigurationDefaultMessages.getDefaultConfig()));
		} catch (Exception e) {
			JOptionPane
					.showMessageDialog(
							null,
							this.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.ERROR_READING_FILE)
									+ " \""
									+ this.pathConfiguration
											.getConfig(ConfigurationDefaultPath.MESSAGES)
									+ "\"\n("
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.CONCERNING)
									+ " "
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.CONCERNING_MESSAGES)
									+ ")\n"
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.LOADING_DEFAULT_SETTINGS),
							this.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.CONFIGURATION_FILE_ERROR),
							JOptionPane.ERROR_MESSAGE);
			this.messageConfiguration = new Configuration(
					ConfigurationDefaultMessages.getDefaultConfig());
		}

		// RULES CONFIG
		this.rulesConfiguration = new Configuration(this.pathConfiguration
				.getConfig("rules"));
		try {
			this.rulesConfiguration.loadFile();
			this.rulesConfiguration = new Configuration(ConfigurationDefault
					.fillHoles(rulesConfiguration.getDetailedConfig(),
							ConfigurationDefaultRules.getDefaultConfig()));
		} catch (Exception e) {
			JOptionPane
					.showMessageDialog(
							null,
							this.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.ERROR_READING_FILE)
									+ " \""
									+ this.pathConfiguration
											.getConfig(ConfigurationDefaultPath.RULES)
									+ "\"\n("
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.CONCERNING)
									+ " "
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.CONCERNING_RULES)
									+ ")\n"
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.LOADING_DEFAULT_SETTINGS),
							this.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.CONFIGURATION_FILE_ERROR),
							JOptionPane.ERROR_MESSAGE);
			this.rulesConfiguration = new Configuration(
					ConfigurationDefaultRules.getDefaultConfig());
		}

		// REGEX CONFIG
		this.regexConfiguration = new Configuration(this.pathConfiguration
				.getConfig("regex"));
		try {
			this.regexConfiguration.loadFile();
			this.regexConfiguration = new Configuration(ConfigurationDefault
					.fillHoles(regexConfiguration.getDetailedConfig(),
							ConfigurationDefaultRegex.getDefaultConfig()));
		} catch (Exception e) {
			JOptionPane
					.showMessageDialog(
							null,
							this.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.ERROR_READING_FILE)
									+ " \""
									+ this.pathConfiguration
											.getConfig(ConfigurationDefaultPath.REGEX)
									+ "\"\n("
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.CONCERNING)
									+ " "
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.CONCERNING_REGEX)
									+ ")\n"
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.LOADING_DEFAULT_SETTINGS),
							this.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.CONFIGURATION_FILE_ERROR),
							JOptionPane.ERROR_MESSAGE);
			this.regexConfiguration = new Configuration(
					ConfigurationDefaultRegex.getDefaultConfig());
		}

		// OPTION CONFIG
		this.optionConfiguration = new Configuration(this.pathConfiguration
				.getConfig("options"));
		try {
			this.optionConfiguration.loadFile();
			this.optionConfiguration = new Configuration(ConfigurationDefault
					.fillHoles(optionConfiguration.getDetailedConfig(),
							ConfigurationDefaultOptions.getDefaultConfig()));
		} catch (Exception e) {
			JOptionPane
					.showMessageDialog(
							null,
							this.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.ERROR_READING_FILE)
									+ " \""
									+ this.pathConfiguration
											.getConfig(ConfigurationDefaultPath.OPTIONS)
									+ "\"\n("
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.CONCERNING)
									+ " "
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.CONCERNING_OPTIONS)
									+ ")\n"
									+ this.errorConfiguration
											.getConfig(ConfigurationDefaultErrors.LOADING_DEFAULT_SETTINGS),
							this.errorConfiguration
									.getConfig(ConfigurationDefaultErrors.CONFIGURATION_FILE_ERROR),
							JOptionPane.ERROR_MESSAGE);
			this.optionConfiguration = new Configuration(
					ConfigurationDefaultOptions.getDefaultConfig());
		}

		// SHOW
		if (optionConfiguration.getConfig("debug").compareToIgnoreCase("on") == 0) {
			System.out.println(pathConfiguration);
			System.out.println(errorConfiguration);
			System.out.println(messageConfiguration);
			System.out.println(regexConfiguration);
			System.out.println(optionConfiguration);
		}
		return 0;
	}

	public Map configToMap() {
		Map hashMap = new HashMap();

		hashMap.put(ConfigurationDefault.PATH_SECTION, pathConfiguration);
		hashMap.put(ConfigurationDefault.ERROR_SECTION, errorConfiguration);
		hashMap.put(ConfigurationDefault.MESSAGE_SECTION, messageConfiguration);
		hashMap.put(ConfigurationDefault.REGEX_SECTION, regexConfiguration);
		hashMap.put(ConfigurationDefault.OPTION_SECTION, optionConfiguration);
		hashMap.put(ConfigurationDefault.RULES_SECTION, rulesConfiguration);

		return hashMap;
	}

	/**
	 * initiates object with a preexsisting configuration map
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param Map
	 *            Object containning Strings rules, path, error, message, option
	 *            (keys to UniversalConfiguration Objects) and regex (key to
	 *            RegexConfiguration)
	 * @throws AnnotationException 
	 * @throws ConfigException 
	 * @throws IllegalAccessException 
	 */
	public ConfigurationStorage(Map map) throws IllegalAccessException, ConfigException, AnnotationException {
		this.magicWord = new MagicWords();
		this.keys = new KeyBindings();
		this.mnemonics = new Mnemonics();
		
		// Map hashMap = new HashMap();
		pathConfiguration = (Configuration) map
				.get(ConfigurationDefault.PATH_SECTION);
		errorConfiguration = (Configuration) map
				.get(ConfigurationDefault.ERROR_SECTION);
		messageConfiguration = (Configuration) map
				.get(ConfigurationDefault.MESSAGE_SECTION);
		regexConfiguration = (Configuration) map
				.get(ConfigurationDefault.REGEX_SECTION);
		optionConfiguration = (Configuration) map
				.get(ConfigurationDefault.OPTION_SECTION);
		rulesConfiguration = (Configuration) map
				.get(ConfigurationDefault.RULES_SECTION);
	}

	public void saveToFile() throws IOException, NullPointerException {
		this.pathConfiguration.saveFile(this.pathConfiguration
				.getConfig(ConfigurationDefaultPath.PATH));
		this.errorConfiguration.saveFile(this.pathConfiguration
				.getConfig(ConfigurationDefaultPath.ERRORS));
		this.messageConfiguration.saveFile(this.pathConfiguration
				.getConfig(ConfigurationDefaultPath.MESSAGES));
		this.regexConfiguration.saveFile(this.pathConfiguration
				.getConfig(ConfigurationDefaultPath.REGEX));
		this.optionConfiguration.saveFile(this.pathConfiguration
				.getConfig(ConfigurationDefaultPath.OPTIONS));
		this.rulesConfiguration.saveFile(this.pathConfiguration
				.getConfig(ConfigurationDefaultPath.RULES));
	}

}
