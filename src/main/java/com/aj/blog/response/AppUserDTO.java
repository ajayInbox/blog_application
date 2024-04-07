package com.aj.blog.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {
    private Long userId;
    private String handleName;
    private String email;
    private LocalDate joinedAt;
    private String aboutYou;
    private String profileImageUrl;
    private List<Long> articles;
}
