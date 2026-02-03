package edu.ban7.rh_cesi_26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.rh_cesi_26.view.AppUserView;
import edu.ban7.rh_cesi_26.view.ResourceView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser {

    public interface OnCreate{}
    public interface OnUpdate{}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(AppUserView.class)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Email(groups = {OnCreate.class, OnUpdate.class})
    @JsonView({AppUserView.class,ResourceView.class})
    private String email;

    @Column(nullable = false)
    @NotBlank(groups = {OnCreate.class})
    private String password;

    @ManyToMany
    @JoinTable(name = "favorite",
            //joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_id"))
    private List<Resource> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    @JsonView(AppUserView.class)
    private List<Resource> createdResources = new ArrayList<>();

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
}
