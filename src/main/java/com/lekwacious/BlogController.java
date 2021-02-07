package com.lekwacious;

import com.lekwacious.data.PostRepository;
import com.lekwacious.model.Category;
import com.lekwacious.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BlogController {

    private PostRepository postRepository;

    private static final Logger logger = LoggerFactory.getLogger(Category.class);

    public BlogController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping("/")
    public String listPosts(ModelMap modelMap) {
        List<Post> posts = postRepository.findAll();
        modelMap.put("posts", posts);
        return "home";
    }

    @RequestMapping("/post/{id}")
    public String postDetails(@PathVariable Long id, ModelMap modelMap) {
        Post post = postRepository.findById(id).orElse(null);


        modelMap.put("post", post);
        assert post != null;
        logger.info(post.getCategory().getName());
        return "post-details";
    }
}
