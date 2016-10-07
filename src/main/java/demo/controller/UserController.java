package demo.controller;

import demo.dto.CatalogDTO;
import demo.exceptions.NoSuchCatalogException;
import demo.services.CatalogManager;
import demo.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Test on 9/16/2016.
 */

@Controller
@RequestMapping("/admin")
public class UserController {

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

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<CatalogDTO> catalogDTOs = catalogManager.getAllCatalogs();
            modelAndView.addObject("catalogs", catalogDTOs);
        } catch (NoSuchCatalogException e) {
            modelAndView.addObject("noCatalogs", "No catalogs yet.");
            e.printStackTrace();
        } finally {
            modelAndView.setViewName("home");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/create_catalog", method = RequestMethod.GET)
    public String createCatalog(){
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView uploadImage(@RequestParam("description") String description,
                       @RequestParam("url") String url,
                       @RequestParam("image") MultipartFile image) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/home");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authName = auth.getName();
        try {
            catalogManager.addCatalog(authName,
                    url,
                    description,
                    image.getBytes());
            return modelAndView;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/update_user", method = RequestMethod.GET)
    public ModelAndView signup() {
        return new ModelAndView("update_user");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String receivedUsername = request.getParameter(USERNAME);
        String receivedName = request.getParameter(NAME);
        String receivedPassword = request.getParameter(PASSWORD);
        return new ModelAndView("redirect:/admin/home");

    }


    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void downloadImage2(@RequestParam("id") String id,
                               HttpServletResponse response) throws IOException {

        handleDownload(id, response);
    }

    private void handleDownload(String id, HttpServletResponse response) throws IOException {
        CatalogDTO catalogDTO = catalogManager.getCatalogById(Long.parseLong(id));
        response.setContentLength(catalogDTO.getImage().length);

        OutputStream outputStream = response.getOutputStream();
        outputStream.write(catalogDTO.getImage().length);
        outputStream.close();
    }
}
