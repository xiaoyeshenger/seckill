package com.jsxa.vapp.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import java.util.List;

@Slf4j
public class ExcelUtil {

    /**
     * @Author: zhangyong
     * description:
     * @Date: 2021-11-01 14:11
     * @Param: workbook excel文件对象,位置信息字符串,格式为 row_col(行_列),即图片在excel文件中的第几行第几列
     * @Return:
     */
    public static byte[] getPictureInfoByExcel(Workbook workbook, String positionInfo) {
        String sheetName = workbook.getSheetName(0);
        if("2007".equals(sheetName)){
            XSSFSheet xssfSheet = (XSSFSheet) workbook.getSheetAt(0);
            for (POIXMLDocumentPart dr : xssfSheet.getRelations()) {
                if (dr instanceof XSSFDrawing) {
                    XSSFDrawing drawing = (XSSFDrawing) dr;
                    List<XSSFShape> shapes = drawing.getShapes();
                    for (XSSFShape shape : shapes) {
                        //XSSFClientAnchor anchor = pic.getPreferredSize();
                        XSSFPicture pic = (XSSFPicture) shape;
                        XSSFClientAnchor anchor = pic.getClientAnchor();
                        CTMarker ctMarker = anchor.getFrom();
                        int rowNumber = ctMarker.getRow();
                        int colNumber = ctMarker.getCol();
                        if((rowNumber+"_"+colNumber).equals(positionInfo)){
                            byte[] data = pic.getPictureData().getData();
                            return data;
                        }
                    }
                }
            }

        }else{
            HSSFSheet hssfSheet = (HSSFSheet) workbook.getSheetAt(0);
            for (HSSFShape hssfShape : hssfSheet.getDrawingPatriarch().getChildren()) {
                if (hssfShape instanceof HSSFPicture) {
                    HSSFPicture pic = (HSSFPicture) hssfShape;
                    HSSFClientAnchor anchor = pic.getClientAnchor();
                    int rowNumber = anchor.getRow1();
                    int colNumber = anchor.getCol1();
                    if((rowNumber+"_"+colNumber).equals(positionInfo)){
                        byte[] data = pic.getPictureData().getData();
                        return data;
                    }

                }
            }
        }
        return new byte[0];
    }


}
