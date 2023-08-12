package com.blogApp.demo.Service;

import java.util.*;
import com.blogApp.demo.Dto.CommentDto;

public interface CommentService {
	void createComment(String postUrl,CommentDto commentDto);
	
	List<CommentDto> findAllComments();
	
	void deleteComment(Long commentId);
	
	
	List<CommentDto> findCommentsByPost();
}
