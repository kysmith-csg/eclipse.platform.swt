package org.eclipse.swt.widgets;

/*
 * Copyright (c) 2000, 2002 IBM Corp.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */

import org.eclipse.swt.internal.gtk.*;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;

/**
 * Instances of this class provide a surface for drawing
 * arbitrary graphics.
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>(none)</dd>
 * <dt><b>Events:</b></dt>
 * <dd>(none)</dd>
 * </dl>
 * <p>
 * This class may be subclassed by custom control implementors
 * who are building controls that are <em>not</em> constructed
 * from aggregates of other controls. That is, they are either
 * painted using SWT graphics calls or are handled by native
 * methods.
 * </p>
 *
 * @see Composite
 */
public class Canvas extends Composite {

	Caret caret;

/*
 *   ===  CONSTRUCTORS  ===
 */


Canvas () {}

/**
 * Constructs a new instance of this class given its parent
 * and a style value describing its behavior and appearance.
 * <p>
 * The style value is either one of the style constants defined in
 * class <code>SWT</code> which is applicable to instances of this
 * class, or must be built by <em>bitwise OR</em>'ing together 
 * (that is, using the <code>int</code> "|" operator) two or more
 * of those <code>SWT</code> style constants. The class description
 * for all SWT widget classes should include a comment which
 * describes the style constants which are applicable to the class.
 * </p>
 *
 * @param parent a composite control which will be the parent of the new instance (cannot be null)
 * @param style the style of control to construct
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the parent</li>
 *    <li>ERROR_INVALID_SUBCLASS - if this class is not an allowed subclass</li>
 * </ul>
 *
 * @see SWT
 * @see Widget#checkSubclass
 * @see Widget#getStyle
 */
public Canvas (Composite parent, int style) {
	super (parent, style);
}

/**
 * Returns the caret.
 * <p>
 * The caret for the control is automatically hidden
 * and shown when the control is painted or resized,
 * when focus is gained or lost and when an the control
 * is scrolled.  To avoid drawing on top of the caret,
 * the programmer must hide and show the caret when
 * drawing in the window any other time.
 * </p>
 *
 * @return the caret
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public Caret getCaret () {
	checkWidget();
	return caret;
}

/**
 * Scrolls a rectangular area of the receiver by first copying 
 * the source area to the destination and then causing the area
 * of the source which is not covered by the destination to
 * be repainted. Children that intersect the rectangle are
 * optionally moved during the operation. In addition, outstanding
 * paint events are flushed before the source area is copied to
 * ensure that the contents of the canvas are drawn correctly.
 *
 * @param destX the x coordinate of the destination
 * @param destY the y coordinate of the destination
 * @param x the x coordinate of the source
 * @param y the y coordinate of the source
 * @param width the width of the area
 * @param height the height of the area
 * @param all <code>true</code>if children should be scrolled, and <code>false</code> otherwise
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void scroll (int destX, int destY, int x, int y, int width, int height, boolean all) {
	if (!isValidThread ()) error (SWT.ERROR_THREAD_INVALID_ACCESS);
	if (!isValidWidget ()) error (SWT.ERROR_WIDGET_DISPOSED);

	if (width <= 0 || height <= 0) return;
	int deltaX = destX - x, deltaY = destY - y;
	if (deltaX == 0 && deltaY == 0) return;
	if (!isVisible ()) return;
	
	/* Hide the caret */
	boolean isVisible = (caret != null) && (caret.isVisible ());
	if (isVisible) caret.hideCaret ();
	
	GtkWidget widget = new GtkWidget();
	OS.memmove (widget, paintHandle(), GtkWidget.sizeof);
	int window = widget.window;
	if (window == 0) return;

	/* Emit a NoExpose Event */
	int gc = OS.gdk_gc_new (window);
	OS.gdk_gc_set_exposures(gc, true);
	OS.gdk_window_copy_area (window, gc, x, y, window, x, y, width, height);
	OS.gdk_gc_destroy (gc);

	/* Flush outstanding Exposes */
	int eventHandle=0;
	while ((eventHandle = OS.gdk_event_get_graphics_expose(window)) != 0) {
		OS.gtk_widget_event(handle, eventHandle);
		OS.gdk_event_free(eventHandle);	
	}

