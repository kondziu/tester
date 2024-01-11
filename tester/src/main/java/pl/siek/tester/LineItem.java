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
import java.util.LinkedList;
import java.util.List;

public class LineItem {
	/**
	 * Holds information about a single question and answer.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 */
	public List answerList;
	public String question;
	public String hint;
	public String type;
	Configuration config;

	/**
	 * initiates object
	 * 
	 * @author K. Siek
	 * @version 2.1
	 * @param Configuration
	 *            regex config
	 */
	LineItem(Configuration config) {
		this.config = config;
		answerList = new LinkedList();
	}

	/**
	 * initiates object
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param List
	 *            list of Strings containing answers
	 * @param String
	 *            hint
	 * @param String
	 *            question
	 * @param String
	 *            type (i.e. text, sound, image)
	 * @param Configuration
	 *            regex config
	 */
	LineItem(List answerList, String hint, String question, String type,
			Configuration config) {
		this.answerList = answerList;
		this.question = question;
		this.hint = hint;
		this.type = type;
		this.config = config;
	}

	/**
	 * converts the object into a line of text
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @return String
	 */
	public String toString() {
		String answer = new String();
		// String hintAndType = new String();
		if (answerList == null)
			return null;
		for (Iterator iterator = answerList.listIterator(); iterator.hasNext();) {
			answer += (String) iterator.next();
			if (iterator.hasNext())
				answer += config.getConfig("alternative");
		}
		if (hint == null || hint.length() == 0)
			hint = "";

		if (type != null && type != "text" && type != "")
			question = config.getConfig(ConfigurationDefaultRegex.TYPE_MODIFIER
					+ type.trim())
					+ question;

		String left, right;
		if (config.getConfig("question position").compareToIgnoreCase("right") == 0) {
			left = answer;
			right = question;
		} else {
			right = answer;
			left = question;
		}
		// hintAndType="type: "+type;
		// else hintAndType=hint+"; type: "+type;

		return left + config.getConfig("hint open") + hint
				+ config.getConfig("hint close") + right;

	}
}
