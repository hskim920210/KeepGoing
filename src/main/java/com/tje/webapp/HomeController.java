package com.tje.webapp;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tje.model.Board_Item;
import com.tje.model.Board_Notice;
import com.tje.model.Board_Free;
import com.tje.model.SimpleBoardFreeView;
import com.tje.model.SimpleBoardItemView;
import com.tje.model.SimpleBoardReviewView;
import com.tje.model.DetailBoardFreeView;
import com.tje.page.Criteria;
import com.tje.page.PageMaker;
import com.tje.service.*;
import com.tje.service.DetailBoardFreeViewService;

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
	private Board_freeService b_fService;
	@Autowired
	private Board_freeViewService b_fvService;
	@Autowired
	private DetailBoardFreeViewService dbfvService;
	@Autowired
	private DetailBoardFreeView_UpdateService dbfvuService;
	
	
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
	
	@RequestMapping("/qna")
	public String Qna() {
		return "qna";
	}	
	
	
	//////////////////////////////////
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
			// TODO: handle exception
		}
		
		return "상품 추가 과정에서 문제가 발생하였습니다.";
	}
	
	@GetMapping("/item_view/{board_id}")
	public String item_view(Model model, 
			@PathVariable(value = "board_id") Integer board_id) {
		
		SimpleBoardItemView item=new SimpleBoardItemView();
		item.setBoard_id(board_id);
		
		model.addAttribute("searchedItem", ivService.service(item));
		
		return "item_view";
	}
	

	
	@RequestMapping("/review")
	public String Review(Model model) {
		List<SimpleBoardReviewView> result = (List<SimpleBoardReviewView>)sbrvsbddService.service();
		model.addAttribute("simpleBoardReviewViewList", result);
		return "review";
	}
	
	@RequestMapping("/review/write")
	public String ReviewWrite(Model model) {
		List<SimpleBoardReviewView> result = (List<SimpleBoardReviewView>)sbrvsbddService.service();
		model.addAttribute("simpleBoardReviewViewList", result);
		return "reviewWrite";
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
}
