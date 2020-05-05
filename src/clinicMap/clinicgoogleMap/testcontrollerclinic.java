package clinicMap.clinicgoogleMap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import clinicMap.clinicgoogleMap.ClinicServiceDavid;

@Controller
public class testcontrollerclinic {

	@Autowired
	private ClinicServiceDavid service;
	@Autowired
	private positionService service2;
	@Autowired
	private driverService service3;
	
	
	@RequestMapping(path = "/googleMap", method = RequestMethod.GET)
	 public String start() {
	  return "index";
	 }
	
	@RequestMapping(path = "/guest", method = RequestMethod.GET)
	public String start2() {
		return "testselect20201";
	}
	
	@RequestMapping(path = "/driver", method = RequestMethod.GET)
	public String start3() {
		return "testselect20202";
	}
//--------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/addGoogles", method = RequestMethod.POST)
	public void submit(HttpServletResponse res) throws IOException {
		List<Clinicdavid> qa = service.queryAllData();
		JSONArray jaray = new JSONArray();
		for (Clinicdavid i : qa) {
			JSONObject jso = new JSONObject();
			jso.put("clinicAddress", i.getClinicAddress());
			jso.put("clinicName", i.getClinicName());
			jaray.put(jso);
		}

		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jaray);

	}

	@RequestMapping(value = "/checkboxvalues", method = RequestMethod.POST)
	public void createRpt(@RequestParam(value = "selectval") String selectval, HttpServletResponse res)
			throws IOException {
		String sqlstr = selectval;
		System.out.println(sqlstr);
		System.out.println("dd");
		List<Clinicdavid> qa = service.selectData(sqlstr);

		JSONArray jaray = new JSONArray();
		for (Clinicdavid i : qa) {
			JSONObject jso = new JSONObject();
			jso.put("clinicName", i.getClinicName());
			jso.put("clinicLat", i.getClinicLat());
			jso.put("clinicLng", i.getClinicLng());
			jaray.put(jso);
		}
		// res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jaray);

	}

	@RequestMapping(value = "/selectmes", method = RequestMethod.POST)
	public void messageselect(@RequestParam(value = "message") String message, HttpServletResponse res)
			throws IOException {
		String message2 = message;
		System.out.println(message2);

		List<Clinicdavid> qa = service.selectmesData(message2);

		JSONArray jaray = new JSONArray();
		for (Clinicdavid i : qa) {
			JSONObject jso = new JSONObject();
			jso.put("clinicName", i.getClinicName());
			jso.put("clinicLat", i.getClinicLat());
			jso.put("clinicLng", i.getClinicLng());
			jaray.put(jso);
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jaray);

	}
