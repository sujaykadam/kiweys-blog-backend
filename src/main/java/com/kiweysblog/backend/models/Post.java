package com.kiweysblog.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 56, nullable = false)
    private String title;
    @Column(length = 1024, nullable = false)
    private String content;
    @Column(name = "image_name",length = 56, nullable = false)
    private String imageName;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "modified_date")
    private Date modifiedDate;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User author;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();
}
