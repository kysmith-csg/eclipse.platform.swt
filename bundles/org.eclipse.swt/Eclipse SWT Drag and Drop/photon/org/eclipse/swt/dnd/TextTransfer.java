package org.eclipse.swt.dnd;

/*
 * (c) Copyright IBM Corp. 2000, 2001, 2002.
 * All Rights Reserved
 */
 
import org.eclipse.swt.internal.Converter;
import org.eclipse.swt.internal.photon.OS;

/**
 * The class <code>TextTransfer</code> provides a platform specific mechanism 
 * for converting plain text represented as a java <code>String</code> 
 * to a platform specific representation of the data and vice versa.  See 
 * <code>Transfer</code> for additional information.
 */
public class TextTransfer extends ByteArrayTransfer {

	private static TextTransfer _instance = new TextTransfer();
	private static final String TYPENAME = "TEXT";
	private static final int TYPEID = registerType(TYPENAME);

private TextTransfer() {
}
/**
 * Returns the singleton instance of the TextTransfer class.
 *
 * @return the singleton instance of the TextTransfer class
 */
public static TextTransfer getInstance () {
	return _instance;
}
/**
 * This implementation of <code>javaToNative</code> converts plain text
 * represented by a java <code>String</code> to a platform specific representation.
 * For additional information see <code>Transfer#javaToNative</code>.
 * 
 * @param object a java <code>String</code> containing text
 * @param transferData an empty <code>TransferData</code> object; this
 *  object will be filled in on return with the platform specific format of the data
 */
public void javaToNative (Object object, TransferData transferData){
	if (object == null || !(object instanceof String)) return;
	byte [] buffer = Converter.wcsToMbcs (null, (String)object, true);
	super.javaToNative(buffer, transferData);
}
/**
 * This implementation of <code>nativeToJava</code> converts a platform specific 
 * representation of plain text to a java <code>String</code>.
 * For additional information see <code>Transfer#nativeToJava</code>.
 * 
 * @param transferData the platform specific representation of the data to be 
 * been converted
 * @return a java <code>String</code> containing text if the 
 * conversion was successful; otherwise null
 */
public Object nativeToJava(TransferData transferData){

	if (transferData.pData == 0 || !(isSupportedType(transferData))) return null;
	
	int size = transferData.length;
	if (size == 0) return null;
	byte[] buffer = new byte[OS.strlen(transferData.pData)];
	OS.memmove(buffer, transferData.pData, buffer.length);

	// convert byte array to a string
	char [] unicode = Converter.mbcsToWcs (null, buffer);
	return new String (unicode);
}
protected String[] getTypeNames(){
	return new String[]{TYPENAME};
}
protected int[] getTypeIds(){
	return new int[]{TYPEID};
}
}