//--------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/updatemy", method = RequestMethod.POST)
	public void updateguest(@RequestParam(value = "orderdata") String mylatlng) throws IOException {
		System.out.println("mylatlng測試 + " + mylatlng);
		String[] tokens = mylatlng.split(",");
		System.out.println(tokens[0]);
		System.out.println(tokens[1]);
		System.out.println(tokens[2]);
		int i = Integer.parseInt(tokens[0]);
	    service2.updateguestData(i, tokens[1], tokens[2]);
	}

	@RequestMapping(value = "/updatedestination", method = RequestMethod.POST)
	public void updatedestination(@RequestParam(value = "destinationlatlng") String destinationlatlng)
			throws IOException {
		System.out.println("destinationlatlng測試" + destinationlatlng);
		String[] tokens = destinationlatlng.split(",");
		System.out.println(tokens[0]);
		System.out.println(tokens[1]);
		int id = 101;
		service2.updatedestinationData(id, tokens[0], tokens[1]);
	}

	@RequestMapping(value = "/updatedriver", method = RequestMethod.POST)
	public void updatedriver(@RequestParam(value = "driverlatlng") String driverlatlng) throws IOException {
		System.out.println("driverlatlng測試" + driverlatlng);
		String[] tokens = driverlatlng.split(",");
		System.out.println(tokens[0]);
		System.out.println(tokens[1]);
		System.out.println(tokens[2]);
		int i = Integer.parseInt(tokens[0]);
		service2.updatedriverData(i, tokens[1], tokens[2]);
	}

	@RequestMapping(value = "/selectall", method = RequestMethod.POST)
	public void selectid(@RequestParam(value = "positionidcheck") int positionidcheck, HttpServletResponse res) throws IOException {
		int forid = positionidcheck;
		System.out.println("我選定的單號"+forid);
		position qa = service2.selectpositionData(forid);
		
		JSONObject jaray = new JSONObject();
		
	    jaray.put("positionID", qa.getpositionID());
	    jaray.put("clinicName", qa.getclinicName());
	    jaray.put("guestlat", qa.getguestlat());
	    jaray.put("guestlng", qa.getguestlng());
	    jaray.put("driverlat", qa.getdriverlat());
	    jaray.put("driverlng", qa.getdriverlng());
	    jaray.put("destinationlat", qa.getdestinationlat());
	    jaray.put("destinationlng", qa.getdestinationlng());
	    

	    
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
	
		out.print(jaray);

	}
	
	
	@RequestMapping(value = "/neworder", method = RequestMethod.POST)
	public void newfororder(@RequestParam("orderdata") String orderdata,HttpServletResponse res) throws IOException {
		position Pposition = new position();
		System.out.println("hello"+orderdata+"??");
		String[] data = orderdata.split(",");
		Pposition.setdrivername(data[0]);
		System.out.println(data[0]+data[1]);
		Pposition.setclinicName(data[1]);
		Pposition.setguestlat(data[2]);
		Pposition.setguestlng(data[3]);
		Pposition.setdriverlat(data[4]);
		Pposition.setdriverlng(data[5]);
		Pposition.setdestinationlat(data[6]);
		Pposition.setdestinationlng(data[7]);
		Pposition.setthispricetotal(data[8]);
		service2.neworderinData(Pposition);
		
		
		List<position> forid=service2.selectthisorderid(data[0],data[1],data[8]);
		
		JSONArray jaray = new JSONArray();
		for (position i : forid) {
			JSONObject jso = new JSONObject();
			jso.put("positionID", i.getpositionID());	
			jaray.put(jso);
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jaray);	                   
	}
//--------------------------------------------------------------------------------------------------	
	@RequestMapping(value = "/selectorder", method = RequestMethod.POST)
	public void selectfororder(@RequestParam(value = "driveridname") String driveridname,HttpServletResponse res) throws IOException {
		String driveridname2 = driveridname;
		List<position> qa = service2.selectAllOrderData(driveridname2);

		JSONArray jaray = new JSONArray();
		//JSONObject jso = new JSONObject();		
		
		for (position i : qa) {
			JSONObject jso = new JSONObject();
			jso.put("positionID", i.getpositionID());
			jso.put("drivername", i.getdrivername());
			jso.put("clinicName", i.getclinicName());
			jso.put("guestlat", i.getguestlat());
			jso.put("guestlng", i.getguestlng());
			jso.put("driverlat", i.getdriverlat());
			jso.put("driverlng", i.getdriverlng());
			jso.put("destinationlat", i.getdestinationlat());
			jso.put("destinationlng", i.getdestinationlng());		
			jaray.put(jso);
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jaray);

	}
//--------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/showfordriver", method = RequestMethod.POST)
	public void showfordriver(HttpServletResponse res)throws IOException {

		List<driver> qa = service3.showdriver();
		JSONArray jaray = new JSONArray();
		for (driver i : qa) {
			JSONObject jso = new JSONObject();
			jso.put("drivername", i.getdrivername());
			jso.put("drivernowlat", i.getdrivernowlat());
			jso.put("drivernowlng", i.getdrivernowlng());
			jaray.put(jso);
		}

		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jaray);

	}
	@RequestMapping(path="/cliniclnglat",method=RequestMethod.POST)
public void cliniclnglat(@RequestParam("clinicid")String clinicid,HttpServletResponse response) throws IOException {
		Clinicdavid cbean = service.queryData(clinicid);
		JSONObject jobj=new JSONObject(cbean);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jobj);
}
	
	
	
	
	
}
