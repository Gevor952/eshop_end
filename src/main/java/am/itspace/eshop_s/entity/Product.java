package am.itspace.eshop_s.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double price;
    private String description;
    @Column(name = "pic_name")
    private String picName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at")
    private Date createdAt;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;
}
