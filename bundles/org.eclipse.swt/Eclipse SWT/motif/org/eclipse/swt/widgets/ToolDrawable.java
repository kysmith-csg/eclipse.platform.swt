package org.eclipse.swt.widgets;

/*
 * Copyright (c) 2000, 2002 IBM Corp.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.motif.*;

class ToolDrawable implements Drawable {
	
	Device device;
	int display;
	int drawable;
	Font font;
	int colormap;
	
public int internal_new_GC (GCData data) {
	int xGC = OS.XCreateGC (display, drawable, 0, null);
	if (xGC == 0) SWT.error(SWT.ERROR_NO_HANDLES);
	if (data != null) {
		data.device = device;
		data.display = display;
		data.drawable = drawable;
		data.fontList = font.handle;
		data.codePage = font.codePage;
		data.colormap = colormap;
	}
	return xGC;
}

public void internal_dispose_GC (int xGC, GCData data) {
	OS.XFreeGC (display, xGC);
}
}
