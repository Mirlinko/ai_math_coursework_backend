package com.example.demo.models;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Document(collection = "Trains")
@Getter
public class Train {
    @Id
    private String id;

    @Setter
    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "^[0-9]{6,8}$", message = "Name must be between 6 and 8 digits")
    @Indexed(unique = true)
    private String name;

    @Setter
    @NotEmpty(message = "Destination cannot be empty")
    private String destination;

    @Setter
    @NotNull(message = "Time cannot be empty")
    private Date time;
}
