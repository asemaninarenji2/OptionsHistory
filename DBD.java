import java.sql.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.Component.*;
import java.lang.Object.*;
import java.lang.annotation.*;
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;
import java.io.*;
import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.scene.control.cell.*;
import javafx.beans.property.*;
import javafx.event.*;
import java.sql.*;
public class DBD  // standa for DATABASE DRIVER
{
	
	private Options optionObj;
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	//private ResultSet rs2;
	String insertStr = new String();
	
	public void connect()throws Exception
	{
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		// create  the connection object  
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","java","mM190980");
			
		
	}
	public void disconnect()throws Exception
	{
		con.close();
		
	}
	public String addPriceObj(Prices price)
	{
		
		
		
		
		/*
		String stkID = new String(ID);
		String dat = new String(date);
		String price = new String(pric);
		System.out.println(dat);*/
		try
		{
			connect();
			insertStr="insert into system.price values('"+price.getStkId()+"',"+price.getDat()+","+price.getPrice()+")";
			stmt=con.createStatement();
			stmt.execute(insertStr);
			disconnect();
			
		}
		catch (Exception e)
		{
			
			return "(LINE 65):"+e.getMessage();
		}
		return "added";
		
	}
	
	//
	public String addOptionList(String area, String dat)
	{
		
		Scanner s = new Scanner(area);
		String optionID = new String();
		String stkId= new String ();
		String expDate = new String();
		String optionType=new String();
		String exercisePrice=new String ();
		double bid=0.0;
		double offer=0.0;
		String temp = new String();
		String []array= new String[9];
		
		
		try		
		{	
			connect();
			
			while(s.hasNext())
			{	
				temp = s.nextLine();
				array = temp.split("\\s+");
				optionID= array[0];
				stkId= optionID.substring(0,3);
				expDate=array[1];
				optionType=array[2];
				exercisePrice=array[3];
				bid=Double.parseDouble(array[4]);
				offer=Double.parseDouble(array[5]);
															
				insertStr="insert into system.options values('" + optionID +"','"+stkId+"','"+expDate+"','"+optionType+"','"+ exercisePrice+
					"',"+ bid +","+ offer+","+dat +")";
				//System.out.println(insertStr);
				stmt=con.createStatement();
				stmt.execute(insertStr);
				stmt.execute("commit");
				
				
				
				
				
				
				
			}
			disconnect();
		}
		catch(Exception e)
		{
			
			return e.getMessage();
		}
		return "ok";
		
	}
	public ArrayList<Options> findOption(String stkId,String type,String exerPrice,String expDate,ArrayList<Double> sharePrice)
	{
		String queryStr1 = new String ();
		String queryStr2 =new String ();
		String conition1 = new String();
		String conition2 = new String();
		String conition3 = new String();
		ArrayList<Options> optionsListResult = new ArrayList<Options>();
		
				
		//assessing conditons of the query
		if (type.length()!=0)
		{
			conition1=" AND o.optionType='"+type+"'";
		}
		if (exerPrice.length()!=0)
		{
			conition2=" AND o.exercisePrice='"+exerPrice+"'";
		}
		if (expDate.length()!=0)
		{
			conition3=" AND o.expDate='"+expDate+"'";
		}
		
		queryStr1=("select o.optionid,o.stkid,o.expdate,o.optionType,o.exercisePrice,o.bid,o.offer,o.dat,p.price from system.options o,system.price p where o.stkid='"
			+ stkId + "'" +			conition1 + conition2 + conition3 + "AND o.dat=p.dat AND o.stkid=p.stkID");
		System.out.println("to DB>> "+queryStr1);
		
		
		try
		{
			connect();
			stmt = con.createStatement(); 
			rs = stmt.executeQuery(queryStr1);
			
			
			
			
			
			while(rs.next())  
			{
				
				
				optionObj=new Options(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
					rs.getDouble(6),rs.getDouble(7),rs.getString(8));
				sharePrice.add(rs.getDouble(9));
				
				optionsListResult.add(optionObj);
							
			}
			
			
			
		}
		catch (Exception ex)
		{
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Error");
			alert.setContentText("DB controler(line 195):\n update failed\n"+ex.getMessage());
			alert.showAndWait();
			
			//System.out.println(e.getMessage());
		}
		return optionsListResult;
		
		
		
	}
	
	
	public String query(String queryString)throws Exception
	{
		String str = new String ();
		ResultSetMetaData rsmd;
		connect();
		stmt = con.createStatement();
		try{
			System.out.println(queryString);
			
			rs=stmt.executeQuery(queryString);
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println(">>");
		rsmd= rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
											
					while (rs.next())
					{
						for (int i=1;i<= columnCount;i++)
						{
							str += rs.getString(i);
							str+="\t";
						}
						str+="\n";
						
						
					}
		return str;
		
	}
		
	
	
}
/*
													inserting options tuples
													while(s.hasNext())
													{	
														insertStr="insert into options values(?,?,?,?,?,?,?,?)";
														ps=con.prepareStatement(insertStr);
														
														optionID=s.nextString();
														ps.setString(1,optionID);
														ps.setString(2,optionID.subString(0,3));
														ps.setString(3,s.nextString());
														ps.setString(4,s.nextString());
														ps.setString(5,s.nextString());
														ps.setString(6,s.nextFloat());
														ps.setString(7,s.nextFloat());
														ps.setString(8,dat);
														preparedStatement.executeUpdate();
													}*/