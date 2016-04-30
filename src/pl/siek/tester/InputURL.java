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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

public class InputURL implements Input {
	private String adress;

	/**
	 * Loads the file into memory.
	 * 
	 * @author K. Siek
	 * @version 2.2
	 * @throws IOException
	 */
	public List obtain() throws IOException {
		return obtain(null);
	}

	/**
	 * Loads the file into memory.
	 * 
	 * @param encoding
	 * @author K. Siek
	 * @version 2.2
	 * @throws IOException
	 */
	public List obtain(String encoding) throws IOException {
		if (encoding != null && encoding.equals("default")) {
			encoding = null;
		}

		URL url = new URL(adress);
		URLConnection urlConnection = url.openConnection();
		InputStream inputStream = urlConnection.getInputStream();

		InputStreamReader reader = (encoding == null) ? new InputStreamReader(
				inputStream) : new InputStreamReader(inputStream, encoding);

		BufferedReader link = new BufferedReader(reader);

		String string;
		List list = new LinkedList();
		while ((string = link.readLine()) != null) {
			list.add(string);
		}
		link.close();
		return list;
	}

	/**
	 * Creates an instance of the class, sets the url.
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 *            url of file
	 */
	public InputURL(String url) {
		this.adress = url;
	}

}
