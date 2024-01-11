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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Clock extends TimerTask {
	Timer timer;
	JLabel label;
	Calendar calendar;

	public Clock(JLabel label) {
		this.label = label;
	}

	public void init() {
		timer = new Timer();
		calendar = new GregorianCalendar();
		timer.schedule(this, 0L, 1000L);

	}

	public void run() {
		Date date = new Date();
		calendar.setTime(date);
		int seconds = calendar.get(Calendar.SECOND);
		int minutes = calendar.get(Calendar.MINUTE);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		String text = new String();

		if (hours < 10)
			text += "0";
		text += String.valueOf(hours) + " : ";
		if (minutes < 10)
			text += "0";
		text += String.valueOf(minutes) + " : ";
		if (seconds < 10)
			text += "0";
		text += String.valueOf(seconds);

		label.setText(text);
	}

}
