package redt.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import redt.app.model.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
