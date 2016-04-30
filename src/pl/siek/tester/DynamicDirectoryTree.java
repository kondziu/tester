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

/*
 * This code is based on an example provided by Richard Stanford, 
 * a tutorial reader.
 */

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class DynamicDirectoryTree extends JPanel {

	private static final long serialVersionUID = 1L;

	protected DefaultMutableTreeNode rootNode;
	protected DefaultTreeModel treeModel;
	protected JTree tree;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();

	// private Map buttonMap;

	public Object getLastSelectedPathComponent() {
		return tree.getLastSelectedPathComponent();
	}

	public DynamicDirectoryTree(String rootDirName, Icon rootIcon,
			Icon dirIcon, Icon fileIcon, String fileMsg, String unknownMsg,
			String errorMessage, String quickViewCaption, Map buttonMap) {
		super(new GridLayout(1, 0));

		rootNode = new DefaultMutableTreeNode(rootDirName);
		treeModel = new DefaultTreeModel(rootNode);
		treeModel.addTreeModelListener(new MyTreeModelListener());

		tree = new JTree(treeModel);
		// tree.setEditable(true);
		tree.setCellRenderer(new DynamicDirectoryTreeRenderer(rootDirName,
				rootIcon, dirIcon, fileIcon, fileMsg, unknownMsg, errorMessage,
				quickViewCaption));
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
		tree.addTreeSelectionListener(new MyTreeSelectionListener(buttonMap));
		// tree.getModel().addTreeModelListener(new
		// DynamicDirectoryTreeModelListener());
		// tree.setRootVisible(false);
		ToolTipManager.sharedInstance().registerComponent(tree);
		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane);
	}

	/** Remove all nodes except the root node. */
	public void clear() {
		rootNode.removeAllChildren();
		treeModel.reload();
	}

	/** Remove the currently selected node. */
	public void removeCurrentNode() {
		TreePath currentSelection = tree.getSelectionPath();
		if (currentSelection != null) {
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
					.getLastPathComponent());
			MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
			if (parent != null) {
				treeModel.removeNodeFromParent(currentNode);
				return;
			}
		}

		// Either there was no selection, or the root was selected.
		toolkit.beep();
	}

	/** Add child to the currently selected node. */
	public DefaultMutableTreeNode addObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = tree.getSelectionPath();

		if (parentPath == null) {
			parentNode = rootNode;
		} else {
			parentNode = (DefaultMutableTreeNode) (parentPath
					.getLastPathComponent());
		}

		return addObject(parentNode, child, true);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
			Object child) {
		return addObject(parent, child, false);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
			Object child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

		if (parent == null) {
			parent = rootNode;
		}

		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

		// Make sure the user can see the lovely new node.
		if (shouldBeVisible) {
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		return childNode;
	}

	class MyTreeSelectionListener implements TreeSelectionListener {
		Map buttonMap;

		public MyTreeSelectionListener(Map buttonMap) {
			this.buttonMap = buttonMap;
		}

		/*
		 * public static String ADD_COMMAND = "add"; public static String
		 * REMOVE_COMMAND = "remove"; public static String CLOSE_COMMAND =
		 * "clear"; public static String SET_COMMAND = "set"; public static
		 * String DOWNLOAD_COMMAND = "download"; public static String
		 * MKDIR_COMMAND = "mkdir";
		 */
		public void valueChanged(TreeSelectionEvent e) {
			TreePath currentSelection = e.getNewLeadSelectionPath();
			if (currentSelection != null) {
				DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
						.getLastPathComponent());
				File file = null;
				if (!currentNode.isRoot())
					file = (File) currentNode.getUserObject();

				if (file == null || file.isDirectory()) {
					((JButton) buttonMap.get("add")).setEnabled(true);
					((JButton) buttonMap.get("set")).setEnabled(false);
					((JButton) buttonMap.get("remove")).setEnabled(true);
					((JButton) buttonMap.get("mkdir")).setEnabled(true);
					((JButton) buttonMap.get("download")).setEnabled(true);
				} else {
					((JButton) buttonMap.get("add")).setEnabled(false);
					((JButton) buttonMap.get("set")).setEnabled(true);
					((JButton) buttonMap.get("remove")).setEnabled(true);
					((JButton) buttonMap.get("mkdir")).setEnabled(false);
					((JButton) buttonMap.get("download")).setEnabled(false);
				}
			}
		}
	}

	class MyTreeModelListener implements TreeModelListener {
		public void treeNodesChanged(TreeModelEvent e) {
			DefaultMutableTreeNode node;
			node = (DefaultMutableTreeNode) (e.getTreePath()
					.getLastPathComponent());

			/*
			 * If the event lists children, then the changed node is the child
			 * of the node we've already gotten. Otherwise, the changed node and
			 * the specified node are the same.
			 */
			try {
				int index = e.getChildIndices()[0];
				node = (DefaultMutableTreeNode) (node.getChildAt(index));
			} catch (NullPointerException exc) {
				// Intentionally left empty.
			}

			System.out.println("hello !!");
			// System.out.println("The user has finished editing the node.");
			// System.out.println("New value: " + node.getUserObject());
		}

		public void treeNodesInserted(TreeModelEvent e) {
			// Intentionally left empty.
		}

		public void treeNodesRemoved(TreeModelEvent e) {
			// Intentionally left empty.
		}

		public void treeStructureChanged(TreeModelEvent e) {
			// Intentionally left empty.
		}
	}
}
