package com.demoweb.controller;

import com.demoweb.common.Util;
import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardCommentDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.service.BoardService;
import com.demoweb.ui.ThePager;
import com.demoweb.view.DownloadView1;
import com.demoweb.websocket.DemoWebSocketHandler;
import jakarta.servlet.http.HttpServletRequest;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = {"/board"})
public class BoardController {

    @Setter(onMethod_ = {@Autowired})
    private BoardService boardService;

    @Setter(onMethod_ = {@Autowired})
    private DemoWebSocketHandler socketHandler;

    @Value("${upload.path}") // application.properties 파일의 upload.path 속성의 값 injection
    private String uploadPath;

    //	@GetMapping(path = {"/list"})
//	public String listAll(Model model) {
//		
//		List<BoardDto> boards = boardService.findAllBaord();
//		model.addAttribute("boardList", boards); // Model 타입 전달인자에 데이터 저장 -> View(JSP)로 전달
//		
//		return "board/list-without-page"; 	// /WEB-INF/views/ + board/list-without-page + .jsp
//	}
    @GetMapping(path = {"/list"})
    public String listRange(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                            HttpServletRequest req, Model model) {

        int pageSize = 3;        // 한 페이지에 표시하는 데이터 갯수
        int pagerSize = 3;        // 한 번에 표시되는 페이지 번호 갯수
        int dataCount = boardService.getBoardCount();    // 전체 데이터 갯수
        String uri = req.getRequestURI();
        String linkUrl = uri.substring(uri.lastIndexOf("/") + 1); // 페이지 번호를 클릭했을 때 요청을 보낼 URL ( 목록 페이지 경로 )
        String queryString = req.getQueryString();

        int start = pageSize * (pageNo - 1) + 1; // 현재 페이지의 첫번째 데이터 행 번호

        // ThePager pager = new ThePager(dataCount, page, pageSize, pagerSize, linkUrl);
        ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, linkUrl, queryString);

        List<BoardDto> boards = boardService.findBaordByRange2(pageNo - 1, pageSize);

        model.addAttribute("boardList", boards); // Model 타입 전달인자에 데이터 저장 -> View(JSP)로 전달
        model.addAttribute("pager", pager);
        model.addAttribute("pageNo", pageNo);

