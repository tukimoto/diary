package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainContoroller {
	@Autowired
	UserDataRepository repository;
	
	@Autowired
	DiaryRepository  diaryrepository;
	
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
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView loginGet(ModelAndView mv) {
		List<UserData> customers=repository.findAll();
		mv.addObject("customers",customers);
		mv.setViewName("login");
		return mv;
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView indexpost(@ModelAttribute("formModel")UserData UserData, ModelAndView mv) {
		repository.saveAndFlush(UserData);	
//		System.out.print(UserData.getId());
		return new ModelAndView("redirect:/mypage/"+UserData.getId());
	}
	@RequestMapping(value="/mypage/{id}",method=RequestMethod.GET)
	public ModelAndView myget(@PathVariable("id")long id, ModelAndView mv) {
		UserData UserId=repository.findById(id);
	 	List<Diary> diarylist=diaryrepository.findByUserdata(UserId);
		mv.addObject("diarylist",diarylist);
		mv.setViewName("mypage");
		return mv;
	}
	@RequestMapping(value="/mypage/{id}",method=RequestMethod.POST)
	public ModelAndView mypost(@PathVariable("id")int id,@RequestParam("text")String text,@RequestParam("data")String data,HttpServletRequest httpServletRequest,ModelAndView mv) {
		String username=httpServletRequest.getRemoteUser();
		 UserData userdata=repository.findById(id);
		 Diary d=new Diary();
		 d.setUserdata(userdata);
		 d.setDiary(text);
		 d.setData(data);
		diaryrepository.save(d);
			
		return new ModelAndView("redirect:/mypage/"+id);
	}
	
		
	
	
}
