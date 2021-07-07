package pl.training.shop.common;

import lombok.Data;
import lombok.Value;

import java.util.List;
@Value
public class PagedResult<T> {

    List<T> data;
    int pageNumber;
    int totalPages;
}
