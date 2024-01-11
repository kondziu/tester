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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* Hello to you senhor Joe!
 * it would be terribly nice to replace this class with one
 * that uses some sort of statistical or AI mechanisms to
 * enhance the working of this alorithm, because
 * some slight adjustments might help...
 * 
 * maybe elimninate strings that are too short or too long to fit
 * maybe save information on which rules fit most often and try those first 
 */

public class LineComparator {

	private Map rules;

	private boolean replace;
	private boolean ignoreCase;
	private boolean strongCheck;

	/**
	 * initiates object, saves some configuration data
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param ConfigurationStorage
	 *            configuration And Settings, takes out the necessary data
	 */
	public LineComparator(ConfigurationStorage configurationAndSettings) {
		this.rules = configurationAndSettings.rulesConfiguration.getConfig();
		this.ignoreCase = configurationAndSettings.optionConfiguration
				.getConfig("ignore case").compareToIgnoreCase("on") == 0;
		// this.replace=((String)configurationAndSettings.regexConfiguration.getConfig("replacement point")).compareToIgnoreCase("check")==0;
		this.strongCheck = configurationAndSettings.optionConfiguration
				.getConfig("use rules").compareToIgnoreCase("on") == 0;
	}

	/**
	 * Checks if a string is within a list of strings, does not use replacement
	 * rules
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param List
	 * @param String
	 * @return boolean true if match
	 */
	private boolean weakComparison(List list, String string) {
		string = LineParser.replace(string);
		for (Iterator i = list.iterator(); i.hasNext();) {
			String item;
			if (replace)
				item = LineParser.replace((String) i.next());
			else
				item = (String) i.next();

			if (match(item, string))
				return true;
		}

		return false;

	}

	/**
	 * Checks if a string is within a list of strings, uses replacement rules
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param List
	 * @param String
	 * @return boolean true if match
	 */
	private boolean strongComparison(List list, String string) {
		string = LineParser.replace(string);
		// System.out.println("Doing strong comparison between:"+list+" :and: "+string);
		for (Iterator i = list.iterator(); i.hasNext();) {
			String item;
			if (replace)
				item = LineParser.replace((String) i.next());
			else
				item = (String) i.next();
			// System.out.println("\tcomparing item:"+item+" :and: "+string);
			if (match(item, string))
				return true;
			for (Iterator j = rules.keySet().iterator(); j.hasNext();) {
				// System.out.println("\tno match: initiating rule checking");
				String key = (String) j.next();
				String regex = LineParser.convertToRegex(key);
				String replacement = (String) rules.get(key);
				// System.out.println("\t\tkey:         "+key);
				// System.out.println("\t\tregex:       "+regex);
				// System.out.println("\t\treplacement: "+replacement);
				if (contains(item, key)) {
					// System.out.println("\t\tbefore item replacement: "+item);
					item = item.replaceAll(regex, replacement);
					// System.out.println("\t\tafter item replacement: "+item);
					// System.out.println("\t\tmatching "+item+" :and: "+string);
					if (match(item, string))
						return true;
				}
				if (contains(string, key)) {
					// System.out.println("\t\tbefore string replacement: "+string);
					string = string.replaceAll(regex, replacement);
					// System.out.println("\t\tafter string replacement: "+string);
					// System.out.println("\t\tmatching "+item+" :and: "+string);
					if (match(item, string))
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * checks if one stirng is contained in the other
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 *            container
	 * @param String
	 *            contained (the one we look for in the other)
	 * @return boolean true if contained is present within container
	 */
	private boolean contains(String container, String contained) {
		return container.indexOf(contained) != -1;
	}

	/**
	 * compares a string to another string
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 * @param String
	 * @return boolean true if match
	 */
	private boolean match(String a, String b) {
		if (ignoreCase)
			return a.trim().compareToIgnoreCase(b.trim()) == 0;
		return a.trim().compareTo(b.trim()) == 0;
	}

	/**
	 * compares a string to a list of strings
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param List
	 *            of Strings
	 * @param String
	 * @return boolean true if match
	 */
	public boolean compare(List list, String string) {

		if (weakComparison(list, string))
			return true;
		else if (strongCheck)
			return strongComparison(list, string);
		return false;
	}
}
