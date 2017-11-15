function ajaxRequest(type,url,pms,func){
			//type  ����ʽ
			//url  servlet ��ַ  get����ʽ+����
			//pms   �������    post
			//����
	
			//����ajax����
			var request;
			
			if (window.XMLHttpRequest) {
				request = new XMLHttpRequest();
			}else if(window.ActiveXObject){
				request = new ActiveXObject("Msxml2.XMLHTTP"); 
			}
			
			//������   get--- post
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
					var result = request.responseText;  //�õ��������˷��ص�����
					func(result);
				}
			};
			
			if(type == "get"){
				request.send(null);  //�����дnull,����ᱨ��
			}else{
				request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				request.send(pms);  //�����дnull,����ᱨ��
			}
		}