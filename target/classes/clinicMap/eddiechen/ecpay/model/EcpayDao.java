package clinicMap.eddiechen.ecpay.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import clinicMap.eddiechen.backendsystem.model.Clinicchen;
import clinicMap.eddiechen.ecpay.payment.integration.AllInOne;
import clinicMap.eddiechen.ecpay.payment.integration.domain.AioCheckOutALL;
import clinicMap.eddiechen.ecpay.payment.integration.domain.AioCheckOutCVS;
import clinicMap.eddiechen.ecpay.payment.integration.domain.InvoiceObj;
import clinicMap.tingyen.model.Clinic;

@Repository
public class EcpayDao {
	public static AllInOne all;
	private static SessionFactory sessionFactory;
	
	@Autowired
	public EcpayDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		EcpayDao.sessionFactory = sessionFactory;
	}
	

	
	
	public static void initial(){
		all = new AllInOne("");
	}
	
	public static String genAdPayment(String uuid/*, String clinicID*/){
//		Session session  = EcpayDao.sessionFactory.getCurrentSession();
		AioCheckOutALL obj = new AioCheckOutALL();
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");  
		String strDate = dateFormat.format(date); 
		obj.setMerchantTradeNo(uuid);
		obj.setMerchantTradeDate(strDate);
		obj.setTotalAmount("100");
		obj.setTradeDesc("test Description");
		obj.setItemName("ClinicMap廣告贊助");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setClientBackURL("http://clinicmap.tk/changeStatusAfterPay?id=" + uuid/*clinicID*/);
//		obj.setOrderResultURL("http://localhost:8080/SpringAllForOne/Test.jsp");
		obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);
//		Sponsor s = new Sponsor();
//		s.setClinicAccount(clinicAccount);
//		s.setTradeID(id);
//		s.setSponsorTime(strDate);
//		session.save(s);
		return form;
	}
	
	public static void changeStatus(int clinicID) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Clinicchen clinic = session.get(Clinicchen.class, clinicID);
			System.out.println(clinic.getClinicID());
			clinic.setClinicStatus("CS3");
			session.update(clinic);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public static String genTexiPayment(String uuid, String priceTotal, String positionidcheck/*, String clinicID*/) {
//		Session session  = EcpayDao.sessionFactory.getCurrentSession();
		AioCheckOutALL obj = new AioCheckOutALL();
		
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");  
		String strDate = dateFormat.format(date);  
		obj.setMerchantTradeNo(uuid);
		obj.setMerchantTradeDate(strDate);
		obj.setTotalAmount(priceTotal);
		obj.setTradeDesc("DriverPayment");
		obj.setItemName("司機接送");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setClientBackURL("http://localhost/clinicMap/guest?payedsuccess&"+positionidcheck);
		obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);

//		Sponsor s = new Sponsor();
//		s.setClinicID(clinicID);
//		s.setTradeID(id);
//		s.setSponsorTime(strDate);
//		session.save(s);
		return form;
		
	}
}
