package com.ooad.pixeledit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="images")
public class Image {

  @Id
  @Column(nullable=false)
  private String name;
  @Column(nullable=false)
  private String url;
  @Column(nullable=false)
  private String author;
}
