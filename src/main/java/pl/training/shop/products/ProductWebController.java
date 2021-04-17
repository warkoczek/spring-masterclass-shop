package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.training.shop.common.PagedResult;

@Controller
@RequiredArgsConstructor
public class ProductWebController {

    private final ProductService productService;
    private final Mapper mapper;

    @GetMapping("show-product.html")
    public ModelAndView showProducts(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize){
        ModelAndView modelAndView = new ModelAndView("products");
        PagedResult<Product> products = productService.getAll(pageNumber, pageSize);
        modelAndView.addObject("products", products);
        return modelAndView;
    }


}
