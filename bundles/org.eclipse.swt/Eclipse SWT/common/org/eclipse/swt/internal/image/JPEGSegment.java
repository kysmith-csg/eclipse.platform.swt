package org.eclipse.swt.internal.image;

/*
 * Copyright (c) 2000, 2002 IBM Corp.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */

import org.eclipse.swt.*;

class JPEGSegment {
	public byte[] reference;

	JPEGSegment() {
	}
	
	public JPEGSegment(byte[] reference) {
		this.reference = reference;
	}
	
	public int signature() {
		return 0;
	}
	
	public boolean verify() {
		return getSegmentMarker() == signature();
	}
	
	public int getSegmentMarker() {
		return ((reference[0] & 0xFF) << 8 | (reference[1] & 0xFF));
	}
	
	public void setSegmentMarker(int marker) {
		reference[0] = (byte)((marker & 0xFF00) >> 8);
		reference[1] = (byte)(marker & 0xFF);
	}
	
	public int getSegmentLength() {
		return ((reference[2] & 0xFF) << 8 | (reference[3] & 0xFF));
	}
	
	public void setSegmentLength(int length) {
		reference[2] = (byte)((length & 0xFF00) >> 8);
		reference[3] = (byte)(length & 0xFF);
	}
	
	public boolean writeToStream(LEDataOutputStream byteStream) {
		try {
			byteStream.write(reference);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