	/* Scroll the window */
	int gc1 = OS.gdk_gc_new (window);
	OS.gdk_gc_set_exposures(gc1, true);
	OS.gdk_window_copy_area (window, gc1, destX, destY, window, x, y, width, height);
	OS.gdk_gc_destroy (gc1);
	boolean disjoint = (destX + width < x) || (x + width < destX) || (destY + height < y) || (y + height < destY);
	if (disjoint) {
		OS.gdk_window_clear_area(window, x, y, width, height);
	} else {
		if (deltaX != 0) {
			int newX = destX - deltaX;
			if (deltaX < 0) newX = destX + width;
			OS.gdk_window_clear_area_e(window, newX, y, Math.abs (deltaX), height);
		}
		if (deltaY != 0) {
			int newY = destY - deltaY;
			if (deltaY < 0) newY = destY + height;
			OS.gdk_window_clear_area_e (window, x, newY, width, Math.abs (deltaY));
		}
	}
	
	/* Show the caret */
	if (isVisible) caret.showCaret ();
}
/**
 * Sets the receiver's caret.
 * <p>
 * The caret for the control is automatically hidden
 * and shown when the control is painted or resized,
 * when focus is gained or lost and when an the control
 * is scrolled.  To avoid drawing on top of the caret,
 * the programmer must hide and show the caret when
 * drawing in the window any other time.
 * </p>
 * @param caret the new caret for the receiver, may be null
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_INVALID_ARGUMENT - if the caret has been disposed</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setCaret (Caret caret) {
	if (!isValidThread ()) error (SWT.ERROR_THREAD_INVALID_ACCESS);
	if (!isValidWidget ()) error (SWT.ERROR_WIDGET_DISPOSED);
	Caret newCaret = caret;
	Caret oldCaret = this.caret;
	this.caret = newCaret;
	if (isFocusControl()) {
		if (oldCaret != null) oldCaret.killFocus ();
		if (newCaret != null) newCaret.setFocus ();
	}
}
public boolean setFocus () {
	if (!isValidThread ()) error (SWT.ERROR_THREAD_INVALID_ACCESS);
	if (!isValidWidget ()) error (SWT.ERROR_WIDGET_DISPOSED);
	if ((style & SWT.NO_FOCUS) != 0) return false;
	return super.setFocus ();
}
int processFocusIn (int int0, int int1, int int2) {
	int result = super.processFocusIn (int0, int1, int2);
	if (caret != null) caret.setFocus ();
	return result;
}
int processFocusOut(int int0, int int1, int int2) {
	int result = super.processFocusOut (int0, int1, int2);
	if (caret != null) caret.killFocus ();
	return result;
}
/*
int processMouseDown (int callData, int arg1, int int2) {
	if ((UtilFuncs.GTK_WIDGET_GET_FLAGS(handle) & OS.GTK_HAS_FOCUS) == 0)
		OS.gtk_widget_grab_focus(handle);
	GdkEventButton gdkEvent = new GdkEventButton ();
	OS.memmove (gdkEvent, callData, GdkEventButton.sizeof);
	int eventType = SWT.MouseDown;
	if (gdkEvent.type == OS.GDK_2BUTTON_PRESS) eventType = SWT.MouseDoubleClick;
	sendMouseEvent (eventType, gdkEvent.button, gdkEvent.state, gdkEvent.time, (int)gdkEvent.x, (int)gdkEvent.y);
	if (gdkEvent.button == 3 && menu != null) {
		menu.setVisible (true);
	}
	return 1;
}

int processMouseUp (int callData, int arg1, int int2) {
	GdkEventButton gdkEvent = new GdkEventButton ();
	OS.memmove (gdkEvent, callData, GdkEventButton.sizeof);
	sendMouseEvent (SWT.MouseUp, gdkEvent.button, gdkEvent.state, gdkEvent.time, (int)gdkEvent.x, (int)gdkEvent.y);
	return 1;
}
*/
int processPaint (int callData, int arg1, int int2) {
	//if (!hooks (SWT.Paint)) return 0;

	GdkEventExpose gdkEvent = new GdkEventExpose ();
	OS.memmove (gdkEvent, callData, GdkEventExpose.sizeof);
	Event event = new Event ();
	event.count = gdkEvent.count;
	event.x = gdkEvent.x;  event.y = gdkEvent.y;
	event.width = gdkEvent.width;  event.height = gdkEvent.height;
	GC gc = event.gc = new GC (this);
	sendEvent (SWT.Paint, event);
	gc.dispose ();
	event.gc = null;
	return 0;
}
void releaseWidget () {
	if (caret != null) caret.releaseWidget ();
	caret = null;
	super.releaseWidget ();
}

}
