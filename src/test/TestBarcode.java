package test;

import java.awt.Font;

import com.onbarcode.barcode.EAN13;
import com.onbarcode.barcode.IBarcode;

public class TestBarcode {

	public static void main(String[] args) {
		EAN13 barcode = new EAN13();

		/*
		 * EAN 13 Valid data char set: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
		 * 
		 * EAN 13 Valid data length: 12 digits only, excluding the last checksum
		 * digit
		 */
		barcode.setData("012345678901");

		// for EAN13 with supplement data (2 or 5 digits)
		/*
		 * barcode.setSupData("12"); // supplement bar height vs bar height
		 * ratio barcode.setSupHeight(0.8f); // space between barcode and
		 * supplement barcode (in pixel) barcode.setSupSpace(15);
		 */

		// Unit of Measure, pixel, cm, or inch
		barcode.setUom(IBarcode.UOM_PIXEL);
		// barcode bar module width (X) in pixel
		barcode.setX(3f);
		// barcode bar module height (Y) in pixel
		barcode.setY(75f);

		// barcode image margins
		barcode.setLeftMargin(0f);
		barcode.setRightMargin(0f);
		barcode.setTopMargin(0f);
		barcode.setBottomMargin(0f);

		// barcode image resolution in dpi
		barcode.setResolution(72);

		// disply barcode encoding data below the barcode
		barcode.setShowText(true);
		// barcode encoding data font style
		barcode.setTextFont(new Font("Arial", 0, 12));
		// space between barcode and barcode encoding data
		barcode.setTextMargin(6);

		// barcode displaying angle
		barcode.setRotate(IBarcode.ROTATE_0);

		/*
		 * to save into gif file, file name end with .gif to save into jpeg
		 * file, file name end with .jpg to save into png file, file name end
		 * with .png to save into eps file, call method drawBarcode2EPS and file
		 * name end with .eps
		 */
		try {
			barcode.drawBarcode("D:\\ean13.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
