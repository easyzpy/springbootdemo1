package com.springboot.controller;

import com.springboot.dao.UserRepository;
import com.springboot.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;

@Controller
@RequestMapping(value = "/")
public class UserController {

    @Value(value = "${book.author}")
    private String bookAuthor;
    @Value("${book.name}")
    private String bookName;
    @Value("${book.pinyin}")
    private String bookPinYin;

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);

    @RequestMapping
    public String index1(){
        return index();
    }
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }
    @RequestMapping(value="/hello")
    public String thymeleaf(HttpServletRequest request, ModelMap model){
        String name = request.getParameter("name");
        LOGGER.error(name);
        model.put("name", name);

        return "hello";
    }
    @RequestMapping(value = "/show")
    @ResponseBody
    public User show(@RequestParam(value = "name")String name){
        User userByName = userRepository.findUserByName(name);

        return userByName;
    }
    @RequestMapping(value="showConstant")
    @ResponseBody
    public String howConstant(){
        return "Hello Spring Boot! The BookName is "+bookName+";and Book Author is "+bookAuthor+";and Book PinYin is "+bookPinYin;
    }
}