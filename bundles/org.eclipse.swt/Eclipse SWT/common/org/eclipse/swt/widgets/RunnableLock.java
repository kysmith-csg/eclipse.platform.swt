package org.eclipse.swt.widgets;

/*
 * Copyright (c) 2000, 2002 IBM Corp.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */

/**
 * Instances of this class are used to ensure that an
 * application cannot interfere with the locking mechanism
 * used to implement asynchonous and synchronous communication
 * between widgets and background threads.
 */

class RunnableLock {
	Runnable runnable;
	Thread thread;
	Throwable throwable;
	
RunnableLock (Runnable runnable) {
	this.runnable = runnable;
}

boolean done () {
	return runnable == null || throwable != null;
}

void run () {
	if (runnable != null) runnable.run ();
	runnable = null;
}

}
