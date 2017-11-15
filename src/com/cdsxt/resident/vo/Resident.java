package com.cdsxt.resident.vo;

import java.util.Date;

public class Resident {
	private int id;
	private String rname;
	private int gender;
	private int age;
	private String cid;
	private int edu;
	private String email;
	private String rent;
	private String phone;
	private Date bdate;
	private Date edate;
	private String rltprepath;
	private String rltrealpath;
	private String photoname;
	
	public Resident(){
		
	}

	public Resident(int id, String rname, int gender, int age, String cid,
			int edu, String email, String rent, String phone, Date bdate,
			Date edate, String rltprepath, String rltrealpath, String photoname) {
		super();
		this.id = id;
		this.rname = rname;
		this.gender = gender;
		this.age = age;
		this.cid = cid;
		this.edu = edu;
		this.email = email;
		this.rent = rent;
		this.phone = phone;
		this.bdate = bdate;
		this.edate = edate;
		this.rltprepath = rltprepath;
		this.rltrealpath = rltrealpath;
		this.photoname = photoname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public int getEdu() {
		return edu;
	}

	public void setEdu(int edu) {
		this.edu = edu;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRent() {
		return rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public String getRltprepath() {
		return rltprepath;
	}

	public void setRltprepath(String rltprepath) {
		this.rltprepath = rltprepath;
	}

	public String getRltrealpath() {
		return rltrealpath;
	}

	public void setRltrealpath(String rltrealpath) {
		this.rltrealpath = rltrealpath;
	}

	public String getPhotoname() {
		return photoname;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}
	
	
	
}
