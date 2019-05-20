package com.btjf.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.btjf.vo.EvaluateVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by yj on 2019/5/17.
 */
public class Export {



    public static void createExcel(List<EvaluateVo> list, String path){
        FileOutputStream fos=null;
        Workbook workbook=new XSSFWorkbook();
        Sheet sheet=workbook.createSheet("评价统计");
        String[] title={"编号","办事日期","办事时间","办事部门","窗口人员","办理窗口","办公人员","手机号","提问评价","是否满意","星级评价"};
        Row row=sheet.createRow((short)0);
        int i=0;
        for(String s:title){
            Cell cell=row.createCell(i);
            cell.setCellValue(s);
            i++;
        }
        int j=1;
        for(EvaluateVo t:list){
            //创建第二行
            Row rowData=sheet.createRow((short)j);
            //第一列数据
            Cell cell0=rowData.createCell((short)0);
            cell0.setCellValue(t.getID());
            //设置单元格的宽度
            sheet.setColumnWidth((short)0, (short)10000);
            //第二列数据
            Cell cell1=rowData.createCell((short)1);
            cell1.setCellValue(t.getCreateDate());
            //设置单元格的宽度
            sheet.setColumnWidth((short)0, (short)10000);
            //第三列数据
            Cell cell2=rowData.createCell((short)2);
            cell2.setCellValue(t.getBeginTime());
            //设置单元格的宽度
            sheet.setColumnWidth((short)0, (short)10000);
            //第四列数据
            Cell cell3=rowData.createCell((short)3);
            cell3.setCellValue(t.getDeptName());
            //设置单元格的宽度
            sheet.setColumnWidth((short)0, (short)10000);
            //第五列数据
            Cell cell4=rowData.createCell((short)4);
            cell4.setCellValue(t.getStaffName());
            //设置单元格的宽度
            sheet.setColumnWidth((short)0, (short)10000);
            //第六列数据
            Cell cell5=rowData.createCell((short)5);
            cell5.setCellValue(t.getWindowId());
            //设置单元格的宽度
            sheet.setColumnWidth((short)0, (short)10000);
            //第七列数据
            Cell cell6=rowData.createCell((short)6);
            cell6.setCellValue(t.getCustName());
            //设置单元格的宽度
            sheet.setColumnWidth((short)0, (short)10000);
            //第八列数据
            Cell cell7=rowData.createCell((short)7);
            cell7.setCellValue(t.getCustMobile());
            //设置单元格的宽度
            sheet.setColumnWidth((short)0, (short)10000);
            //第九列数据
            Cell cell8=rowData.createCell((short)8);
            cell8.setCellValue(t.getQuestion());
            //设置单元格的宽度
            sheet.setColumnWidth((short)0, (short)10000);
            //第十列数据
            Cell cell9=rowData.createCell((short)9);
            cell9.setCellValue(t.getAnswer()== null? "":t.getAnswer().toString());
            //设置单元格的宽度
            sheet.setColumnWidth((short)0, (short)10000);
            Cell cel20=rowData.createCell((short)10);
            cel20.setCellValue(t.getEvaluate()== null? "":t.getEvaluate().toString());
            //设置单元格的宽度
            sheet.setColumnWidth((short)0, (short)10000);
            j++;
        }
        try {
            //导出数据库文件保存路径
            fos=new FileOutputStream(path + System.currentTimeMillis() + ".xlsx");
      /*if(fos.toString().endsWith("xlsx")){
        workbook=new XSSFWorkbook();
      }else if(fos.toString().endsWith("xls")){
        workbook=new HSSFWorkbook();
      }*/
            //将工作簿写入文件
            workbook.write(fos);
            System.out.println("导出文件成功");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("导出文件失败");
        }
    }
//    public static void main(String[] args) throws Exception {
//        //连接数据库
//        Connection conn=BaseDAO.getConnection();
//        PreparedStatement ps=null;
//        String sql="select * from TEST";
//        //执行sql语句
//        ps=conn.prepareStatement(sql);
//        //查询数据库之后得到的结果
//        ResultSet rs=ps.executeQuery();
//        List<Test> list=new ArrayList<Test>();
//        //遍历查询结果
//        while(rs.next()){
//            Test test=new Test();
//            test.setA(rs.getString("A"));
//            test.setB(rs.getString("B"));
//            test.setC(rs.getString("C"));
//            test.setD(rs.getString("D"));
//            test.setE(rs.getString("E"));
//            test.setF(rs.getString("F"));
//            test.setG(rs.getString("G"));
//            test.setH(rs.getString("H"));
//            test.setI(rs.getString("I"));
//            test.setJ(rs.getString("J"));
//            list.add(test);
//        }
//        createExcel(list);
//    }

}
