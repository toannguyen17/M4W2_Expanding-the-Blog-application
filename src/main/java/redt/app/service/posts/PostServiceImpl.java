package redt.app.service.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import redt.app.model.Category;
import redt.app.model.Posts;
import redt.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IPostService {
	@Autowired
	private PostRepository postRepository;

	@Override
	public Page<Posts> findAll(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Override
	public void save(Posts posts) {
		postRepository.save(posts);
	}

	@Override
	public Posts findById(Long id) {
		return postRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		postRepository.delete(id);
	}

	@Override
	public Page<Posts> findAllByNameContainingAndCategory(String s, Category c, Pageable pageable) {
		return postRepository.findAllByNameContainingAndCategory(s, c, pageable);
	}

	@Override
	public Page<Posts> findAllByNameContaining(String s, Pageable pageable) {
		return postRepository.findAllByNameContaining(s, pageable);
	}

	@Override
	public Page<Posts> findAllByCategory(Category category, Pageable pageable) {
		return postRepository.findAllByCategory(category, pageable);
	}
}
