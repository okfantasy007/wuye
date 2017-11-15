package util;

import java.util.ArrayList;
import java.util.List;
import com.sun.org.apache.bcel.internal.generic.NEW;

@SuppressWarnings("unused")
public class Page {
	private int num;//当前页
	private int rowCount;//总记录数
	private int pageSize = 10;//每页显示数据量
	private int navCount;//总页数
	private int first = 1;//首页
	private int last;//尾页
	private int pre;//上一页
	private int next;//下一页
	private int begin;//导航起始位置数
	private int end;//导航结束位置数
	@SuppressWarnings({ "unchecked" })
	public List list;
	
	//传进当前页  和  总记录数
	public Page(int pageNum,int count) {
		this.rowCount = count;
		//计算总页数
		this.navCount = (int) Math.ceil((rowCount*1.0/this.pageSize));
		//计算当前页
		this.num = Math.min(pageNum, this.navCount);
		this.num = Math.max(this.num, this.first);
		//首页尾页
		this.last = this.navCount;
		//上一页   下一页
		this.pre = Math.max(this.num-1, this.first);
		this.next = Math.min(this.num+1, this.navCount);
		//导航的起始位置和结束位置
		if (this.num - 5 <=0) {
			this.begin = 1 ;
		}else{
			this.begin = this.num - 5;
		}
		
		if (this.begin + 9 < this.navCount) {
			this.end = this.begin + 9;
		}else{
			this.end = this.navCount;
		}
		
		if (this.end>10 ) {
			this.begin = this.end - 9;
		}
		
		if (this.end == this.navCount) {
			this.begin = 1;
		}
	}

	@SuppressWarnings({ "unchecked" })
	public List getList() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public void setList(List list) {
		this.list = list;
	}

	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getRowCount() {
		return rowCount;
	}
	
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getNavCount() {
		return navCount;
	}
	
	public void setNavCount(int navCount) {
		this.navCount = navCount;
	}
	
	public int getFirst() {
		return first;
	}
	
	public void setFirst(int first) {
		this.first = first;
	}
	public int getLast() {
		return last;
	}
	
	public void setLast(int last) {
		this.last = last;
	}
	
	public int getPre() {
		return pre;
	}
	
	public void setPre(int pre) {
		this.pre = pre;
	}
	
	public int getNext() {
		return next;
	}
	
	public void setNext(int next) {
		this.next = next;
	}
	
	public int getBegin() {
		return begin;
	}
	
	public void setBegin(int begin) {
		this.begin = begin;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
}
