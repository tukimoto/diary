package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainContoroller {
	@Autowired
	UserDataRepository repository;
	
	@Autowired
	DiaryRepository  diaryrepository;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView indexGet(ModelAndView mv) {
		mv.setViewName("index");
		return mv;
	}
	@RequestMapping(value="/log",method=RequestMethod.GET)
	public ModelAndView logGet(ModelAndView mv) {
		mv.setViewName("log");
		return mv;
	}
	@RequestMapping(value="/log",method=RequestMethod.POST)
	public ModelAndView logPost(ModelAndView mv) {
		return new ModelAndView("redirect:/mypage");
	}
	
//	@RequestMapping(value="/login",method=RequestMethod.GET)
//	public ModelAndView loginGet(ModelAndView mv) {
//		List<UserData> customers=repository.findAll();
//		mv.addObject("customers",customers);
//		mv.setViewName("login");
//		return mv;
//	}
//	@RequestMapping(value="/login",method=RequestMethod.POST)
//	public ModelAndView indexpost(@ModelAttribute("formModel")UserData UserData, ModelAndView mv) {
//		repository.saveAndFlush(UserData);	
//		return new ModelAndView("redirect:/mypage/"+UserData.getId());
//	}
		@RequestMapping(value="/mypage",method=RequestMethod.GET)
	public ModelAndView myget(@PathVariable("id")long id, ModelAndView mv) {
		UserData UserId=repository.findById(id);
	 	List<Diary> diarylist=diaryrepository.findByUserdata(UserId);
		mv.addObject("diarylist",diarylist);
		mv.setViewName("mypage");
		return mv;
	}
		
	@RequestMapping(value="/mypage",method=RequestMethod.GET)
	public ModelAndView mypage(ModelAndView mv) {
		mv.setViewName("mypage");
		return mv;
	}
	@RequestMapping(value="/mypage",method=RequestMethod.POST)
	public ModelAndView mypost(@PathVariable("id")int id,@RequestParam("text")String text,@RequestParam("data")String data,HttpServletRequest httpServletRequest,ModelAndView mv) {
		String username=httpServletRequest.getRemoteUser();
		 UserData userdata=repository.findById(id);
		 Diary d=new Diary();
		 d.setUserdata(userdata);
		 d.setDiary(text);
		 d.setData(data);
		diaryrepository.save(d);
			
		return new ModelAndView("redirect:/mypage");
	}
	private final String VIEW_NAME="index";
	@GetMapping("cookie0")
	public String index(HttpServletResponse httpServletResponse) {
		httpServletResponse.addCookie(new Cookie("key1","hello"));
		return VIEW_NAME;
	}
	@GetMapping("cookies1")
	public String showCookies1(@CookieValue("key1")String cookieValue) {
		System.out.println(cookieValue);
		return VIEW_NAME;
	}
	@GetMapping("cookies2")
	public String showCookies2(@CookieValue(name="key1",required=false,defaultValue="default value1")String cookieValue) {
		System.out.println(cookieValue);
		return VIEW_NAME;
	}
	
	@RequestMapping(value="/seve",method=RequestMethod.GET)
	public String save() {
		session.setAttribute("data", "保存したいデータ");
		return "index";
	}
	@RequestMapping(value="/load",method=RequestMethod.GET)
	public String load() {
		String data =(String) session.getAttribute("data");
		System.out.print(data);
		session.invalidate();
		return "index";
	}
		
	
	
}
