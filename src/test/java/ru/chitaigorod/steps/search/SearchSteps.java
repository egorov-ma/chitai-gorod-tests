package ru.chitaigorod.steps.search;

import ru.chitaigorod.models.search.SearchResponseModel;

public class SearchSteps {
    SearchApi searchApi = new SearchApi();
    public SearchResponseModel searchItem(String item, String token) {
        return searchApi.getSearch(item, token);
    }
}
