package com.example.sbprojectex.Filter.httpCache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


/*
 * HttpServletResponse의 outputStream 데이터를 가져오기위한 wrapper class 
 * 
 */
public class CachedHttpServletResponse extends HttpServletResponseWrapper{

    // output될 두개의 스트림 정의
    private ByteArrayOutputStream baos; // byteData로 출력될 스트림
    private OutputStream out;           // response로 출력될 스트림

    public CachedHttpServletResponse(HttpServletResponse response) throws IOException {
        super(response);
        this.baos = new ByteArrayOutputStream(); // new stream 생성
        this.out = response.getOutputStream();   // reponse될 outputStream을 참조
    }

    // @Override
    // public PrintWriter getWriter() throws IOException {
    //     return new PrintWriter(baos);
    // }

    // 두 개의 스트림에 데이터를 write하기위해  ServletOutputStream에게 넘겨줌.
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new CachedServletOutputStream(out, baos);
    }

    // byteData는 콘솔에 텍스트로 출력하기위해 String 변환
    public String toDateString() throws IOException{       
        return new String(this.baos.toByteArray(), StandardCharsets.UTF_8);
    }


   

    
    
}
