function ajaxRequest(type,url,pms,func){
			//type  请求方式
			//url  servlet 地址  get、方式+参数
			//pms   请求参数    post
			//函数
	
			//创建ajax对象
			var request;
			
			if (window.XMLHttpRequest) {
				request = new XMLHttpRequest();
			}else if(window.ActiveXObject){
				request = new ActiveXObject("Msxml2.XMLHTTP"); 
			}
			
			//打开连接   get--- post
			if (type == "get") {
				if(pms.length>0){
					
					request.open(type, (url+"?"+pms));
				}else{
					request.open(type, url);
				}
			}else if(type == "post"){
				request.open(type, url);
			}
			//alert(request.readyState);
		
			request.onreadystatechange = function(){
				if(request.readyState == 4){
					var result = request.responseText;  //得到服务器端返回的数据
					func(result);
				}
			};
			
			if(type == "get"){
				request.send(null);  //如果不写null,火狐会报错。
			}else{
				request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				request.send(pms);  //如果不写null,火狐会报错。
			}
		}