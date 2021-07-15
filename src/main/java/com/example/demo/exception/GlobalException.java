package com.example.demo.exception;

import com.example.demo.util.CodeMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private CodeMsg codeMsg;


}
