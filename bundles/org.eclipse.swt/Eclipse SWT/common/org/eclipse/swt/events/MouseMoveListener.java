package org.eclipse.swt.events;

/*
 * Copyright (c) 2000, 2002 IBM Corp.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */

import org.eclipse.swt.internal.SWTEventListener;

/**
 * Classes which implement this interface provide a method
 * that deals with the events that are generated as the mouse
 * pointer moves.
 * <p>
 * After creating an instance of a class that implements
 * this interface it can be added to a control using the
 * <code>addMouseMoveListener</code> method and removed using
 * the <code>removeMouseMoveListener</code> method. As the
 * mouse moves, the mouseMove method will be invoked.
 * </p>
 *
 * @see MouseEvent
 */
public interface MouseMoveListener extends SWTEventListener {

/**
 * Sent when the mouse moves.
 *
 * @param e an event containing information about the mouse move
 */
public void mouseMove(MouseEvent e);
}
