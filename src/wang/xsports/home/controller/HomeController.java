package wang.xsports.home.controller;

import com.jfinal.core.Controller;

public class HomeController extends Controller{
    public void index() {
        render("/index/index.html");
    }

}
