package com.blogApp.demo.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.demo.Dto.PostDto;
import com.blogApp.demo.Entity.Post;
import com.blogApp.demo.Entity.User;
import com.blogApp.demo.Mapper.PostMapper;
import com.blogApp.demo.Repository.PostRepository;
import com.blogApp.demo.Repository.UserRepository;
import com.blogApp.demo.Service.PostService;
import com.blogApp.demo.Util.SecurityUtils;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;


	@Override
	public List<PostDto> findAllPosts() {
		List<Post> posts =  postRepository.findAll();
		return posts.stream().map(PostMapper::mapToPostDto)//method reference
				.collect(Collectors.toList());
		/*
		 * it's looking heard because which data we get from DB is Post type . and we need to convert it
		 *  Post to PostDto.so extra line for this conversion.
		 *  and when we return list of posts. 
		 */
	}

	@Override
	public void createPost(PostDto postDto) {
		
		String email = SecurityUtils.getCurrentUser().getUsername();
		User user = userRepository.findByEmail(email);
		//convert postDto to post jpa entity.
		Post post = PostMapper.mapToPost(postDto);
		post.setCreatedBy(user);
		postRepository.save(post);
		
	}

	@Override
	public PostDto findPostById(Long postId) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId).get();
		return PostMapper.mapToPostDto(post);
	}

	@Override
	public void updatePost(PostDto postDto) {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Post post = PostMapper.mapToPost(postDto);
		post.setCreatedBy(createdBy);
		postRepository.save(post);
		
	}

	@Override
	public void deletePost(Long postId) {
		postRepository.deleteById(postId);
		
	}

	@Override
	public PostDto findPostByUrl(String postUrl) {
		Post post = postRepository.findByUrl(postUrl).get();
		return PostMapper.mapToPostDto(post);
	}

	@Override
	public List<PostDto> searchPosts(String query) {
		
		List<Post> posts = postRepository.searchPosts(query);
		return posts.stream().map(PostMapper::mapToPostDto).
				collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostsByUser() {
		
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Long userId = createdBy.getId();
		List<Post> posts = postRepository.findPostsByUser(userId);
		
		return posts.stream()
				.map((post)->PostMapper.mapToPostDto(post)).collect(Collectors.toList());
	}

}
