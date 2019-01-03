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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.hubery.collapsar.entity.VirtualIdentity;
import pers.hubery.collapsar.service.VirtualIdentityManageService;

import java.util.List;
import java.util.Optional;

/**
 * 虚拟身份Controller
 *
 * @author Hubery
 * @version VidController.java, 2018年12月14日 01:37
 */
@RestController
@RequestMapping("/vid")
public class VidController {

    @Autowired
    private VirtualIdentityManageService virtualIdentityManageService;

    /**
     * Find all list.
     *
     * @return the list
     */
    @GetMapping("/findAll")
    public List<VirtualIdentity> findAll() {
        return virtualIdentityManageService.findAll();
    }

    /**
     * Find virtual identity by id .
     *
     * @param id the id
     * @return the virtual identity
     */
    @GetMapping("/findById")
    public VirtualIdentity findById(Integer id) {

        Optional<VirtualIdentity> vid = virtualIdentityManageService.findById(id);

        return vid.orElseThrow(() -> new RuntimeException("不存在对应的记录！id：" + id));
    }

    /**
     * Save virtual identity .
     *
     * @param vid the vid
     * @return the virtual identity
     */
    @PostMapping("/save")
    public VirtualIdentity saveVirtualIdentity(VirtualIdentity vid) {
        return virtualIdentityManageService.saveVirtualIdentity(vid);
    }

}
