package com.gabriel.blog.services;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gabriel.blog.dto.PostRequest;
import com.gabriel.blog.dto.PostView;
import com.gabriel.blog.entities.AppUser;
import com.gabriel.blog.entities.Category;
import com.gabriel.blog.entities.Post;
import com.gabriel.blog.handlers.NotFoundException;
import com.gabriel.blog.repository.PostRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final AppUserService appUserService;
	private final CategoryService categoryService;
	
	public Post getPost(Long id) {
		return postRepository.findById(id).orElseThrow(()->new NotFoundException("post does not exist"));
	}

	
	public Post deletePost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(()->new NotFoundException("post does not exist"));
		postRepository.delete(post);
		return post;
	}
	

	public Post addPost(PostRequest postRequest) {
		AppUser user = appUserService.findUserById(postRequest.getIdUser()).orElseThrow(()->new NotFoundException("user does not exist"));
		Post post =new ModelMapper()
								.typeMap(PostRequest.class, Post.class)
								.addMappings(mapper -> mapper.skip(Post::setId))
								.map(postRequest);
		post.setUser(user);
		post.setCreationDate(LocalDateTime.now());
		return postRepository.save(post);
	}

	
	@Transactional
	public Post editPost(Long id, PostRequest post) {
		Post postToEdit = postRepository.findById(id).orElseThrow(()->new NotFoundException("post does not exist"));
		AppUser user = appUserService.findUserById(post.getIdUser()).orElseThrow(()->new NotFoundException("user does not exist"));
		Category category = categoryService.findCategoryById(post.getIdCategory()).orElseThrow(()->new NotFoundException("category does not exist"));
		postToEdit.setTitle(post.getTitle());
		postToEdit.setContent(post.getContent());
		postToEdit.setImageUrl(post.getImageUrl());
		postToEdit.setCategory(category);
		postToEdit.setUser(user);
		return postToEdit;
	}

	
	public List<PostView> getAllPosts() {
		return postRepository.findAll().stream()
										.sorted(Comparator.comparing(Post::getCreationDate,Comparator.reverseOrder()))
										.map(this::mapPostToPostView)
										.collect(Collectors.toList());
	}
	
	private PostView mapPostToPostView(Post post) {
		
		PostView postView = new ModelMapper().map(post, PostView.class);
		postView.setCategory(post.getCategory().getName());
		return postView;
		
	}

	
	public List<PostView> getAllPostsFilterByTitle(String title) {
		return getAllPosts().stream()
								.filter(post -> post.getTitle().contains(title))
								.collect(Collectors.toList());
	}

	
	public List<PostView> getAllPostsFilterByCategory(String category) {
		return getAllPosts().stream()
				.filter(post -> post.getCategory().contains(category))
				.collect(Collectors.toList());
	}

	
	public List<PostView> getAllPostsFilterByTitleAndCategory(String title, String category) {
		return getAllPosts().stream()
				.filter(post -> post.getTitle().contains(title) && post.getCategory().contains(category))
				.collect(Collectors.toList());
	}
	
	
}
