package ru.chitaigorod.steps.product;

import ru.chitaigorod.models.product.error.ProductErrorResponseModel;
import ru.chitaigorod.models.search.SearchResponseModel;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.chitaigorod.data.ErrorsData.NOT_FOUND;

public class ProductSteps {
    ProductApi productApi = new ProductApi();
    public void addItemById(SearchResponseModel item, String token) {
        productApi.postAddItem(item.getIncluded().get(0).getAttributes().getId(), token);
    }

    public ProductErrorResponseModel addErrItemById(int errorId, String token) {
        return productApi.postAddErrItem(errorId, token);
    }

    public void checkErrMsg(ProductErrorResponseModel responseErr) {
        assertThat(responseErr.getMessage()).isEqualTo(NOT_FOUND);
    }
}
