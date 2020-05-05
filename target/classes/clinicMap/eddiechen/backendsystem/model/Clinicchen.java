package clinicMap.eddiechen.backendsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Clinic")
@Component

public class Clinicchen {
	private int clinicID;
	private String clinicName;
	private String clinicAccount;
	private String clinicPwd;
	private String clinicAddress;
	private String clinicDescription;
	private byte[] clinicPhoto;
	private byte[] clinicLicense;

	private String clinicPhone;
	private String clinicClass;
	private String clinicType;
	private String clinicTime;
	private String clinicStatus;
	private String clinicEmail;

	private String cliniclat;
	private String cliniclng;

	public Clinicchen() {

	}

	public Clinicchen(String clinicName, String clinicAccount, String clinicPwd, String clinicAddress,
			String clinicDescription, byte[] clinicPhoto, byte[] clinicLicense, String clinicPhone, String clinicClass,
			String clinicType, String clinicTime, String clinicStatus, String clinicEmail, String cliniclat, String cliniclng) {
		this.clinicName = clinicName;
		this.clinicAccount = clinicAccount;
		this.clinicPwd = clinicPwd;
		this.clinicAddress = clinicAddress;
		this.clinicDescription = clinicDescription;
		this.clinicPhoto = clinicPhoto;
		this.clinicLicense = clinicLicense;
		this.clinicPhone = clinicPhone;
		this.clinicClass = clinicClass;
		this.clinicType = clinicType;
		this.clinicTime = clinicTime;
		this.clinicStatus = clinicStatus;
		this.clinicEmail = clinicEmail;
		this.cliniclat = cliniclat;
		this.cliniclng = cliniclng;
	}

	@Id
	@Column(name = "clinicID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getClinicID() {
		return clinicID;
	}

	public void setClinicID(int clinicID) {
		this.clinicID = clinicID;
	}

	@Column(name = "clinicName")
	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	@Column(name = "clinicAccount")
	public String getClinicAccount() {
		return clinicAccount;
	}

	public void setClinicAccount(String clinicAccount) {
		this.clinicAccount = clinicAccount;
	}

	@Column(name = "clinicPwd")
	public String getClinicPwd() {
		return clinicPwd;
	}

	public void setClinicPwd(String clinicPwd) {
		this.clinicPwd = clinicPwd;
	}

	@Column(name = "clinicAddress")
	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	@Column(name = "clinicDescription")
	public String getClinicDescription() {
		return clinicDescription;
	}

	public void setClinicDescription(String clinicDescription) {
		this.clinicDescription = clinicDescription;
	}

	@Column(name = "clinicPhoto")
	public byte[] getClinicPhoto() {
		return clinicPhoto;
	}

	public void setClinicPhoto(byte[] clinicPhoto) {
		this.clinicPhoto = clinicPhoto;
	}

	@Column(name = "clinicLicense")
	public byte[] getClinicLicense() {
		return clinicLicense;
	}

	public void setClinicLicense(byte[] clinicLicense) {
		this.clinicLicense = clinicLicense;
	}

	@Column(name = "clinicPhone")
	public String getClinicPhone() {
		return clinicPhone;
	}

	public void setClinicPhone(String clinicPhone) {
		this.clinicPhone = clinicPhone;
	}

	@Column(name = "clinicClass")
	public String getClinicClass() {
		return clinicClass;
	}

	public void setClinicClass(String clinicClass) {
		this.clinicClass = clinicClass;
	}

	@Column(name = "clinicType")
	public String getClinicType() {
		return clinicType;
	}

	public void setClinicType(String clinicType) {
		this.clinicType = clinicType;
	}

	@Column(name = "clinicTime")
	public String getClinicTime() {
		return clinicTime;
	}

	public void setClinicTime(String clinicTime) {
		this.clinicTime = clinicTime;
	}

	@Column(name = "clinicStatus")
	public String getClinicStatus() {
		return clinicStatus;
	}

	public void setClinicStatus(String clinicStatus) {
		this.clinicStatus = clinicStatus;
	}

	@Column(name = "clinicEmail")
	public String getClinicEmail() {
		return clinicEmail;
	}

	public void setClinicEmail(String clinicEmail) {
		this.clinicEmail = clinicEmail;
	}
	
	@Column(name="clinicLat")
	public String getCliniclat() {
		return cliniclat;
	}

	public void setCliniclat(String cliniclat) {
		this.cliniclat = cliniclat;
	}
	
	@Column(name="clinicLng")
	public String getCliniclng() {
		return cliniclng;
	}

	public void setCliniclng(String cliniclng) {
		this.cliniclng = cliniclng;
	}

}
