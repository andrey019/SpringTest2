package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private AdvDAO advDAO;

	@RequestMapping("/")
	public ModelAndView listAdvs() {
		return new ModelAndView("index", "advs", advDAO.getAll(false));
	}

	@RequestMapping(value = "/add_page")
	public String addPage() {
		return "add_page";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam(value="pattern") String pattern) {
		return new ModelAndView("index", "advs", advDAO.list(pattern));
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value="id") long id, HttpServletResponse response) {
		if (advDAO.deleteToBacket(id)) {
			return "redirect:/";
		} else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
	}

	@RequestMapping("/image/{file_id}")
	public void getFile(HttpServletResponse response, @PathVariable("file_id") long fileId) {
		try {
			byte[] content = advDAO.getPhoto(fileId);
			response.setContentType("image/png");
			response.getOutputStream().write(content);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAdv(@RequestParam(value="name") String name,
						 @RequestParam(value="shortDesc") String shortDesc,
						 @RequestParam(value="longDesc", required=false) String longDesc,
						 @RequestParam(value="phone") String phone,
						 @RequestParam(value="price") double price,
						 @RequestParam(value="photo") MultipartFile photo,
						 HttpServletResponse response) {

		try {
			Advertisement adv = new Advertisement(
					name, shortDesc, longDesc, phone, price,
					photo.isEmpty() ? null : new Photo(photo.getOriginalFilename(), photo.getBytes())
			);
			advDAO.add(adv);
			return "redirect:/";
		} catch (IOException ex) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}
}