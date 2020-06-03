package com.springboot.springbootdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
public class SpringBootJdbcTest {
	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://Localhost:3306/springbootdemo", "root", "root");
		return con;
	}
	
	@RequestMapping("/welcome")
	public String welcome()
	{
		return "Welcome Onkar";
	}
	
	@RequestMapping("/hobbylist")
	public String[] hobbylist()
	{
		String[] hobbies= new String[10];
		hobbies[0]="Onkar";
		hobbies[1]="Amar";
		hobbies[2]="Saurabh";
		return hobbies;
	}
	
	@RequestMapping("/employeelist")
	public ArrayList<employee> employeelist()
	{
		ArrayList<employee> employee=new ArrayList<>();
		employee e=new employee("Onkar", "Pune", 25);
		employee e1=new employee("Nabhraj", "Saswad", 26);
		employee.add(e);
		employee.add(e1);
		return employee;
	}
	
	//Show All Employee "Rest API 1"
	@GetMapping("showallEmployee")
	public ArrayList<employee1> showemployeelist()
	{
		ArrayList<employee1> employeelist=new ArrayList<>();
		try 
		{
			Connection con=getConnection();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from employee, country where country.cid=employee.cid");
			while(rs.next())
			{
				employee1 e=new employee1();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPhoneno(rs.getString(3));
				e.setDepartment(rs.getString(4));
				e.setStatus(rs.getString(5));
				e.setCreateddtm(rs.getString(6));
				e.setCreatedby(rs.getString(7));
				e.setUpdateddtm(rs.getString(8));
				e.setUpdatedby(rs.getString(9));
				e.setCountryname(rs.getString(12));
				employeelist.add(e);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return employeelist;
	}
	
	//Show All Employee which have status Active OR Inactive "Rest API 2"
	@GetMapping(value = "/status/{status}")
	public ArrayList<employee1> statusemployeelist(@PathVariable("status") String status)
	{
		System.out.println("Status >> "+status);
		ArrayList<employee1> employeelist=new ArrayList<>();
		try 
		{
			Connection con=getConnection();
			Statement stmt=con.createStatement();
			String sql="select * from employee, country where country.cid=employee.cid and status='"+status+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				employee1 e=new employee1();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPhoneno(rs.getString(3));
				e.setDepartment(rs.getString(4));
				e.setStatus(rs.getString(5));
				e.setCreateddtm(rs.getString(6));
				e.setCreatedby(rs.getString(7));
				e.setUpdateddtm(rs.getString(8));
				e.setUpdatedby(rs.getString(9));
				e.setCountryname(rs.getString(12));
				employeelist.add(e);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeelist;
	}
	
	//Show All Employee which have id "Rest API 3"
	@GetMapping(value = "{id}")
	public ArrayList<employee1> idemployeelist(@PathVariable("id") int id)
	{
		System.out.println("Status >> "+id);
		ArrayList<employee1> employeelist=new ArrayList<>();
		try 
		{
			Connection con=getConnection();
			Statement stmt=con.createStatement();
			String sql="select * from employee, country where country.cid=employee.cid and id='"+id+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				employee1 e=new employee1();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPhoneno(rs.getString(3));
				e.setDepartment(rs.getString(4));
				e.setStatus(rs.getString(5));
				e.setCreateddtm(rs.getString(6));
				e.setCreatedby(rs.getString(7));
				e.setUpdateddtm(rs.getString(8));
				e.setUpdatedby(rs.getString(9));
				e.setCountryname(rs.getString(12));
				employeelist.add(e);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeelist;
	}

	//Show All Employee which have name "Rest API 4"
	@GetMapping(value = "/showemployeesByname/{name}")
	public ArrayList<employee1> idemployeelist(@PathVariable("name") String name)
	{
		System.out.println("Status >> "+name);
		ArrayList<employee1> employeelist=new ArrayList<>();
		try 
		{
			Connection con=getConnection();
			Statement stmt=con.createStatement();
			String sql="select * from employee, country where country.cid=employee.cid and name='"+name+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				employee1 e=new employee1();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPhoneno(rs.getString(3));
				e.setDepartment(rs.getString(4));
				e.setStatus(rs.getString(5));
				e.setCreateddtm(rs.getString(6));
				e.setCreatedby(rs.getString(7));
				e.setUpdateddtm(rs.getString(8));
				e.setUpdatedby(rs.getString(9));
				e.setCountryname(rs.getString(12));
				employeelist.add(e);
			}
		} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeelist;
	}
	
	//Show All Employee which have date today "Rest API 5"
		@GetMapping(value = "/showemployeesBeforeToday")
		public ArrayList<employee1> todayemployeelist()
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime now = LocalDateTime.now();
			String date=dtf.format(now);
			System.out.println("date >> "+date);
			ArrayList<employee1> employeelist=new ArrayList<>();
			try 
			{
				Connection con=getConnection();
				Statement stmt=con.createStatement();
				String sql="select * from employee, country where country.cid=employee.cid and createddtm='"+date+"'";
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next())
				{
					employee1 e=new employee1();
					e.setId(rs.getInt(1));
					e.setName(rs.getString(2));
					e.setPhoneno(rs.getString(3));
					e.setDepartment(rs.getString(4));
					e.setStatus(rs.getString(5));
					e.setCreateddtm(rs.getString(6));
					e.setCreatedby(rs.getString(7));
					e.setUpdateddtm(rs.getString(8));
					e.setUpdatedby(rs.getString(9));
					e.setCountryname(rs.getString(12));
					employeelist.add(e);
				}
			} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return employeelist;
	}
	

	//add Employee"Rest API 5"
	@PostMapping(value = "/addemployee")
	public ResponseEntity<String> addemployee(@RequestBody employee1 employee)
	{
		System.out.println(employee);
		String name=employee.getName();
		String phoneno=employee.getPhoneno();
		String department=employee.getDepartment();
		String status=employee.getStatus();
		String createddtm=employee.getCreateddtm();
		String createdby=employee.getCreatedby();
		String updateddtm=employee.getUpdateddtm();
		String updatedby=employee.getUpdatedby();
		int cid=employee.getCid();
		int update=0;
		try 
		{
			Connection con=getConnection();
			Statement stmt=con.createStatement();
			String sql="insert into employee(name,phoneno,department,status,createddtm,createdby,updateddtm,updateby,cid) values('"+name+"','"+phoneno+"','"+department+"','"+status+"','"+createddtm+"','"+createdby+"','"+updateddtm+"','"+updatedby+"',"+cid+")";
			System.out.println(sql);
			update=stmt.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(update>0)
		{
			return new ResponseEntity<String>("Employee added in db successfully", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Employee added in db Unsuccessfully", HttpStatus.OK);
		}
	}

	//add Country "Rest API 6"
	@PostMapping(value = "/addcountry")
	public ResponseEntity<String> addcountry(@RequestBody employee1 employee)
	{
		System.out.println(employee);
		int update=0;
		try 
		{
			Connection con=getConnection();
			Statement stmt=con.createStatement();
			String sql="insert into country(countryname) values('"+employee.getCountryname()+"')";
			System.out.println(sql);
			update=stmt.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(update>0)
		{
			return new ResponseEntity<String>("Country added in db successfully", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Country added in db Unsuccessfully", HttpStatus.OK);
		}
	}

	//updateCountry "Rest API 6"
	@PutMapping(value = "/updatecountry")
	public ResponseEntity<String> updatecountry(@RequestBody employee1 employee)
	{
		System.out.println(employee);
		int update=0;
		try 
		{
			Connection con=getConnection();
			Statement stmt=con.createStatement();
			String sql="update country set countryname='"+employee.getCountryname()+"' where cid="+employee.getCid()+"";
			System.out.println(sql);
			update=stmt.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(update>0)
		{
			return new ResponseEntity<String>("Country updated successfully", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Country updated Unsuccessfully", HttpStatus.OK);
		}
	}

	//delete Country by country name"Rest API 8"
	@DeleteMapping(value = "/deletecountry/{countryname}")
	public ResponseEntity<String> deletecountry(@PathVariable("countryname") String countryname)
	{
		System.out.println(countryname);
		int update=0;
		try 
		{
			Connection con=getConnection();
			Statement stmt=con.createStatement();
			String sql="delete from country where countryname='"+countryname+"'";
			System.out.println(sql);
			update=stmt.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(update>0)
		{
			return new ResponseEntity<String>("Country Deleted successfully", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Country deleted Unsuccessfully", HttpStatus.OK);
		}
	}
	
	//delete employee by id name"Rest API 9"
	@DeleteMapping(value = "/deleteemployee/{id}")
	public ResponseEntity<String> deleteemployee(@PathVariable("id") int id)
	{
		System.out.println(id);
		int update=0;
		try 
		{
			Connection con=getConnection();
			Statement stmt=con.createStatement();
			String sql="delete from employee where id='"+id+"'";
			System.out.println(sql);
			update=stmt.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(update>0)
		{
			return new ResponseEntity<String>("Employee Deleted successfully", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Employee deleted Unsuccessfully", HttpStatus.OK);
		}
	}
}
