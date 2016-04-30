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
import java.util.Map;

public class ConfigurationDefaultRegex extends ConfigurationDefault {

	public static String ALTERNATIVE = "alternative";
	public static String COMMENT = "comment";
	public static String HINT_CLOSE = "hint close";
	public static String HINT_OPEN = "hint open";
	public static String QUESTION_POSITION = "question position";
	public static String REPLACE_EXCLAMATION = "replace: !";
	public static String REPLACE_COMMA = "replace: ,";
	public static String REPLACE_STOP = "replace: .";
	public static String REPLACE_COLON = "replace: :";
	public static String REPLACE_SEMICOLON = "replace: ;";
	public static String REPLACE_QUESTION = "replace:<QUESTION>";
	public static String REPLACEMENT_POINT = "replacement point";
	public static String TYPE_IMAGE = "type: image";
	public static String TYPE_SOUND = "type: sound";
	public static String TYPE_MEDIA = "type: media";
	public static String TYPE_MODIFIER = "type: ";

	public static final String IMAGE = "image";
	public static final String SOUND = "sound";
	public static final String MEDIA = "media";

	public static Map getDefaultConfig() {
		Map map = new HashMap();

		map
				.put(
						ALTERNATIVE,
						new ConfigurationItem(
								ALTERNATIVE,
								"||",
								"if multiple answers are correct this symbol is used to divide them",
								null));
		map
				.put(
						COMMENT,
						new ConfigurationItem(
								COMMENT,
								"//",
								"some lines may include commented fragments at the end of line, which will not be read by the program<BR>for economical purposes these are not saved in the memory, and might disappear",
								null));
		map.put(HINT_CLOSE, new ConfigurationItem(HINT_CLOSE, "]",
				"denotes the start of hint", null));
		map.put(HINT_OPEN, new ConfigurationItem(HINT_OPEN, "[",
				"denotes the end of hint", null));
		map
				.put(
						QUESTION_POSITION,
						new ConfigurationItem(
								QUESTION_POSITION,
								"right",
								"denotes the position of the question in line <BR> (in relation to the hint based in the centre)",
								null));
		map
				.put(
						REPLACE_EXCLAMATION,
						new ConfigurationItem(
								REPLACE_EXCLAMATION,
								"<SPACE>",
								"exclamation mark '!' will be replaced with an empty character ' '",
								null));
		map
				.put(
						REPLACE_COMMA,
						new ConfigurationItem(
								REPLACE_COMMA,
								"<SPACE>",
								"comma ',' will be replaced with an empty character ' '",
								null));
		map
				.put(
						REPLACE_STOP,
						new ConfigurationItem(
								REPLACE_STOP,
								"<SPACE>",
								"full stop mark '.' will be replaced with an empty character ' '",
								null));
		map
				.put(
						REPLACE_COLON,
						new ConfigurationItem(
								REPLACE_COLON,
								"<SPACE>",
								"colon ':' will be replaced with an empty character ' '",
								null));
		map.put(REPLACE_SEMICOLON, new ConfigurationItem(REPLACE_SEMICOLON,
				"<SPACE>",
				"semicolo';' will be replaced with an empty character ' '",
				null));
		map
				.put(
						REPLACE_QUESTION,
						new ConfigurationItem(
								REPLACE_QUESTION,
								"<SPACE>",
								"question mark '?' will be replaced with an empty character ' '",
								null));
		map.put(REPLACEMENT_POINT, new ConfigurationItem(REPLACEMENT_POINT,
				"file",
				"where will the characters for marked replacement be removed",
				null));
		map.put(TYPE_IMAGE, new ConfigurationItem(TYPE_IMAGE, "<img>",
				"if placed, question becomes path for an image", null));
		// map.put(IMAGE,new ConfigurationItem(IMAGE,"image",null,null));
		map.put(TYPE_SOUND, new ConfigurationItem(TYPE_SOUND, "<snd>",
				"if placed, question becomes path for a sound file", null));
		map
				.put(
						TYPE_MEDIA,
						new ConfigurationItem(
								TYPE_MEDIA,
								"<media>",
								"if placed, question becomes path for a multimedia (sound, video etc.) file <br> and will be played via an external multimedia application",
								null));

		return map;
	}
}
