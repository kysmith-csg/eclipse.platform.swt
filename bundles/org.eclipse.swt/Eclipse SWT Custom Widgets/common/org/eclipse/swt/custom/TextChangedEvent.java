package org.eclipse.swt.custom;
/*
 * Copyright (c) 2000, 2002 IBM Corp.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */

import org.eclipse.swt.events.*;

/**
 * This event is sent by the StyledTextContent implementor when a change to 
 * the text occurs.
 */
public class TextChangedEvent extends TypedEvent {
/**
 * Create the TextChangedEvent to be used by the StyledTextContent implementor.
 * <p>
 *
 * @param source the object that will be sending the TextChangedEvent, 
 * 	cannot be null	
 */
public TextChangedEvent(StyledTextContent source) {
	super(source);
}
}
