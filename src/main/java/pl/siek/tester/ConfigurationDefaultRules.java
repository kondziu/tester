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

public class ConfigurationDefaultRules extends ConfigurationDefault {

	public static String ARE_NOT = "are not";
	public static String CANNOT = "cannot";
	public static String COULD_NOT = "could not";
	public static String DO_NOT = "do not";
	public static String HAD_GOT = "had got";
	public static String HAVE_GOT = "have got";
	public static String HE_HAD = "he had";
	public static String HE_HAS = "he has";
	public static String HE_IS = "he is";
	public static String HE_WILL = "he will";
	public static String HE_WOULD = "he would";
	public static String I_AM = "i am";
	public static String I_HAD = "i had";
	public static String I_HAVE = "i have";
	public static String I_WILL = "i will";
	public static String I_WOULD = "i would";
	public static String IS_NOT = "is not";
	public static String IT_HAD_ = "it had";
	public static String IT_HAS = "it has";
	public static String IT_IS = "it is";
	public static String IT_WILL = "it will";
	public static String IT_WOULD_ = "it would";
	public static String MUST_NOT = "must not";
	public static String NEED_NOT = "need not";
	public static String SHE_HAD = "she had";
	public static String SHE_HAS = "she has";
	public static String SHE_IS = "she is";
	public static String SHE_WILL = "she will";
	public static String SHE_WOULD = "she would";
	public static String SHOULD_NOT = "should not";
	public static String THEY_ARE = "they are";
	public static String THEY_HAD = "they had";
	public static String THEY_HAVE = "they have";
	public static String THEY_WILL = "they will";
	public static String THEY_WOULD = "they would";
	public static String WE_ARE = "we are";
	public static String WE_HAD = "we had";
	public static String WE_HAVE = "we have";
	public static String WE_WILL = "we will";
	public static String WE_WOULD = "we would";
	public static String WILL_NOT = "will not";
	public static String WOULD_NOT = "would not";
	public static String YOU_ARE = "you are";
	public static String YOU_HAD = "you had";
	public static String YOU_HAVE = "you have";
	public static String YOU_WILL = "you will";
	public static String YOU_WOULD = "you would";

	public static Map getDefaultConfig() {
		Map map = new HashMap();
		map.put(ARE_NOT, new ConfigurationItem(ARE_NOT, "aren't", null, null));
		map.put(CANNOT, new ConfigurationItem(CANNOT, "can't", null, null));
		map.put(COULD_NOT, new ConfigurationItem(COULD_NOT, "couldn't", null,
				null));
		map.put(DO_NOT, new ConfigurationItem(DO_NOT, "don't", null, null));
		map.put(HAD_GOT, new ConfigurationItem(HAD_GOT, "had", null, null));
		map.put(HAVE_GOT, new ConfigurationItem(HAVE_GOT, "have", null, null));
		map.put(HE_HAD, new ConfigurationItem(HE_HAD, "he'd", null, null));
		map.put(HE_HAS, new ConfigurationItem(HE_HAS, "he's", null, null));
		map.put(HE_IS, new ConfigurationItem(HE_IS, "he's", null, null));
		map.put(HE_WILL, new ConfigurationItem(HE_WILL, "he'll", null, null));
		map.put(HE_WOULD, new ConfigurationItem(HE_WOULD, "he'd", null, null));
		map.put(I_AM, new ConfigurationItem(I_AM, "i'm", null, null));
		map.put(I_HAD, new ConfigurationItem(I_HAD, "i'd", null, null));
		map.put(I_HAVE, new ConfigurationItem(I_HAVE, "i've", null, null));
		map.put(I_WILL, new ConfigurationItem(I_WILL, "i'll", null, null));
		map.put(I_WOULD, new ConfigurationItem(I_WOULD, "i'd", null, null));
		map.put(IS_NOT, new ConfigurationItem(IS_NOT, "isn't", null, null));
		map.put(IT_HAD_, new ConfigurationItem(IT_HAD_, "it'd", null, null));
		map.put(IT_HAS, new ConfigurationItem(IT_HAS, "it's", null, null));
		map.put(IT_IS, new ConfigurationItem(IT_IS, "it's", null, null));
		map.put(IT_WILL, new ConfigurationItem(IT_WILL, "it'll", null, null));
		map
				.put(IT_WOULD_, new ConfigurationItem(IT_WOULD_, "it'd", null,
						null));
		map.put(MUST_NOT,
				new ConfigurationItem(MUST_NOT, "mustn't", null, null));
		map.put(NEED_NOT,
				new ConfigurationItem(NEED_NOT, "needn't", null, null));
		map.put(SHE_HAD, new ConfigurationItem(SHE_HAD, "she'd", null, null));
		map.put(SHE_HAS, new ConfigurationItem(SHE_HAS, "she's", null, null));
		map.put(SHE_IS, new ConfigurationItem(SHE_IS, "she's", null, null));
		map
				.put(SHE_WILL, new ConfigurationItem(SHE_WILL, "she'll", null,
						null));
		map.put(SHE_WOULD,
				new ConfigurationItem(SHE_WOULD, "she'd", null, null));
		map.put(SHOULD_NOT, new ConfigurationItem(SHOULD_NOT, "shouldn't",
				null, null));
		map.put(THEY_ARE,
				new ConfigurationItem(THEY_ARE, "they're", null, null));
		map
				.put(THEY_HAD, new ConfigurationItem(THEY_HAD, "they'd", null,
						null));
		map.put(THEY_HAVE, new ConfigurationItem(THEY_HAVE, "they've", null,
				null));
		map.put(THEY_WILL, new ConfigurationItem(THEY_WILL, "they'll", null,
				null));
		map.put(THEY_WOULD, new ConfigurationItem(THEY_WOULD, "they'd", null,
				null));
		map.put(WE_ARE, new ConfigurationItem(WE_ARE, "we're", null, null));
		map.put(WE_HAD, new ConfigurationItem(WE_HAD, "we'd", null, null));
		map.put(WE_HAVE, new ConfigurationItem(WE_HAVE, "we've", null, null));
		map.put(WE_WILL, new ConfigurationItem(WE_WILL, "we'll", null, null));
		map.put(WE_WOULD, new ConfigurationItem(WE_WOULD, "we'd", null, null));
		map.put(WILL_NOT, new ConfigurationItem(WILL_NOT, "won't", null, null));
		map.put(WOULD_NOT, new ConfigurationItem(WOULD_NOT, "wouldn't", null,
				null));
		map.put(YOU_ARE, new ConfigurationItem(YOU_ARE, "you're", null, null));
		map.put(YOU_HAD, new ConfigurationItem(YOU_HAD, "you'd", null, null));
		map
				.put(YOU_HAVE, new ConfigurationItem(YOU_HAVE, "you've", null,
						null));
		map
				.put(YOU_WILL, new ConfigurationItem(YOU_WILL, "you'll", null,
						null));
		map.put(YOU_WOULD,
				new ConfigurationItem(YOU_WOULD, "you'd", null, null));

		return map;
	}

}
