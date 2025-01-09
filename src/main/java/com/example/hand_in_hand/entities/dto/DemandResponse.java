package com.example.hand_in_hand.entities.dto;

import com.example.hand_in_hand.entities.models.Category;
import com.example.hand_in_hand.entities.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DemandResponse {
    private int id;
    private String description;

    private CategoryDto category;
    private UserDto needy;

    public DemandResponse(int id, String description, Category category, User needy) {
        this.id = id;
        this.description = description;
        this.category = new CategoryDto(category);
        this.needy = new UserDto(needy);
    }

    @Getter
    @Setter
    public static class CategoryDto {
        private String name;

        public CategoryDto(Category category) {
            this.name = category.getCategoryName();
        }
    }
    @Getter
    @Setter
    public static class UserDto {
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;

        public UserDto(User user) {
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.email = user.getEmail();
            this.phoneNumber = user.getPhoneNumber();
        }
    }
}
