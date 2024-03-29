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

import pl.siek.tester.Main;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.exceptions.ConfigException;

public class Tester {

	/**
	 * This runs the program.
	 * 
	 * @param not
	 *            important
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main().setVisible(true);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ConfigException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AnnotationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
