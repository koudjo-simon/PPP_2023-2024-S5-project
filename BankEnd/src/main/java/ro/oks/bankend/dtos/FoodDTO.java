package ro.oks.bankend.dtos;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import ro.oks.bankend.model.model_utils.FoodStatus;

import java.util.Date;

@Data
public class FoodDTO {
    private String foodId;
    private String name;
    private Double price;
    private boolean favorite;
    private double star;
    private String tags;
    private String imageUrl;
    private String cookTIme;
    private String origins;

    @Hidden
    private FoodStatus foodStatus;

    @Hidden
    private Date addDate;

    @Hidden
    private Date lastModifiedDate;
}
