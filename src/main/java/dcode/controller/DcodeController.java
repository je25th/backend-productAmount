package dcode.controller;

import dcode.model.request.ProductInfoRequest;
import dcode.model.response.ErrorResponse;
import dcode.model.response.ProductAmountResponse;
import dcode.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dcode")
public class DcodeController {

    private final ProductService service;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse illegalExHandler(IllegalArgumentException e) {
        return new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST), e.getMessage());
    }

    //상품 가격 추출 api
    @GetMapping("/product-amount")
    public ResponseEntity<ProductAmountResponse> getProductAmount() {

        ProductAmountResponse response = service.getProductAmount(getParam());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ProductInfoRequest getParam(){
        int[] couponIds = {1,2};

        ProductInfoRequest request = ProductInfoRequest.builder()
                .productId(1)
                .couponIds(couponIds)
                .build();

        return request;
    }
}
