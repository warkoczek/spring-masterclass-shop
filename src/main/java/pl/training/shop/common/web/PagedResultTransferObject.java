package pl.training.shop.common.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagedResultTransferObject<T> {
    private List<T> data;
    private int pageNumber;
    private int totalPages;
}
