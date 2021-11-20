package com.gabriel.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.blog.models.Post;
import com.gabriel.blog.models.PostRequest;
import com.gabriel.blog.services.PostService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {

	private final PostService postService;
	
	@GetMapping
	public ResponseEntity<List<Post>> getAllPosts (){
		return new ResponseEntity<List<Post>>(postService.getAllPosts(), HttpStatus.OK);
	}
	
	@GetMapping(params = "title")
	public ResponseEntity<List<Post>> getAllPostsFilterByTitle(@RequestParam(name = "title") String title){
		return new ResponseEntity<List<Post>>(postService.getAllPostsFilterByTitle(title), HttpStatus.OK);
	}
	
	@GetMapping(params = "category")
	public ResponseEntity<List<Post>> getAllPostsFilterByCategory(@RequestParam(name = "category") String category){
		return new ResponseEntity<List<Post>>(postService.getAllPostsFilterByCategory(category), HttpStatus.OK);
	}
	
	@GetMapping(params = {"title", "category"})
	public ResponseEntity<List<Post>> getAllPostsFilterByTitleAndCategory(@RequestParam(name = "title") String title, @RequestParam(name = "category") String category){
		return new ResponseEntity<List<Post>>(postService.getAllPostsFilterByTitleAndCategory(title, category), HttpStatus.OK);
	}
	
	@GetMapping(path = "{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public ResponseEntity<Post> getPost (@PathVariable("id") Long id){
		return new ResponseEntity<Post>(postService.getPost(id),HttpStatus.OK);
	}
	
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public ResponseEntity<Post> addPost(@Valid @RequestBody PostRequest post){
		return new ResponseEntity<Post>(postService.addPost(post), HttpStatus.OK);
	}
	
	
	@PatchMapping(path = "{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public ResponseEntity<Post> editPost(@RequestBody PostRequest post, @PathVariable("id") Long id){
		return new ResponseEntity<Post>(postService.editPost(id,post), HttpStatus.OK);
	}
	
	
	@DeleteMapping(path = "{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public ResponseEntity<Post> deletePost (@PathVariable("id") Long id){
		return new ResponseEntity<Post>(postService.deletePost(id),HttpStatus.OK);
	}
}
