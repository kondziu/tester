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
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class LineParser implements Parser {
	private final boolean DEBUG = false;
	private List list;
	private Configuration regexConfig;
	private static Map replacementMap;
	private static Set regexControlCharacterSet;

	/**
	 * converts a formated string into a more complex Line object.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 * @return Line
	 * @throws IOException
	 */
	public LineItem convert(String string) throws ConverterException {
		// System.out.println("converting: "+string);
		int commentIndex = string.indexOf(convertToRegex(regexConfig
				.getConfig("comment")));
		// System.out.println("index of "+string.indexOf(convertToRegex((String)regexConfig.getConfig("comment")))+" : "+commentIndex);
		if (commentIndex != -1)
			string = string.substring(0, commentIndex);

		if ((string.trim()).compareTo("") == 0)
			return null;

		if (DEBUG)
			System.out.println("before: " + string);
		string = string.replaceAll(" {2,}", " ");
		if (DEBUG)
			System.out.println("after: " + string);

		int hintIndex = string.indexOf(regexConfig.getConfig("hint open"));
		int hintIndexEnd = string.indexOf(regexConfig.getConfig("hint close"),
				hintIndex);

		if (hintIndex == -1 || hintIndexEnd == -1) {
			// TODO ERROR HANDLING
			// System.out.println("Error looking for hint brackets in line: "+string
			// );

			throw new ConverterException(string);
		}
		String hint = string.substring(hintIndex + 1, hintIndexEnd);

		int q, a;
		if (regexConfig.getConfig("question position").compareToIgnoreCase(
				"left") == 0) {
			q = 0;
			a = 1;
		} else {
			q = 1;
			a = 0;
		}
		String regex = convertToRegex(regexConfig.getConfig("hint open"))
				+ ".*" + convertToRegex(regexConfig.getConfig("hint close"));
		// System.out.println("regex: "+regex);
		String answersAndQuestions[] = string.split(regex, 2);
		String question = answersAndQuestions[q].trim();
		String answers = answersAndQuestions[a].trim();
		String answerArray[] = answers.split(convertToRegex(regexConfig
				.getConfig("alternative")));

		LinkedList answerList = new LinkedList();
		// System.out.println(answerArray.length);

		boolean replacementOn = regexConfig.getConfig("replacement point")
				.compareToIgnoreCase("file") == 0;
		for (int i = 0; i < answerArray.length; i++) {
			if (replacementOn)
				answerArray[i] = replace(answerArray[i]);
			answerList.add(answerArray[i].trim());
		}
		if (DEBUG) {
			System.out.println(answerList);
			System.out.println(hint);
			System.out.println(answersAndQuestions[q].trim());
		}

		String type = "text";
		for (Iterator i = regexConfig.getConfig().keySet().iterator(); i
				.hasNext();) {
			String temp = (String) i.next();
			if (temp.trim().startsWith("type:")
					&& question.lastIndexOf(regexConfig.getConfig(temp)) != -1) {
				type = temp.replaceFirst("type:", "").trim();
				question = (question.replaceAll(convertToRegex(regexConfig
						.getConfig(temp)), "")).trim();
				break;
			}
		}
		// System.out.println("Type: "+type);

		return new LineItem(answerList, hint, question, type, regexConfig);
	}

	/**
	 * converts stored list of Strings into a list of Line objects.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @return Object (List)
	 * @throws IOException
	 * @throws ConverterException
	 */
	public Object convert() throws ConverterException {
		List convertedList = new LinkedList();
		for (Iterator i = list.iterator(); i.hasNext();) {
			LineItem line = this.convert((String) i.next());
			if (line != null)
				convertedList.add(line);
		}

		return convertedList;
	}

	/**
	 * Constructs an object.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param List
	 *            input data
	 * @param Configuration
	 *            input file format data
	 */
	public LineParser(List sourceList, Configuration regexConfig) {
		this.list = sourceList;
		this.regexConfig = regexConfig;

		replacementMap = new HashMap();
		for (Iterator i = regexConfig.getDetailedConfig().keySet().iterator(); i
				.hasNext();) {
			// String key = (String)i.next();
			Object mapKey = i.next();
			ConfigurationItem item = (ConfigurationItem) regexConfig
					.getDetailedConfig().get(mapKey);
			String key = (String) item.getKey();
			if (key.startsWith("replace:")) {
				String replacementKey = convertToRegex(key.replaceFirst(
						"replace:", "").trim());
				Object replacementItem = item.getState();

				replacementMap.put(replacementKey, replacementItem);
				// System.out.println("_"+replacementKey+"_"+replacementItem+"_");
			}
		}
	}

	/**
	 * Replaces all strings (specified in configuration) with thier
	 * replacements.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 * @return String
	 */
	public static String replace(String string) {
		for (Iterator i = replacementMap.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			String item = (String) replacementMap.get(key);
			string = string.replaceAll(key, item);
		}
		return string.replaceAll(" {2,}", " ");
	}

	/**
	 * Creates a set of regex control characters
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	private static void initiateRegexControlCharacterSet() {
		regexControlCharacterSet = new HashSet();
		regexControlCharacterSet.add("[");
		regexControlCharacterSet.add("(");
		regexControlCharacterSet.add("{");
		regexControlCharacterSet.add("[");
		regexControlCharacterSet.add("<");
		regexControlCharacterSet.add("]");
		regexControlCharacterSet.add(")");
		regexControlCharacterSet.add("}");
		regexControlCharacterSet.add("]");
		regexControlCharacterSet.add(">");
		regexControlCharacterSet.add(".");
		regexControlCharacterSet.add(",");
		regexControlCharacterSet.add(":");
		regexControlCharacterSet.add("?");
		regexControlCharacterSet.add("!");
		regexControlCharacterSet.add("&");
		regexControlCharacterSet.add("|");
		regexControlCharacterSet.add("^");
		regexControlCharacterSet.add("$");
		regexControlCharacterSet.add("+");
		regexControlCharacterSet.add("-");
		// regexControlCharacterSet.add("\\"); //causes exceptions because it
		// changes \\Q and \\E!...tackled individually
		regexControlCharacterSet.add("*");
		regexControlCharacterSet.add("=");
	}

	/**
	 * Converts a string to a regular expression, so that it is considered
	 * properly by the matcher.
	 * 
	 * @author K. Siek
	 * @version 2.002
	 * @param String
	 * @return String
	 */
	public static String convertToRegex(String string) {
		if (regexControlCharacterSet == null)
			initiateRegexControlCharacterSet();
		if (string.indexOf("\\") != -1) {
			string = quote(string, "\\");
		}
		for (Iterator i = regexControlCharacterSet.iterator(); i.hasNext();) {
			String unsafe = (String) i.next();
			boolean stringIsUnsafe = string.indexOf(unsafe) != -1;
			// boolean stringIsUnQuoted = ((string.indexOf("\\Q")==-1 )&&(
			// string.indexOf("\\E")==-1));
			// boolean stringIsQuoted = ((string.indexOf("\\Q")<position
			// )&&(string.indexOf("\\E",position)>position));
			if (stringIsUnsafe) {
				string = quote(string, unsafe);
			}
		}
		return string;
	}

	/**
	 * Puts quotation symbols around selected unsafe string of characters
	 * 
	 * @author K. Siek
	 * @version 2.002
	 * @param String
	 *            string with unsafe symbols
	 * @param String
	 *            characters to quote
	 * @return String
	 */
	public static String quote(String string, String charactersToQuote) {
		return string.replaceAll("\\Q" + charactersToQuote + "\\E", "\\\\Q"
				+ charactersToQuote + "\\\\E");
	}
}
