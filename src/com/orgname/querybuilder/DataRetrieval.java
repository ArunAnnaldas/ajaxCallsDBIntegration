package com.orgname.querybuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/TestingAjaxCall")
public class DataRetrieval extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Statement st;
	private static ResultSet r;

	private static final String TABLE_NAME = "EmployeeDetails";
	private static final String ID_COLUMN = "ID";
	private static final String FIRSTNAME_COLUMN = "FirstName";
	private static final String LASTNAME_COLUMN = "LastName";
	private static final String JOININGDATE_COLUMN = "JoiningDate";

	public DataRetrieval() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * List<Employees> emp = new ArrayList<Employees>(); Employees p = new
		 * Employees("HeadPhones", true); Employees p1 = new
		 * Employees("HeadPhones1", true); Employees p2 = new
		 * Employees("HeadPhones2", true); Employees p3 = new
		 * Employees("HeadPhones3", true); emp.add(p); emp.add(p1); emp.add(p2);
		 * emp.add(p3);
		 * 
		 * String json = new Gson().toJson(emp);
		 * response.setContentType("application/json");
		 * response.setCharacterEncoding("UTF-8");
		 * response.getWriter().write(json);
		 */
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// JSONArray jsonArr = jsonArrayConversion(dbConnection(request,
			// response));
			String s = jsonConversion(dbConnection(request, response));

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(s);
			// doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String constructWhereClause(HttpServletRequest request)
	{
		String whereClause=" where ";
		List<String> paramNames = new ArrayList<String>();
		
		if (request.getParameterMap().containsKey("firstName")) {
			 paramNames.add("firstName like '%" +request.getParameter("firstName") + "%'");
		}
		if (request.getParameterMap().containsKey("lastName")) {
			paramNames.add("lastName = '%" +request.getParameter("lastName") +"%'");
			
		}
		
		for(int i = 0; i<paramNames.size();i++){
			if(i>0)
			{
				whereClause = whereClause + " and ";
			}
			whereClause = whereClause + paramNames.get(i);
		}
	
		
		return whereClause;
	}
	
	private ResultSet dbConnection(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String whereClause = constructWhereClause(request);
			
			Connection con = Database.getInstance().getConnection();
			st = con.createStatement();
			
			r = st.executeQuery("select * from " + TABLE_NAME + whereClause);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String jsonConversion(ResultSet rs) {
		Integer ID;
		String FirstName;
		String LastName;
		Date JoiningDate;

		Gson gson = new Gson();
		// StringBuilder strJsonData = new StringBuilder();
		List<Employees> emp = new ArrayList<Employees>();
		try {
			while (rs.next()) {
				ID = rs.getInt(ID_COLUMN);
				FirstName = rs.getString(FIRSTNAME_COLUMN);
				LastName = rs.getString(LASTNAME_COLUMN) + new Random().nextInt();
				JoiningDate = rs.getDate(JOININGDATE_COLUMN);
				Employees emp1 = new Employees(ID, FirstName, LastName, JoiningDate);
				emp.add(emp1);
			}
			// strJsonData.append(gson.toJson(emp));
			return gson.toJson(emp);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * private static JSONArray jsonArrayConversion(ResultSet rs) throws
	 * SQLException { JSONArray json = new JSONArray(); ResultSetMetaData rsmd =
	 * rs.getMetaData(); try { while (rs.next()) { int numColumns =
	 * rsmd.getColumnCount(); JSONObject obj = new JSONObject();
	 * 
	 * for (int i = 1; i < numColumns + 1; i++) { String column_name =
	 * rsmd.getColumnName(i);
	 * 
	 * if (rsmd.getColumnType(i) == java.sql.Types.ARRAY) { obj.put(column_name,
	 * rs.getArray(column_name)); } else if (rsmd.getColumnType(i) ==
	 * java.sql.Types.BIGINT) { obj.put(column_name, rs.getInt(column_name)); }
	 * else if (rsmd.getColumnType(i) == java.sql.Types.BOOLEAN) {
	 * obj.put(column_name, rs.getBoolean(column_name)); } else if
	 * (rsmd.getColumnType(i) == java.sql.Types.BLOB) { obj.put(column_name,
	 * rs.getBlob(column_name)); } else if (rsmd.getColumnType(i) ==
	 * java.sql.Types.DOUBLE) { obj.put(column_name, rs.getDouble(column_name));
	 * } else if (rsmd.getColumnType(i) == java.sql.Types.FLOAT) {
	 * obj.put(column_name, rs.getFloat(column_name)); } else if
	 * (rsmd.getColumnType(i) == java.sql.Types.INTEGER) { obj.put(column_name,
	 * rs.getInt(column_name)); } else if (rsmd.getColumnType(i) ==
	 * java.sql.Types.NVARCHAR) { obj.put(column_name,
	 * rs.getNString(column_name)); } else if (rsmd.getColumnType(i) ==
	 * java.sql.Types.VARCHAR) { obj.put(column_name,
	 * rs.getString(column_name)); } else if (rsmd.getColumnType(i) ==
	 * java.sql.Types.TINYINT) { obj.put(column_name, rs.getInt(column_name)); }
	 * else if (rsmd.getColumnType(i) == java.sql.Types.SMALLINT) {
	 * obj.put(column_name, rs.getInt(column_name)); } else if
	 * (rsmd.getColumnType(i) == java.sql.Types.DATE) { obj.put(column_name,
	 * rs.getDate(column_name)); } else if (rsmd.getColumnType(i) ==
	 * java.sql.Types.TIMESTAMP) { obj.put(column_name,
	 * rs.getTimestamp(column_name)); } else { obj.put(column_name,
	 * rs.getObject(column_name)); } } json.put(obj); } } catch (Exception e) {
	 * e.printStackTrace(); } return json; }
	 */

}
