package com.blogApp.demo.Mapper;

import java.util.stream.Collectors;

import com.blogApp.demo.Dto.PostDto;
import com.blogApp.demo.Entity.Post;

/*
 * PostMapper map PostDto to Post class and Post class to PostDto class.
 * because PostDto communicate with view and then tell Post class(JPA) class and Post class communicate with database 
 * and then tell PostDto class.
 * so we need to map this two class.
 */

/*
 * Here we convert two method one convert PostDto to Post entity and other convert Post to PostDto entity.
 */
public class PostMapper {
	
	//map Post entity to PostDto.
	public static PostDto mapToPostDto(Post post) {
		PostDto postDto = PostDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.url(post.getUrl())
				.content(post.getContent())
				.shortDescription(post.getShortDescription())
				.createdOn(post.getCreatedOn())
				.updatedOn(post.getUpdatedOn())
				.comments(post.getComment().stream()//implement letter V:82
							.map((comment) -> CommentMapper.mapToCommentDto(comment))
							.collect(Collectors.toSet()))
				.build();
		return postDto;
	}
	
	//map PostDto to Post entity.
	public static Post mapToPost(PostDto postDto) {
		return Post.builder()
				.id(postDto.getId())
				.title(postDto.getTitle())
				.url(postDto.getUrl())
				.content(postDto.getContent())
				.shortDescription(postDto.getShortDescription())
				.createdOn(postDto.getCreatedOn())
				.updatedOn(postDto.getUpdatedOn())
				.build();
	}
	
}
