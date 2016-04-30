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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class InputFile implements Input {
	private File file;

	/**
	 * obtains file to disk
	 * 
	 * @param encoding
	 * @author K. Siek
	 * @version 2.2
	 * @return List of Strings
	 */
	public List obtain(String encoding) throws IOException {
		if (encoding != null && encoding.equals("default")) {
			encoding = null;
		}
		
		List list = new LinkedList();
		InputStream stream = new FileInputStream(file);
		
		InputStreamReader reader = (encoding == null) ? new InputStreamReader(
				stream) : new InputStreamReader(stream, encoding);
				
		BufferedReader bufferedReader = new BufferedReader(reader);
		
		String string = new String();
		while ((string = bufferedReader.readLine()) != null) {
			list.add(string);
		}
		bufferedReader.close();

		return list;
	}

	/**
	 * obtains file to disk
	 * 
	 * @author K. Siek
	 * @version 2.2
	 * @return List of Strings
	 */
	public List obtain() throws IOException {
		return obtain(null);
	}

	/**
	 * initiates object, sets file path to obtain information run method
	 * obtain()
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 *            path
	 */
	public InputFile(String filename) {
		this.file = new File(filename);
	}
}
