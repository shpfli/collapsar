/*
 * Copyright (c) 2018. Hubery
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package pers.hubery.collapsar.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Restful风格controller
 *
 * @author Hubery
 * @version RestfulController.java, 2018年11月21日 00:13
 */
@RestController
public class RestfulController {

    /**
     * Welcome string.
     * <p>命令式的、同步阻塞</p>
     * <p>http://localhost:8888/hi</p>
     *
     * @return the string
     */
    @GetMapping("/hi")
    public String hi(String name) {
        return "Hi, " + resolveName(name);
    }

    /**
     * Hello mono.
     * <p>reactive模式：响应式的、异步非阻塞</p>
     * <p>http://localhost:8888/hello?name=hubery</p>
     *
     * @param name the name
     * @return the mono
     */
    @GetMapping("/hello")
    public Mono<String> hello(String name) {
        return Mono.just("Hello, " + resolveName(name));
    }

    /**
     * return name if name is not empty, else world.
     * @param name
     * @return
     */
    private String resolveName(String name) {
        return (StringUtils.isEmpty(name) ? "world" : name);
    }
}
