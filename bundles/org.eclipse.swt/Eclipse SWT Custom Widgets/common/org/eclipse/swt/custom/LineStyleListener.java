package org.eclipse.swt.custom;
/*
 * Copyright (c) 2000, 2002 IBM Corp.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */

import org.eclipse.swt.internal.SWTEventListener;

public interface LineStyleListener extends SWTEventListener {
/**
 * This method is called when a line is about to be drawn in order to get the
 * line's style information.
 * <p>
 *
 * @param event.lineOffset line start offset (input)	
 * @param event.lineText line text (input)
 * @param event.styles array of StyleRanges, need to be in order (output)
 */
public void lineGetStyle(LineStyleEvent event);
}
