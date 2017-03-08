package Servlet;

import Entities.Dynamic;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dream Sky on 2017/3/5.
 * 动态处理的Servlet
 */
@WebServlet("/DynamicServlet")
public class DynamicServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                /*从响应对象中获取输出流对象*/
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        switch (action) {
            case "LoadDynamic":
                System.out.println("LoadDynamic");
                LoadDynamic(request, response);
                break;
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /*加载动态*/
    private void LoadDynamic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json;charset=utf-8");
        List<Dynamic> dynamicList = new ArrayList<>();
        Dynamic dynamic = new Dynamic();
        dynamic.setAlbum_id(1231314);
        dynamic.setDynamic_id(23112412);
        dynamic.setDynamic_content("ljopasdjgklsdjlfsdg");
        dynamic.setLike(1231);
        dynamic.setUser_id("dasdada");
        dynamicList.add(dynamic);
        dynamicList.add(dynamic);
        Gson gson = new Gson();
        String json = gson.toJson(dynamicList);
        System.out.println(json);
        out.println(json);
        out.flush();
        out.close();
    }


    public void send(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        String val = request.getParameter("user");
        /*从响应对象中获取输出流对象*/
        PrintWriter out = response.getWriter();
        out.println("send");
        System.out.println(val);
        out.close();
    }
}
