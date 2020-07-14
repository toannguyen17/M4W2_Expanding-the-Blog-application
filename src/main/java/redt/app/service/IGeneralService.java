package redt.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGeneralService<T> {
	Page<T> findAll(Pageable pageable);

	void save(T t);

	T findById(Long id);

	void delete(Long id);
}
