package com.blogApp.demo.Mapper;

import com.blogApp.demo.Dto.CommentDto;
import com.blogApp.demo.Entity.Comment;

public class CommentMapper {
	//convert comment entity to CommentDto
	public static CommentDto mapToCommentDto(Comment comment) {
		return CommentDto.builder()
				.id(comment.getId())
				.name(comment.getName())
				.email(comment.getEmail())
				.content(comment.getContent())
				.createdOn(comment.getCreatedOn())
				.updatedOn(comment.getUpdatedOn())
				.build();
	}
	
	
	//convert CommentDto to comment Entity
	public static Comment mapToComment(CommentDto commentDto) {
		return Comment.builder()
				.id(commentDto.getId())
				.name(commentDto.getName())
				.email(commentDto.getEmail())
				.content(commentDto.getContent())
				.createdOn(commentDto.getCreatedOn())
				.updatedOn(commentDto.getUpdatedOn())
				.build();
	}
}
