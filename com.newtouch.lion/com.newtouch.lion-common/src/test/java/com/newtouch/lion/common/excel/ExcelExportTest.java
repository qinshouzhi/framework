/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ExcelExportTest.java 9552 2015年1月26日 下午5:58:13 WangLijun$
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
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
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
public class ExcelExportTest {
	@Test
	public void test() {
		// 测试学生
		ExcelExport<Student> ex = new ExcelExport<Student>();
		String[] headers = { "学号", "姓名", "年龄", "性别", "出生日期" };
		List<Student> dataset = new ArrayList<Student>();
		dataset.add(new Student(10000001, "张三", 20, true, new Date()));
		dataset.add(new Student(20000002, "李四", 24, false, new Date()));
		dataset.add(new Student(30000003, "王五", 22, true, new Date()));
		// 测试图书
		ExcelExport<Book> ex2 = new ExcelExport<Book>();
		String[] headers2 = { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN",
				"图书出版社", "封面图片" };
		List<Book> dataset2 = new ArrayList<Book>();
		try {
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

			OutputStream out = new FileOutputStream("D:/app/excel/a.xls");
			OutputStream out2 = new FileOutputStream("D:/app/excel/b.xls");
			ex.export("学生",headers, dataset, out);
			ex2.export("书",headers2, dataset2, out2);
			out.close();
			 
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
