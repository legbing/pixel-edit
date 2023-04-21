package com.ooad.pixeledit.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Review
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String imageTitle;

    @Column(nullable=true)
    private Boolean isApproved = null;

    public Review(String imageTitle) {
        this.imageTitle = imageTitle;
        
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean approved) {
        isApproved = approved;
    }

    public String getImageUrl() {
    return "file:///D:\\OOAD_project\\pixeledit_older\\pixeledit\\pixeledit\\uploads\\" + imageTitle;
    
  }

}