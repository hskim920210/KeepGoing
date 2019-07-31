package com.tje.webapp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

import com.fasterxml.jackson.core.JsonParser;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tje.model.Board_Free;
import com.tje.model.Board_Item;
import com.tje.model.Board_Notice;
import com.tje.model.Comment;
import com.tje.model.DetailBoardFreeView;
import com.tje.model.DetailBoardItemView;
import com.tje.model.LikeAndDislike;
import com.tje.model.Member;
import com.tje.model.SimpleBoardFreeView;
import com.tje.model.SimpleBoardReviewView;
import com.tje.page.Criteria;
import com.tje.page.PageMaker;
import com.tje.service.AllItemListService;
import com.tje.service.Board_NoticeSelectAllByBoardIdDescService;
import com.tje.service.Board_freeService;
import com.tje.service.Board_freeViewService;
import com.tje.service.CommentAddService;
import com.tje.service.CommentDeleteService;
import com.tje.service.CommentSelectService;
import com.tje.service.DetailBoardFreeViewService;
import com.tje.service.DetailBoardFreeView_UpdateService;
import com.tje.service.ItemAddService;
import com.tje.service.ItemViewCntUpdateService;
import com.tje.service.ItemViewService;
import com.tje.service.LikeAndDislikeService;
import com.tje.service.SimpleBoardFreeViewSelectByDateDescService;
import com.tje.service.SimpleBoardItemListCountCriteriaService;
import com.tje.service.SimpleBoardItemListCriteriaService;
import com.tje.service.SimpleBoardReviewViewSelectByDateDescService;

@Controller
public class HomeController {
	@Autowired
	private AllItemListService ilService;
	@Autowired
	private SimpleBoardFreeViewSelectByDateDescService sbfvsbddService;
	@Autowired
	private SimpleBoardReviewViewSelectByDateDescService sbrvsbddService;
	@Autowired
	private Board_NoticeSelectAllByBoardIdDescService b_nsabdService;
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
	private Board_freeService b_fService;
	@Autowired
	private Board_freeViewService b_fvService;
	@Autowired
	private DetailBoardFreeViewService dbfvService;
	@Autowired
	private DetailBoardFreeView_UpdateService dbfvuService;
	@Autowired
	private CommentDeleteService cdService;
	@Autowired
	private LikeAndDislikeService ladService;
	
	@RequestMapping("/")
	public String home(HttpServletResponse res, HttpServletRequest req) {
		
		try {
			res.sendRedirect(req.getContextPath() + "/home");
		} catch (IOException e) {
		}
		return null;
	}
	
	
	@RequestMapping("/home")
	public String index() {
		return "home";
	}
	
	@RequestMapping("/cs")
	public String Cs() {
		return "cs";
	}	
	
	@RequestMapping("/notice")
	public String Notice(Model model) {
		List<Board_Notice> board_noticeList = (List<Board_Notice>)b_nsabdService.service();
		System.out.println(board_noticeList);
		model.addAttribute("board_noticeList", board_noticeList);
		return "notice";
	}	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Q&A 폼추가
	@RequestMapping("/qna")
	public String Qna(Model model) {
		List<SimpleBoardFreeView> simpleBoardFreeViewList = (List<SimpleBoardFreeView>)sbfvsbddService.service();
		model.addAttribute("simpleBoardFreeViewList", simpleBoardFreeViewList);
		return "qna";
	}
	
