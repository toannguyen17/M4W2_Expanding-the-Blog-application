package redt.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import redt.app.model.Category;
import redt.app.model.Posts;

public interface PostRepository extends PagingAndSortingRepository<Posts, Long> {
	Page<Posts> findAllByNameContainingAndCategory(String name, Category category, Pageable pageable);

	Page<Posts> findAllByNameContaining(String name, Pageable pageable);

	Page<Posts> findAllByCategory(Category category, Pageable pageable);
}
