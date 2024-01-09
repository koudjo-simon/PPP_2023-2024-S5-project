package ro.oks.bankend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import ro.oks.bankend.model.model_utils.FoodStatus;

import java.util.Date;

@Entity
@Data
public class Food {
    @Id
    private String foodId;
    private String name;
    private Double price;
    private boolean favorite;
    private double star;
    private String tags;
    private String imageUrl;
    private String cookTIme;
    private String origins;
    @Enumerated(EnumType.STRING)
    private FoodStatus foodStatus;
    private Date addDate;
    private Date lastModifiedDate;
}
