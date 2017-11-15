package com.cdsxt.resident.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.ImageTool;
import util.Page;
import com.cdsxt.resident.dao.ResidentDao;
import com.cdsxt.login.LoginDao;
import com.cdsxt.resident.vo.Resident;
import com.cdsxt.resident.vo.Draw;
import com.google.gson.Gson;

@SuppressWarnings({ "unused", "serial" })
public class ResidentServlet extends HttpServlet {	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String opt = req.getParameter("opt");
		if(opt.equals("list")){
			findAllByPage(req,resp);
		}else if(opt.equals("add")){
			try {
				addResident(req,resp);
				findAllByPage(req,resp);
			}catch(FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (opt.equals("del")) {
			del(req, resp);
			findAllByPage(req,resp);
		}else if (opt.equals("deleteAll")) {
			delAll(req, resp);
			findAllByPage(req, resp);
		}else if (opt.equals("updatebefore")) {
			updateBefore(req,resp);
		}else if (opt.equals("update")) {
			try {
				update(req,resp);
				findAllByPage(req,resp);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(opt.equals("search")){
			System.out.println(opt);
			search(req,resp);
		}else if (opt.equals("findByPage")) {
			findByPage(req, resp);
		}else if (opt.equals("showMsg")) {
			showMsg(req, resp);
		}else if(opt.equals("drawage")){
			drawAge(req,resp);
		}else if(opt.equals("drawedu")){
			drawEdu(req,resp);
		}
	}
	
	public void drawAge(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ResidentDao residentDao = new ResidentDao();
		List<Resident> residents1 = residentDao.dataByage(21,30);
		List<Resident> residents2 = residentDao.dataByage(31,40);
		List<Resident> residents3 = residentDao.dataByage(41,50);
		List<Resident> residents4 = residentDao.dataByage(51,60);
		
		int num1=residents1.size();
		int num2=residents2.size();
		int num3=residents3.size();
		int num4=residents4.size();
		
		List<Draw> draws = new ArrayList<Draw>();
		Draw draw1=new Draw();
		Draw draw2=new Draw();
		Draw draw3=new Draw();
		Draw draw4=new Draw();
		draw1.setLabel("21-30");
		draw1.setValue(num1);
		draw2.setLabel("31-40");
		draw2.setValue(num2);
		draw3.setLabel("41-50");
		draw3.setValue(num3);
		draw4.setLabel("51-60");
		draw4.setValue(num4);
		
		System.out.println("----- 年龄在21至30岁之间的人数 : "+num1+" -----");
		System.out.println("----- 年龄在31至40岁之间的人数 : "+num2+" -----");
		System.out.println("----- 年龄在41至50岁之间的人数 : "+num3+" -----");
		System.out.println("----- 年龄在51至60岁之间的人数 : "+num4+" -----");
		
		draws.add(draw1);
		draws.add(draw2);
		draws.add(draw3);
		draws.add(draw4);
		
		Gson gson =new Gson();
		String drawage=gson.toJson(draws);
		req.setAttribute("drawage", drawage);
		req.getRequestDispatcher("drawage.jsp").forward(req, resp);
	}
	
	public void drawEdu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ResidentDao residentDao = new ResidentDao();
		List<Resident> residents1 = residentDao.dataByedu(1);
		List<Resident> residents2 = residentDao.dataByedu(2);
		List<Resident> residents3 = residentDao.dataByedu(3);
		List<Resident> residents4 = residentDao.dataByedu(4);
		List<Resident> residents5 = residentDao.dataByedu(5);
		
		int num1=residents1.size();
		int num2=residents2.size();
		int num3=residents3.size();
		int num4=residents4.size();
		int num5=residents5.size();
	
		List<Draw> draws = new ArrayList<Draw>();
		Draw draw1=new Draw();
		Draw draw2=new Draw();
		Draw draw3=new Draw();
		Draw draw4=new Draw();
		Draw draw5=new Draw();
		
		draw1.setLabel("专科以下");
		draw1.setValue(num1);
		draw2.setLabel("专科");
		draw2.setValue(num2);
		draw3.setLabel("本科");
		draw3.setValue(num3);
		draw4.setLabel("硕士");
		draw4.setValue(num4);
		draw5.setLabel("硕士以上");
		draw5.setValue(num5);
		
		System.out.println("----- 专科以下学历的人数 : "+num1+" -----");
		System.out.println("----- 专科学历的人数 : "+num2+" -----");
		System.out.println("----- 本科学历的人数 : "+num3+" -----");
		System.out.println("----- 硕士学历的人数 : "+num4+" -----");
		System.out.println("----- 硕士以上学历的人数 : "+num5+" -----");
		
		draws.add(draw1);
		draws.add(draw2);
		draws.add(draw3);
		draws.add(draw4);
		draws.add(draw5);
		
		Gson gson =new Gson();
		String drawedu=gson.toJson(draws);
		req.setAttribute("drawedu", drawedu);
		req.getRequestDispatcher("drawedu.jsp").forward(req, resp);
	}
	
	public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ResidentDao residentDao = new ResidentDao();
		List<Resident> residents = residentDao.findAll();
		req.setAttribute("residents", residents);
		req.getRequestDispatcher("jsp/resident/residentList.jsp").forward(req, resp);
	}
	
	//查看用户信息，包括照片信息
	@SuppressWarnings({ "static-access" })
	public void showMsg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		Resident resident = new ResidentDao().findById(id);
		
		/*
		 * 以下这段代码是为了
		 * 对数据库中有对应照片相对地址的用户
		 * 但是在对应绝对地址中无法读取对应用户照片
		 * 则将这类用户在数据库中的相对地址字段置为null
		 * 对所有用户做一次清理
		 * 
		 */
		List<Resident> residents = new ResidentDao().findAll();
		for (int i = 0; i < residents.size(); i++) {
			Resident resident2 = residents.get(i);
			String prePath = resident2.getRltprepath();
			if(prePath!=null&&!prePath.isEmpty()&&!prePath.equals("")){
				System.out.println("===== prePath ："+prePath+" =====");
				String prename = prePath.substring(prePath.lastIndexOf("/")+1);
				String absPrePath = this.getServletContext().getRealPath("rsdupload")+File.separator+prename;
				File file = new File(absPrePath);
				if(!file.canRead()){
					System.out.println("===== 删除住户的名字："+resident2.getRname()+" =====");
					resident2.setRltprepath(null);	
					resident2.setRltrealpath(null);
					resident2.setPhotoname(null);
					new ResidentDao().updateResident(resident2);	
				}
			}
		}
		
		String rltPrePath = resident.getRltprepath();
		/*
		 * 加上以下这段代码
		 * 是为了防止重新登录时
		 * 再次查看用户信息
		 * 不能显示用户照片的情况
		 * 
		 * 用户之所以能够显示照片
		 * 是因为这里
		 * imageTool.getPreviewImage(file, file2, type);
		 * 中有对原始照片和预览照片的一个写入过程
		 * 如果没有这个写入过程的话
		 * 是不能显示照片的
		 * 即使知道用户照片的相对路径
		 *
		 */
		if(rltPrePath!=null&&!rltPrePath.isEmpty()&&!rltPrePath.equals("")){
			System.out.println("===== 用户图片的相对地址："+rltPrePath+"=====");
			String type = rltPrePath.substring(rltPrePath.lastIndexOf(".")+1);
			String preName = rltPrePath.substring(rltPrePath.lastIndexOf("/")+1);
			String pname = preName.substring(1);
			System.out.println("----- 用户图片的名字（包括.jpg等后缀）： "+pname+" -----");
			String absRealPath = this.getServletContext().getRealPath("rsdupload")+File.separatorChar+pname;
			System.out.println("----- 用户图片的绝对路径: "+absRealPath+" -----");
			File file = new File(absRealPath);
			//处理预览图片
			System.out.println("----- 用户预览图片的名字: "+preName+" -----");
			String absPrePath = this.getServletContext().getRealPath("rsdupload")+File.separatorChar+preName;
			System.out.println("----- 用户预览图片的绝对路径: "+absPrePath+" -----");
			File file2 = new File(absPrePath);
			ImageTool imageTool = new ImageTool();
			try {
				if(file.canRead()){//如果用户照片文件可读取则进行下一步生成预览，对图片大小、色调等进行调整
					imageTool.getPreviewImage(file, file2, type);
				}else{//如果无法读取则不生成预览
					resident.setRltprepath(null);
					resident.setRltrealpath(null);
					resident.setPhotoname(null);
					new ResidentDao().updateResident(resident);	
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		req.setAttribute("resident", resident);
		req.getRequestDispatcher("jsp/resident/showMsg.jsp").forward(req, resp);
	}
	
	//分页
	public void findAllByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ResidentDao residentDao = new ResidentDao();
		Page page = new Page(1, residentDao.count());
		List<Resident> residents = residentDao.findByPage(page.getNum(), page.getPageSize());
		page.setList(residents);
		System.out.println("----- "+residents.size()+" -----");
		req.setAttribute("page", page);
		req.getRequestDispatcher("jsp/resident/residentList.jsp").forward(req, resp);
	}
	//分页
	public void findByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int num = Integer.parseInt(req.getParameter("num"));
		ResidentDao residentDao = new ResidentDao();
		Page page = new Page(num, residentDao.count());
		List<Resident> residents = residentDao.findByPage(page.getNum(), page.getPageSize());
		page.setList(residents);
		System.out.println("----- 当前页码 : "+page.getNum()+" -----");
		System.out.println("----- 总记录数 : "+page.getNavCount()+" -----");
		System.out.println("----- begin : "+page.getBegin()+" ————> end : "+page.getEnd()+" -----");
		req.setAttribute("page", page);
		req.getRequestDispatcher("jsp/resident/residentList.jsp").forward(req, resp);
	}
	
	//更新后留在原页面，分页
	public void updatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, FileUploadException{
		ResidentDao residentDao = new ResidentDao();
		Page page = new Page(1, residentDao.count());
		List<Resident> residents = residentDao.findByPage(page.getNum(), page.getPageSize());
		page.setList(residents);
		System.out.println("----- "+residents.size()+" -----");
		req.setAttribute("page", page);
		req.getRequestDispatcher("jsp/resident/residentList.jsp").forward(req, resp);
	}
	
	public void del(HttpServletRequest req, HttpServletResponse resp){
		int id = Integer.parseInt(req.getParameter("id"));
		ResidentDao residentDao = new ResidentDao();
		residentDao.del(id);
	}
	
	public void delAll(HttpServletRequest req, HttpServletResponse resp){
		String ids = req.getParameter("ids");
		ResidentDao residentDao = new ResidentDao();
		String[] ss = ids.split(";");
		for (int i = 0; i < ss.length; i++) {
			//System.out.println("===="+ss[i]);
			residentDao.del(Integer.parseInt(ss[i]));
		}
	}
		
	@SuppressWarnings({ "unchecked", "static-access"})
	public void addResident(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException{
		    System.out.println("addResident");
	    	DiskFileItemFactory factory = new DiskFileItemFactory();
	    	factory.setSizeThreshold(100*1024);
	    	ServletFileUpload upload = new ServletFileUpload(factory);
	    	Resident resident = new Resident();
	    	List<FileItem> fis = upload.parseRequest(req);
	    	String rent1 = "";
	    	String rent2 = "";
	    	String rent3 = "";
	    	for (Iterator iterator = fis.iterator(); iterator.hasNext();) {
				FileItem fileItem = (FileItem) iterator.next();
				try {
				     	System.out.println("----- "+fileItem.getString("utf-8")+" -----");
				      }catch(UnsupportedEncodingException e1) {
					    e1.printStackTrace();
				      }
				if(fileItem.isFormField()){//FileItem对象里面封装的数据是一个普通文本表单字段，isFormField()返回true
					try {
						  /*
						   * 注意fileItem.getFieldName()
						   * 与fileItem.getName()的区别
						   * getFieldName方法用来返回
						   * 表单标签的name属性的值
						   * 如<input name="tag"  type="text"/>
						   * 则fileItem.getFieldName()="tag"
						   * getName方法用来获得文件上传字段中的文件名
						   */
					      String name = fileItem.getFieldName();
					      if(name.equals("rname")){
					    	  resident.setRname(fileItem.getString("utf-8"));
					      }else if(name.equals("gender")){
					          if(Integer.parseInt(fileItem.getString("utf-8"))==0){
						             resident.setGender(0);
					           }else{
						             resident.setGender(1);
					           }
					     }else if(name.equals("edu")){
					          if(Integer.parseInt(fileItem.getString("utf-8"))==1){
						             resident.setEdu(1);
					           }else if(Integer.parseInt(fileItem.getString("utf-8"))==2){
						             resident.setEdu(2);
					           }else if(Integer.parseInt(fileItem.getString("utf-8"))==3){
						             resident.setEdu(3);
					           }else if(Integer.parseInt(fileItem.getString("utf-8"))==4){
						             resident.setEdu(4);
					           }else if(Integer.parseInt(fileItem.getString("utf-8"))==5){
						             resident.setEdu(5);
					           }
				         }else if(name.equals("cid")){
			                 resident.setCid(fileItem.getString("utf-8"));
		                 }else if(name.equals("age")){
			                 resident.setAge(Integer.parseInt(fileItem.getString("utf-8")));
		                 }else if(name.equals("rent1")){
			                 rent1 = fileItem.getString("utf-8");
		                 }else if(name.equals("rent2")){
			                 rent2 = fileItem.getString("utf-8");
		                 }else if(name.equals("rent3")){
			                 rent3 = fileItem.getString("utf-8");
		                 }else if(name.equals("phone")){
						     resident.setPhone(fileItem.getString("utf-8"));
					     }else if(name.equals("email")){
						     resident.setEmail(fileItem.getString("utf-8"));
				      	}else if(name.equals("bdate")){
						     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						     resident.setBdate(df.parse(fileItem.getString("utf-8")));
					    }else if(name.equals("edate")){
			                 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			                 resident.setEdate(df.parse(fileItem.getString("utf-8")));
		                }
					}catch(UnsupportedEncodingException e) {
						e.printStackTrace();
		       }catch(ParseException e) {
				   e.printStackTrace();
		       }		
		       resident.setRent(rent1+rent2+rent3);
			   }else{//FileItem对象里面封装的数据是一个文件表单字段，isFormField()返回false
				/*
				 * 获取文件域
				 * 处理原图片
				 */
				System.out.println("-----> fileItem："+fileItem+" <-----");
				if(!fileItem.getName().equals("")){//点击且选择了上传照片
					System.out.println("-----> fileItem.getFieldName()："+fileItem.getFieldName()+" <-----");
					System.out.println("-----> fileItem.getName()："+fileItem.getName()+" <-----");
					System.out.println("----- 上传图片的名字: "+fileItem.getName().substring(0, fileItem.getName().lastIndexOf("."))+" -----");
					resident.setPhotoname(fileItem.getName().substring(0, fileItem.getName().lastIndexOf(".")));//给photoname属性赋值
					String type = fileItem.getName().substring(fileItem.getName().lastIndexOf(".")+1);
					System.out.println("----- 上传图片的格式："+type+" -----");
					//对上传的图片做唯一化处理：根据获取的当前时间保证上传图片名字的唯一性
					String pname = System.currentTimeMillis()+"."+fileItem.getName().substring(fileItem.getName().lastIndexOf(".")+1);
					System.out.println("----- 唯一化处理后图片的名字: "+pname+" -----");
					resident.setRltrealpath("rsdupload/"+pname);//给rltrealpath属性赋值
					System.out.println("----- 唯一化处理后图片的相对路径 : "+resident.getRltrealpath()+" -----");
					String absRealPath = this.getServletContext().getRealPath("rsdupload")+File.separatorChar+pname;
					System.out.println("----- 唯一化处理后图片的绝对路径: "+absRealPath+" -----");
					File file = new File(absRealPath);
					try {
							fileItem.write(file);
						}catch(Exception e) {
							e.printStackTrace();
					}	
					//处理预览图片
					String preName = "s"+pname;
					System.out.println("----- 生成预览图片的名字: "+preName+" -----");
					String absPrePath = this.getServletContext().getRealPath("rsdupload")+File.separatorChar+preName;
					System.out.println("----- 生成预览图片的绝对路径: "+absPrePath+" -----");
					resident.setRltprepath("rsdupload/"+preName);//给rltprepath属性赋值
					File file2 = new File(absPrePath);
					ImageTool imageTool = new ImageTool();
					try {
						imageTool.getPreviewImage(file, file2, type);//将唯一化和预览图片写入相应的绝对路径
						}catch(Exception e) {
						e.printStackTrace();
					}
				}
				new ResidentDao().add(resident);
			}
		}
	 }
	
	public void updateBefore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ResidentDao residentDao = new ResidentDao();
		int id = Integer.parseInt(req.getParameter("id"));
		Resident resident = residentDao.findById(id);
		req.setAttribute("resident", resident);
		req.getRequestDispatcher("jsp/resident/update.jsp").forward(req, resp);
	}
	
	@SuppressWarnings({ "unchecked", "static-access"})
	public void update(HttpServletRequest req, HttpServletResponse resp ) throws FileUploadException, ServletException, IOException {
		System.out.println("update");
    	DiskFileItemFactory factory = new DiskFileItemFactory();
    	factory.setSizeThreshold(100*1024);
    	ServletFileUpload upload = new ServletFileUpload(factory);
    	Resident resident = new Resident();
    	List<FileItem> fis = upload.parseRequest(req);
   
    	String rent1 = "";
    	String rent2 = "";
    	String rent3 = "";
    	
    	Integer updId = -1;
    	/*
    	 * 先获得欲更新的用户ID
    	 * 为了根据这个ID获得更新的用户
    	 * 然后通过这个用户获得预览图片的相对地址
    	 */
    	for (Iterator iterator = fis.iterator(); iterator.hasNext();) {
    		FileItem fileItem = (FileItem) iterator.next();
			try {
			     	System.out.println("----- "+fileItem.getString("utf-8")+" -----");
			      } catch (UnsupportedEncodingException e1) {
				    e1.printStackTrace();
			      }
	        if(fileItem.isFormField()){
	        	String name=fileItem.getFieldName();
	        	if(name.equals("id")){
	        		updId = Integer.parseInt(fileItem.getString("utf-8"));
	        		System.out.println("=*=*=*=*="+updId+"=*=*=*=*=");
		        }
	        }
    	}
    	
    	Resident upd = new ResidentDao().findById(updId);
    	
    	for (Iterator iterator = fis.iterator(); iterator.hasNext();) {
    		FileItem fileItem = (FileItem) iterator.next();
			try {
			     	System.out.println("----- "+fileItem.getString("utf-8")+" -----");
			      } catch (UnsupportedEncodingException e1) {
				    e1.printStackTrace();
			      }
	        if(fileItem.isFormField()){
				try {
				      String name=fileItem.getFieldName();
				      if(name.equals("rname")){
				    	  upd.setRname(fileItem.getString("utf-8"));
				      }else if(name.equals("id")){
				    	  upd.setId(Integer.parseInt(fileItem.getString("utf-8")));
			                 System.out.println("====="+upd.getId()+"=====");
		              }else if(name.equals("gender")){
					          if(Integer.parseInt(fileItem.getString("utf-8"))==0){
					        	  upd.setGender(0);
					           }else{
					        	   upd.setGender(1);
					           }
				     }else if(name.equals("edu")){
				          if(Integer.parseInt(fileItem.getString("utf-8"))==1){
				        	  upd.setEdu(1);
				           }else if(Integer.parseInt(fileItem.getString("utf-8"))==2){
				        	   upd.setEdu(2);
				           }else if(Integer.parseInt(fileItem.getString("utf-8"))==3){
				        	   upd.setEdu(3);
				           }else if(Integer.parseInt(fileItem.getString("utf-8"))==4){
				        	   upd.setEdu(4);
				           }else if(Integer.parseInt(fileItem.getString("utf-8"))==5){
				        	   upd.setEdu(5);
				           }
			         }else if(name.equals("cid")){
			        	 upd.setCid(fileItem.getString("utf-8"));
	                 }else if(name.equals("age")){
	                	 upd.setAge(Integer.parseInt(fileItem.getString("utf-8")));
	                 }else if(name.equals("rent1")){
		                 rent1=fileItem.getString("utf-8");
	                 }else if(name.equals("rent2")){
		                 rent2=fileItem.getString("utf-8");
	                 }else if(name.equals("rent3")){
		                 rent3=fileItem.getString("utf-8");
	                 }else if(name.equals("phone")){
	                	 upd.setPhone(fileItem.getString("utf-8"));
				     }else if(name.equals("email")){
				    	 upd.setEmail(fileItem.getString("utf-8"));
			      	}else if(name.equals("bdate")){
		                 DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		                 upd.setBdate(df.parse(fileItem.getString("utf-8")));
				    }else if(name.equals("edate")){
		                 DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		                 upd.setEdate(df.parse(fileItem.getString("utf-8")));
	                 }
				}catch(UnsupportedEncodingException e) {
					e.printStackTrace();
	       }catch(ParseException e) {
			   e.printStackTrace();
	       }		
	       upd.setRent(rent1+rent2+rent3);
		   }else{
				/*
				 * 获取文件域
				 * 处理原图片
				 */
			    if(updId != -1){//获取到待更新的用户ID
			    	System.out.println("=*=*=*=*= "+updId+" =*=*=*=*=");
				    String rltPrePath = upd.getRltprepath();
				    if(rltPrePath!=null){//数据库中原先有该用户照片的相对地址信息
				        String type = rltPrePath.substring(rltPrePath.lastIndexOf(".")+1);//从数据库相对地址中获得的图片类型
					    System.out.println("===== "+rltPrePath+" =====");
					    System.out.println("===== "+fileItem+" =====");
					    System.out.println("===== "+fileItem.getName()+" =====");
					    String photoName = fileItem.getName();
						if(photoName.equals("")){//如果获得的photoName为空则表明用户没有点击上传
							String prename = rltPrePath.substring(rltPrePath.lastIndexOf("/")+1);
							System.out.println("----- 原用户上传后生成预览图片的名字 : "+prename+" -----");
							System.out.println("----- 原用户上传后生成预览图片的类型 : "+type+" -----");
							System.out.println("----- 原用户上传后生成预览图片的相对路径 : "+rltPrePath+" -----");
							String absPrePath = this.getServletContext().getRealPath("rsdupload")+File.separatorChar+prename;
							System.out.println("----- 原用户上传后生成预览图片的绝对路径: "+absPrePath+" -----");
							upd.setRltprepath(rltPrePath);
							upd.setRltrealpath(upd.getRltrealpath());
							upd.setPhotoname(upd.getPhotoname());
							/*
							 * 如果没有点击上传则不用进行预览相关事项了
							 * 因为此时图像的相对地址在数据库中可以查到
							 * 而预览图片在第一次上传时就已经生成了
							 * 
							 * 注意：此时虽然没有点击上传但是此时仍然需要重新将
							 * 原来数据库里的相对地址等set一遍 即先get再set的顺序
							 * 如upd.setRltrealpath(upd.getRltrealpath())
							 * 如果不重新set一遍的话用户原来存在数据库里的相对地址等
							 * 都会被置为null，那么本来用户照片可以显示的也会变得不能显示了
							 * 因为没有重新set一遍，将原来的相对地址给弄没了
							 * 而显示用户照片时需要根据数据库里存的预览照片的相对地址
							 * 进行显示的，没有预览照片相对地址了，当然就不能显示了
							 */
							/*
							File file = new File(absRealPath);
							try {
									fileItem.write(file);
								}catch(Exception e) {
									e.printStackTrace();
							}	
							//处理预览图片
							String preName = "s"+pname;
							System.out.println("----- 生成预览图片的名字: "+preName+" -----");
							String absPrePath = this.getServletContext().getRealPath("rsdupload")+File.separatorChar+preName;
							System.out.println("----- 生成预览图片的绝对路径: "+absPrePath+" -----");
							resident.setRltprepath("rsdupload/"+preName);//给rltprepath属性赋值
							File file2 = new File(absPrePath);
							ImageTool imageTool = new ImageTool();
							try {
								imageTool.getPreviewImage(file, file2, type);//将唯一化和预览图片写入相应的绝对路径
								}catch(Exception e) {
								e.printStackTrace();
							}
							*/	
						}else{//如果获得的photoName不为空则表明用户又重新点击上传
							type = photoName.substring(photoName.lastIndexOf(".")+1);//type更新为最新上传图片的类型
							System.out.println("----- 用户新上传图片的名字: "+photoName.substring(0, fileItem.getName().lastIndexOf("."))+" -----");
							resident.setPhotoname(photoName.substring(0, fileItem.getName().lastIndexOf(".")));//给photoname属性赋值
							String pname = System.currentTimeMillis()+"."+photoName.substring(photoName.lastIndexOf(".")+1);
							System.out.println("----- 用户新上传图片的格式："+photoName.substring(photoName.lastIndexOf(".")+1)+" -----");
							System.out.println("----- 唯一化处理后对应用户新上传图片的名字 : "+pname+" -----");
							resident.setRltrealpath("rsdupload/"+pname);//给rltrealpath赋值
							System.out.println("----- 唯一化处理后对应用户新上传图片的相对路径 : "+resident.getRltrealpath()+" -----");
							String absrealpath = this.getServletContext().getRealPath("rsdupload")+File.separatorChar+pname;
							System.out.println("----- 唯一化处理后对应用户新上传图片的绝对路径: "+absrealpath+" -----");
							File file = new File(absrealpath);
							try {
									fileItem.write(file);
								}catch(Exception e) {
									e.printStackTrace();
							}	
							//处理预览图片
							String preName="s"+pname;
							/*
							 * 给photo赋值，
							 * 将生成的预览图片作为用户头像比较合理
							 * 因为生成的预览图片大小都相同，且较小
							 * 适合作为用户头像展示
							 * 而用户上传的图片大小可能不一
							 * 不适合作为用户头像展示
							 */
							resident.setRltprepath("rsdupload/"+preName);//给rltprepath属性赋值
							System.out.println("----- 生成预览图片的名字: "+preName+" -----");
							String absPrePath=this.getServletContext().getRealPath("rsdupload")+File.separatorChar+preName;
							System.out.println("----- 生成预览图片的绝对路径: "+absPrePath+" -----");
							File file2 = new File(absPrePath);
							ImageTool imageTool = new ImageTool();
							try {
								imageTool.getPreviewImage(file, file2, type);
							} catch (Exception e) {
								e.printStackTrace();
							}	
						}
				    }else{//数据库中原先没有该用户照片的相对地址信息
			    	   String photoName = fileItem.getName();
					   if(!photoName.equals("")){//如果获得的type不为空则表明用户又重新点击上传
							String type = photoName.substring(photoName.lastIndexOf(".")+1);
							System.out.println("----- 用户新上传图片的名字: "+photoName.substring(0, fileItem.getName().lastIndexOf("."))+" -----");
							upd.setPhotoname(photoName.substring(0, fileItem.getName().lastIndexOf(".")));//给photoname属性赋值
							String pname = System.currentTimeMillis()+"."+photoName.substring(photoName.lastIndexOf(".")+1);
							System.out.println("----- 用户新上传图片的格式："+type+" -----");
							System.out.println("----- 唯一化处理后对应用户新上传图片的名字 : "+pname+" -----");
							upd.setRltrealpath("rsdupload/"+pname);//给rltrealpath赋值
							System.out.println("----- 唯一化处理后对应用户新上传图片的相对路径 : "+upd.getRltrealpath()+" -----");
							String absrealpath = this.getServletContext().getRealPath("rsdupload")+File.separatorChar+pname;
							System.out.println("----- 唯一化处理后对应用户新上传图片的绝对路径: "+absrealpath+" -----");
							File file = new File(absrealpath);
							try {
									fileItem.write(file);
								}catch(Exception e) {
									e.printStackTrace();
							}	
							//处理预览图片
							String preName="s"+pname;
							/*
							 * 给photo赋值，
							 * 将生成的预览图片作为用户头像比较合理
							 * 因为生成的预览图片大小都相同，且较小
							 * 适合作为用户头像展示
							 * 而用户上传的图片大小可能不一
							 * 不适合作为用户头像展示
							 */
							upd.setRltprepath("rsdupload/"+preName);//给rltprepath属性赋值
							System.out.println("----- 生成预览图片的名字: "+preName+" -----");
							String absPrePath=this.getServletContext().getRealPath("rsdupload")+File.separatorChar+preName;
							System.out.println("----- 生成预览图片的绝对路径: "+absPrePath+" -----");
							File file2 = new File(absPrePath);
							ImageTool imageTool = new ImageTool();
							try {
								imageTool.getPreviewImage(file, file2, type);
							} catch (Exception e) {
								e.printStackTrace();
							}	
						}
				    }
			    }
		   }	
    	}
    	new ResidentDao().updateResident(upd);	
	}
	
	public void search(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException{
	     	String category=(String)req.getParameter("category");
	     	String key=req.getParameter("key");
			System.out.println("----- "+category+" -----");
			System.out.println("----- "+key+" -----");
			ResidentDao residentDao = new ResidentDao();
			String content = "";
			if (category.equals("1")) {
				content = "rname";
			}else if (category.equals("2")) {
				content = "cid";
			}else if(category.equals("3")){
				content="edu";
			}else if(category.equals("4")){
				content="rent";
			}
			List<Resident> residents = residentDao.search(content, key);
			Page page = new Page(1,residents.size());
			page.setPageSize(10);
			page.setRowCount(residents.size());
	        page.setList(residents);
			System.out.println("----- "+residents.size()+" -----");
			req.setAttribute("page", page);
			req.getRequestDispatcher("jsp/resident/residentList.jsp").forward(req, resp);
		}	
}
