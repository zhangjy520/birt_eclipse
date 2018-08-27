package com.bmsmart.mes.mesDemo.domain.vo.response;

public class Pager {
	private  Integer pagesize = 10 ;
	private Integer pageno =1 ;
	private Integer totalRowcnt;
	private Integer currentPage;
	
	
	public Pager(){
		 
	}
	
	public Pager(Integer pageno ,Integer pagesize){
		 this.pageno = pageno;
		 this.pagesize = pagesize;
	}
	
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getPageno() {
		return pageno;
	}
	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}
	public Integer getTotalRowcnt() {
		return totalRowcnt;
	}
	public void setTotalRowcnt(Integer totalRowcnt) {
		this.totalRowcnt = totalRowcnt;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	
	
	
}
