package basilium.basiliumspring.repository.category;

import basilium.basiliumspring.domain.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MemoryCategoryRepositoryTest {

    private MemoryCategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository = new MemoryCategoryRepository();

        // 테스트를 위해 초기 카테고리 데이터를 추가합니다.
        categoryRepository.createCategory(new Category(1L, "상의"));
        categoryRepository.createCategory(new Category(2L, "하의"));
        categoryRepository.createCategory(new Category(3L, "원피스"));
    }

    @Test
    void createCategory() {
        // 새로운 카테고리 생성과 반환 테스트
        Category newCategory = new Category(4L, "신발");
        Category createdCategory = categoryRepository.createCategory(newCategory);
        assertEquals(newCategory, createdCategory);
    }

    @Test
    void getCategoryById() {
        // 카테고리 ID를 기반으로 카테고리 조회 테스트
        //given
        Long categoryId = 2L;
        //when
        Category category = categoryRepository.getCategoryById(categoryId);
        //then
        assertEquals("하의", category.getCategoryName());
    }

    @Test
    void getCategoryByName() {
        // 카테고리 NAME를 기반으로 카테고리 조회 테스트
        String categoryName = "상의";
        Category category = categoryRepository.getCategoryByName(categoryName);
        assertEquals(1L, category.getCategoryId());
    }

    @Test
    void getAllCategories() {
        // 모든 카테고리 조회 테스트
        List<Category> allCategories = categoryRepository.getAllCategories();
        assertEquals(3, allCategories.size());
    }

    @Test
    void updateCategory() {
        // 기존 카테고리를 업데이트하고, 업데이트된 카테고리를 반환하는 테스트
        Long categoryId = 3L;
        Category existingCategory = categoryRepository.getCategoryById(categoryId);
        Category updatedCategory = new Category(categoryId, "드레스");
        categoryRepository.updateCategory(updatedCategory);
        Category retrievedCategory = categoryRepository.getCategoryById(categoryId);
        assertEquals(updatedCategory.getCategoryName(), retrievedCategory.getCategoryName());
    }

    @Test
    void deleteCategoryById() {
        // 카테고리 ID를 기반으로 카테고리를 삭제하는 테스트
        Long categoryId = 1L;
        categoryRepository.deleteCategoryById(categoryId);
        Category deletedCategory = categoryRepository.getCategoryById(categoryId);
        assertNull(deletedCategory);
    }

    @Test
    void deleteCategoryByName() {
        // 카테고리 NAME를 기반으로 카테고리를 삭제하는 테스트
        String categoryName = "하의";
        categoryRepository.deleteCategoryByName(categoryName);
        Category deletedCategory = categoryRepository.getCategoryByName(categoryName);
        assertNull(deletedCategory);
    }
}