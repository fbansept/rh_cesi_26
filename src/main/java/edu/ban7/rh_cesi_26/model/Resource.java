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
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ResourceView.class)
    private Integer id;

    @JsonView({AppUserView.class,ResourceView.class})
    private String name;

    private String description;

    @ManyToOne(optional = false)
    private TypeResource type;

    @ManyToOne(optional = false)
//    @JsonView(AppUserView.class) SURTOUT NE PAS AJOUTER LE JSONVIEW AppUserView
    private AppUser owner;

}
