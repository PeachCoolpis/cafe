package sample.cafe;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sample.cafe.api.controller.order.OrderController;
import sample.cafe.api.controller.product.ProductController;
import sample.cafe.api.service.order.OrderService;
import sample.cafe.api.service.product.ProductService;

@WebMvcTest(controllers = {
        OrderController.class,
        ProductController.class
})
public abstract class ControllerTestSupport {
    
    @Autowired
    protected MockMvc mockMvc;
    
    @Autowired
    protected ObjectMapper objectMapper;
    
    @MockBean
    protected OrderService orderService;
    
    @MockBean
    protected ProductService productService;
}
