package com.tje.webapp;

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

import com.tje.model.Board_Free;
import com.tje.model.DetailBoardFreeView;
import com.tje.model.SimpleBoardFreeView;
import com.tje.service.AllItemListService;
import com.tje.service.Board_freeService;
import com.tje.service.Board_freeViewService;
import com.tje.service.CommentAddService;
import com.tje.service.CommentDeleteService;
import com.tje.service.CommentSelectService;
import com.tje.service.DetailBoardFreeViewService;
import com.tje.service.DetailBoardFreeView_DeleteService;
import com.tje.service.DetailBoardFreeView_UpdateService;
import com.tje.service.ItemAddService;
import com.tje.service.ItemViewCntUpdateService;
import com.tje.service.ItemViewService;
import com.tje.service.LikeAndDislikeService;
import com.tje.service.SimpleBoardFreeViewSelectByDateDescService;
import com.tje.service.SimpleBoardItemListCountCriteriaService;
import com.tje.service.SimpleBoardItemListCriteriaService;


@Controller
public class HomeController {
	@Autowired
	private AllItemListService ilService;
	@Autowired
	private SimpleBoardFreeViewSelectByDateDescService sbfvsbddService;
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
	private Board_freeService b_fService;
	@Autowired
	private Board_freeViewService b_fvService;
	@Autowired
	private DetailBoardFreeViewService dbfvService;
	@Autowired
	private DetailBoardFreeView_UpdateService dbfv_uService;
	@Autowired
	private CommentDeleteService cdService;
	@Autowired
	private DetailBoardFreeView_DeleteService dbfv_dService;
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

	////////////////////////////////자유게시판
	
	
	
	
	
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
