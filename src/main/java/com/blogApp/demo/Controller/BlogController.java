package com.blogApp.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.blogApp.demo.Dto.CommentDto;
import com.blogApp.demo.Dto.PostDto;
import com.blogApp.demo.Service.PostService;

@Controller
public class BlogController {
	
	@Autowired
	private PostService postService;
	
	//handler method to handle http://localhost:8080/
	@GetMapping("/")
	public String viewBlogPost(Model model) {
		List<PostDto> postsResponse = postService.findAllPosts();
		model.addAttribute("postsResponse", postsResponse);
		return "blog/View_posts";
	}
	
	
	//handler method to handle view post request
	@GetMapping("/post/{postUrl}")
	private String showPost(@PathVariable("postUrl") String postUrl,Model model) {
		PostDto postDto = postService.findPostByUrl(postUrl);
		model.addAttribute("post", postDto);
		CommentDto commentDto = new CommentDto();
		model.addAttribute("comment", commentDto);
		return "blog/blog_post";
	}
	
	
	//handler method to handle blog search request.
	//	http://localhost:8080/page/search?query=java
	@GetMapping("/page/search")
	public String searchPosts(@RequestParam(value="query") String query,Model model) {
		List<PostDto> postResponse = postService.searchPosts(query);
		model.addAttribute("postsResponse", postResponse);
		return "blog/View_posts";
	}
	
	
	
	
	
	
}
