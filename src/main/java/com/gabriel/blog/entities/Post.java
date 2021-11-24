package com.gabriel.blog.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "posts")
@SQLDelete(sql = "UPDATE posts SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	private String imageUrl;
	//private String category;
	private LocalDateTime creationDate;
	private boolean deleted = Boolean.FALSE;
	
	@OneToOne
	@JoinColumn(name="id_user")
	private AppUser user;
	
	@ManyToOne
	@JoinColumn(name="id_category")
	private Category category;

	
}
