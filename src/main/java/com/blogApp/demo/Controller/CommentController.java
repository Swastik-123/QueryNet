package com.blogApp.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.blogApp.demo.Dto.CommentDto;
import com.blogApp.demo.Dto.PostDto;
import com.blogApp.demo.Service.CommentService;
import com.blogApp.demo.Service.PostService;

import jakarta.validation.Valid;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private PostService postService;
	
	//handler method to handle form submit request.
	
	@PostMapping("/{postUrl}/comments")
	public String createComment(@PathVariable("postUrl") String postUrl,Model model,
								@Valid @ModelAttribute("comment") CommentDto commentDto,
								BindingResult result) {
		
		PostDto postDto = postService.findPostByUrl(postUrl);
		
		if(result.hasErrors()) {
			model.addAttribute("post",postDto);
			model.addAttribute("comment",commentDto);
			return "blog/blog_post";
		}
		
		
		commentService.createComment(postUrl, commentDto);
//		/post/{postUrl} here we pass the postUrl dynamically 
		return "redirect:/post/"+postUrl;
	}
}
