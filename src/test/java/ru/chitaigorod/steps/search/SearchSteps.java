package ru.chitaigorod.steps.search;

import ru.chitaigorod.models.search.SearchResponseModel;
import ru.chitaigorod.models.search.error.SearchErrorResponseModel;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.chitaigorod.data.ErrorsData.ERR_PHRASE;

public class SearchSteps {
    SearchApi searchApi = new SearchApi();
    public SearchResponseModel searchItem(String item, String token) {
        return searchApi.getSearch(item, token);
    }

    public SearchErrorResponseModel searchItem(String token) {
        return searchApi.getSearch(token);
    }

    public void checkSearchTransformedPhrase(SearchResponseModel response, String item) {
        assertThat(response.getData().getAttributes().getTransformedPhrase()).isEqualTo(item);
    }

    public void checkSearchErrTitle(SearchErrorResponseModel response) {
        assertThat(response.getErrors().get(0).getTitle()).isEqualTo(ERR_PHRASE);
    }
}
