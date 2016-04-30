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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//UPDATED 1808
public class ConfigurationParser implements Parser {
	private LinkedList list;

	public Object convert() {
		Map output = new HashMap();
		for (Iterator iterator = list.listIterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			if (string.compareTo("") == 0)
				continue;
			String[] strings = string.split("[\\t ]*\\?\\?[\\t ]*");
			try {
				String key = replace(strings[0]);
				String state = replace(strings[1]);
				String description;
				if (strings.length > 2)
					description = replace(strings[2]);
				else
					description = "";
				Set possible = new HashSet();

				for (int i = 3; i < strings.length; i++) {
					possible.add(replace(strings[i]));
				}

				output.put(strings[0], new ConfigurationItem(key, state,
						description, possible));

			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO ERROR HANDLING
				if ((string.trim()).endsWith("??")) {
					output.put(strings[0], " ");
				} else
					// TODO
					System.err
							.println("error reading configuartion file at line: \""
									+ string + "\"");
				// e.printStackTrace();
			}
		}
		return output;
	}

	public static String trim(String string) {
		if (string != " ")
			string = string.trim();
		return string;
	}

	public static String replace(String string) {
		if (string.indexOf("<SPACE>") != -1)
			string = string.replaceAll("<SPACE>", " ");
		if (string.indexOf("<QUESTION>") != -1)
			string = string.replaceAll("<QUESTION>", "? ");
		// if(string.indexOf("<BR>")!=-1)string="<HTML>"+string;
		return string.replaceAll(" {2,}", " ");
	}

	public static String unreplace(String string) {
		// if(string.indexOf("<SPACE>")!=-1)string=string.replaceAll("<SPACE>"," ");
		// if(string.indexOf("<LINE>")!=-1)string=string.replaceAll("<LINE>","\n");
		// return string.replaceAll(" {2,}"," ");
		Pattern pattern = Pattern.compile(" *");
		Matcher matcher = pattern.matcher(string);
		if (matcher.matches())
			string = "<SPACE>";
		if (string.indexOf("?") != -1)
			string = string.replaceAll("\\Q?\\E", "<QUESTION>");

		// while(string.startsWith(" "))stat.replaceFirst(" ","<SPACE>");
		return string.replaceAll("\n", "<LINE>");
	}

	// UPDATED 1808
	public ConfigurationParser(List sourceList) {
		list = (LinkedList) sourceList;
	}

}
