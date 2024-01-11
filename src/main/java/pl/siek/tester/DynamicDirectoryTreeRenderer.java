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

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class DynamicDirectoryTreeRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 9218503094273330361L;
	private Icon dirIcon;
	private Icon fileIcon;
	private Icon rootIcon;
	private File rootDir;

	private String error, quickView;
	private String file;
	private String unknown;

	public DynamicDirectoryTreeRenderer(String rootDirName, Icon rootIcon,
			Icon dirIcon, Icon fileIcon, String file, String unknown,
			String error, String quickView) {
		this.dirIcon = dirIcon;
		this.fileIcon = fileIcon;
		this.rootIcon = rootIcon;
		this.rootDir = new File(rootDirName);
		this.error = error;
		this.quickView = quickView;
		this.file = file;
		this.unknown = unknown;
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		// TODO IF NO FILE IT STOPPED HERE THREW CLASS CAST EX
		File dir;
		if (node.isRoot())
			dir = rootDir;
		else
			dir = (File) (node.getUserObject());

		super.getTreeCellRendererComponent(tree, dir.getName(), sel, expanded,
				leaf, row, hasFocus);
		if (node.isRoot()) {
			setToolTipText(node.toString());
			setIcon(rootIcon);
		} else if (!dir.exists()) {
			setToolTipText(error);
			// TODO DESC...
		} else {
			if (dir.isDirectory()) {
				setIcon(dirIcon);
				setToolTipText(dir.getPath());
			} else // if(dir.isFile())
			{
				setIcon(fileIcon);
				// UN-THINGY THIS
				// PUT ICON IN!! BUT!! IT DOESN'T WORK
				String[] byDots = dir.getName().split("\\.");
				String type;
				try {// <HTML> ///0HERE
					type = "<HTML><B>" + byDots[byDots.length - 1] + " " + file
							+ "</B>";
				} catch (Exception e) {
					type = unknown;
				}
				setToolTipText(type);
				if (dir.getName().endsWith(".txt"))
					try {
						BufferedReader file = new BufferedReader(
								new FileReader(dir));// +type+~//down!
						String string = new String("<html><b>" + type + "<br>"
								+ quickView + ":</b><br><i>");
						String s;
						int i = 0;
						while (((s = file.readLine()) != null) && (i < 4)) {
							string += s + "<br>";
							i++;
						}
						if ((s = file.readLine()) != null)
							string += "...";
						string += "</i>";
						file.close();
						setToolTipText(string);

					} catch (IOException e) {
						// TODO IOEXception
						setToolTipText(error);
					}

				// setIcon(fileIcon);

			}
		}
		return this;
	}
}