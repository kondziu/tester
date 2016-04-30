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
import java.util.Map;

public class ConfigurationDefault {

	public static String OPTION_SECTION = "option";
	public static String PATH_SECTION = "path";
	public static String ERROR_SECTION = "error";
	public static String MESSAGE_SECTION = "message";
	public static String REGEX_SECTION = "regex";
	public static String RULES_SECTION = "rules";

	public static Map fillHoles(Map map, Map defaults) {

		for (Iterator i = defaults.keySet().iterator(); i.hasNext();) {
			Object key = i.next();
			ConfigurationItem item = (ConfigurationItem) map.get(key);
			Object state = null;
			if (item != null)
				state = item.getState();
			if (item == null || state == null)
				map.put(key, defaults.get(key));
		}
		return map;
	}
}
