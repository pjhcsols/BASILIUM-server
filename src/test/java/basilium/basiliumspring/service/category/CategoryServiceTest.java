package basilium.basiliumspring.service.category;

import basilium.basiliumspring.domain.category.Category;
import basilium.basiliumspring.repository.category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryService = new CategoryService(categoryRepository);

        // 테스트를 위한 초기 카테고리 데이터 설정
        List<Category> initialCategories = new ArrayList<>();
        initialCategories.add(new Category(1L, "상의"));
        initialCategories.add(new Category(2L, "하의"));
        initialCategories.add(new Category(3L, "원피스"));

        // getCategoryById 메서드를 Mock으로 대체하여 테스트 데이터 제공
        when(categoryRepository.getCategoryById(1L)).thenReturn(initialCategories.get(0));
        when(categoryRepository.getCategoryById(2L)).thenReturn(initialCategories.get(1));
        when(categoryRepository.getCategoryById(3L)).thenReturn(initialCategories.get(2));
    }

    @Test
    void createCategory() {
        // Given
        Category newCategory = new Category(4L, "신발");

        // When
        when(categoryRepository.createCategory(newCategory)).thenReturn(newCategory);
        Category createdCategory = categoryService.createCategory(newCategory);

        // Then
        assertNotNull(createdCategory);
        assertEquals(newCategory.getCategoryId(), createdCategory.getCategoryId());
        assertEquals(newCategory.getCategoryName(), createdCategory.getCategoryName());
    }

    @Test
    void getCategoryById() {
        // Given
        Long categoryId = 1L;

        // When
        Category category = categoryService.getCategoryById(categoryId);

        // Then
        assertNotNull(category);
        assertEquals("상의", category.getCategoryName());
    }

    @Test
    void getCategoryByName() {
        // Given
        String categoryName = "상의";

        // When
        when(categoryRepository.getCategoryByName(categoryName)).thenReturn(new Category(1L, categoryName));
        Category category = categoryService.getCategoryByName(categoryName);

        // Then
        assertNotNull(category);
        assertEquals(categoryName, category.getCategoryName());
    }

    @Test
    void getAllCategories() {
        // Given
        List<Category> allCategories = new ArrayList<>();
        allCategories.add(new Category(1L, "상의"));
        allCategories.add(new Category(2L, "하의"));
        allCategories.add(new Category(3L, "원피스"));

        // When
        when(categoryRepository.getAllCategories()).thenReturn(allCategories);
        List<Category> categories = categoryService.getAllCategories();

        // Then
        assertNotNull(categories);
        assertEquals(3, categories.size());
    }

    @Test
    void updateCategory() {
        // Given
        Long categoryId = 3L;
        Category updatedCategory = new Category(categoryId, "드레스");

        // When
        when(categoryRepository.updateCategory(updatedCategory)).thenReturn(updatedCategory);
        Category retrievedCategory = categoryService.updateCategory(updatedCategory);

        // Then
        assertNotNull(retrievedCategory);
        assertEquals(updatedCategory.getCategoryId(), retrievedCategory.getCategoryId());
        assertEquals(updatedCategory.getCategoryName(), retrievedCategory.getCategoryName());
    }

    @Test
    void deleteCategoryById() {
        // Given
        Long categoryId = 2L;

        // Before deleting, check if the category exists
        assertNotNull(categoryService.getCategoryById(categoryId));

        // When
         categoryService.deleteCategoryById(categoryId);

        // Then
        assertNull(categoryService.getCategoryById(categoryId));
    }



    /*
    @Test
    void deleteCategoryById() {
        // Given
        Long categoryId = 2L;

        // When
        try {
            categoryService.deleteCategoryById(categoryId);
        } catch (IllegalArgumentException e) {
            // Then
            assertEquals("삭제할 카테고리가 존재하지 않습니다.", e.getMessage());
        }
    }
*/
    @Test
    void deleteCategoryByName() {
        // Given
        String categoryName = "하의";

        // When
        categoryService.deleteCategoryByName(categoryName);

        // Then
        assertNull(categoryService.getCategoryByName(categoryName));
    }

    @Test
    void searchCategoriesByKeyword() {
        // Given
        String keyword = "상";

        // When
        when(categoryRepository.getAllCategories()).thenReturn(createSampleCategories());
        List<Category> categories = categoryService.searchCategoriesByKeyword(keyword);

        // Then
        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals("상의", categories.get(0).getCategoryName());
    }

    @Test
    void sortCategoriesAlphabetically() {
        // Given
        List<Category> unsortedCategories = createSampleCategories();
        List<Category> sortedCategories = new ArrayList<>(unsortedCategories);
        sortedCategories.sort((c1, c2) -> c1.getCategoryName().compareToIgnoreCase(c2.getCategoryName()));

        // When
        when(categoryRepository.getAllCategories()).thenReturn(unsortedCategories);
        List<Category> categories = categoryService.sortCategoriesAlphabetically();

        // Then
        assertNotNull(categories);
        assertEquals(sortedCategories.size(), categories.size());
        for (int i = 0; i < sortedCategories.size(); i++) {
            assertEquals(sortedCategories.get(i).getCategoryName(), categories.get(i).getCategoryName());
        }
    }

    private List<Category> createSampleCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "상의"));
        categories.add(new Category(2L, "하의"));
        categories.add(new Category(3L, "원피스"));
        return categories;
    }
}