/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.helpers;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Helper class facilitating the interaction with Spring Security's 
 * 'SecurityContextHolder', and authentication in general.
 * @author Guillaume Prevost
 *
 */
public class FileTypeHelper {
	
	private static String[] arrPlainTextFiletypes = {"text/plain", "application/txt", "browser/internal", "text/anytext", "widetext/plain", "widetext/paragraph"};
	private static String[] arrJpegFiletypes = {"image/jpeg", "image/jpg", "image/jp_", "application/jpg", "application/x-jpg", "image/pjpeg", "image/pipeg", "image/vnd.swiftview-jpeg","image/x-xbitmap"};
	private static  String[] arrCsvFiletypes = {"text/comma-separated-values", "text/csv", "application/csv", "application/excel", "application/vnd.ms-excel", "application/vnd.msexcel"};
    
	/**
	 * Check whether the given file is among the allowed content type
	 */
	public static Boolean IsFileContentTypeAllowed(MultipartFile file) {
		return (IsFilePlaintext(file) || IsFileJpeg(file) || IsFileCsv(file));
	}
	
	/**
	 * Checks whether the given content type is plain text
	 */
	public static Boolean IsFilePlaintext(MultipartFile file) {
		return IsContentTypePlaintext(file.getContentType());
	}
	
	/**
	 * Checks whether the given content type is plain text
	 */
	public static Boolean IsContentTypePlaintext(String contentType) {
		
		if (ArrayUtils.contains(arrPlainTextFiletypes, contentType))
			return true;
		return false;
	}
	
	/**
	 * Checks whether the given content type is a JPEG picture
	 */
	public static Boolean IsFileJpeg(MultipartFile file) {
		return IsContentTypeJpeg(file.getContentType());
	}
	
	/**
	 * Checks whether the given content type is JPEG
	 */
	public static Boolean IsContentTypeJpeg(String contentType) {
		
		if (ArrayUtils.contains(arrJpegFiletypes, contentType))
			return true;
		return false;
	}
	
	/**
	 * Checks whether the given content type is a CSV file
	 */
	public static Boolean IsFileCsv(MultipartFile file) {
		return IsContentTypeCsv(file.getContentType());
	}
	
	/**
	 * Checks whether the given content type is CSV
	 */
	public static Boolean IsContentTypeCsv(String contentType) {
		
		if (ArrayUtils.contains(arrCsvFiletypes, contentType))
			return true;
		return false;
	}
}
