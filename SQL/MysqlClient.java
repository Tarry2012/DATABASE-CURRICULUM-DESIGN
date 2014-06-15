package SQL;
import java.sql.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.spi.DirStateFactory.Result;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class MysqlClient
{
	//数据库连接
	private static Connection conn;
	//设置数据库Url
	private static String databaseUrl = "jdbc:mysql://127.0.0.1:3306/Manager";
	//设置数据库用户
	private static String dataUserName = "root";
	//设置数据库密码
	private static String dataUserPasswd = "tarry";
	
	//构造方法，连接数据库
	private MysqlClient(){
		try{
			if (conn == null){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(databaseUrl,dataUserName,dataUserPasswd);
			}
		} catch (Exception ee){
			ee.printStackTrace();
		}
	}
	
	//执行查询SQL语句
	private static ResultSet executeQuery(String sql){
		try{
			if (conn == null){ 
				new MysqlClient();
			}
			//	ResultSet.TYPE_SCROLL_SENSITIVE双向滚动，并及时跟踪数据库的更新
			//  ResultSet.CONCUR_UPDATABLE 用户不可更新记录集中的数据
			System.out.println("query:" + sql);
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery(sql);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
		}
	}
	
	//执行更新删除插入SQL语句
	private static int executeUpdate(String sql){
		try{
			if (conn == null){
				new MysqlClient();
			}
			System.out.println("update:" + sql);
			return conn.createStatement().executeUpdate(sql);
		} catch(Exception e){
			e.printStackTrace();
			return -1;
		}finally{
		}
	}
	
	//断开与数据的连接
	/*public static void close(){
		try{
			conn.close();
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
		}
	}
	*/
	
	//登陆用户
	public static String[] check(String userName, String userPass){
		//存储用户的三个状态值：用户名，密码，等级
		String [] operate = new String[3];
		//查询用户是否存在语句
		String selectSQL = "select * from rigister where User=" + "\"" + userName + "\"" + "and password = " + "\"" + userPass + "\";"; 
		try{
			//执行查询语句
			ResultSet rs = executeQuery(selectSQL);
			//从结果集中获取用户的用户名、密码和等级状态
			while (rs.next()){
				operate[0] = rs.getString("User");
				operate[1] = rs.getString("password");
				operate[2] = rs.getString("admin");
			}
		} catch (SQLException e){
			System.out.println("输入SQL有错误！");
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		//断开数据连接
	//	MysqlClient.close();
		return operate;
 	}
	
	//增加用户
	public static int InsertUser(String name,String passwd,String admin){
		//增加用户的插入语句
		String insertSQL = "insert into rigister values(" + "\"" + name + "\"" + "," + "\"" + passwd + "\"" + "," + admin + ");";
		int result = -1;
		try{
			//执行插入语句
			result = executeUpdate(insertSQL);
		}catch (Exception e){
			e.printStackTrace();
		}
		//断开数据连接
	//	MysqlClient.close();
		return result;
	}
	
	//删除用户
	public static int DeleterUser(String name){
		//删除用户语句
		String deleterSQL = "delete from rigister where User = " + "\"" + name + "\";";
		int result = -1;
		try{
			//执行删除语句
			result = executeUpdate(deleterSQL);
		}catch (Exception e){
			e.printStackTrace();
		}
		//断开数据库连接
		//MysqlClient.close();
		return result;
	}
	
	//从一个视图中，查询某个员工的所有工资信息：总工资、职位工资、奖惩工资、工龄工资
	public static String[] EmpSelectInfo(String num){
		//查询语句
			String selectSQL = "select * from EmployeeInfo where id="+ "\"" + num + "\"";
			String[] result = new String [6];
			try{
				//执行查询语句
				ResultSet rs = executeQuery(selectSQL);
				//获得查询结果
				while (rs.next()){
					//得到员工的工作ID
					result[0] = rs.getString("id");
					//得到员工的姓名
					result[1] = rs.getString("empname");
					//得到员工的总工资
					result[2] = rs.getString("sum");
					//得到员工的职位工资
					result[3] = rs.getString("post");
					//员工的奖惩工资
					result[4] = rs.getString("reward");
					//得到员工的工龄工资
					result[5] = rs.getString("worktime");
				//	System.out.println("mysqlclient:" + result[0] + result[1] + result[2]);
				}
			} catch(SQLException e){
				System.out.println("SQL语句输入错误！");
				e.printStackTrace();
			}catch (Exception e){
				e.printStackTrace();
			}
			//断开数据连接
	//		MysqlClient.close();
			return result;
	}
	
	//删除员工
	public static int DeleteName(String num){
		//查询语句
		String deleterUser = "delete from employee where emp_id =" + "\"" + num + "\";";
		int result = -1;
		try{
			//执行删除语句
			result = executeUpdate(deleterUser);
			System.out.println(result);
		} catch(Exception e){
			e.printStackTrace();
		}
		//断开数据库连接
	//	MysqlClient.close();
		return result;
	}
	
	//查询某个部门信息，部门号，部门名称、部门位置、部门总人数、部门平均工资
	public static String [] DepSelectInfo(String num){
		//部门信息
		String selectSql1 = "select * from department where dep_id=" + "\"" + num + "\";";
		//部门人总数
		String selectSql2 = "select count(*)  from employee group by dep_id having dep_id= " + "\"" + num + "\";";
		//部门平局工资
		String selectSql3 = "select avg(emp_wage) from employee	group by dep_id having dep_id =" + "\"" + num + "\"";
		//保存查询结果集的结果
		String[] result = new String[5];
		try{
			//执行查询语句
			ResultSet rsInfo = executeQuery(selectSql1);
			while (rsInfo.next()){
						//System.out.println(rsInfo.getString("dep_id") + rsInfo.getString("dep_name") + 
							//	rsInfo.getString("dep_place"));
						//获得部门号
						result[0] = rsInfo.getString("dep_id");
						//获得部门名称
						result[1] = rsInfo.getString("dep_name");
						//获得部门位置
						result[2] = rsInfo.getString("dep_place");
			}
			//获得部门的总人数
			ResultSet rsCount = executeQuery(selectSql2);
			while (rsCount.next()){
				result[3] = rsCount.getString(1);
		//		System.out.println("count:" + count);
			}
			//获得该部门的平均工资
			ResultSet rsSum = executeQuery(selectSql3);
			while (rsSum.next()){
				result[4] =rsSum.getString(1);
				//System.out.println(result[4]);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		//断开与数据库的连接
	//	MysqlClient.close();
		return result;
	}
	
	//查询统计信息，员工的总人数、员工的平均工资、员工的最高工资、员工的最低工资、最高工资的部门、最低工资的部门
	public static String[] StatisticsInfo(){
		//获得员工总数
		String SumNum = "select count(*) from employee;";
		//员工的平均工资
		String AvgSalary = "select avg(emp_wage) from employee;";
		//员工的最高工资
		String HighSalary = "select max(emp_wage) from employee;";
		//员工的最低工资
		String LowSalary = "select min(emp_wage) from employee;";
		//最高工资的部门
		String HighDep = "select dep_name"+
				" from department"+
				" where department.dep_id = "+ 
					"(select dep_id"+
					" from employee"+	
					" where emp_wage = "+
					"("+
					"select Max(emp_wage)"+
					" from employee"+
					"));";
		//最低工资的部门	
		String LowDep = "select dep_name from department where department.dep_id ="+ 
				" (select dep_id from employee" +
				" where emp_wage = (select Min(emp_wage) from employee));";
		//保存查询结果集中的结果
		String[] result = new String [6];
		try{
			//执行查询员工总人数
			ResultSet rsSum = executeQuery(SumNum);
			while (rsSum.next()){
				result[0] = rsSum.getString(1);
			}
			//执行查询员工的平均工资
			ResultSet rsAvg = executeQuery(AvgSalary);
			while (rsAvg.next()){
				result[1] = rsAvg.getString(1);
			}
			//执行查询员工的最高工资
			ResultSet rsHSalary = executeQuery(HighSalary);
			while (rsHSalary.next()){
				result[2] = rsHSalary.getString(1);
			}
			//执行查询员工的最低工资
			ResultSet rsLSalary = executeQuery(LowSalary);
			while (rsLSalary.next()){
				result[3] = rsLSalary.getString(1);  
			}
			//执行查询最高工资的部门
			ResultSet rsHDep = executeQuery(HighDep);
			while (rsHDep.next()){
				result[4] = rsHDep.getString(1);
			}
			//执行查询最低工资的部门
			ResultSet rsLDep = executeQuery(LowDep);
			System.out.println("mmmmmm!");
			while (rsLDep.next()){
				System.out.println("mmmmmm22!");
				result[5] = rsLDep.getString(1);
				System.out.println("ffffff:" + result[5]);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		//断开与数据库的连接
	//	MysqlClient.close();
		return result;
	}
	
	//增加员工，员工号、员工姓名、员工年龄、员工工龄、员工性别、员工号码、员工职位、员工部门
	public static int InsertEmp(String num, String name,String age,String time, 
			String sex, String tel, String post, String dep){
		//插入语句
		String insertSql = "insert into employee(emp_id,emp_name,emp_age,emp_time,emp_sex,emp_tel,emp_position,dep_id) values(" + 
				"\"" + num + "\"," + "\"" + name + "\"," + "\"" + age + "\"," + "\"" + time + "\"," + "\"" + sex + "\"," + "\"" + tel + "\","
				+ "\"" + post + "\"," + "\"" + dep + "\");";
		int rs = -1;
		try{
			//执行插入语句
			rs = executeUpdate(insertSql);
			//断开与数据库的连接
		//	MysqlClient.close();
			//及时更新插入新员工的总工资信息
			int rsupdate = executeUpdate("update employee set employee.emp_wage=employee.emp_wage+("
					+"select positionWages.wages from positionWages where employee.emp_position = positionWages.position)+"
					+ "(select timeWages.wages from timeWages where employee.emp_time= timeWages.cometime)+ "
					+ "(select SumReward.SumMoney from SumReward where employee.emp_id = SumReward.emp_id) "
					+ "where employee.emp_id = " + "\"" + num + "\";");
			System.out.println("rsupdate: " + rsupdate);		
		}catch (Exception e){
			e.printStackTrace();
		}
		//断开与数据库的连接
//		MysqlClient.close();
		return rs;
}	
	//获得所有员工的信息,员工号、员工姓名、员工年龄、员工工龄、员工性别、员工电话、员工职位、员工部门id，员工的部门名称
	public static Vector<Vector<String>> GetAllEmp(Vector<String> columnNames){
		//查询语句
		String selectSQl = "select emp_id, emp_name, emp_age,emp_time,emp_sex,emp_tel,emp_position,employee.dep_id,dep_name"+
								" from employee,department"+
								" where employee.dep_id = department.dep_id;";
			try(
					//执行查询结果
					ResultSet rs = executeQuery(selectSQl))
					{   //获得结果信息
						ResultSetMetaData rsmd = rs.getMetaData();
						Vector<Vector<String>> data = new Vector<>();
						//得到结果集中的各个字段名
						for (int i = 0; i < rsmd.getColumnCount(); i++){
							//保存各字段名
							columnNames.add(rsmd.getColumnName(i + 1));
						}
						//得到结果集中所有的结果
						while (rs.next()){
							Vector<String> vec = new Vector<>();
							for (int i = 0; i < rsmd.getColumnCount(); i++){
								vec.add(rs.getString(i + 1));
							}
							//保存结果
							data.add(vec);
						}
						//断开数据库的连接
			//			MysqlClient.close();
						return data;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			//断开与数据的连接
	//		MysqlClient.close();
			return null;
	}
	
	//修改员工信息，即重新更细员工信息，员工姓名、员工年龄、员工工龄、员工性别、员工电话、员工职位、员工部门号
	public static int EmpModifyInfo(String num,String name,String age,String time, String sex, String tel,String post,
				String depNum){
		//修改语句
		String modifySql = "update employee set " + "emp_name =" + "\"" + name + "\"," +  "emp_age =" + "\"" + age + "\"," 
				   + "emp_time =" + "\"" + time + "\"," + "emp_sex =" + "\"" + sex + "\"," + "emp_tel =" + "\"" + tel +
				   "\"," + "emp_position = " + "\"" + post + "\""
				   		+ "where emp_id = " + "\"" + num + "\";";
		int rs = -1;
		try{
			//执行修改语句
			rs = executeUpdate(modifySql);
		//	System.out.println("empModifyhaha:" + rs);
			//断开与数据库的连接
		//	MysqlClient.close();
			return rs;
		}catch (Exception e){
			e.printStackTrace();
		}
		//断开与数据库的连接
	//	MysqlClient.close();
		return rs;
	}
	
	//修改奖惩表信息，节假日加班、普通加班次数，奖励，请假、迟到、旷班次数
	public static Vector<Vector<String>> GetAllReward(Vector<String> columnNames){
		//查询语句
		String selectSQl = "select rewardPunish.emp_id,emp_name,festival, usually,reward, vacate, comelate,desert from rewardPunish,employee "+
						"where rewardPunish.emp_id = employee.emp_id;";
			try(
					//执行查询语句
					ResultSet rs = executeQuery(selectSQl))
					{
						//获得结果集的信息
						ResultSetMetaData rsmd = rs.getMetaData();
						Vector<Vector<String>> data = new Vector<>();
						//获得结果集的列名
						for (int i = 0; i < rsmd.getColumnCount(); i++){
							columnNames.add(rsmd.getColumnName(i + 1));
						}
						//获得结果集的所有值
						while (rs.next()){
							Vector<String> vec = new Vector<>();
							for (int i = 0; i < rsmd.getColumnCount(); i++){
								vec.add(rs.getString(i + 1));
							}
							data.add(vec);
						}
						//断开与数据库的连接
					//	MysqlClient.close();
						return data;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			//断开与数据库的连接
		//	MysqlClient.close();
			return null;
	}
	
	//修改奖惩信息,奖惩节日加班次数、普通加班次数、奖励金额、请假次数、迟到次数、旷班次数
	public static int ModifyReward(String emp_id, String emp_name,String festival, String usually,String reward,
			String vacate, String comelate, String desert){
		//修改语句
		String modifySql = "update rewardPunish set" + " festival=" + "\"" + festival + "\"," + "usually=" + "\"" + usually + "\"," + 
		"reward=" + "\"" + reward + "\"," + "vacate=" + "\"" + vacate + "\"," + "comelate=" + "\"" + comelate + "\"," + "desert=" + "\"" + desert + "\"" +
				" where rewardPunish.emp_id = " +"\"" + emp_id + "\"" ;
	//	System.out.println("aaaa:" + modifySql);
		int rs = -1;
		try{
			//执行修改语句
			rs = executeUpdate(modifySql);
			//断开与数据的链接
		//	MysqlClient.close();
			//执行修改语句
			int rsupdate = executeUpdate("update employee set employee.emp_wage=employee.emp_wage+("
					+"select positionWages.wages from positionWages where employee.emp_position = positionWages.position)+"
					+ "(select timeWages.wages from timeWages where employee.emp_time = timeWages.cometime)+"
					+ "(select SumReward.SumMoney from SumReward where SumReward.emp_id = employee.emp_id)"+ 
					"where emp_id = " + "\"" + emp_id + "\";");
			//断开与数据库的连接
	//		MysqlClient.close();
			return rs;
		}catch (Exception e){
			e.printStackTrace();
		}
		//断开与数据库的连接
	//	MysqlClient.close();
		return rs;
	}
}
	