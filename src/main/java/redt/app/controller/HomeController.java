package redt.app.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import redt.app.form.PostForm;
import redt.app.model.Category;
import redt.app.model.Posts;
import redt.app.service.category.CategoryService;
import redt.app.service.posts.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
public class HomeController {
	@Autowired
	private IPostService postService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public String index(@RequestParam("q") Optional<String> q, @RequestParam("c") Optional<Category> c, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 2) Pageable pageable, Model model){
		Page<Posts> list = null;

		if (q.isPresent() && c.isPresent()) {
			list = postService.findAllByNameContainingAndCategory(q.get(), c.get(), pageable);
		} else if (q.isPresent() && !c.isPresent()){
			list = postService.findAllByNameContaining (q.get(), pageable);
		} else if (!q.isPresent() && c.isPresent()){
			list = postService.findAllByCategory (c.get(), pageable);
		}else{
			list = postService.findAll(pageable);
		}
		model.addAttribute("list", list);

		Iterable<Category> categorys = categoryService.findAll();
		model.addAttribute("categorys", categorys);


		return "index";
	}

	@GetMapping("/add")
	public String add(Model model){
		Iterable<Category> categorys = categoryService.findAll();
		model.addAttribute("categorys", categorys);
		return "add";
	}

	@PostMapping("/add")
	public String addPost(@ModelAttribute Posts posts, Model model){
		postService.save(posts);

		Iterable<Category> categorys = categoryService.findAll();
		model.addAttribute("categorys", categorys);

		model.addAttribute("success", "Add successfully");

		return "add";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model){
		Posts post = postService.findById(id);
		if (post == null){
			return "redirect:/";
		}
		Iterable<Category> categorys = categoryService.findAll();
		model.addAttribute("categorys", categorys);
		model.addAttribute("post", post);
		return "edit";
	}

	@PostMapping("/edit/{id}")
	public String editPost(@ModelAttribute Posts posts, Model model){
		postService.save(posts);

		Iterable<Category> categorys = categoryService.findAll();
		model.addAttribute("categorys", categorys);

		model.addAttribute("post", posts);
		model.addAttribute("success", "Save successfully");

		return "edit";
	}

	@GetMapping("/post/{id}")
	public String post(@PathVariable Long id, Model model){
		Posts posts = postService.findById(id);
		if (posts != null){
			model.addAttribute("title", posts.getName());
			model.addAttribute("post", posts);
			return "post";
		}
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id){
		postService.delete(id);
		return "redirect:/";
	}
}