	@RequestMapping("/qna/write")
	public String QnaWrite(Model model) {
		return "add_qna";
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////
	@RequestMapping("/free")
	public String Free(Model model) {
		List<SimpleBoardFreeView> simpleBoardFreeViewList = (List<SimpleBoardFreeView>)sbfvsbddService.service();
		model.addAttribute("simpleBoardFreeViewList", simpleBoardFreeViewList);
		return "free";
	}
	
	@GetMapping("/free_view/{board_id}")
	public String free_view(Model model, 
			@PathVariable(value = "board_id") Integer board_id) {
		
		DetailBoardFreeView free=new DetailBoardFreeView();
		free.setBoard_id(board_id);
		System.out.println(free.getBoard_id());
		
		model.addAttribute("searchedFree", (DetailBoardFreeView)dbfvService.service(free));
		
		return "free_view";
	}
	
	
	@GetMapping("/add_free")
	public String Add_free() {
		return "add_free";
	}
	
	@PostMapping("/add_free")
	public String Add_free(Board_Free board_Free) {
		
		
		int r=(int) b_fService.service(board_Free);
		if(r==1) {
			return "free_view";
		}
		
		return "글 등록에 실패하였습니다";
		
		
	}
	
	@GetMapping("/update_free/{board_id}")
	public String Update_free(Model model, @PathVariable(value = "board_id") Integer board_id) {
		DetailBoardFreeView free=new DetailBoardFreeView();
		free.setBoard_id(board_id);
		System.out.println(free.getBoard_id());
		
		model.addAttribute("searchedFree", (DetailBoardFreeView)dbfvService.service(free));
		
		return "update_free";
	}
	
	@PostMapping("/update_free/{board_id}")
	public String Update_free(DetailBoardFreeView detailBoardFreeView) {
		
		int r=(int) dbfvuService.service(detailBoardFreeView);
		if(r==1) {
			return "free";
		}
		
		return "글 등록에 실패하였습니다";

	}

	///////////////////////////////
	
	@RequestMapping(value = {"/item","/item/{curPageNo}"})
	public String Item(Model model,Criteria criteria,
			@PathVariable(value="curPageNo", required = false) Integer curPageNo) {
		
		System.out.println(criteria.toString());
		
		if(curPageNo==null)
			curPageNo=1;
		
		criteria.setPage(curPageNo);
		criteria.setPerPageNum(6);
		model.addAttribute("item_list", sbilcService.service(criteria));
		PageMaker pageMaker=new PageMaker();
		
		pageMaker.setCri(criteria);
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
		comment.setTopic(5);
		
		
		Member login_member=(Member) session.getAttribute("login_member");
		if(login_member==null)
			model.addAttribute("btn_status", 0);
		else {
			String member_id=login_member.getMember_id();
			
			LikeAndDislike lad=new LikeAndDislike();
			lad.setMember_id(member_id);
			lad.setBoard_id(board_id);
			lad.setTopic(5);
			
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
	public String like_and_dislike(HttpSession session,
			@RequestBody Map<String, Object> map) {
		
		Member member=(Member) session.getAttribute("login_member");
		LikeAndDislike model=new LikeAndDislike();
		model.setMember_id( member.getMember_id() );
		model.setBoard_id( (int) map.get("board_id") );
		model.setTopic( (int) map.get("topic") );
		int status=(int) map.get("status");
		
		int r=0;
		
		switch (status) {
		case 1:
			model.setIs_like(1);
			r=(int) ladService.update(model);
			break;
		case 2:
			model.setIs_like(1);
			r=(int) ladService.insert(model);
			break;
		case 3:
			r=(int) ladService.delete(model);
			break;
		case 4:
			model.setIs_like(2);
			r=(int) ladService.update(model);
			break;
		case 5:
			model.setIs_like(2);
			r=(int) ladService.insert(model);
			break;
		case 6:
			r=(int) ladService.delete(model);
			break;
		default:
			break;
		}
		
		if(r!=0) {
			return "success";
		}
		
		return "fail";
	}
	
	@RequestMapping("/review")
	public String Review(Model model) {
		List<SimpleBoardReviewView> result = (List<SimpleBoardReviewView>)sbrvsbddService.service();
		model.addAttribute("simpleBoardReviewViewList", result);
		return "review";
	}
	
	@RequestMapping("/review/write")
	@GetMapping("/review/write")
	public String ReviewWrite(Model model) {
		List<SimpleBoardReviewView> result = (List<SimpleBoardReviewView>)sbrvsbddService.service();
		model.addAttribute("simpleBoardReviewViewList", result);
		return "reviewWrite";
	}
	
	@PostMapping("/review/write")
	public String ReviewWritePost(SimpleBoardReviewView sbrv) {
		System.out.println(sbrv.getTitle());
		System.out.println(sbrv.getContent());
		System.out.println(sbrv.getCategory());
		
		return "reviewWriteResult";
	}
	
	@RequestMapping("/cart")
	public String Cart() {
		return "cart";
	}
	
	@GetMapping("/regist")
	public String registGet(HttpSession session) {
		session.removeAttribute("login_sns_member");
		return "regist";
	}
	@GetMapping("/regist/provision")
	public String registProvision() {
		return "/terms/provision";
	}
	
	@GetMapping("/regist/finance")
	public String registFinance() {
		return "/terms/finance";
	}
	
	@GetMapping("/regist/individual")
	public String registIndividual() {
		return "/terms/individual";
	}
	
	@GetMapping("/regist/thirdparty")
	public String registThirdparty() {
		return "/terms/thirdparty";
	}
	
	@GetMapping("/regist/location")
	public String registLocation() {
		return "/terms/location";
	}
	

	@GetMapping("/regist/individual_option")
	public String registIndividual_option() {
		return "/terms/individual_option";
	}
}
