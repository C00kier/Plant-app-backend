package com.plantapp.plantapp.profile_image.model;

import com.plantapp.plantapp.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @Lob
    @Column(length = 1000)
    private byte[] imageData;
}
