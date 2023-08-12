package com.blogApp.demo.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.demo.Dto.CommentDto;
import com.blogApp.demo.Entity.Comment;
import com.blogApp.demo.Entity.Post;
import com.blogApp.demo.Entity.User;
import com.blogApp.demo.Mapper.CommentMapper;
import com.blogApp.demo.Repository.CommentRepository;
import com.blogApp.demo.Repository.PostRepository;
import com.blogApp.demo.Repository.UserRepository;
import com.blogApp.demo.Service.CommentService;
import com.blogApp.demo.Util.SecurityUtils;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void createComment(String postUrl, CommentDto commentDto) {
		
		Post post = postRepository.findByUrl(postUrl).get(); 
		
		Comment comment = CommentMapper.mapToComment(commentDto);
		comment.setPost(post);
		commentRepository.save(comment);
		
	}

	@Override
	public List<CommentDto> findAllComments() {
		List<Comment> comments = commentRepository.findAll();
		return comments.stream()
				.map(CommentMapper::mapToCommentDto)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
		
	}

	@Override
	public List<CommentDto> findCommentsByPost() {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Long userId = createdBy.getId();
		List<Comment> comments = commentRepository.findCommentsByPost(userId);
		return comments.stream()
				.map((comment)->CommentMapper.mapToCommentDto(comment))
				.collect(Collectors.toList());
	}

}
