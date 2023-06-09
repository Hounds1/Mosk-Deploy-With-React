package team.mosk.api.server.domain.category.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UpdateCategoryRequest {

    private Long categoryId;
    private String name;
}
