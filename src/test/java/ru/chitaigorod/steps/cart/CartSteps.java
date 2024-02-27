package ru.chitaigorod.steps.cart;

import ru.chitaigorod.models.cart.CartResponseModel;
import ru.chitaigorod.models.cartshort.CartShortResponseModel;
import ru.chitaigorod.models.deleteCart.DeleteCartResponseModel;
import ru.chitaigorod.models.search.SearchResponseModel;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.chitaigorod.data.ErrorsData.ERR_DELETE;

public class CartSteps {
    GetCartApi getCart = new GetCartApi();
    DeleteCartApi deleteCart = new DeleteCartApi();


    public void deleteAllCart(String token) {
        deleteCart.allCart(token);
    }

    public void deleteItemById(CartResponseModel cart, String token) {
        deleteCart.productItem(cart.getProducts().get(0).getId(), token);
    }

    public DeleteCartResponseModel deleteAgainItemByIde(CartResponseModel cart, String token) {
        return deleteCart.err(cart.getProducts().get(0).getId(), token);
    }

    public void checkDeleteErrMsg(DeleteCartResponseModel response) {
        assertThat(response.getMessage()).isEqualTo(ERR_DELETE);
    }

    public CartResponseModel getCart(String token) {
        return getCart.getCart(token);
    }

    public CartShortResponseModel getCartShort(String token) {
        return getCart.getCartShort(token);
    }

    public void checkCartProductId(CartResponseModel cart, SearchResponseModel searchProduct) {
        assertThat(cart.getProducts().get(0).getGoodsId())
                .isEqualTo(searchProduct.getIncluded().get(0).getAttributes().getId());
    }

    public void checkCartShortNull(CartShortResponseModel cartShort) {
        assertThat(cartShort.getData().getQuantity()).isEqualTo(0);
    }
}
