/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ExcelExportSimpleTest.java 9552 2015年1月27日 下午6:25:32 WangLijun$
 */
package com.newtouch.lion.common.excel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.newtouch.lion.common.date.DateUtil;

/**
 * <p>
 * Title: 简单Excel导出功能测试
 * </p>
 * <p>
 * Description: 简单Excel导出功能测试
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class ExcelExportSimpleTest {
	@Test
	public void export() {
		// 测试学生
		 
		ExcelExportSimple<Student> exs = new ExcelExportSimple<Student>();
		List<Header> headers1=new ArrayList<Header>();
		headers1.add(new Header("id","学号"));
		headers1.add(new Header("name","姓名"));
		headers1.add(new Header("sex","性别"));
		headers1.add(new Header("age","年龄"));
		headers1.add(new Header("birthday","出生日期"));
		headers1.add(new Header("regDate","注册日期",5000));
		ExcelSheet excelSheet=new ExcelSheet("学生",headers1);
		
		List<Student> dataset = new ArrayList<Student>();
		dataset.add(new Student(10000001, "张三", 20, true, new Date(),
				new Date()));
		dataset.add(new Student(20000002, "李四", 24, false, new Date(),
				new Date()));
		dataset.add(new Student(30000003, "王五", 22, true, new Date(),
				new Date()));
		// 测试图书
		ExcelExport<Book> ex2 = new ExcelExport<Book>();
		String[] headers2 = { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN",
				"图书出版社", "封面图片" };
		List<Book> dataset2 = new ArrayList<Book>();
		try {
			@SuppressWarnings("resource")
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream("D:/app/excel/preview.jpg"));
			byte[] buf = new byte[bis.available()];
			while ((bis.read(buf)) != -1) {
				//
			}
			dataset2.add(new Book(1, "jsp", "leno", 300.33f, "1234567",
					"清华出版社", buf));
			dataset2.add(new Book(2, "java编程思想", "brucl", 300.33f, "1234567",
					"阳光出版社", buf));
			dataset2.add(new Book(3, "DOM艺术", "lenotang", 300.33f, "1234567",
					"清华出版社", buf));
			dataset2.add(new Book(4, "c++经典", "leno", 400.33f, "1234567",
					"清华出版社", buf));
			dataset2.add(new Book(5, "c#入门", "leno", 300.33f, "1234567",
					"汤春秀出版社", buf));

			OutputStream out = new FileOutputStream("D:/app/excel/a1.xls");
			OutputStream out2 = new FileOutputStream("D:/app/excel/b1.xls");
			// 性别转换
			Map<Object, Object> sexMap = new HashMap<Object, Object>();
			sexMap.put(Boolean.FALSE, "女");
			sexMap.put(Boolean.TRUE, "男");

			Map<String, Map<Object, Object>> fieldCodeTypes = new HashMap<String, Map<Object, Object>>();
			fieldCodeTypes.put("sex", sexMap);

			Map<String, String> dataFormats = new HashMap<String, String>();
			dataFormats.put("birthday", DateUtil.FORMAT_DATE_YYYY_MM_DD);

			exs.export(excelSheet,dataset, out, fieldCodeTypes, dataFormats);
			ex2.export("书", headers2, dataset2, out2);
			out.close();
			out2.close();
			System.out.println("excel导出成功！");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
