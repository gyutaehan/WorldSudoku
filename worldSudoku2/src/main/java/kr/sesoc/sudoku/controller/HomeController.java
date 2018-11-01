package kr.sesoc.sudoku.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewRequestBodyAdvice;

import kr.sesoc.sudoku.util.SudokuGenerator;
import kr.sesoc.sudoku.util.SudokuSolver;
import kr.sesoc.sudoku.vo.Board;
import kr.sesoc.sudoku.vo.Member;
import kr.sesoc.sudoku.vo.Record;

@Controller
public class HomeController {
	
	@Autowired
	SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String loginForm(Locale locale, Model model, Member member, HttpSession httpSession) {
		return "user/loginForm";
	}
	
	@RequestMapping(value = "/signupForm", method = RequestMethod.GET)
	public String signupForm(Locale locale, Model model) {
		
		return "user/signupForm";
	}
	
	@RequestMapping(value = "/userInfoForm", method = RequestMethod.GET)
	public String userInfoForm(Locale locale, Model model, HttpSession httpSession) {
		String userId = (String)httpSession.getAttribute("userId");
		Member user = sqlSession.selectOne("memberMapper.selectGetById", userId);
		model.addAttribute("user", user);
		return "user/userInfoForm";
	}
	
	@RequestMapping(value = "/sudokuForm", method = RequestMethod.GET)
	public String sudokuForm(Locale locale, Model model) {
		
		return "user/sudokuForm";
	}
	@RequestMapping(value = "/rankForm", method = RequestMethod.GET)
	public String rankForm(Locale locale, Model model, HttpSession httpSession) {
		String userId = (String) httpSession.getAttribute("userId");
		
		List<Record> recordList = sqlSession.selectList("recordMapper.getAllRecord");
		List<Member> memberList = sqlSession.selectList("memberMapper.selectAllMember");
		int index=0;
		for(Member m : memberList) {
			System.out.println(m.getUserId());
			if(m.getUserId().equals(userId)) {
				System.out.println(index);
				break;
			}
			index++;
		}
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("recordList", recordList);
		model.addAttribute("index", index);
		
		
		return "user/rankForm";
	}
	@RequestMapping(value = "/leagueForm", method = RequestMethod.GET)
	public String leagueForm(Locale locale, Model model, HttpSession httpSession) {
		String userId = (String) httpSession.getAttribute("userId");
		List<Board> boardList =  sqlSession.selectList("boardMapper.getAllBoard");
		model.addAttribute("boardList", boardList);
		
		return "sudoku/leagueForm";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Locale locale, Model model, Member member, HttpSession httpSession) {
		System.out.println(member);
		Member m = sqlSession.selectOne("memberMapper.login", member);
		if(m != null) {
			System.out.println(m);
			httpSession.setAttribute("userId", m.getUserId());
		}else {	
			return "loginForm";
		}
		return "redirect:/";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(Locale locale, Model model, Member member, HttpSession httpSession) {
		System.out.println(member);
		sqlSession.insert("memberMapper.signup", member);
		return "redirect:/";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model, Member member, HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/";
	}
	@RequestMapping(value = "/easy", method = RequestMethod.GET)
	public @ResponseBody int[][][] easy(Locale locale, Model model) {
		SudokuGenerator sg = new SudokuGenerator();
		SudokuSolver ss = new SudokuSolver();
		int[][] copyArray = new int[9][9];
		for(int i=0;i<1000;i++)
		{
			ss.reset(sg.nextBoard(35));
			for(int j=0; j<9; j++) {
				for(int z=0; z<9; z++) {
					copyArray[j][z] = ss.getBoard()[j][z];
				}
			}
			if(ss.solve())
			{
				break;
			}
		}
		int[][][] result = {copyArray, ss.getBoard()};
		//int[][][] result = {ss.getBoard(), ss.getBoard()};
		return result;
	}
	@RequestMapping(value = "/medium", method = RequestMethod.GET)
	public @ResponseBody int[][][] medium(Locale locale, Model model) {
		SudokuGenerator sg = new SudokuGenerator();
		SudokuSolver ss = new SudokuSolver();
		int[][] copyArray = new int[9][9];
		for(int i=0;i<2000;i++)
		{
			ss.reset(sg.nextBoard(47));
			for(int j=0; j<9; j++) {
				for(int z=0; z<9; z++) {
					copyArray[j][z] = ss.getBoard()[j][z];
				}
			}
			if(ss.solve())
			{
				break;
			}
		}
		int[][][] result = {copyArray, ss.getBoard()};
	    //int[][][] result = {ss.getBoard(), ss.getBoard()};
		return result;
	}
	@RequestMapping(value = "/difficult", method = RequestMethod.GET)
	public @ResponseBody int[][][] difficult(Locale locale, Model model) {
		SudokuGenerator sg = new SudokuGenerator();
		SudokuSolver ss = new SudokuSolver();
		int[][] copyArray = new int[9][9];
		for(int i=0;i<2000;i++)
		{
			ss.reset(sg.nextBoard(54));
			for(int j=0; j<9; j++) {
				for(int z=0; z<9; z++) {
					copyArray[j][z] = ss.getBoard()[j][z];
				}
			}
			if(ss.solve())
			{
				break;
			}
		}
		int[][][] result = {copyArray, ss.getBoard()};
	    //int[][][] result = {ss.getBoard(), ss.getBoard()};
		return result;
	}
	
	@RequestMapping(value = "/succSudoku", method = RequestMethod.POST)
	public @ResponseBody String succSudoku(Locale locale, Model model, Member member, HttpSession httpSession, Record record) 
	{
		int result = 0;
		String userId = (String)httpSession.getAttribute("userId");
		record.setUserId(userId);
		System.out.println(record);
		if(userId !=null) {
			result = sqlSession.update("memberMapper.succSudoku", userId);
			sqlSession.insert("recordMapper.writeRecord", record);
		}else {
			System.out.println("세션만료");
		}
		
		return String.valueOf(result);
	}
	@RequestMapping(value = "/writeBoardForm", method = RequestMethod.GET)
	public String writeBoardForm(Locale locale, Model model, Member member, HttpSession httpSession, Record record) 
	{
		
		return "sudoku/writeBoardForm";
	}
	@RequestMapping(value = "/writeBoard", method = RequestMethod.POST)
	public String writeBoard(Locale locale, Model model, Member member, HttpSession httpSession, Board board) 
	{
		String userId = (String)httpSession.getAttribute("userId");
		board.setUserId(userId);
		sqlSession.insert("boardMapper.writeBoard", board);
		return "redirect:leagueForm";
	}
	
	@RequestMapping(value = "/boardDetailForm", method = RequestMethod.GET)
	public String boardDetailForm(Locale locale, Model model, Member member, HttpSession httpSession, String boardSeq) 
	{
		Board board = sqlSession.selectOne("boardMapper.getOneBoard",boardSeq);
		model.addAttribute("board", board);
		return "sudoku/boardDetailForm";
	}
	
}
