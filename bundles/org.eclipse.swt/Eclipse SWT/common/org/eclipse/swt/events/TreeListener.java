package org.eclipse.swt.events;

/*
 * Copyright (c) 2000, 2002 IBM Corp.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */

import org.eclipse.swt.internal.SWTEventListener;

/**
 * Classes which implement this interface provide methods
 * that deal with the expanding and collapsing of tree
 * branches.
 * <p>
 * After creating an instance of a class that implements
 * this interface it can be added to a control using the
 * <code>addTreeListener</code> method and removed using
 * the <code>removeTreeListener</code> method. When a branch
 * of a tree is expanded or collapsed, the appropriate method
 * will be invoked.
 * </p>
 *
 * @see TreeAdapter
 * @see TreeEvent
 */
public interface TreeListener extends SWTEventListener {

/**
 * Sent when a tree branch is collapsed.
 *
 * @param e an event containing information about the tree operation
 */
public void treeCollapsed(TreeEvent e);

/**
 * Sent when a tree branch is expanded.
 *
 * @param e an event containing information about the tree operation
 */
public void treeExpanded(TreeEvent e);
}
