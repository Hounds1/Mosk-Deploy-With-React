package team.mosk.api.server.domain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.mosk.api.server.domain.category.dto.CreateCategoryRequest;
import team.mosk.api.server.domain.category.dto.UpdateCategoryRequest;
import team.mosk.api.server.domain.category.dto.CategoryResponse;
import team.mosk.api.server.domain.category.service.CategoryReadService;
import team.mosk.api.server.domain.category.service.CategoryService;
import team.mosk.api.server.global.security.principal.CustomUserDetails;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryReadService categoryReadService;

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse> create(@Validated @RequestBody CreateCategoryRequest request,
                                                   @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(request.toEntity(), userDetails.getId()));
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId,
                                       @AuthenticationPrincipal CustomUserDetails userDetails) throws IllegalAccessException {
        categoryService.delete(categoryId, userDetails.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/categories")
    public ResponseEntity<CategoryResponse> update(@Validated @RequestBody UpdateCategoryRequest request,
                                                   @AuthenticationPrincipal CustomUserDetails userDetails) throws IllegalAccessException {
        return ResponseEntity.ok(categoryService.update(request, userDetails.getId()));
    }

    @GetMapping("/categories/{storeId}")
    public ResponseEntity<List<CategoryResponse>> findAllByStoreId(@PathVariable Long storeId) {
        return ResponseEntity.ok(categoryReadService.findAllByStoreId(storeId));
    }
}
