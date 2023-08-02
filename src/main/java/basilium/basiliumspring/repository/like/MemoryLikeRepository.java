package basilium.basiliumspring.repository.like;

import basilium.basiliumspring.domain.like.Like;
import basilium.basiliumspring.domain.user.NormalUser;

import java.util.*;

public class MemoryLikeRepository implements LikeRepository {
    private static Map<Long, Like> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public long countByProductId(Long productId) {
        long count = 0L;
        ArrayList<Like> temp = new ArrayList<> (store.values());
        for (Like item : temp)
        {
            if (item.getProductId() == productId)
                count++;
        }
        return count;
    }

    @Override
    public long countByUserId(String UserId) {
        long count = 0L;
        ArrayList<Like> temp = new ArrayList<> (store.values());
        for (Like item : temp)
        {
            if (item.getProductId().equals(UserId))
                count++;
        }
        return count;
    }

    @Override
    public void doLike(Like like) {
        like.setId(++sequence);
        store.put(like.getId(), like);
    }

    @Override
    public void unDoLike(Like like) {
        ArrayList<Like> temp = new ArrayList<> (store.values());
        for (Like item : temp)
        {
            if (item.getProductId() == like.getProductId() && item.getUserId() == like.getUserId())
            {
                store.remove(item.getId());
                break;
            }
        }
    }
}
