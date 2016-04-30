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
import java.util.Set;

public class ConfigurationItem {
	private Object key;
	private Object state;
	private Set possible;
	private String description;

	public boolean setState(Object state) {
		if (possible == null || possible.isEmpty() || possible.contains(state)) {
			this.state = state;
			return true;
		}
		return false;
	}

	public boolean setDescription(String description) {
		this.description = description;
		return true;
	}

	/**
	 * Returns a description (tooltip material) including author's brief summary
	 * and possible states if any)
	 * 
	 * @version 2.003
	 * @return String
	 * @author K. Siek
	 */
	public String getDescription() {
		if (possible == null)
			return null;
		String possibilities = new String("(");
		for (Iterator i = possible.iterator(); i.hasNext();) {
			possibilities += (String) i.next();
			if (i.hasNext())
				possibilities += "/";
		}
		possibilities += ") ";
		return "<html>" + possibilities + description + "</html>";
	}

	/**
	 * Transforms the configuration into a string (text file) representation.
	 * 
	 * @version 2.003
	 * @return String
	 * @author K. Siek
	 */
	public String toString() {
		String possibilities = "";
		if (possible != null)
			for (Iterator i = possible.iterator(); i.hasNext();)
				possibilities += "??" + i.next().toString().trim();

		String desc;
		if (description != null && description.length() > 0)
			desc = "??" + ConfigurationParser.unreplace(description).trim();
		else
			desc = "";

		return ConfigurationParser.unreplace(key.toString()).trim() + "??"
				+ ConfigurationParser.unreplace(state.toString()).trim() + desc
				+ possibilities;

	}

	public Object getState() {
		return state;
	}

	public Object getKey() {
		// if(key==null)System.out.println(state);
		return ConfigurationParser.replace(key.toString());
	}

	public boolean setKey(Object key) {
		this.key = key;
		return true;
	}

	/**
	 * Initiates object with full entry data
	 * 
	 * @version 2.001
	 * @param Object
	 *            key (prefferably String)
	 * @param Object
	 *            state (prefferably String)
	 * @param String
	 *            description
	 * @param Set
	 *            of Strings possibilities
	 * @author K. Siek
	 */
	public ConfigurationItem(Object key, Object state, String description,
			Set possible) {
		this.key = key;
		this.possible = possible;
		/*
		 * if(possible!=null) if(possible.contains(state))
		 */
		this.state = state;
		/*
		 * else this.state=null;
		 */
		this.description = description;
	}

	/**
	 * Initiates empty object;
	 * 
	 * @version 2.003
	 * @author K. Siek
	 */
	public ConfigurationItem() {
		// Intentionally empty.
	}

	/**
	 * See if a given state is possible
	 * 
	 * @param String
	 *            state
	 * @return boolean true if state is possible or no possible states exist.
	 * @version 2.001
	 * @author K. Siek
	 */
	public boolean isPossible(String state) {
		if (possible == null)
			return true;
		if (possible.isEmpty())
			return true;
		return possible.contains(state);
	}

	/**
	 * Get the set of possible states
	 * 
	 * @version 2.001
	 * @author K. Siek
	 */
	public Set getPossibilities() {
		return possible;
	}

	public void setPossible(Set possible) {
		this.possible = possible;
	}
}
