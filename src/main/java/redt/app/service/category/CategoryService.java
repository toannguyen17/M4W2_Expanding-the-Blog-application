package redt.app.service.category;

import redt.app.model.Category;
import redt.app.service.IGeneralService;

public interface CategoryService extends IGeneralService<Category> {
	Iterable<Category> findAll();
}
