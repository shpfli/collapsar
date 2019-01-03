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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.hubery.collapsar.entity.VirtualIdentity;
import pers.hubery.collapsar.service.VirtualIdentityManageService;

import java.util.Date;
import java.util.List;

/**
 * The type View controller.
 *
 * @author Hubery
 * @version ViewController.java, 2018年12月14日 00:43
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private VirtualIdentityManageService virtualIdentityManageService;

    /**
     * Welcome string.
     * <p>http://localhost:8888/welcome</p>
     *
     * @return the string
     */
    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * Show vid page
     * <p>http://localhost:8888/vid</p>
     *
     * @return the string
     */
    @RequestMapping("/vidSpace")
    public String vidSpace(ModelMap modelMap) {

        List<VirtualIdentity> virtualIdentityList = virtualIdentityManageService.findAll();
        modelMap.put("vidList", virtualIdentityList);
        return "vidSpace";
    }

    @RequestMapping("/vid/modify/{id}")
    public String vidForm(ModelMap modelMap, @PathVariable Integer id) {

        VirtualIdentity vid = virtualIdentityManageService.findById(id).orElseGet(() -> new VirtualIdentity());
        modelMap.put("vid", vid);
        return "vidForm";
    }

    @RequestMapping("/vid/add")
    public String addVid(ModelMap modelMap) {

        VirtualIdentity vid = new VirtualIdentity();
        Date now = new Date();
        vid.setCreateTime(now);
        vid.setModifiedTime(now);
        modelMap.put("vid", vid);

        return "vidForm";
    }

    /**
     * Save virtual identity .
     *
     * @param vid the vid
     * @return 保存成功后展示vidSpace
     */
    @PostMapping("/vid/save")
    public String saveVirtualIdentity(ModelMap modelMap, VirtualIdentity vid) {

        virtualIdentityManageService.saveVirtualIdentity(vid);

        List<VirtualIdentity> virtualIdentityList = virtualIdentityManageService.findAll();
        modelMap.put("vidList", virtualIdentityList);
        return "redirect:/view/vidSpace";
    }
}
