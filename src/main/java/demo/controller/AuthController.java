package demo.controller;

import demo.services.CatalogManager;
import demo.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Test on 9/16/2016.
 */

@Controller
@RequestMapping("")
public class AuthController {

    public static final String USERNAME = "username";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";

    @Autowired
    UserManager userManager;

    @Autowired
    CatalogManager catalogManager;


    @Autowired
    Environment environment;

    @RequestMapping(value = "")
    public ModelAndView root() {
        userManager.initDefaults();
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(Authentication authentication) {
        if (authentication != null) {
            if (authentication.getAuthorities().iterator().next().toString().equals("ROLE_" + environment.getProperty("role_user")))
                return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("login_page");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        return new ModelAndView("create_user");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String register(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String receivedUsername = request.getParameter(USERNAME);
        String receivedName = request.getParameter(NAME);
        String receivedPassword = request.getParameter(PASSWORD);

        if (receivedUsername.isEmpty() || receivedName.isEmpty() || receivedPassword.isEmpty()) {
            redirectAttributes.addAttribute("error", "Please fill all fields!");
            return "redirect:" + request.getHeader("Referer");
        }
        userManager.addUser(receivedName, receivedUsername, receivedPassword, true, environment.getProperty("role_user"));
        return "redirect:/" + "login";
    }






}
