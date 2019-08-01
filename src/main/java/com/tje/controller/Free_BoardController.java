package com.tje.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tje.page.*;
import com.tje.model.*;
import com.tje.service.*;

@Controller
public class Free_BoardController {
	
	
	@Autowired
	private DetailBoardFreeView_DeleteService dbfv_dService; //자유게시판 삭제 서비스
	@Autowired
	private DetailBoardFreeViewService dbfvService; // 자유게시판 상세내역 서비스
	@Autowired
	private DetailBoardFreeView_UpdateService dbfv_uService; // 자유게시판 수정 서비스
	@Autowired
	private Board_freeService b_fService; // 자유게시판 조회 서비스
	@Autowired
	private SimpleBoardFree_ListCountCriteriaService sbf_lccService; //	자유게시판 첫화면 _ 목록 개수 기준 서비스
	@Autowired
	private SimpleBoardFree_ListCriteriaService sbf_lcService; // 자유게시판 첫화면 _리스트 기준 서비스
	@Autowired
	private CommentAddService caService; // 댓글 등록 서비스
	@Autowired
	private CommentSelectService csSercie; // 댓글 조회 서비스
	@Autowired
	private CommentDeleteService cdService; // 댓글 삭제 서비스
	@Autowired
	private FreeViewCnt_UpdateService fvcuService; // 댓글 카운트 서비스/////////// 수정해얗 
	@Autowired
	private LikeAndDislikeService ladService; // 좋아요 싫어요 
	
	

	
	////////////////////// 게시글 등록////////////////////
		@GetMapping("/add_free")
		public String Add_free() {
			return "add_free";
		}
		
		@PostMapping("/add_free")
		public String Add_free(Board_Free board_Free ,Model model ) {
			
			
			Integer board_id = (Integer)b_fService.service(board_Free);
			DetailBoardFreeView dbfv = new DetailBoardFreeView();
			dbfv.setBoard_id(board_id);
			if(board_id != null) {
				model.addAttribute("searchedFree", (DetailBoardFreeView)dbfvService.service(dbfv));
				return "free_view";
			}
			
			
			return "글 등록에 실패하였습니다";
			
			
		}
	////////////////////// 게시글 등록////////////////////
//		
//		
//		

	////////////////////// 게시글 수정////////////////////
	
	// 수정 하기전에 기존의 작성한 값을 불러온다.
	@GetMapping("/update_free/{board_id}")
	public String Update_free(Model model, @PathVariable(value = "board_id") Integer board_id) {
		DetailBoardFreeView free=new DetailBoardFreeView();
		free.setBoard_id(board_id);
		System.out.println(free.getBoard_id());
		
		model.addAttribute("searchedFree", (DetailBoardFreeView)dbfvService.service(free));
		return "update_free";
	}
	
	@PostMapping("/update_free/{board_id}")
	public String Update_freePost(DetailBoardFreeView detailBoardFreeView, Model model, @PathVariable(value = "board_id") Integer board_id) {
		Integer result = (Integer)dbfv_uService.service(detailBoardFreeView);
		if (result != null) {
			model.addAttribute("resultMsg", "수정 완료");
			return "update_free_view";
		}
		model.addAttribute("resultMsg", "수정 실패");
		return "update_free_view";
	}
	////////////////////// 게시글 수정////////////////////
//	
//	
//	
	////////////////////// 게시글 삭제////////////////////
	@GetMapping("/delete_free/{board_id}")
	public String Delete_free(DetailBoardFreeView detailBoardFreeView, @PathVariable(value = "board_id") Integer board_id, Model model) {
		System.out.println(detailBoardFreeView.getBoard_id());
		int r=(int) dbfv_dService.service(detailBoardFreeView);
		if(r==1) {
			model.addAttribute("resultMsg", "삭제 성공");
			return "delete_free";
		}
		
		model.addAttribute("resultMsg", "삭제 실패");
		return "delete_free";

	}
	//////////////////////게시글 삭제////////////////////
//	
//	
//	
	//////////////////////페이징 처리////////////////////
	
	@RequestMapping(value = {"/free","/free/{curPageNo}"})
	public String free(Model model,Criteria criteria,
			@PathVariable(value="curPageNo", required = false) Integer curPageNo) {


		if(curPageNo==null)
			curPageNo=1;
		
		criteria.setPage(curPageNo);
		criteria.setPerPageNum(16);
		model.addAttribute("free_list", sbf_lcService.service(criteria));
		
		PageMaker pageMaker=new PageMaker();
		
		pageMaker.setCri(criteria);
		pageMaker.setTotalCount((int)sbf_lccService.service());
		System.out.println(pageMaker.toString());
		
		model.addAttribute("curPageNo", curPageNo);	 // 기준
		model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		

		return "free";
	}
	//////////////////////페이징 처리////////////////////
//	
//	
//			
	/////////////////////자유게시판 조회 ////////////////	
	
	
	
	@GetMapping("/free_view/{board_id}")
	public String free_view(Model model, 
			@PathVariable(value = "board_id") Integer board_id,
			HttpSession session) {
		
		DetailBoardFreeView free =new DetailBoardFreeView();
		free.setBoard_id(board_id);
		
		model.addAttribute("searchedFree", (DetailBoardFreeView)dbfvService.service(free));
		
		Comment comment=new Comment();
		comment.setBoard_id(board_id);
		comment.setTopic(2);
		
		/////////// 좋아요 싫어요
		Member login_member=(Member) session.getAttribute("login_member");
		if(login_member==null)
			model.addAttribute("btn_status", 0);
		else {
			String member_id=login_member.getMember_id();
			
			LikeAndDislike lad=new LikeAndDislike();
			lad.setMember_id(member_id);
			lad.setBoard_id(board_id);
			lad.setTopic(2);
			
			LikeAndDislike result=(LikeAndDislike) ladService.selectOne(lad);
			if(result==null)
				model.addAttribute("btn_status", 0);
			else
				model.addAttribute("btn_status", result.getIs_like());
		}
		
		model.addAttribute("commentList", csSercie.service(comment));
		
		return "free_view";		

		}
		/////////////////////자유게시판 조회 ////////////////	
	


}
