package com.ssxu.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UploadUtils {
    // 创建一个新的Excel
    static HSSFWorkbook workBook = null;

    // 创建sheet页
    static HSSFSheet sheet = null;

    /**
     * 保存查询结果至excel中，并下载Excel
     *
     * @param resp response
     * @param name 文件名字
     * @param head 导出列表的名称
     */
    public static void saveDataToExcel(HttpServletResponse resp, String name, String[] head) {
        init(head);
        outPutExcel(resp, name);// 输出Excel
    }

    private static void init(String[] head) {
        // 创建一个新的Excel
        workBook = new HSSFWorkbook();
        // 创建sheet页
        sheet = workBook.createSheet();
        // 设置表名
        // 第0行 列头
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 420);
        for (int i = 0; i < head.length; i++) {
            String cellValue = head[i];
            int width = cellValue.length() < 10 ? 100 : 300;
            sheet.setColumnWidth((short) i, (short) width * 35);
            createHeadCell(row, (short) i).setCellValue(cellValue);
        }
    }

    /**
     * 创建Header单元格
     *
     * @return headCell对象
     */
    private static HSSFCell createHeadCell(HSSFRow row, short colNum) {
        HSSFCell cell = createCell(row, colNum);
        cell.setCellStyle(setStyleText());
        return cell;
    }

    /**
     * 创建单元格 param sheet : sheet页 param rowNum : 行号 param colNum : 列号
     *
     * @return cell对象
     */
    private static HSSFCell createCell(HSSFRow row, int colNum) {
        HSSFCell cell = row.createCell(colNum);
        return cell;
    }

    /**
     * 根据文件名称封装不同的workbook
     *
     * @param fi   流
     * @param name 文件名称
     * @return workbook
     */
    public static Workbook getWorkbook(InputStream fi, String name) throws Exception {
        String uploadName = name.substring(name.lastIndexOf("."));
        Workbook wb;
        if (uploadName.endsWith(".xls"))
            wb = new HSSFWorkbook(fi); //处理以.xls结尾的excel
        else
            wb = new XSSFWorkbook(fi);//处理以.xlsx结尾的excel
        return wb;
    }

    /**
     * 设置为文本格式
     *
     * @return HSSFCellStyle
     */
    private static HSSFCellStyle setStyleText() {
        HSSFCellStyle textStyle = workBook.createCellStyle();
        HSSFDataFormat format = workBook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        return textStyle;
    }

    /**
     * 设置header单元格默认格式：居中、灰色、12号字、粗体
     *
     * @return 格式对象
     */
    /** private HSSFCellStyle getHeaderStyle(HeadStyle headStyle) {
     HSSFCellStyle style = workBook.createCellStyle();
     HSSFFont font = workBook.createFont();
     setBorder(style);
     style.setAlignment(getTextAlign(headStyle.getTextalign()));
     style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 单元格填充：使用前景颜色
     style.setFillForegroundColor(getFillgroundColor(headStyle.getBackgroundcolor()));//
     font.setFontHeightInPoints((short) 11);// 设置字体高度：11号
     // HSSFColor.WHITE.index
     font.setColor(getColor(headStyle.getColor()));
     // 把字体应用到当前的样式
     style.setFont(font);
     return style;
     }*/

    /**private short getTextAlign(String textAlign) {
     short textAlignIndex = 0;
     if ("".equalsIgnoreCase(textAlign) || textAlign.isEmpty()) {
     return HSSFCellStyle.ALIGN_CENTER;
     }
     if ("center".equalsIgnoreCase(textAlign)) {
     textAlignIndex = HSSFCellStyle.ALIGN_CENTER;
     } else if ("left".equalsIgnoreCase(textAlign)) {
     textAlignIndex = HSSFCellStyle.ALIGN_LEFT;
     } else if ("right".equalsIgnoreCase(textAlign)) {
     textAlignIndex = HSSFCellStyle.ALIGN_RIGHT;
     }
     return textAlignIndex;
     }*/

    /** private short getFillgroundColor(String backgroundcolor) {
     if (backgroundcolor.startsWith("#")) {
     int color0 = 0;
     int color1 = 0;
     int color2 = 0;
     if (backgroundcolor.length() == 7) {
     color0 = Integer.parseInt(backgroundcolor.substring(1, 3), 16);
     color1 = Integer.parseInt(backgroundcolor.substring(3, 5), 16);
     color2 = Integer.parseInt(backgroundcolor.substring(5, 7), 16);
     } else if (backgroundcolor.length() == 4) {
     color0 = Integer.parseInt(backgroundcolor.substring(1, 2) + backgroundcolor.substring(1, 2), 16);
     color1 = Integer.parseInt(backgroundcolor.substring(2, 3) + backgroundcolor.substring(2, 3), 16);
     color2 = Integer.parseInt(backgroundcolor.substring(3, 4) + backgroundcolor.substring(3, 4), 16);
     }

     HSSFPalette palette = workBook.getCustomPalette();
     palette.setColorAtIndex((short) 60, (byte) color0, (byte) color1, (byte) color2);
     }
     return 60;
     }*/

    /**private short getColor(String color) {
     if (color.startsWith("#")) {
     int color0 = 0;
     int color1 = 0;
     int color2 = 0;
     if (color.length() == 7) {
     color0 = Integer.parseInt(color.substring(1, 3), 16);
     color1 = Integer.parseInt(color.substring(3, 5), 16);
     color2 = Integer.parseInt(color.substring(5, 7), 16);
     } else if (color.length() == 4) {
     color0 = Integer.parseInt(color.substring(1, 2) + color.substring(1, 2), 16);
     color1 = Integer.parseInt(color.substring(2, 3) + color.substring(2, 3), 16);
     color2 = Integer.parseInt(color.substring(3, 4) + color.substring(3, 4), 16);
     }
     HSSFPalette palette = workBook.getCustomPalette();
     palette.setColorAtIndex((short) 61, (byte) color0, (byte) color1, (byte) color2);
     }
     return 61;
     }*/

    /**
     * 设置单元格默认格式：边框样式
     *
     * @return 格式对象
     */
    /**private HSSFCellStyle setBorder(HSSFCellStyle style) {
     style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
     style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
     style.setBorderRight(HSSFCellStyle.BORDER_THIN);
     style.setBorderTop(HSSFCellStyle.BORDER_THIN);
     return style;
     }*/

    /**
     * 初始化表体
     *
     * @param list
     *            查询结果
     */
    /**private void initBody(List list, List<Head> headerList) {
     // 定义样式style：居左、自动换行，style1：居右，style2：居中,style3：居左
     HSSFCellStyle style = workBook.createCellStyle();
     style.setWrapText(false);// 不自动换行
     style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
     HSSFCellStyle style1 = workBook.createCellStyle();
     // style1.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
     style1.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
     HSSFCellStyle style2 = workBook.createCellStyle();
     style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     HSSFCellStyle style3 = workBook.createCellStyle();
     style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);

     for (int i = 0; i < list.size(); i++) {
     HSSFRow row = sheet.createRow(i + 1);
     int colNum = 0;
     Map recordMap = (Map) list.get(i);
     // 根据表头循环，写入对应单元格数据，循环一次写入一行数据
     for (Head head : headerList) {
     HSSFCell cell;
     String dataIndex = head.getDataIndex();
     String type = (String) map.get(colNum);
     if ("int".equalsIgnoreCase(type)) {
     cell = createBodyCell(style1, row, (short) colNum);
     } else {
     cell = createBodyCell(style3, row, (short) colNum);
     }
     cell.setCellType(HSSFCell.CELL_TYPE_STRING);
     row.setHeight((short) 380);
     Object ssString = recordMap.get(dataIndex.toLowerCase());
     if (ssString != null) {
     cell.setCellValue(ssString.toString());
     } else {
     cell.setCellValue("");
     }

     colNum++;
     }
     }
     }*/

    /**
     * 创建Body单元格
     *
     * @return Body对象
     */
    /** private HSSFCell createBodyCell(HSSFCellStyle style, HSSFRow row, short colNum) {
     HSSFCell cell = createCell(row, colNum);
     cell.setCellStyle(getBodyStyle(style, colNum));
     return cell;
     }*/

    /**
     * 设置body单元格默认格式
     *
     * @return 格式对象
     */
    /** private HSSFCellStyle getBodyStyle(HSSFCellStyle style, short colNum) {
     setBorder(style);
     return style;
     }*/

    /**
     * 输出Excel
     */
    private static void outPutExcel(HttpServletResponse response, String name) {
        // 通过Response把数据以Excel格式保存
        response.reset();
        response.setContentType("application/msexcel;charset=UTF-8");
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String fileName = "模板" + name + df.format(new Date());
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String((fileName + ".xls").getBytes("GBK"), "ISO8859_1"));
            // 定义输出类型
            OutputStream out = response.getOutputStream();
            workBook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * excel单元格校验
     *
     * @param row  第几行
     * @param name 名字
     * @param val  值
     * @return
     */
    public static String excelJy(int row, String name, String val) {
        if (StringUtils.isEmpty(val)) {
            return "第" + row + "行" + name + "不能为空<br />";
        }
        if (name.equals("身份证号")) {
            if (!RegUtil.isIdCard(val)) {
                return "第" + row + "行" + name + "格式不正确,只能是英文和数字<br />";
            }
        } else if (name.equals("手机号")) {
            if (!RegUtil.isPhone(val)) {
                return "第" + row + "行" + name + "格式不正确<br />";
            }
        }
        return "";
    }

    /**
     * 校验手机号是否存在
     *
     * @param row       第几行
     * @param phoneList 数据库所有的手机号
     * @param name      提示名称
     * @param phone     要校验的手机号
     * @return true 存在  false 不存在
     */
    public static String isExitPhone(int row, String name, List<String> phoneList, String phone) {
        if (phoneList.contains(phone))
            return "第" + row + "行" + name + "手机号存在重复<br />";
        return "";
    }

    /**
     * 格式不正确校验
     *
     * @param row
     * @return
     */
    public static String excelGs(int row, String name) {
        return "第" + row + "行" + name + "没有找到对应的数据<br />";
    }
}
