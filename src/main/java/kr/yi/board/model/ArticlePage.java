package kr.yi.board.model;

import java.util.List;

public class ArticlePage {
	private int total; //전체 게시판의 글의 갯수
	private int currentPage; //현재 페이지 번호
	private List<Article> content; //현재 화면에 display될 게시판 리스트 내용
	private int totalPage; //전체 페이지 수
	private int startPage; //시작 번호
	private int endPage; //끝 번호
	private int pageSize=10; //한 화면에 표시될 페이지 갯수
	
	//size : 한페이지에 display될 게시글의 갯수
	public ArticlePage(int total,int currentPage,int size, List<Article> content) {
		this.total=total;
		this.currentPage=currentPage;
		this.content=content;
		if(total==0) {
			totalPage=0;
			startPage=0;
			endPage=0;
		}else {
			//123 : 13페이지
			totalPage=total/size; //123/10=12
			if(total%size>0) { 
				totalPage++; //13
			}
			//현재 페이지 번호가 12일 경우, modVal 2가 됨
			int modVal=currentPage%pageSize;
			
			//12/10=1  ->  1*10=10 ->10+1=11
			startPage=currentPage/pageSize*pageSize+1;
			
			//currentPage=20, startPage=21
			if(modVal==0) {  //현 페이지의 제일 마지막 번호인가
				startPage-=pageSize; //시작페이지 11
			}
			
			//시작 페이지11 :끝 20
			endPage=startPage+(pageSize-1);
			
			//게시물의 갯수 :123 , 총 페이지 번호:13
			//start 11,endPage 20
			if(endPage>totalPage) {
				endPage=totalPage;
			}
		}
	}
	
	
	public int getTotal() {
		return total;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public List<Article> getContent() {
		return content;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	
	public boolean hasNoArticles() {
		return total==0;
	}
	
	public boolean hasArticles() {
		return total>0;
	}
	
	
	public int getTotalPage() {
		return totalPage;
	}


	@Override
	public String toString() {
		return "ArticlePage [total=" + total + ", currentPage=" + currentPage + ", content=" + content + ", totalPage="
				+ totalPage + ", startPage=" + startPage + ", endPage=" + endPage + ", pageSize=" + pageSize + "]";
	}
	
	
}
