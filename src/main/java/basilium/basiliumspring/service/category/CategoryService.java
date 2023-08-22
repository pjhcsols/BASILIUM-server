package basilium.basiliumspring.service.category;

import basilium.basiliumspring.domain.category.Category;
import basilium.basiliumspring.repository.category.CategoryRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        // 새로운 카테고리 생성 및 저장
        return categoryRepository.createCategory(category);
    }

    public Category getCategoryById(Long categoryId) {
        // 카테고리 ID를 사용하여 카테고리 조회
        return categoryRepository.getCategoryById(categoryId);
    }

    public Category getCategoryByName(String categoryName) {
        // 카테고리 이름을 사용하여 카테고리 조회
        return categoryRepository.getCategoryByName(categoryName);
    }

    public List<Category> getAllCategories() {
        // 모든 카테고리 조회
        return categoryRepository.getAllCategories();
    }

    public Category updateCategory(Category category) {
        // 카테고리 업데이트
        return categoryRepository.updateCategory(category);
    }

    /*
    public void deleteCategoryById(Long categoryId) {
        // categoryId에 해당하는 카테고리를 찾아서 삭제하거나, 없으면 예외를 던짐
        Category categoryToDelete = categoryRepository.getCategoryById(categoryId);

        if (categoryToDelete == null) {
            throw new IllegalArgumentException("삭제할 카테고리가 존재하지 않습니다.");
        }

        categoryRepository.deleteCategoryById(categoryId);
    }

     */
    public void deleteCategoryById(Long categoryId) {
        // 카테고리 ID를 사용하여 카테고리 삭제
        categoryRepository.deleteCategoryById(categoryId);
    }

    public void deleteCategoryByName(String categoryName) {
        // 카테고리 이름을 사용하여 카테고리 삭제
        categoryRepository.deleteCategoryByName(categoryName);
    }

    // 다음은 추가 기능입니다.

    // 특정 키워드로 카테고리를 검색합니다.
    public List<Category> searchCategoriesByKeyword(String keyword) {
        return categoryRepository.getAllCategories()
                .stream()
                .filter(category -> category.getCategoryName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // 카테고리를 알파벳 순으로 정렬합니다.
    public List<Category> sortCategoriesAlphabetically() {
        return categoryRepository.getAllCategories()
                .stream()
                .sorted(Comparator.comparing(Category::getCategoryName))
                .collect(Collectors.toList());
    }
    /*
    // 카테고리를 계층 구조로 관리합니다.
    public void createSubCategory(Category parentCategory, Category subCategory) {
        // 카테고리 계층 관계를 설정하고 저장
        subCategory.setParentCategoryId(parentCategory.getCategoryId());
        categoryRepository.createCategory(subCategory);
    }

     */
    /*
    // 상위 카테고리에 속한 하위 카테고리들을 조회합니다.
    public List<Category> getSubCategoriesOfParent(Long parentCategoryId) {
        return categoryRepository.getAllCategories()
                .stream()
                .filter(category -> parentCategoryId.equals(category.getParentCategoryId()))
                .collect(Collectors.toList());
    }
     */
}

