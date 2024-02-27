package ru.chitaigorod.steps.product;

import ru.chitaigorod.models.search.SearchResponseModel;

public class ProductSteps {
    ProductApi productApi = new ProductApi();
    public void addItemById(SearchResponseModel item, String token) {
        productApi.postAddItem(item.getIncluded().get(0).getAttributes().getId(), token);
    }
}
