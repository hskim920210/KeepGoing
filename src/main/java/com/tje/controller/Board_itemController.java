package com.tje.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tje.model.Board_Item;
import com.tje.model.Comment;
import com.tje.model.DetailBoardItemView;
import com.tje.model.LikeAndDislike;
import com.tje.model.Member;
import com.tje.page.Criteria;
import com.tje.page.PageMaker;
import com.tje.service.CommentAddService;
import com.tje.service.CommentDeleteService;
import com.tje.service.CommentSelectService;
import com.tje.service.ItemAddService;
import com.tje.service.ItemViewCntUpdateService;
import com.tje.service.ItemViewService;
import com.tje.service.LikeAndDislikeService;
import com.tje.service.SimpleBoardItemListCountCriteriaService;
import com.tje.service.SimpleBoardItemListCriteriaService;

@Controller
public class Board_itemController {
	
//	@Autowired
//	private AllItemListService ilService;
	@Autowired
	private SimpleBoardItemListCriteriaService sbilcService;
	@Autowired
	private SimpleBoardItemListCountCriteriaService sbilccService;
	@Autowired
	private ItemAddService aiService;
	@Autowired
	private ItemViewService ivService;
	@Autowired
	private ItemViewCntUpdateService ivcuService;
	@Autowired
	private CommentAddService caService;
	@Autowired
	private CommentSelectService csSercie;
	@Autowired
	private CommentDeleteService cdService;
	@Autowired
	private LikeAndDislikeService ladService;
	
	@RequestMapping(value = {"/item","/item/{curPageNo}"})
	public String Item(Model model,
			Criteria criteria,
			@PathVariable(value="curPageNo", required = false) Integer curPageNo) {
		
		System.out.println(criteria.toString());
		
		if(curPageNo==null)
			curPageNo=1;
		
		// 현재 페이지
		criteria.setPage(curPageNo);
		// 페이지 당 게시물 갯수
		criteria.setPerPageNum(6);
		model.addAttribute("item_list", sbilcService.service(criteria));
		PageMaker pageMaker=new PageMaker();
		
		
		pageMaker.setCri(criteria);
		// 게시물 전체 갯수
		pageMaker.setTotalCount((int)sbilccService.service());
		System.out.println(pageMaker.toString());
		
		model.addAttribute("curPageNo", curPageNo);
		model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		
//		List<SimpleBoardItemView> result =  (List<SimpleBoardItemView>) ilService.service();
//		model.addAttribute("item_list", result);
		return "item";
	}
	
	@GetMapping("/add_item")
	public String add_item() {
		return "add_item";
	}
	
	@PostMapping(value = "/add_item", produces = "application/text; charset=utf8")
	@ResponseBody
	public String add_item(HttpServletRequest request) {
		String dirPath=request.getSession().getServletContext().getRealPath("/resources/images");
		System.out.println(dirPath);
		File dir=new File(dirPath);
		
		if(!dir.exists())
			dir.mkdirs();
		
		int size=10*1024*1024;
		
		MultipartRequest multipartRequest=null;
		int result=0;
		
		try {
			multipartRequest=new MultipartRequest(request, dirPath, size, "utf-8", new DefaultFileRenamePolicy());
			
			String member_id=multipartRequest.getParameter("member_id");
			String title=multipartRequest.getParameter("title");
			String content=multipartRequest.getParameter("content");
			String strCategory=multipartRequest.getParameter("category");
			int category=Integer.parseInt(strCategory);
			int number=Integer.parseInt(multipartRequest.getParameter("number"));
			String price=multipartRequest.getParameter("price");
			String imageName=multipartRequest.getFilesystemName("image");
			String orginName=multipartRequest.getOriginalFileName("image");
			
			Board_Item item=new Board_Item(0, 3, category, title, content, member_id, number, price, imageName, 0, null);
			
			result=(int) aiService.service(item);
			System.out.println(result);
			
			if(result==1)
				return "상품 추가가 완료되었습니다.";
			
		} catch (Exception e) {
		}
		
		return "상품 추가 과정에서 문제가 발생하였습니다.";
	}
	
	@GetMapping("/item_view/{board_id}")
	public String item_view(Model model, 
			@PathVariable(value = "board_id") Integer board_id,
			HttpSession session) {
		
		DetailBoardItemView item=new DetailBoardItemView();
		item.setBoard_id(board_id);
		
		Comment comment=new Comment();
		comment.setBoard_id(board_id);
		comment.setTopic(3);
		
		// 로그인 여부에 따른 버튼 상태
		Member login_member=(Member) session.getAttribute("login_member");
		if(login_member==null)
			model.addAttribute("btn_status", 0);
		else {
			// 로그인 한 상태에서 게시물에 좋아요 or 싫어요를 눌렀었는지 체크
			String member_id=login_member.getMember_id();
			
			LikeAndDislike lad=new LikeAndDislike();
			lad.setMember_id(member_id);
			lad.setBoard_id(board_id);
			lad.setTopic(3);
			
			LikeAndDislike result=(LikeAndDislike) ladService.selectOne(lad);
			if(result==null)
				model.addAttribute("btn_status", 0);
			else
				model.addAttribute("btn_status", result.getIs_like());
		}
		
		
		if((int)ivcuService.service(item)!=1)
			return "redirect:error/item_view";
		
		model.addAttribute("searchedItem", ivService.service(item));
		model.addAttribute("commentList", csSercie.service(comment));
		
		return "item_view";
	}
	
	@PostMapping(value = "/comment_add")
	@ResponseBody
	public Comment comment_add(
			Comment comment,
			Model model,
			HttpServletResponse response) {
		
		int r=(int) caService.service(comment);
		comment.setComment_id(r);
		
		response.setContentType("application/json; charset=utf-8");
		
		if(r!=0) {
			return comment;
		}
		
		return null;
	}
	
	@PostMapping(value = "/comment_delete", produces = "application/text; charset=utf8")
	@ResponseBody
	public String comment_delete(@RequestParam("comment_id") int comment_id) {
		
		Comment comment=new Comment();
		comment.setComment_id(comment_id);
		
		int r=(int) cdService.service(comment);
		
		if(r==1) {
			return "댓글 삭제을 완료했습니다.";
		}
		
		return "댓글 삭제를 실패했습니다.";
	}
	
	@PostMapping(value = "/like_and_dislike", produces = "application/text; charset=utf8")
	@ResponseBody
	public String like_and_dislike(
			@RequestBody Map<String, Object> map,
			HttpSession session,
			Model model) {
		
		Member login_member=(Member) session.getAttribute("login_member");
		
		LikeAndDislike lad=new LikeAndDislike();
		lad.setMember_id(login_member.getMember_id());
		lad.setBoard_id((int) map.get("board_id"));
		lad.setTopic((int) map.get("topic"));
		
		int status=(int) map.get("status");
		
		int r=0;
		
		switch (status) {
		case 1:	
			lad.setIs_like(1);
			r=(int) ladService.update(lad);
			break;
		case 2:	
			lad.setIs_like(1);
			r=(int) ladService.insert(lad);
			break;
		case 3:	
			r=(int) ladService.delete(lad);
			break;
		case 4:	
			lad.setIs_like(2);
			r=(int) ladService.update(lad);
			break;
		case 5:	
			lad.setIs_like(2);
			r=(int) ladService.insert(lad);
			break;
		case 6:	
			r=(int) ladService.delete(lad);
			break;
		default:
			break;
		}
		
		if(r!=0) {
			return "success";
		}
		
		return "fail";
	}
}
