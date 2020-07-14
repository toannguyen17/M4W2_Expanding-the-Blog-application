package redt.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import redt.app.model.Category;
import redt.app.model.Posts;
import redt.app.service.category.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categories")
	public String category(@PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable, Model model){
		Page<Category> list = categoryService.findAll(pageable);
		model.addAttribute("list", list);
		return "category/list";
	}

	@GetMapping("/categories/create")
	public String add(Model model){
		model.addAttribute("category", new Category());
		return "category/add";
	}

	@PostMapping("/categories/create")
	public String addPost(@ModelAttribute Category category, Model model){
		categoryService.save(category);
		model.addAttribute("success", "Add successfully");
		return "category/add";
	}

	@GetMapping("/categories/edit/{id}")
	public String edit(@PathVariable Long id, Model model){
		Category category = categoryService.findById(id);
		if (category == null){
			return "redirect:/categories";
		}
		model.addAttribute("category", category);
		return "category/edit";
	}

	@PostMapping("/categories/edit/{id}")
	public String editPost(@ModelAttribute Category category, Model model){
		categoryService.save(category);
		model.addAttribute("success", "Save successfully");
		model.addAttribute("category", category);
		return "category/edit";
	}

	@GetMapping("/categories/delete/{id}")
	public String delete(@PathVariable Long id){
		categoryService.delete(id);
		return "category/list";
	}
}
