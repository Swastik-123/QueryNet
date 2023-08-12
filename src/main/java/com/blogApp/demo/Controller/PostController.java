package com.blogApp.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blogApp.demo.Dto.CommentDto;
import com.blogApp.demo.Dto.PostDto;
import com.blogApp.demo.Service.CommentService;
import com.blogApp.demo.Service.PostService;

import jakarta.validation.Valid;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;

	//uncomment if not working.
//	public PostController(PostService postService) {
//		super();
//		this.postService = postService;
//	}
	
	
	
	
	//create handler method ,GET request and return model and view.
	@GetMapping("/admin/posts")
	public String posts(Model model) {
		
//		List<PostDto> posts = postService.findAllPosts();
		List<PostDto> posts = postService.findPostsByUser();//V:107(at v:107 i comment the previous line and add this line)
		model.addAttribute("posts",posts);
		return "/admin/posts";
	}

	
	
//	1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
	
	//handler method to handle new post request
	@GetMapping("/admin/posts/newpost")
	public String newPostForm(Model model) {
		PostDto postDto = new PostDto();
		model.addAttribute("post",postDto);
		return "admin/create_post";
	}
	
	
	
	
	//handler method to handle form submission request
	@PostMapping("/admin/posts")
	public String createPost( @Valid @ModelAttribute("post") PostDto postDto,
								BindingResult result,
								Model model) {
		
		if(result.hasErrors()) { 
			model.addAttribute("post",postDto);//if any error then return the same page
			return "admin/create_post";
		}
		postDto.setUrl(getUrl(postDto.getTitle()));
		postService.createPost(postDto);
		return "redirect:/admin/posts";
		/*
		 * when user submit the form and save into the database then redirect user to the list blog post page 
		 * of any page (depends on the project)
		 */
	}
//	1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//	1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
	
	//handler method to handle edit post request.
	@GetMapping("/admin/posts/{postId}/edit")
	public String editPostForm(@PathVariable("postId") Long PostId,Model model) {
		PostDto postDto = postService.findPostById(PostId);
		model.addAttribute("post",postDto);
		return "admin/edit_post";
	}
	
	//handler method to handle edit post form submission request
	@PostMapping("/admin/posts/{postId}")
	public String updatePost(@PathVariable("postId") Long postId,
								@Valid @ModelAttribute("post") PostDto postDto,
								BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("post",postDto);//if any error then return the same page
			return "admin/edit_post";
		}
		postDto.setId(postId);
		postService.updatePost(postDto);
		return "redirect:/admin/posts";
	}
	
//	1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//	1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
	
	//handler method to handle delete post method.
	@GetMapping("/admin/posts/{postId}/delete")
	public String deletePost(@PathVariable("postId") Long postId) {
		postService.deletePost(postId);
		return "redirect:/admin/posts";
	}
	
	
	//handler method to handle view post method.
	@GetMapping("/admin/posts/{postUrl}/view")
	public String viewPost(@PathVariable("postUrl") String postUrl,Model model) {
		PostDto psotDto = postService.findPostByUrl(postUrl);
		model.addAttribute("post",psotDto);
		return "admin/view_post";
	}
	
	//handler method to handle Search post method.
	//localhost:8080/admin/posts/search?query=
	@GetMapping("/admin/posts/search")
	public String searchPosts(@RequestParam(value="query") String query,Model model) {
		List<PostDto> posts = postService.searchPosts(query);
		model.addAttribute("posts",posts);
		return "admin/posts";
	}
	
	
//	1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
	//handler method to handle the list of comments
	@GetMapping("/admin/posts/comments")
	public String postComments(Model model) {
//		List<CommentDto> comments = commentService.findAllComments();
		List<CommentDto> comments = commentService.findCommentsByPost();//v:108
		model.addAttribute("comments", comments);
		return "admin/comments";
	}
	//handler method to handle delete comment request
	@GetMapping("/admin/posts/comments/{commentId}")
	public String deleteComment(@PathVariable("commentId") Long commentId) {
		commentService.deleteComment(commentId);
		return "redirect:/admin/posts/comments";
	}
	
	
//	1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static String getUrl(String postTitle) {//create a url. v:48
		
		//title : Oops concept using java
		//url : oops-concept-using-java
		
		String title = postTitle.trim().toLowerCase();
		String url = title.replaceAll("\\s+", "-");//replace the space with -
		url = url.replaceAll("[^A-Za-z0-9]", "-");//if any other character instead of A to Z ,a to z and 0 to 9 then replace it with - .
		return url;
	}
	
	
	
	
	
	
	
	
}
