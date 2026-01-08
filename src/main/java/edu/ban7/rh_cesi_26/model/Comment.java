package edu.ban7.rh_cesi_26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.rh_cesi_26.view.AppUserView;
import edu.ban7.rh_cesi_26.view.ResourceView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(ResourceView.class)
    private String content;

    @ManyToOne(optional = false)
    private Resource resource;

    @ManyToOne(optional = false)
    @JsonView(ResourceView.class)
    private AppUser author;

}