        return "board/list";    // /WEB-INF/views/ + board/list + .jsp
    }

    @GetMapping(path = {"/write"})
    public String writeForm() {

        return "board/write";
    }

    @PostMapping(path = {"/write"})
    public String write(@ModelAttribute("board") BoardDto board,
                        MultipartFile attach, // <input type="file"의 데이터를 담은 변수
                        HttpServletRequest req) {

        // 데이터 읽기 : 컨트롤러 메서드의 전달인자를 통해 자동으로 데이터 읽기 실행
        // 데이터 처리 : 서비스 호출
        ArrayList<BoardAttachDto> attachments = new ArrayList<>();
        if (!attach.isEmpty() && attach.getOriginalFilename().length() > 0) {
            BoardAttachDto attachment = new BoardAttachDto();
            try {
                String userFileName = attach.getOriginalFilename();
                String savedFileName = Util.makeUniqueFileName(userFileName);

//				String dir = req.getServletContext().getRealPath("/board-attachments");
//				attach.transferTo(new File(dir, savedFileName)); // 파일 저장

                attach.transferTo(new File(uploadPath, savedFileName)); // 파일 저장

                attachment.setUserFileName(userFileName);
                attachment.setSavedFileName(savedFileName);
                attachments.add(attachment);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        board.setAttachments(attachments);

        try {
            boardService.writeBoard(board);
        } catch (Exception ex) {
            System.out.println("글쓰기 실패");
        }

        sendMessages();

        // 이동
        return "redirect:list";
    }

    @GetMapping(path = {"/detail"}) // 주소?d1=v1&d2=v2
    public String detailWithQueryString(
            @RequestParam(value = "boardno", required = false) Integer boardNo,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, Model model) {


        if (boardNo == null) { // 요청 데이터의 값이 없는 경우
            return "redirect:list";
        }

        BoardDto board = boardService.findBoardByBoardNo(boardNo);

        model.addAttribute("board", board);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("enter", "\n");

        return "board/detail";
    }

    @GetMapping(path = {"/detail/{boardNo}"}) // 주소/data
    public String detailWithPathVariable(@PathVariable("boardNo") Integer boardNo, Model model) {

        BoardDto board = boardService.findBoardByBoardNo(boardNo);
        model.addAttribute("board", board);

        return "board/detail";
    }

    @GetMapping(path = {"/download"})
    public View downloadWithQueryString(@RequestParam("attachno") int attachNo, Model model) {

        BoardAttachDto boardAttach = boardService.findBoardAttachByAttachNo(attachNo);

        model.addAttribute("attach", boardAttach); // View에서 사용하도록 데이터 전달
        model.addAttribute("uploadPath", uploadPath);

        // 다운로드 처리 -> 사용자 정의 View 사용
        return new DownloadView1();
        // return new DownloadView2();
    }

    @GetMapping(path = {"/delete"})
    public String delete(int boardNo, @RequestParam(defaultValue = "-1") int pageNo) {

        if (pageNo == -1) {
            return "redirect:list";
        }

        boardService.deleteBoard(boardNo);

        return String.format("redirect:list?pageNo=%d", pageNo);

    }

    @GetMapping(path = {"/edit"})
    public String showEditForm(int boardNo, @RequestParam(defaultValue = "-1") int pageNo, Model model) {

        if (pageNo == -1) {
            return "redirect:list";
        }

        BoardDto board = boardService.findBoardByBoardNo(boardNo);
        model.addAttribute("board", board);
        model.addAttribute("pageNo", pageNo);

        return "board/edit";
    }

    @PostMapping(path = {"/edit"})
    public String editBoard(BoardDto board, MultipartFile attach, HttpServletRequest req,
                            @RequestParam(required = false) Integer pageNo) {

        if (board.getBoardNo() == 0 || pageNo == null) {
            return "redirect:list";
        }

        if (attach != null && attach.getSize() > 0) {
            BoardAttachDto attachment = new BoardAttachDto();
            ArrayList<BoardAttachDto> attachments = new ArrayList<>();
            try {
                String dir = req.getServletContext().getRealPath("/board-attachments");
                String userFileName = attach.getOriginalFilename();
                String savedFileName = Util.makeUniqueFileName(userFileName);
                attach.transferTo(new File(dir, savedFileName)); // 파일 저장

                attachment.setBoardNo(board.getBoardNo());
                attachment.setUserFileName(userFileName);
                attachment.setSavedFileName(savedFileName);
                attachments.add(attachment);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            board.setAttachments(attachments);
        }

        try {
            boardService.modifyBoard(board);
        } catch (Exception ex) {
            ex.printStackTrace();
            return String.format("redirect:edit?boardNo=%d&pageNo=%d", board.getBoardNo(), pageNo);
        }

        return String.format("redirect:detail?boardno=%d&pageNo=%d", board.getBoardNo(), pageNo);
    }

    @GetMapping(path = {"/delete-attach"})
    @ResponseBody
    public String deleteAttach(@RequestParam(required = false) Integer attachNo, HttpServletRequest req) {

        if (attachNo == null) {
            return "Invalid attachNo";
        }
        BoardAttachDto attach = boardService.findBoardAttachByAttachNo(attachNo);
        String dirPath = req.getServletContext().getRealPath("/board-attachments");
        File file = new File(dirPath, attach.getSavedFileName());
        if (file.exists()) {
            file.delete();
        }
        boardService.deleteBoardAttach(attachNo);

        return "success";
    }


    @GetMapping(path = {"/list-comment"})
    public String listComment(int boardNo, Model model) {

        List<BoardCommentDto> comments = boardService.findBoardCommentsByBoardNo(boardNo);
        model.addAttribute("comments", comments);
        model.addAttribute("enter", "\n");

        return "board/comment-list";
    }

    public void sendMessages() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String message = "WebSocket event - " + sdf.format(new Date());
        // String message = "WebSocket event - " + Math.ceil(Math.random() * 100);
        try {
            // socketHandler.sendMessageToAll(message);
            socketHandler.sendMessageToSomeone(message, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(path = { "/show-img/{attachNo}"})
    public String showImage(@PathVariable("attachNo") Integer attachNo, Model model)  {
        BoardAttachDto boardAttach = boardService.findBoardAttachByAttachNo(attachNo);
        model.addAttribute("attach", boardAttach);

        return "board/show-img";
    }
    @GetMapping(path = {"/download-img/{fileName}"})
    public ResponseEntity<Resource> showImageFile(@PathVariable("fileName")String fileName) {
        try {
            Path file = Paths.get(uploadPath).resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }


}













