package basilium.basiliumspring.repository.category;


import basilium.basiliumspring.domain.category.Category;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class MemoryCategoryRepository implements CategoryRepository {

    private List<Category> categories;

    public MemoryCategoryRepository() {
        categories = new ArrayList<>();
        // 초기 카테고리 데이터를 추가하려면 아래와 같이 할 수 있습니다.
        // categories.add(new Category(1L, "상의"));
        // categories.add(new Category(2L, "하의"));
        // categories.add(new Category(3L, "원피스"));
        // ...
    }

    @Override
    public Category createCategory(Category category) {
        // 새로운 카테고리를 추가하는 코드 구현
        categories.add(category);
        return category;
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        // categoryId에 해당하는 카테고리를 찾아서 반환하는 코드 구현
        for (Category category : categories) {
            if (category.getCategoryId().equals(categoryId)) {
                return category;
            }
        }
        return null; // 해당 ID의 카테고리를 찾지 못했을 경우 null 반환
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        // categoryName에 해당하는 카테고리를 찾아서 반환하는 코드 구현
        for (Category category : categories) {
            if (category.getCategoryName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null; // 해당 이름의 카테고리를 찾지 못했을 경우 null 반환
    }

    @Override
    public List<Category> getAllCategories() {
        // 모든 카테고리를 리스트로 반환하는 코드 구현
        return categories;
    }

    @Override
    public Category updateCategory(Category category) {
        // 기존 카테고리를 업데이트하는 코드 구현
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getCategoryId().equals(category.getCategoryId())) {
                categories.set(i, category);
                return category;
            }
        }
        return null; // 해당 ID의 카테고리를 찾지 못했을 경우 null 반환
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        // categoryId에 해당하는 카테고리를 삭제하는 코드 구현
        categories.removeIf(category -> category.getCategoryId().equals(categoryId));
    }

    @Override
    public void deleteCategoryByName(String categoryName) {
        // categoryName에 해당하는 카테고리를 삭제하는 코드 구현
        categories.removeIf(category -> category.getCategoryName().equalsIgnoreCase(categoryName));
    }
    // 부모 카테고리 ID로 하위 카테고리를 조회하는 메서드
    /*
    @Override
    public List<Category> getCategoriesByParentId(Long parentCategoryId) {
        List<Category> subCategories = new ArrayList<>();
        for (Category category : categories) {
            if (parentCategoryId.equals(category.getParentCategoryId())) {
                subCategories.add(category);
            }
        }
        return subCategories;
    }

     */
}

