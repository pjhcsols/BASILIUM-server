package basilium.basiliumspring.repository.category;


import basilium.basiliumspring.domain.category.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryCategoryRepository implements CategoryRepository {

    private Map<Long, Category> categoriesMap;

    public MemoryCategoryRepository() {
        categoriesMap = new HashMap<>();
    }


    // 새로운 카테고리를 위해 다음 사용 가능한 ID+1를 생성하는 유틸리티 메서드
    private long generateNextId() {
        return categoriesMap.keySet().stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0L) + 1;
    }
    @Override
    public Category createCategory(Category category) { //카테고리 생성 관리자만
        long nextId = generateNextId();
        category.setCategoryId(nextId);
        categoriesMap.put(nextId, category);
        return category;
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoriesMap.get(categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        return new ArrayList<>(categoriesMap.values());
    }

    @Override
    public Category updateCategory(Category category) {
        if (!categoriesMap.containsKey(category.getCategoryId())) {
            throw new IllegalArgumentException("Category does not exist");
        }
        categoriesMap.put(category.getCategoryId(), category);
        return category;
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        categoriesMap.remove(categoryId);
    }

}