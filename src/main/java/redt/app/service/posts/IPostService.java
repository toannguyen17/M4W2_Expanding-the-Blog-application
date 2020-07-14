package redt.app.service.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import redt.app.model.Category;
import redt.app.model.Posts;
import redt.app.service.IGeneralService;

import java.util.Optional;

public interface IPostService extends IGeneralService<Posts> {
	Page<Posts> findAllByNameContainingAndCategory(String s, Category c, Pageable pageable);
	Page<Posts> findAllByNameContaining(String s, Pageable pageable);
	Page<Posts> findAllByCategory(Category category, Pageable pageable);
}
