package org.dromara.jsq.config;

import cn.hutool.crypto.digest.MD5;
import org.dromara.common.core.constant.HttpStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import java.util.Objects;

@ControllerAdvice
public class ETagResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NotNull MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter returnType, @NotNull MediaType selectedContentType,
                                  @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {

        if (Objects.isNull(body)) {
            return null;
        }
        // Calculate the MD5 hash of the response body
        String bodyAsString = body.toString();
        String md5 = MD5.create().digestHex(bodyAsString).toUpperCase();

        // Get the ETag from the request headers
        String requestETag = request.getHeaders().getFirst(HttpHeaders.IF_NONE_MATCH);

        if (requestETag != null && requestETag.equals(md5)) {
            // Set HTTP 304 Not Modified
            response.setStatusCode(HttpStatusCode.valueOf(HttpStatus.NOT_MODIFIED));
            return null; // Do not return body content
        } else {
            // Set the ETag header
            response.getHeaders().add(HttpHeaders.ETAG, md5);
            return body;
        }
    }
}
