package com.blogApp.demo.Service;

import java.util.List;

import com.blogApp.demo.Dto.PostDto;
import com.blogApp.demo.Entity.Post;

public interface PostService {
	
	List<PostDto> findAllPosts();//iteriate all the post
	
	void createPost(PostDto postDto);
	
	PostDto findPostById(Long postId);
	
	void updatePost(PostDto postDto);
	
	void deletePost(Long postId);
	
	PostDto findPostByUrl(String postUrl);
	
	List<PostDto> searchPosts(String query);
	
	List<PostDto> findPostsByUser();
	
	
}
