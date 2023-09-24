package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        // 변경 감지
        Item findItem = itemRepository.findOnd(itemId);

        // 되도록이면 set 사용 x
        // change 같은 메서드 만들어서 값 변경을 추적 가능하도록 설계하는 것을 추천 
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItem() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
        return itemRepository.findOnd(id);
    }
}
