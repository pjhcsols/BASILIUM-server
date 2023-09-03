package basilium.basiliumspring.domain.image;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Lob;
import java.util.List;

@Data
@AllArgsConstructor
public class Images {
    private Long productId;

    @ElementCollection
    @Lob
    private List<byte[]> imageData;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<byte[]> getImageData() {
        return imageData;
    }

    public void setImageData(List<byte[]> imageData) {
        this.imageData = imageData;
    }
}
