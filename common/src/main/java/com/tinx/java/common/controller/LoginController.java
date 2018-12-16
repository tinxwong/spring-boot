package com.tinx.java.common.controller;

import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.entity.query.UserQuery;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.service.UserService;
import com.tinx.java.common.utils.ResultUtil;
import com.tinx.java.common.utils.ServletUtils;
import com.tinx.java.common.utils.VerifyCodeUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author tinx
 * @date 2018-11-4 20:47
 */
@Controller
@RequestMapping("/common/ver")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    WebApplicationContext applicationContext;

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    @ResponseBody
    public ModelAndView login() {
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        return view;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public BaseResponse doLogin(UserQuery query) {

        HttpSession session = ServletUtils.getSession();
        String verCode = (String) session.getAttribute(Helper.SESSION_LOIN_VERCODE);
        if (!verCode.equalsIgnoreCase(query.getCheckCode())) {
            return ResultUtil.makeBaseResponse(false, ResponseCode.LOGIN_VERCODE_ERROR);
        }
        User loginUser = userService.doLogin(query.getUserName(), new String(Base64.decodeBase64(query.getPwd())));
        if (loginUser != null) {
            ServletUtils.getSession().setAttribute(Helper.SESSION_USER, loginUser);
            return ResultUtil.makeBaseResponse(true, ResponseCode.SUCCESS);
        } else {
            return ResultUtil.makeBaseResponse(false, ResponseCode.USER_MSG_ERROR);
        }

    }

    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    public void authImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 删除以前的
        session.removeAttribute(Helper.SESSION_LOIN_VERCODE);
        session.removeAttribute(Helper.SESSION_CODE_TIME);
        session.setAttribute(Helper.SESSION_LOIN_VERCODE, verifyCode.toLowerCase());
        session.setAttribute(Helper.SESSION_CODE_TIME, LocalDateTime.now());
        // 生成图片
        int w = 100, h = 30;
        OutputStream out = response.getOutputStream();
        VerifyCodeUtils.outputImage(w, h, out, verifyCode);
    }


    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public ModelAndView logout() {
        HttpSession session = ServletUtils.getSession();
        session.removeAttribute(Helper.SESSION_LOIN_VERCODE);
        session.removeAttribute(Helper.SESSION_CODE_TIME);
        session.removeAttribute(Helper.SESSION_USER);
        session.invalidate();
        ModelAndView view = new ModelAndView();
        view.setViewName("/login");
        return view;
    }



    @GetMapping("/getAllUrl")
    @ResponseBody
    public List<String> getAllUrl() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<String> urlList = new ArrayList<>();
        for (RequestMappingInfo info : map.keySet()) {
            //获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            System.out.println("name:"+info.getName());
            for (String url : patterns) {
                System.out.println("url:"+url);
                urlList.add(url);
            }
        }
        return urlList;
    }
}
