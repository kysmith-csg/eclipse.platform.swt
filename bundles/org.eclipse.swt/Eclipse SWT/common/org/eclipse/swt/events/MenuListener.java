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
 * that deal with the hiding and showing of menus.
 * <p>
 * After creating an instance of a class that implements
 * this interface it can be added to a control using the
 * <code>addMenuListener</code> method and removed using
 * the <code>removeMenuListener</code> method. When a
 * menu is hidden or shown, the appropriate method will
 * be invoked.
 * </p>
 *
 * @see MenuAdapter
 * @see MenuEvent
 */
public interface MenuListener extends SWTEventListener {

/**
 * Sent when a menu is hidden.
 *
 * @param e an event containing information about the menu operation
 */
public void menuHidden(MenuEvent e);

/**
 * Sent when a menu is shown.
 *
 * @param e an event containing information about the menu operation
 */
public void menuShown(MenuEvent e);
}
