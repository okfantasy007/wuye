package com.cdsxt.resident.init;

import java.io.File;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import com.cdsxt.resident.dao.ResidentDao;
import com.cdsxt.resident.vo.Resident;

@SuppressWarnings("serial")
public class PhotoInitServlet extends HttpServlet {
	
	public void init() throws ServletException {
		/*
		 * 以下这段代码是为了在启动服务器的时候
		 * 对数据库中有对应照片相对地址的用户
		 * 但是在对应绝对地址中无法读取对应用户照片
		 * 则将这类用户在数据库中的相对地址字段置为null
		 * 对所有用户做一次清理
		 * 
		 * 不过还有一个问题：
		 * 重新部署服务器之后然后再启动服务器
		 * 用户之前上传的照片全部都没有了
		 * 因为重新部署服务器以为着用户照片原来
		 * 存放的文件夹resources也跟着消失了
		 * 
		 */
		List<Resident> residents = new ResidentDao().findAll();
		System.out.println("====="+this.getServletContext().getRealPath("rsdupload")+"=====");
		for (int i = 0; i < residents.size(); i++) {
			Resident resident = residents.get(i);
			String rltPrePath = resident.getRltprepath();
			if(rltPrePath!=null&&!rltPrePath.isEmpty()&&!rltPrePath.equals("")){
				String photoName = rltPrePath.substring(rltPrePath.lastIndexOf("/")+1);
				String absPrePath = this.getServletContext().getRealPath("rsdupload")+File.separator+photoName;
				File file = new File(absPrePath);
				if(!file.canRead()){
					System.out.println("===== 删除住户的名字："+resident.getRname()+" =====");
					resident.setRltprepath(null);	
					resident.setRltrealpath(null);
					resident.setPhotoname(null);
					new ResidentDao().updateResident(resident);	
				}
			}
		}
	}
	
}
