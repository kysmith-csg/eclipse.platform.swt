package org.eclipse.swt.internal.gtk;

/*
 * (c) Copyright IBM Corp. 2000, 2001, 2002.
 * All rights reserved.
 *
 * The contents of this file are made available under the terms
 * of the GNU Lesser General Public License (LGPL) Version 2.1 that
 * accompanies this distribution (lgpl-v21.txt).  The LGPL is also
 * available at http://www.gnu.org/licenses/lgpl.html.  If the version
 * of the LGPL at http://www.gnu.org is different to the version of
 * the LGPL accompanying this distribution and there is any conflict
 * between the two license versions, the terms of the LGPL accompanying
 * this distribution shall govern.
 */

public class GdkEventKey extends GdkEvent {
	public int window;
	public byte send_event;
	public int time;
	public int state;
	public int keyval;
	public int length;
	public int string;
	public short hardware_keycode;
    public byte group;
   	public static final int sizeof = 36;
}
