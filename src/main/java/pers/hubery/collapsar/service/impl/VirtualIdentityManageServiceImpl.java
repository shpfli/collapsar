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

package pers.hubery.collapsar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.hubery.collapsar.entity.VirtualIdentity;
import pers.hubery.collapsar.repository.VirtualIdentityRepository;
import pers.hubery.collapsar.service.VirtualIdentityManageService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * 虚拟身份管理服务实现
 *
 * @author Hubery
 * @version VirtualIdentityManageServiceImpl.java, 2018年11月19日 23:39
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class VirtualIdentityManageServiceImpl implements VirtualIdentityManageService {

	@Autowired
	private VirtualIdentityRepository virtualIdentityRepository;

	/**
	 * Add virtual identity.
	 *
	 * @param virtualIdentity the virtual identity
	 */
	@Override
	public void addVirtualIdentity(VirtualIdentity virtualIdentity) {

		virtualIdentityRepository.save(virtualIdentity);
	}

	/**
	 * Find all virtual identities list.
	 *
	 * @return the list
	 */
	@Override
	public List<VirtualIdentity> findAll() {

		Iterable<VirtualIdentity> virtualIdentities = virtualIdentityRepository.findAll();

		List<VirtualIdentity> virtualIdentityList = new ArrayList<>();

		virtualIdentities.forEach(virtualIdentity -> {
			virtualIdentityList.add(virtualIdentity);
		});

		return virtualIdentityList;
	}
}
