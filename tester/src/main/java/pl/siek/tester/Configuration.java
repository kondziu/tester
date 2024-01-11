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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Configuration {
	protected String configPath;
	protected Map configuration = new HashMap();

	/**
	 * Returns a string representation of the object
	 * 
	 * @author K. Siek
	 * @version 2.001
	 * @return String
	 */
	public String toString() {
		String output = "";
		for (Iterator i = configuration.keySet().iterator(); i.hasNext();) {
			Object key = i.next();
			ConfigurationItem item = (ConfigurationItem) configuration.get(key);
			output += item.toString() + "\n";
		}
		return output;
	}

	/**
	 * Returns a Map of ConfigurationItem objects
	 * 
	 * @author K. Siek
	 * @version 2.001
	 * @return Map of ConfigurationItem objects
	 */
	public Map getDetailedConfig() {
		return configuration;
	}

	/**
	 * Returns a Map of simplified "states"
	 * 
	 * @author K. Siek
	 * @version 2.001
	 * @return Map of Strings
	 */
	public Map getConfig() {
		HashMap map = new HashMap();
		// System.out.println(configuration);
		for (Iterator i = configuration.keySet().iterator(); i.hasNext();) {
			Object key = i.next();
			ConfigurationItem item = (ConfigurationItem) configuration.get(key);
			// ConfigurationItem k = (ConfigurationItem)item;
			// ConfigurationItem item = (ConfigurationItem)
			// configuration.get(key);
			map.put(item.getKey(), item.getState());
			// System.out.println("hello: \t\t"+item);
		}
		return map;
	}

	/**
	 * Loads a File into memory
	 * 
	 * @author K. Siek
	 * @version 2.001
	 * @throws IOException
	 */
	public void loadFile() throws IOException {
		InputFile input = new InputFile(configPath);
		List list = input.obtain();
		this.configuration = (Map) (new ConfigurationParser(list).convert());
	}

	/**
	 * allows to add item to configuration
	 * 
	 * @author K. Siek
	 * @version 2.003
	 * @param item
	 *            to add
	 */
	public void addConfig(ConfigurationItem item) {
		configuration.put(item.getKey(), item);
	}

	/**
	 * Constructs, loads a File into memory
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 *            file path
	 */
	public Configuration(String path) {
		configPath = path;
	}

	/**
	 * Constructs, loads a Map of ConfigurationItems into memory
	 * 
	 * @author K. Siek
	 * @version 2.001
	 * @param Map
	 */
	public Configuration(Map mapOfConfigurationItems) {
		this.configuration = mapOfConfigurationItems;
	}

	/**
	 * Returns the option's current state.
	 * 
	 * @author K. Siek
	 * @version 2.001
	 * @param Object
	 *            key
	 * @return Object state
	 */
	public String getConfig(Object key) {
		return (String) ((ConfigurationItem) configuration.get(key)).getState();
	}

	/**
	 * Saves the configuration to file path (from which it was read).
	 * 
	 * @author K. Siek
	 * @version 2.003
	 * @throws IOException
	 */
	public void saveFile() throws IOException, NullPointerException {
		OutputFile fileOutput = new OutputFile(configPath);
		List list = new LinkedList();
		for (Iterator i = configuration.keySet().iterator(); i.hasNext();) {
			Object item = configuration.get(i.next());
			list.add(item);
		}
		// System.out.println(list.get(0));
		// Collections.sort(list);
		fileOutput.generate(list);
	}

	public void saveFile(String configPath) throws IOException,
			NullPointerException {
		this.configPath = configPath;
		this.saveFile();
	}

	/**
	 * Retrieves configuration item with detailed config
	 * 
	 * @author K. Siek
	 * @version 2.003
	 * @returns ConfigurationItem
	 */
	public ConfigurationItem getDetailedConfig(String key) {
		return (ConfigurationItem) configuration.get(key);
	}

	public void setPath(String path) {
		configPath = path;
	}

}
