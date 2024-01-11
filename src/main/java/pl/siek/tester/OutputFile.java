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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OutputFile implements Output {

	private String fileAccessPath;

	/**
	 * saves List to disk
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param List
	 *            of Strings to be written to file
	 */
	public void generate(List list) throws IOException, NullPointerException {
		PrintWriter outputFile = new PrintWriter(new BufferedWriter(
				new FileWriter(fileAccessPath)));
		for (int i = 0; i < list.size(); i++) {
			outputFile.println(list.get(i));
		}

		outputFile.close();
	}

	/**
	 * initiates object, sets file path to save information run method
	 * generate()
	 * 
	 * @author K. Siek
	 * @version 2.0
	 * @param String
	 *            path
	 */
	public OutputFile(String filename) {
		fileAccessPath = filename;
	}

}
