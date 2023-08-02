package basilium.basiliumspring.repository.category;

import basilium.basiliumspring.domain.category.Category;

import java.util.List;

public interface CategoryRepository { //CRUD
    // 새로운 카테고리를 생성하고 저장한 후, 저장된 카테고리를 반환합니다.
    Category createCategory(Category category);

    // 카테고리 ID를 기반으로 카테고리를 조회합니다.
    Category getCategoryById(Long categoryId);

    // 모든 카테고리를 조회합니다.
    List<Category> getAllCategories();

    // 기존의 카테고리를 업데이트하고, 업데이트된 카테고리를 반환합니다.
    Category updateCategory(Category category);

    // 카테고리 ID를 기반으로 카테고리를 삭제합니다.
    void deleteCategoryById(Long categoryId);
}

