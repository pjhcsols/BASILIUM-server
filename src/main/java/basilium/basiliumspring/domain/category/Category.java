package basilium.basiliumspring.domain.category;

public class Category {
    private Long categoryId; //productcategoryId와 연결해야됨
    private String categoryName;
    // private Long parentCategoryId;

    public Category(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        //this.parentCategoryId = parentCategoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /*
    public Long getParentCategoryId() {
        return parentcategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentcategoryId = parentCategoryId;
    }

     */
}
