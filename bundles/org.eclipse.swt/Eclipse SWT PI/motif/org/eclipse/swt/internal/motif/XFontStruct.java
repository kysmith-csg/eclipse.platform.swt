package org.eclipse.swt.internal.motif;

/*
 * Copyright (c) 2000, 2002 IBM Corp.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
 
public class XFontStruct {
	public int ext_data;
	public int fid;
	public int direction;
	public int min_char_or_byte2;
	public int max_char_or_byte2;
	public int min_byte1;
	public int max_byte1;
	public int all_chars_exist;
	public int default_char;
	public int n_properties;
	public int properties;
	public short min_bounds_lbearing;
	public short min_bounds_rbearing;
	public short min_bounds_width;
	public short min_bounds_ascent;
	public short min_bounds_descent;
	public short min_bounds_attributes;
	public short max_bounds_lbearing;
	public short max_bounds_rbearing;
	public short max_bounds_width;
	public short max_bounds_ascent;
	public short max_bounds_descent;
	public short max_bounds_attributes;
	public int per_char;
	public int ascent;
	public int descent;
	public static final int sizeof = 80;
}
