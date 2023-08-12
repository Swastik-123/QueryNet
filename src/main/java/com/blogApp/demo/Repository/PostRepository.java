package com.blogApp.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blogApp.demo.Entity.Post;


public interface PostRepository extends JpaRepository<Post,Long>{
	
	Optional<Post> findByUrl(String url);
	
	@Query("SELECT p from Post p WHERE " + 
				"p.title LIKE CONCAT('%', :query, '%') OR " +
				"p.shortDescription LIKE CONCAT('%',:query,'%')") 
	List<Post> searchPosts(String query);
	
	
	//admin can see there own post.
	@Query(value = "select * from posts p where p.created_by =:userId", nativeQuery=true)//v:107
	List<Post> findPostsByUser(Long userId);
}
