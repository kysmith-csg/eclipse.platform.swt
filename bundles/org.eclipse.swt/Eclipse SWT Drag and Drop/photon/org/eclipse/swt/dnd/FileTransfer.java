package org.eclipse.swt.dnd;

/*
 * Copyright (c) 2000, 2002 IBM Corp.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
import org.eclipse.swt.internal.Converter;

/**
 * The class <code>FileTransfer</code> provides a platform specific mechanism 
 * for converting a list of files represented as a java <code>String[]</code> to a 
 * platform specific representation of the data and vice versa.  
 * See <code>Transfer</code> for additional information.
 */
public class FileTransfer extends ByteArrayTransfer {
	
	private static FileTransfer _instance = new FileTransfer();
	private static final String TYPENAME = "files";
	private static final int TYPEID = registerType(TYPENAME);

private FileTransfer() {}
/**
 * Returns the singleton instance of the FileTransfer class.
 *
 * @return the singleton instance of the FileTransfer class
 */
public static FileTransfer getInstance () {
	return _instance;
}
/**
 * This implementation of <code>javaToNative</code> converts a list of file names
 * represented by a java <code>String[]</code> to a platform specific representation.
 * Each <code>String</code> in the array contains the absolute path for a single 
 * file or directory.  For additional information see 
 * <code>Transfer#javaToNative</code>.
 * 
 * @param object a java <code>String[]</code> containing the file names to be 
 * converted
 * @param transferData an empty <code>TransferData</code> object; this
 *  object will be filled in on return with the platform specific format of the data
 */
public void javaToNative(Object object, TransferData transferData) {
	if (object == null || !(object instanceof String[])) return;
	// build a byte array from data
	String[] files = (String[])object;
	
	// create a string separated by "new lines" to represent list of files
	String nativeFormat = "";
	for (int i = 0, length = files.length; i < length; i++){
		nativeFormat += "file:"+files[i]+"\r";
	}
	byte[] buffer = Converter.wcsToMbcs(null, nativeFormat, true);
	// pass byte array on to super to convert to native
	super.javaToNative(buffer, transferData);
}
/**
 * This implementation of <code>nativeToJava</code> converts a platform specific 
 * representation of a list of file names to a java <code>String[]</code>.  
 * Each String in the array contains the absolute path for a single file or directory. 
 * For additional information see <code>Transfer#nativeToJava</code>.
 * 
 * @param transferData the platform specific representation of the data to be 
 * been converted
 * @return a java <code>String[]</code> containing a list of file names if the 
 * conversion was successful; otherwise null
 */
public Object nativeToJava(TransferData transferData) {

	byte[] data = (byte[])super.nativeToJava(transferData);
	if (data == null) return null;
	char[] unicode = Converter.mbcsToWcs(null, data);
	String string  = new String(unicode);
	// parse data and convert string to array of files
	int start = string.indexOf("file:");
	if (start == -1) return null;
	start += 5;
	String[] fileNames = new String[0];
	while (start < string.length()) { 
		int end = string.indexOf("\r", start);
		if (end == -1) end = string.length() - 1;
		String fileName = string.substring(start, end);
		
		String[] newFileNames = new String[fileNames.length + 1];
		System.arraycopy(fileNames, 0, newFileNames, 0, fileNames.length);
		newFileNames[fileNames.length] = fileName;
		fileNames = newFileNames;

		start = string.indexOf("file:", end);
		if (start == -1) break;
		start += 5;
	}
	return fileNames;
}
protected String[] getTypeNames(){
	return new String[]{TYPENAME};
}
protected int[] getTypeIds(){
	return new int[]{TYPEID};
}
}
