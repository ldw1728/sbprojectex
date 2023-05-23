package com.example.sbprojectex.Filter.httpCache;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

import lombok.extern.slf4j.Slf4j;

/**
 * CachedHttpServletRequest를 생성하기위한 ServletInputStream 클래스 정의
 */
@Slf4j
public class CachedServletInputStream extends ServletInputStream{

    private InputStream cachedInputStream;

    public CachedServletInputStream(byte[] cachedBody) {
        this.cachedInputStream = new ByteArrayInputStream(cachedBody);
    }
    
    @Override
    public boolean isFinished() {

        try {
            return cachedInputStream.available() == 0;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        throw new UnsupportedOperationException("Unimplemented method 'isFinished'");
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {
        throw new UnsupportedOperationException("Unimplemented method 'setReadListener'");
    }

    @Override
    public int read() throws IOException {
        return cachedInputStream.read();
    }
    
}
