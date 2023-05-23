package com.example.sbprojectex.Filter.httpCache;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StreamUtils;

/**
 * HttpServletRequest의 InputStream내부의 data를 캐싱하기위해 wrapper클래스 정의
 */
public class CachedHttpServletRequest extends HttpServletRequestWrapper{

    private byte[] rawData;

    // request inpustream 데이터를 읽어와 복사.
    public CachedHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        InputStream is = request.getInputStream();
        this.rawData = StreamUtils.copyToByteArray(is);
    }
    

    //getInputStream와 getReader method override
    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new CachedServletInputStream(this.rawData);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(this.rawData);
        return new BufferedReader(new InputStreamReader(bais));
    }

    public String toDataString(){
        return new String(this.rawData);
    }
    
    
}
