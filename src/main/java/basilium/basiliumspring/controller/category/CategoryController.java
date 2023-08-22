package basilium.basiliumspring.controller.category;

import basilium.basiliumspring.domain.category.Category;
import basilium.basiliumspring.service.category.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 카테고리 생성 엔드포인트
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    // 카테고리 조회 엔드포인트 - ID로 조회
    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    // 카테고리 조회 엔드포인트 - NAME로 조회
    @GetMapping("/name/{categoryName}")
    public Category getCategoryByName(@PathVariable String categoryName) {
        return categoryService.getCategoryByName(categoryName);
    }

    // 모든 카테고리 조회 엔드포인트
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // 카테고리 업데이트 엔드포인트
    @PutMapping("/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        category.setCategoryId(categoryId);
        return categoryService.updateCategory(category);
    }

    // 카테고리 삭제 엔드포인트 - ID로 삭제
    @DeleteMapping("/{categoryId}")
    public void deleteCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
    }

    // 카테고리 삭제 엔드포인트 - NAME로 삭제
    @DeleteMapping("/name/{categoryName}")
    public void deleteCategoryByName(@PathVariable String categoryName) {
        categoryService.deleteCategoryByName(categoryName);
    }
    /*
    // 부모 카테고리로 하위 카테고리 조회 엔드포인트
    @GetMapping("/subcategories/{parentCategoryId}")
    public List<Category> getSubCategoriesOfParent(@PathVariable Long parentCategoryId) {
        return categoryService.getSubCategoriesOfParent(parentCategoryId);
    }

     */
}
