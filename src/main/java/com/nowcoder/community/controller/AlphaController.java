package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author tlbstart
 * @create 2020-07-12-14:00
 */
@Controller  //bean上有这个注解才会被扫描，此外还有Service、Component、Repository
@RequestMapping("/alpha")//给这个类取访问的名
public class AlphaController {

    @Autowired
    private AlphaService alphaService;
    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/hello")
    @ResponseBody//声明返回给浏览器的是字符串，否则默认为网页
    public String sayHello(){
        return  "Hello Spring Boot";
    }
//    @RequestMapping("/data")
//    @ResponseBody//声明返回给浏览器的是字符串，否则默认为网页
//    public String getdata(){
//        return  alphaService.find;
//    }
    @RequestMapping("/http")
    public  void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());//获取请求方式
        System.out.println(request.getServletPath());//获取请求路径
        Enumeration<String> enumeration=request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+":"+value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter writer = response.getWriter();
                ){
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //GET请求  明面上传参数，且数量有限
    //获取参数的两种方式
    //  /students?current=1&limit=20  当前页数，当前显示多少条数据
    @RequestMapping(path = "/students",method= RequestMethod.GET)//请求方式指定为get
    @ResponseBody
    public String getStudents(
            //获取请求中的参数current和limit
            @RequestParam(name="current",required =false, defaultValue ="1" ) int current,//required可以不传，有默认值
            @RequestParam(name="limit",required =false, defaultValue ="10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return  "some students";
    }

    //  /student/123
    @RequestMapping(path = "/student/{id}",method= RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){  //注解路径变量获取id
        System.out.println(id);
        return "a student";
    }

    //POST请求  向服务器提交数据
    @RequestMapping(path = "/student",method= RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){//声明参数只要与表单中一致就能传过来
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //服务器响应html数据
    @RequestMapping(path = "/teacher",method= RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age","30");
        //设置模板的路径和名字,模板放在templates目录下
        mav.setViewName("/demo/view");  //默认为view.html
        return mav;
    }

    @RequestMapping(path = "/school",method= RequestMethod.GET)
    public String getSchool(Model model){  //返回string指的返回view路径
        model.addAttribute("name","北京大学");
        model.addAttribute("age","80");
        return "/demo/view";
    }

    // 响应JSON数据（异步请求）
    //Java对象->JSON对象->JS对象
    @RequestMapping(path = "/emp",method= RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp=new HashMap<>();
        emp.put("name","张三");
        emp.put("age","23");
        return emp;
    }
    //返回多个员工
    @RequestMapping(path = "/emps",method= RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> emp1=new HashMap<>();
        emp1.put("name","张三");
        emp1.put("age","23");
        emp1.put("salary","8000.00");
        list.add(emp1);

        emp1=new HashMap<>();
        emp1.put("name","李四");
        emp1.put("age","33");
        emp1.put("salary","9000.00");
        list.add(emp1);

        emp1=new HashMap<>();
        emp1.put("name","王五");
        emp1.put("age","53");
        emp1.put("salary","10000.00");
        list.add(emp1);

        return list;
    }
}
