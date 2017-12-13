package com.fantasystocks.controller;

import com.fantasystocks.config.TestConfig;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@RunWith(EasyMockRunner.class)
public class ControllerErrorHandlerTest extends EasyMockSupport {
    @TestSubject
    ControllerErrorHandler errorHandler = new ControllerErrorHandler();
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;

    @After
    public void teardown() {
        verifyAll();
    }

    @Test
    public void handlerError_test() {
        expect(httpServletRequest.getRequestURL()).andReturn(new StringBuffer("/test")).once();
        httpServletResponse.setStatus(400);
        expectLastCall().once();
        replayAll();

        errorHandler.handleError(httpServletRequest, httpServletResponse, new Exception());
    }
}
