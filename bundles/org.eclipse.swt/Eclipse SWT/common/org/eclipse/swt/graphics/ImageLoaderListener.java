package org.eclipse.swt.graphics;

/*
 * Copyright (c) 2000, 2002 IBM Corp.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */

import org.eclipse.swt.internal.SWTEventListener;

/**
 * Classes which implement this interface provide methods
 * that deal with the incremental loading of image data. 
 * <p>
 * After creating an instance of a class that implements
 * this interface it can be added to an image loader using the
 * <code>addImageLoaderListener</code> method and removed using
 * the <code>removeImageLoaderListener</code> method. When
 * image data is either partially or completely loaded, this
 * method will be invoked.
 * </p>
 *
 * @see ImageLoader
 * @see ImageLoaderEvent
 */

public interface ImageLoaderListener extends SWTEventListener {

/**
 * Sent when image data is either partially or completely loaded.
 * <p>
 * The timing of when this method is called varies depending on
 * the format of the image being loaded.
 * </p>
 *
 * @param e an event containing information about the image loading operation
 */
public void imageDataLoaded(ImageLoaderEvent e);

}
